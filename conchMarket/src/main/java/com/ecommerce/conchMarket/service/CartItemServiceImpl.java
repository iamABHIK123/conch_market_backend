package com.ecommerce.conchMarket.service;
import com.ecommerce.conchMarket.Repository.CartItemRepo;
import com.ecommerce.conchMarket.utility.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepo.findAll();
    }

    @Override
    public CartItem getCartItemById(Long id) {
        return cartItemRepo.findById(id).orElse(null);
    }

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepo.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        return cartItemRepo.save(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepo.deleteById(id);
    }
}
