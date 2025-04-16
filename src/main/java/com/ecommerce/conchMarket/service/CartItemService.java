package com.ecommerce.conchMarket.service;

import com.ecommerce.conchMarket.utility.CartItem;
import com.ecommerce.conchMarket.utility.User;

import java.util.List;
import java.util.Optional;

public interface CartItemService {

	List<CartItem> getAllCartItems();

	CartItem searchByProductIdAndUserId(Long productId, Long userId);

	Boolean searchByProductId(Long productId);

	CartItem addCartItem(CartItem cartItem);

	CartItem updateCartItem(CartItem cartItem);

	void deleteByUserAndProdId(Long productId, Long userId);

	void deleteByProdId(Long productId);

	List<CartItem> getCartItemByProductId(Long productId);
	
	Long totalProductCount();
	
	List<CartItem> getCartItemsByUserId(Long userId);
}
