package com.ecommerce.conchMarket.controller;

import com.ecommerce.conchMarket.service.AddressService;
import com.ecommerce.conchMarket.utility.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Create a new UserAddress
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping
    public ResponseEntity<UserAddress> createUserAddress(@RequestBody UserAddress userAddress) {
        UserAddress savedUserAddress = addressService.saveUserAddress(userAddress);
        return ResponseEntity.ok(savedUserAddress);
    }

    // Get all UserAddresses
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping
    public ResponseEntity<List<UserAddress>> getAllUserAddresses() {
        List<UserAddress> userAddresses = addressService.getAllUserAddresses();
        return ResponseEntity.ok(userAddresses);
    }

    // Get a UserAddress by ID
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/{id}")
    public ResponseEntity<UserAddress> getUserAddressById(@PathVariable Long id) {
        Optional<UserAddress> userAddress = addressService.getUserAddressById(id);
        return userAddress.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a UserAddress
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PutMapping("/{id}")
    public ResponseEntity<UserAddress> updateUserAddress(@PathVariable Long id, @RequestBody UserAddress userAddress) {
        Optional<UserAddress> existingUserAddress = addressService.getUserAddressById(id);
        if (existingUserAddress.isPresent()) {
            userAddress.setId(id); // Ensure the ID is set to the one in the path
            UserAddress updatedUserAddress = addressService.updateUserAddress(userAddress);
            return ResponseEntity.ok(updatedUserAddress);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a UserAddress by ID
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAddress(@PathVariable Long id) {
        Optional<UserAddress> userAddress = addressService.getUserAddressById(id);
        if (userAddress.isPresent()) {
            addressService.deleteUserAddress(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
