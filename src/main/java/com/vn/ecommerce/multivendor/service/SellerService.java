package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.domain.AccountStatus;
import com.vn.ecommerce.multivendor.exception.SellerException;
import com.vn.ecommerce.multivendor.modal.Seller;

import java.util.List;

public interface SellerService {
    Seller getSellerProfile(String jwt) throws SellerException;
    Seller createSeller(Seller seller) throws Exception;
    Seller getSellerById(Long id) throws SellerException;
    Seller getSellerByEmail(String email) throws Exception;
    List<Seller> getAllTheSeller(AccountStatus status);
    Seller updateSeller(Long id, Seller seller) throws Exception;
    void deleteSeller(Long id) throws Exception;
    Seller verifyEmail(String email, String otp) throws Exception;
    Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception;
}
