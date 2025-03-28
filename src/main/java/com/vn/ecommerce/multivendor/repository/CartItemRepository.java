package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.Cart;
import com.vn.ecommerce.multivendor.modal.CartItem;
import com.vn.ecommerce.multivendor.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
    void deleteByCart(Cart cart);
}
