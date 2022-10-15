package com.motorbikes_hiring.service.userService.userServiceInterface;

import com.motorbikes_hiring.model.user.User;
import com.motorbikes_hiring.payload.request.resetPasswordRequest.ResetPasswordRequest;
import com.motorbikes_hiring.payload.request.userRequest.LoginRequest;
import com.motorbikes_hiring.payload.request.userRequest.RegistrationRequest;
import com.motorbikes_hiring.payload.response.authResponse.JwtResponse;

import javax.mail.MessagingException;

public interface UserServiceInterface {
    JwtResponse handleUserLogin(LoginRequest loginRequest) throws Exception;

    void handleUserRegistration(RegistrationRequest registrationRequest) throws MessagingException;

    void activeAccount(String activateToken);

    void sendTokenForgetPassword(String email) throws MessagingException;

    User verifiedResetCode(Long resetCode);

    void resetPassword(ResetPasswordRequest resetPasswordRequest);

    void handleUserLogout(String accessToken);

    void verifyAccessToken(String accessToken);

    boolean changeRole(String username, String role);
}
