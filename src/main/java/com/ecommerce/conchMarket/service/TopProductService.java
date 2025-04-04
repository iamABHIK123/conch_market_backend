package com.ecommerce.conchMarket.service;

import org.springframework.stereotype.Service;

import com.ecommerce.conchMarket.Repository.PoductRepo;
import com.ecommerce.conchMarket.Repository.TopProductRepo;
import com.ecommerce.conchMarket.dto.TopProductDTO;
import com.ecommerce.conchMarket.utility.NewItems;
import com.ecommerce.conchMarket.utility.Product;
import com.ecommerce.conchMarket.utility.TopProduct;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class TopProductService {

	@Autowired
	private TopProductRepo topProductRepository;

	@Autowired
	private PoductRepo productRepository;

	// Get all top products
	public List<TopProduct> getAllTopProducts() {
		return topProductRepository.findAll();
	}

	public Optional<TopProduct> getTopProductById(Long id) {
		return topProductRepository.findById(id);
	}

	public TopProduct addTopProduct(Long productId) {
		// Ensure product exists
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		// Check if the product is already in NewItems
		if (topProductRepository.findByProductId(productId).isPresent()) {
			throw new RuntimeException("Product is already marked as a new item.");
		}

		TopProduct topProduct = new TopProduct();
		topProduct.setProduct(product);
		return topProductRepository.save(topProduct);
	}

	// Delete a top product by ID
	public void deleteTopProduct(Long productId) {
		topProductRepository.deleteById(productId);
	}
}
