package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUserId(Long userId);
}
