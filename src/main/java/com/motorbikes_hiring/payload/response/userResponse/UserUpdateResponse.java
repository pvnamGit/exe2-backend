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
public class UserUpdateResponse {
    private UserUpdateInformation user;
    private boolean status = true;

    public UserUpdateResponse(User user){
        this.user = new UserUpdateInformation(user);
    }
}
