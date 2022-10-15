package com.motorbikes_hiring.payload.response.userResponse;

import com.motorbikes_hiring.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateInformation {
    private Long id;
    private String fullName;
    private String phone;
    private Integer grade;
    private String address;
    private String avatar;
    private String facebookUrl;

    public UserUpdateInformation(User user){
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.avatar = user.getAvatar();
        this.facebookUrl = user.getFacebookUrl();
    }
}
