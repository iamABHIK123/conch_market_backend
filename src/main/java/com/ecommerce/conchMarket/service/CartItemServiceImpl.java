package com.ecommerce.conchMarket.service;
import com.ecommerce.conchMarket.Repository.CartItemRepo;
import com.ecommerce.conchMarket.utility.CartItem;
import com.ecommerce.conchMarket.utility.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepo.findAll();
    }

    
    @Override
    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepo.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        return cartItemRepo.save(cartItem);
    }

//    @Override
//    public void deleteCartItem(Long id) {
//        cartItemRepo.deleteById(id);
//    }

    public List<CartItem> getCartItemByProductId(Long productId) {
        return cartItemRepo.findByProductId(productId);
    }

    
	@Override
	@Transactional
	public void deleteByUserAndProdId(Long productId, Long userId) {
		cartItemRepo.deleteByProductIdAndUserId(productId, userId);	
	}


	@Override
	public CartItem searchByProductIdAndUserId(Long productId, Long userId) {
		return cartItemRepo.searchByProductIdAndUserId(productId, userId);
	}


	@Override
	public void deleteByProdId(Long productId) {
		cartItemRepo.deleteByProductId(productId);
	}


	@Override
	public Boolean searchByProductId(Long productId) {
	    // Check if the CartItem exists in the repository
	    CartItem cartItem = cartItemRepo.searchByProductId(productId);
	    return cartItem != null;
	}
	

	@Override
	public Long totalProductCount() {
		return cartItemRepo.count();
	}
	
	@Override
	public List<CartItem> getCartItemsByUserId(Long userId) {
	    return cartItemRepo.findProductsByUserId(userId);
	}

}
