package com.motorbikes_hiring.controller.userManagementController;

import com.motorbikes_hiring.model.user.User;
import com.motorbikes_hiring.payload.request.userRequest.PasswordUpdateRequest;
import com.motorbikes_hiring.payload.request.userRequest.UpdateProfileRequest;
import com.motorbikes_hiring.payload.response.authResponse.MessageResponse;
import com.motorbikes_hiring.payload.response.responseMessage.ErrorMessageResponse;
import com.motorbikes_hiring.payload.response.responseMessage.SuccessfulMessageResponse;
import com.motorbikes_hiring.payload.response.userResponse.TutorListResponse;
import com.motorbikes_hiring.payload.response.userResponse.UserUpdateResponse;
import com.motorbikes_hiring.service.userService.userServiceInterface.UserManagementInterface;
import com.motorbikes_hiring.service.userService.userServiceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/public")
public class PublicUserManagementController {

  @Autowired
  private UserServiceInterface userService;

  @Autowired
  private UserManagementInterface userManagement;

  @PutMapping("/user/{id}")
  @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN') or hasAuthority('TUTOR') or hasAuthority('STUDENT')")
  public ResponseEntity<?> updateUser(@RequestHeader(name = "Authorization") String accessToken,
                                      @PathVariable("id") Long id,
                                      @Valid @RequestBody UpdateProfileRequest updateProfileRequest) {
    try {
      User user = userManagement.updateUser(accessToken, id, updateProfileRequest);
      if (user == null) {
        return ResponseEntity.badRequest().body(new ErrorMessageResponse("Update failed"));
      } else {
        return ResponseEntity.ok().body(new UserUpdateResponse(user));
      }
    } catch (NoSuchElementException ex) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse(ex.getMessage()));
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse(ex.getMessage()));
    }
  }

  @PutMapping("/user/{id}/change-password")
  @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN') or hasAuthority('TUTOR') or hasAuthority('STUDENT')")
  public ResponseEntity<?> updateUserPassword(@RequestHeader(name = "Authorization") String accessToken,
                                              @PathVariable("id") Long id,
                                              @Valid @RequestBody PasswordUpdateRequest request) {
    try {
      User user = userManagement.updateUserPassword(accessToken, id, request);
      if (user == null) {
        return ResponseEntity.badRequest().body(new SuccessfulMessageResponse("Update failed!"));
      } else {
        return ResponseEntity.ok().body(new MessageResponse("Password has been updated."));
      }
    } catch (Exception ex) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse(ex.getMessage()));
    }
  }

  @GetMapping("/user/{id}/profile")
  public ResponseEntity<?> responseUserProfile(@PathVariable("id") Long id) {
    try {
      return ResponseEntity.ok().body(userManagement.getUserProfile(id));
    } catch (Exception ex) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse(ex.getMessage()));
    }
  }

  @GetMapping("/tutor")
  public ResponseEntity<?> getListTutor(
      @RequestParam(required = false) String name,
      @RequestParam(name = "page", required = false) Integer page,
      @RequestParam(name = "limit", required = false) Integer limit,
      @RequestParam(name = "order", required = false) String order
  ) {
    try {
      if (page == null || page < 1) {
        page = 1;
      }
      if (limit == null) {
        limit = 20;
      }
      TutorListResponse listResponse = null;

      if (name != null) {
        listResponse = userManagement.publicSearchTutor(name, page, limit);
        return ResponseEntity.ok().body(listResponse);
      }
      if (order != null) {
        listResponse = userManagement.getListTutorOrderByRating(page, limit, order);
        return ResponseEntity.ok().body(listResponse);
      }
      listResponse = userManagement.getListTutor(page, limit);
      return ResponseEntity.ok().body(listResponse);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
