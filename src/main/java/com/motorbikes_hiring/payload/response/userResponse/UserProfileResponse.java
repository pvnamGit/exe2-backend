package com.motorbikes_hiring.payload.response.userResponse;

import com.motorbikes_hiring.model.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
    private String username;

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private String facebookUrl;

    private String avatar;

    private Set<Role> role;

    private Boolean status = true;

    public UserProfileResponse(String username, String fullName, String email, String phone, String address, String facebookUrl, String avatar, Set<Role> role) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.facebookUrl = facebookUrl;
        this.avatar = avatar;
        this.role = role;
    }
}
