package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);
}
