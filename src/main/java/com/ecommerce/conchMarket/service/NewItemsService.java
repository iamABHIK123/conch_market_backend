package com.ecommerce.conchMarket.service;

import com.ecommerce.conchMarket.Repository.NewItemsRepo;
import com.ecommerce.conchMarket.Repository.PoductRepo;
import com.ecommerce.conchMarket.utility.NewItems;
import com.ecommerce.conchMarket.utility.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewItemsService {

    @Autowired
    private NewItemsRepo newItemsRepository;

    @Autowired
    private PoductRepo productRepository;

    public List<NewItems> getAllNewItems() {
        return newItemsRepository.findAll();
    }

    public Optional<NewItems> getNewItemById(Long id) {
        return newItemsRepository.findById(id);
    }

    public NewItems addNewItem(Long productId) {
        // Ensure product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the product is already in NewItems
        if (newItemsRepository.findByProductId(productId).isPresent()) {
            throw new RuntimeException("Product is already marked as a new item.");
        }

        NewItems newItem = new NewItems();
        newItem.setProduct(product);
        return newItemsRepository.save(newItem);
    }

    public void deleteNewItem(Long id) {
        newItemsRepository.deleteById(id);
    }
}
