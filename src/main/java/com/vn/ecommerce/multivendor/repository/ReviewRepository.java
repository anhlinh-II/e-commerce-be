package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
}
