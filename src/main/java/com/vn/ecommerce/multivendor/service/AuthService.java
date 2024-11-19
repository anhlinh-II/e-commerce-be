package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.response.SignupRequest;

public interface AuthService {

    void sendLoginOtp(String email) throws Exception;
    String createUser (SignupRequest req) throws Exception;
}
