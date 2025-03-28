package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.modal.Product;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.modal.Wishlist;

public interface WishlistService {
    Wishlist createWishlist(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishList(User user, Product product);
}
