package com.motorbikes_hiring.payload.request.userRequest;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
