package com.ecommerce.conchMarket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.conchMarket.Repository.PoductRepo;
import com.ecommerce.conchMarket.Repository.TopProductRepo;
import com.ecommerce.conchMarket.dto.ProductDTO;
import com.ecommerce.conchMarket.utility.Product;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

	@Autowired
	private PoductRepo productRepo;

	@Autowired
	private TopProductRepo topProductRepository;

	@Transactional
	public void addProduct(Product p) {
		productRepo.save(p);
	}

	public void updateProduct(Product p) {
		productRepo.save(p);
	}

	// Fetch all top products, get their IDs, then fetch full product details
//	public List<ProductDTO> getAllTopProducts() {
//		// Step 1: Fetch all product IDs from TopProduct table
//		List<Long> productIds = topProductRepository.findAllTopProductIds();
//
//		// Step 2: Use these IDs to fetch Product details and map to DTO
//		return productRepo.findAllById(productIds).stream()
//				.map(product -> new ProductDTO(product.getId(), product.getName(), product.getCategory()))
//				.collect(Collectors.toList());
//	}

	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	public List<Product> getAllProductsByCategory(String category) {
		return productRepo.findByCategory(category);
	}

	public Product getProductById(Long id) {
		return productRepo.findById(id).orElse(null);
	}

	public void deleteProductById(Long id) {
		productRepo.deleteById(id);
	}

	public Product getProdudctByName(String name) {
		return productRepo.findByName(name);
	}

	public Long getProductCount() {
		return productRepo.count();
	}

	public Long getCategoryCount() {
		return productRepo.countDistinctCategories();
	}
}
