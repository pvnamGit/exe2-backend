package com.motorbikes_hiring.payload.request.superAdminRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangeRoleUserRequest {
    private String role;
}
