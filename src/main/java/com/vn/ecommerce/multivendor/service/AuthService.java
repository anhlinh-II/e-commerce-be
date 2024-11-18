package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.response.SignupRequest;

public interface AuthService {

    String createUser (SignupRequest req);
}
