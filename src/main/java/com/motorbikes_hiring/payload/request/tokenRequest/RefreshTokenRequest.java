package com.motorbikes_hiring.payload.request.tokenRequest;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RefreshTokenRequest {
    @NotBlank
    String refreshToken;
}
