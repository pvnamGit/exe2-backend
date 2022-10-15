package com.motorbikes_hiring.payload.response.userResponse;


import com.motorbikes_hiring.model.role.Role;
import com.motorbikes_hiring.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserInformationResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String phone;
    private String avatar;
    private Double avgRate;
    private Long totalRate;
    private Set<Role> role;

    public UserInformationResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.role = user.getRoles();
    }
}
