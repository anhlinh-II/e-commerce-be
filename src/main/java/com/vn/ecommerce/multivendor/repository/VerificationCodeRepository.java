package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByEmail(String email);
}
