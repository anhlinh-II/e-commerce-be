package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.modal.Cart;
import com.vn.ecommerce.multivendor.modal.Coupon;
import com.vn.ecommerce.multivendor.modal.User;

import java.util.List;

public interface CouponService {
    Cart applyCoupon(String code, Double orderValue, User user) throws Exception;
    Cart removeCoupon(String code, User user) throws Exception;
    Coupon findCouponById(Long id) throws Exception;
    Coupon createCoupon(Coupon coupon);
    List<Coupon> findAllCoupons();
    void deleteCoupon(Long id) throws Exception;
}
