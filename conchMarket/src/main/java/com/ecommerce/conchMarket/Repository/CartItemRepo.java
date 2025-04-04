package com.ecommerce.conchMarket.Repository;

import com.ecommerce.conchMarket.utility.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
	
}
