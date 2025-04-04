package com.ecommerce.conchMarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.conchMarket.Repository.PoductRepo;
import com.ecommerce.conchMarket.utility.Product;

@Service
public class ProductService {

    @Autowired
    private PoductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    
    public Product getProductById(Long id) {
    	return productRepo.findById(id).orElse(null);
    }
}
