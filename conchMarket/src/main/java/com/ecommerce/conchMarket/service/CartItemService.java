package com.ecommerce.conchMarket.service;
import com.ecommerce.conchMarket.utility.CartItem;
import java.util.List;

public interface CartItemService {

    List<CartItem> getAllCartItems();

    CartItem getCartItemById(Long id);

    CartItem addCartItem(CartItem cartItem);

    CartItem updateCartItem(CartItem cartItem);

    void deleteCartItem(Long id);
}
