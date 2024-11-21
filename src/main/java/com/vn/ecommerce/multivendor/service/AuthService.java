package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.domain.USER_ROLE;
import com.vn.ecommerce.multivendor.request.LoginRequest;
import com.vn.ecommerce.multivendor.response.AuthResponse;
import com.vn.ecommerce.multivendor.response.SignupRequest;

public interface AuthService {

    void sendLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser (SignupRequest req) throws Exception;
    AuthResponse signing(LoginRequest req);
}
