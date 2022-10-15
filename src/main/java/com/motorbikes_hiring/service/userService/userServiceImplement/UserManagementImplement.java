package com.motorbikes_hiring.service.userService.userServiceImplement;

import com.motorbikes_hiring.model.role.ERole;
import com.motorbikes_hiring.model.role.Role;
import com.motorbikes_hiring.model.user.User;
import com.motorbikes_hiring.payload.request.userRequest.PasswordUpdateRequest;
import com.motorbikes_hiring.payload.request.userRequest.UpdateProfileRequest;
import com.motorbikes_hiring.payload.response.userResponse.TutorListResponse;
import com.motorbikes_hiring.payload.response.userResponse.UserInformationResponse;
import com.motorbikes_hiring.payload.response.userResponse.UserListResponse;
import com.motorbikes_hiring.payload.response.userResponse.UserProfileResponse;
import com.motorbikes_hiring.repository.role.RoleRepository;
import com.motorbikes_hiring.repository.user.UserRepository;
import com.motorbikes_hiring.service.userService.userServiceInterface.UserManagementInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Slf4j
public class UserManagementImplement implements UserManagementInterface {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder encoder;

  @Override
  public User getUser(String username) {
    log.info("Fetching user {} ", username);
    return userRepository.findByUsername(username).orElseThrow(() -> {
      throw new NoSuchElementException("User cannot be found.");
    });
  }

  @Override
  public User getUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> {
          throw new NoSuchElementException("Not found user");
        });
    return user;
  }

  @Override
  public UserProfileResponse getUserProfile(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> {
          throw new NoSuchElementException("Not found user");
        });
    return new UserProfileResponse(
        user.getUsername(),
        user.getFullName(),
        user.getEmail(),
        user.getPhone(),
        user.getAddress(),
        user.getFacebookUrl(),
        user.getAvatar(),
        user.getRoles()
    );
  }

  @Override
  public void saveUser(User user) {
    userRepository.save(user);
  }

  @Override
  public User updateUser(String accessToken, Long id, UpdateProfileRequest updateProfileRequest) {
    accessToken = accessToken.replaceAll("Bearer ", "");
    User user = userRepository.findByAuthorizationToken(accessToken)
        .orElseThrow(() -> {
          throw new NoSuchElementException("Not found user");
        });
    if (user.getId() != id) {
      throw new IllegalStateException("You are not allowed to change other people's accounts");
    }

    if (updateProfileRequest.getPhone() != null) {
      if (updateProfileRequest.getPhone().isEmpty()) {
        user.setPhone(user.getPhone());
      } else {
        user.setPhone(updateProfileRequest.getPhone());
      }
    }

    if (updateProfileRequest.getFullName() != null) {
      if (updateProfileRequest.getFullName().isEmpty()) {
        user.setFullName(user.getFullName());
      } else {
        user.setFullName(updateProfileRequest.getFullName());
      }
    }

    //else if (updateProfileRequest.getGrade().toString().isEmpty()){
    //  user.setGrade(null);
    //}

    if (updateProfileRequest.getAddress() != null) {
      if (updateProfileRequest.getAddress().isEmpty()) {
        user.setAddress(null);
      } else {
        user.setAddress(updateProfileRequest.getAddress());
      }
    }

    if (updateProfileRequest.getAvatar() != null) {
      if (updateProfileRequest.getAvatar().isEmpty()) {
        user.setAvatar(null);
      } else {
        user.setAvatar(updateProfileRequest.getAvatar());
      }
    }

    if (updateProfileRequest.getFacebookUrl() != null) {
      if (updateProfileRequest.getFacebookUrl().isEmpty()) {
        user.setFacebookUrl(null);
      } else {
        user.setFacebookUrl(updateProfileRequest.getFacebookUrl());
      }
    }

    userRepository.save(user);
    return user;

  }

  @Override
  public User updateUserPassword(String accessToken, Long id, PasswordUpdateRequest request) throws Exception {
    accessToken = accessToken.replaceAll("Bearer ", "");
    User user = userRepository.findByAuthorizationToken(accessToken)
        .orElseThrow(() -> {
          throw new NoSuchElementException("Not found user");
        });
    if (user.getId() != id) {
      throw new IllegalStateException("You are not allowed to change other people's password");
    }

    if (encoder.matches(request.getOldPassword(), user.getPassword())) {
      user.setPassword(encoder.encode(request.getNewPassword()));
    } else {
      throw new Exception("The old password is not correct");
    }
    userRepository.save(user);
    return user;
  }

  @Override
  public UserListResponse getAllUser(Integer page, Integer limit) {
    Pageable pageable = PageRequest.of(page - 1, limit);
    Page<User> users = userRepository.findAllByStatusIsTrueOrderByIdDesc(pageable);
    List<UserInformationResponse> userList = new ArrayList<>();
    for (User user : users) {
      UserInformationResponse response = new UserInformationResponse(user);
      userList.add(response);
    }
    UserListResponse response = new UserListResponse(userList);
    response.setTotalUser(users.getTotalElements());
    return response;
  }

  @Override
  public void createAccount(User user) {
    userRepository.save(user);
  }

  @Override
  public void saveRole(Role role) {
    roleRepository.save(role);
  }

  @Override
  public void deleteUser(Long id) throws Exception {
    User user = userRepository.findById(id).get();
    Role role = roleRepository.findByUserRole(ERole.SUPER_ADMIN).get();
    if (user.getRoles().contains(role)) {
      throw new Exception();
    } else {
      user.setStatus(false);
      userRepository.save(user);
    }
  }

  @Override
  public TutorListResponse getListTutor(Integer page, Integer limit) {
    Pageable pageable = PageRequest.of(page - 1, limit);
    Role role = roleRepository.findByUserRole(ERole.USER)
        .orElseThrow(() -> {
          throw new NoSuchElementException("Not found role");
        });
    Page<User> users = userRepository.findAllByRolesAndStatusIsTrueOrderByIdDesc(role, pageable);
    List<UserInformationResponse> tutorList = new ArrayList<>();
    TutorListResponse response = new TutorListResponse(tutorList);
    response.setTotalUser(users.getTotalElements());
    return response;
  }

  //admin search user - by Nam
  @Override
  public UserListResponse adminSearchUser(String id, String name, Integer page, Integer limit) {
    Pageable pageable = PageRequest.of((page - 1), limit);
    Page<User> users = null;
    if (name == null || name.isEmpty()) {
      users = userRepository.findAllByIdAndStatusIsTrue(Long.parseLong(id), pageable).
          orElseThrow(() -> {
            throw new NoSuchElementException("Can't find users that match the search value");
          });
    } else if (id != null && !name.isEmpty() && name != null) {
      users = userRepository.findByIdAndName(Long.parseLong(id), "%" + name + "%", "%" + name + "%", "%" + name + "%", pageable)
          .orElseThrow(() -> {
            throw new NoSuchElementException("Can't find users that match the search value");
          });

    } else {
      users = userRepository.findAllByName("%" + name + "%", "%" + name + "%", "%" + name + "%", pageable)
          .orElseThrow(() -> {
            throw new NoSuchElementException("Can't find users that match the search value");
          });
    }
    List<UserInformationResponse> responseList = new ArrayList<>();
    for (User user : users) {
      UserInformationResponse response = new UserInformationResponse(user);
      responseList.add(response);
    }
    UserListResponse response = new UserListResponse(responseList);
    response.setTotalUser(users.getTotalElements());
    return response;
  }

  //public search tutor - by Nam
  @Override
  public TutorListResponse publicSearchTutor(String name, Integer page, Integer limit) {
    return null;
  }

  @Override
  public TutorListResponse getListTutorOrderByRating(Integer page, Integer limit, String order) {

    return null;
  }
}


