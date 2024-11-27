package com.vn.ecommerce.multivendor.service.impl;

import com.vn.ecommerce.multivendor.modal.Cart;
import com.vn.ecommerce.multivendor.modal.CartItem;
import com.vn.ecommerce.multivendor.modal.Product;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.repository.CartItemRepository;
import com.vn.ecommerce.multivendor.repository.CartRepository;
import com.vn.ecommerce.multivendor.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem addCartItem(User user, Product product, String size, int quantity) {
        Cart cart = findUserCart(user);

        CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart, product, size);

        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);
            cartItem.setMrpPrice(0);
            cartItem.setCart(cart);

            int totalPrice = quantity * product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);

            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);
        }

        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());

        Integer totalPrice = 0;
        Integer totalDiscountedPrice = 0;
        Integer totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getMrpPrice() != null ? cartItem.getMrpPrice() : 0;
            totalDiscountedPrice += cartItem.getSellingPrice() != null ? cartItem.getSellingPrice() : 0;
            totalItem += cartItem.getQuantity();
        }


        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountedPrice));
        cart.setTotalItem(totalItem);

        return cart;
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if (mrpPrice <= 0) {
            return 0;
        }
        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount / mrpPrice) * 100;

        return (int)discountPercentage;
    }

}

