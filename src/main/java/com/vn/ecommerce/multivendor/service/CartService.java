package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.modal.Cart;
import com.vn.ecommerce.multivendor.modal.CartItem;
import com.vn.ecommerce.multivendor.modal.Product;
import com.vn.ecommerce.multivendor.modal.User;
import org.springframework.stereotype.Service;

public interface CartService {
    public CartItem addCartItem(
            User user,
            Product product,
            String size,
            int quantity
    );

    public Cart findUserCart(User user);
}
