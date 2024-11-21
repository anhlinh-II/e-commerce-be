package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.modal.User;

public interface UserService {

    User findUserByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
}
