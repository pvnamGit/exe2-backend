package com.motorbikes_hiring.payload.request.userRequest;

import  lombok.*;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordUpdateRequest {
 private String oldPassword;

 @Size(min = 8)
 private String newPassword;
}
