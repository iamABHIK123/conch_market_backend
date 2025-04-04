package com.ecommerce.conchMarket.controller;

import com.ecommerce.conchMarket.service.NewItemsService;
import com.ecommerce.conchMarket.utility.NewItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/new-items")
public class NewItemsController {

    @Autowired
    private NewItemsService newItemsService;

    @GetMapping
    public List<NewItems> getAllNewItems() {
        return newItemsService.getAllNewItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewItems> getNewItemById(@PathVariable Long id) {
        Optional<NewItems> newItem = newItemsService.getNewItemById(id);
        return newItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{inputValue}")
    public ResponseEntity<NewItems> addNewItem(@PathVariable Long inputValue) {
        try {
        	System.out.println("it is the input val-"+inputValue);
            NewItems newItem = newItemsService.addNewItem(inputValue);
            return ResponseEntity.ok(newItem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewItem(@PathVariable Long id) {
        newItemsService.deleteNewItem(id);
        return ResponseEntity.noContent().build();
    }
}
