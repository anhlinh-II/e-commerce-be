package com.vn.ecommerce.multivendor.controller;

import com.vn.ecommerce.multivendor.domain.USER_ROLE;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.modal.VerificationCode;
import com.vn.ecommerce.multivendor.repository.UserRepository;
import com.vn.ecommerce.multivendor.response.ApiResponse;
import com.vn.ecommerce.multivendor.response.AuthResponse;
import com.vn.ecommerce.multivendor.response.SignupRequest;
import com.vn.ecommerce.multivendor.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {

        String jwt = authService.createUser(req);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("Register successfully!");
        response.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/send/login-signup-otp")
    public ResponseEntity<ApiResponse> sendOtpHandler(@RequestBody VerificationCode req) throws Exception {

        authService.sendLoginOtp(req.getEmail());

        ApiResponse response = new ApiResponse();

        response.setMessage("OTP sent successfully!");

        return ResponseEntity.ok(response);
    }
}
