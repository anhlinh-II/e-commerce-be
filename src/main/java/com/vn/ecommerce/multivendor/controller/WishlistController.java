package com.vn.ecommerce.multivendor.controller;

import com.vn.ecommerce.multivendor.exception.ProductException;
import com.vn.ecommerce.multivendor.modal.Product;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.modal.Wishlist;
import com.vn.ecommerce.multivendor.service.ProductService;
import com.vn.ecommerce.multivendor.service.UserService;
import com.vn.ecommerce.multivendor.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    private final WishlistService wishlistService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<Wishlist> getWishlistByUserId(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Wishlist wishlist = wishlistService.getWishlistByUserId(user);

        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<Wishlist> addProductToWishlist(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Product product = productService.findProductById(productId);
        User user = userService.findUserByJwtToken(jwt);

        Wishlist updatedWishlist = wishlistService.addProductToWishList(user, product);

        return ResponseEntity.ok(updatedWishlist);
    }
}
