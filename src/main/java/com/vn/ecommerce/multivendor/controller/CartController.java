package com.vn.ecommerce.multivendor.controller;

import com.vn.ecommerce.multivendor.modal.Cart;
import com.vn.ecommerce.multivendor.modal.CartItem;
import com.vn.ecommerce.multivendor.modal.Product;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.request.AddItemRequest;
import com.vn.ecommerce.multivendor.response.ApiResponse;
import com.vn.ecommerce.multivendor.service.CartItemService;
import com.vn.ecommerce.multivendor.service.CartService;
import com.vn.ecommerce.multivendor.service.ProductService;
import com.vn.ecommerce.multivendor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        System.out.println("user: " + user);
        Cart cart = cartService.findUserCart(user);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddItemRequest request,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Product product = productService.findProductById(request.getProductId());

        CartItem item = cartService.addCartItem(user, product, request.getSize(), request.getQuantity());

        ApiResponse res = new ApiResponse();
        res.setMessage("Item added to cart successfully!");

        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Item removed from cart");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        CartItem updatedCartItem = null;

        if (cartItem.getQuantity() > 0) {
            updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        }

        return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);
    }
}
