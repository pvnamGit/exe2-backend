package com.motorbikes_hiring.payload.response.userResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorListResponse {
    private Boolean status = true;
    private Long totalUser;
    List<UserInformationResponse> tutorList;

    public TutorListResponse(List<UserInformationResponse> tutorList) {
        this.tutorList = tutorList;
    }


}
