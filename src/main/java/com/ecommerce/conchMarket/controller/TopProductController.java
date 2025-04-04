package com.ecommerce.conchMarket.controller;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.conchMarket.service.TopProductService;
import com.ecommerce.conchMarket.service.ProductService;
import com.ecommerce.conchMarket.utility.TopProduct;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/top-products")
public class TopProductController {

    @Autowired
    private TopProductService topProductService;

    @Autowired
    private ProductService ProductService;

    @GetMapping
    public List<TopProduct> getAllTopProducts() {
        return topProductService.getAllTopProducts();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TopProduct> getTopProductById(@PathVariable Long id) {
        Optional<TopProduct> topProduct = topProductService.getTopProductById(id);
        return topProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{inputValue}")
    public ResponseEntity<TopProduct> addTopProduct(@PathVariable Long inputValue) {
        try {
        	System.out.println("it is the input val-"+inputValue);
        	TopProduct topProduct = topProductService.addTopProduct(inputValue);
            return ResponseEntity.ok(topProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Delete a top product
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteTopProduct(@PathVariable Long productId) {
        topProductService.deleteTopProduct(productId);
        return ResponseEntity.ok("Top Product deleted successfully!");
    }
}
