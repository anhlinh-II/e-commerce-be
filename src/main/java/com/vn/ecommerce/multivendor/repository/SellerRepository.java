package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(String email);
}
