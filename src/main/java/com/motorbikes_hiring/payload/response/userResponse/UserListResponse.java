package com.motorbikes_hiring.payload.response.userResponse;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponse {
    private Boolean status = true;
    private Long totalUser;
    List<UserInformationResponse> userList;

    public UserListResponse(List<UserInformationResponse> userList) {
        this.userList = userList;
    }


}
