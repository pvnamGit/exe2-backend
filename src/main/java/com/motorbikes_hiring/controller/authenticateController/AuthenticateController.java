package com.motorbikes_hiring.controller.authenticateController;

import com.motorbikes_hiring.payload.request.resetPasswordRequest.ResetCodeRequest;
import com.motorbikes_hiring.payload.request.resetPasswordRequest.ResetPasswordRequest;
import com.motorbikes_hiring.payload.request.userRequest.LoginRequest;
import com.motorbikes_hiring.payload.request.userRequest.RegistrationRequest;
import com.motorbikes_hiring.payload.response.authResponse.JwtResponse;
import com.motorbikes_hiring.payload.response.responseMessage.ErrorMessageResponse;
import com.motorbikes_hiring.payload.response.responseMessage.SuccessfulMessageResponse;
import com.motorbikes_hiring.service.userService.userServiceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/auth")

public class AuthenticateController {


  @Autowired
  private UserServiceInterface userService;

  @PostMapping("/log-in")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
    try {
      JwtResponse jwtResponse = userService.handleUserLogin(loginRequest);
      return ResponseEntity.ok().body(jwtResponse);
    } catch (UsernameNotFoundException ex) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse(ex.getMessage()));
    } catch (Exception ex) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse(ex.getMessage()));
    }

  }


  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest)
      throws MessagingException {
    try {
      userService.handleUserRegistration(registrationRequest);
      return ResponseEntity.ok().body(new SuccessfulMessageResponse("User registered successfully!"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse(e.getMessage()));
    }
  }


  @PostMapping("/verify-authorization")
  public ResponseEntity<?> verifyAuthorization(@RequestHeader(name = "Authorization") String accessToken) {
    try {
//      userService.verifyAccessToken(accessToken);
      return ResponseEntity.ok().build();
    } catch (NoSuchElementException ex) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse(ex.getMessage()));
    }
  }

  @GetMapping("/logout")
  public ResponseEntity<?> logout(@RequestHeader(name = "Authorization") String accessToken) {
    userService.handleUserLogout(accessToken);
    return ResponseEntity.ok().build();
  }


  @GetMapping("/activate")
  public ResponseEntity activateUser(@RequestParam(name = "token") String token) {
    userService.activeAccount(token);
    //Bấm vào link là bay về trang chủ liền
    return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://swp391-onlinetutor.herokuapp.com/")).build();
  }

  //Sau khi nhập xong email bấm enter thì chuyển tới trang nhập code thì
  @PostMapping("/send-forgot-password")
  public ResponseEntity<?> sendForgetPassword(@RequestParam(name = "email") String email)
      throws MessagingException {
    try {
      userService.sendTokenForgetPassword(email);
      return ResponseEntity.ok().body(new SuccessfulMessageResponse("Reset code sent to your email"));
    } catch (Exception ex) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse(ex.getMessage()));
    }
  }

  @GetMapping("/reset-code")
  public ResponseEntity<?> verifyResetCode(@RequestBody ResetCodeRequest resetCodeRequest) {
    try {
      userService.verifiedResetCode(resetCodeRequest.getResetCode());
      return ResponseEntity.ok().build();
    } catch (NoSuchElementException ex) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse("Reset code is not existed"));
    }
  }

  //Nếu code đúng thì đi tới trang reset password
  @PostMapping("/reset-password")
  public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
    try {
      userService.resetPassword(resetPasswordRequest);
      return ResponseEntity.ok().build();
    } catch (NoSuchElementException ex) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse(ex.getMessage()));
    }
  }
}
