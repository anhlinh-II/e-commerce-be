package com.vn.ecommerce.multivendor.service.impl;

import com.vn.ecommerce.multivendor.modal.CartItem;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.repository.CartItemRepository;
import com.vn.ecommerce.multivendor.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
        CartItem item = findCartItemById(id);
        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity() * item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity() * item.getProduct().getSellingPrice());

            return cartItemRepository.save(item);
        }
        throw new Exception("you can't update this cart item");
    }

    @Override
    public void removeCartItem(long userId, Long cartItemId) throws Exception {
        CartItem item = findCartItemById(cartItemId);

        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {
            cartItemRepository.delete(item);
        } else {
            throw new Exception("You can't delete this item");
        }
    }

    @Override
    public CartItem findCartItemById(Long id) throws Exception {
        return cartItemRepository.findById(id).orElseThrow(() ->
                    new Exception("cart item not found with id " + id)
                );
    }
}
