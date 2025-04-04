package com.ecommerce.conchMarket.service;

import java.util.List;

import com.ecommerce.conchMarket.utility.Discount;

public interface DiscountService {
	List<Discount> getAllDiscount();
	Discount getDiscountById(Long id);
}
