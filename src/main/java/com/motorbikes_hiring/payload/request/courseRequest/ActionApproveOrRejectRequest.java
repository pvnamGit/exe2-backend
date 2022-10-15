package com.motorbikes_hiring.payload.request.courseRequest;

import lombok.*;
import lombok.AllArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionApproveOrRejectRequest {
    private boolean action;
}
