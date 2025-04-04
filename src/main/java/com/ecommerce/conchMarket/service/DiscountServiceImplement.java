package com.ecommerce.conchMarket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.conchMarket.Repository.DiscountRepo;
import com.ecommerce.conchMarket.utility.Discount;

@Service
public class DiscountServiceImplement implements DiscountService {

    @Autowired
    private DiscountRepo discountRepo;

    @Override
    public List<Discount> getAllDiscount() {
        return discountRepo.findAll();
    }

    @Override
    public Discount getDiscountById(Long id) {
        Optional<Discount> discount = discountRepo.findById(id);
        return discount.orElse(null); // Return the discount if found, otherwise return null
    }
}
