package com.ecommerce.conchMarket.controller;

import com.ecommerce.conchMarket.Repository.UserRepo;
import com.ecommerce.conchMarket.service.AddressService;
import com.ecommerce.conchMarket.utility.User;
import com.ecommerce.conchMarket.utility.UserAddress;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserRepo userRepo;
    
    // Create a new UserAddress
    
    @PostMapping
    public ResponseEntity<UserAddress> createUserAddress(@RequestBody UserAddress userAddress,HttpServletRequest request) {
    	System.out.println("here is the user address"+userAddress);
    	 Long userId = (Long) request.getAttribute("userId");
    	 @SuppressWarnings("deprecation")
		User user=userRepo.getById(userId);
    	 userAddress.setUser(user);
        UserAddress savedUserAddress = addressService.saveUserAddress(userAddress);
        return ResponseEntity.ok(savedUserAddress);
    }

    // Get all UserAddresses
    
    @GetMapping
    public ResponseEntity<List<UserAddress>> getAllUserAddresses() {
        List<UserAddress> userAddresses = addressService.getAllUserAddresses();
        return ResponseEntity.ok(userAddresses);
    }

    // Get a UserAddress by ID
    
    @GetMapping("/{id}")
    public ResponseEntity<UserAddress> getUserAddressById(@PathVariable Long id) {
        Optional<UserAddress> userAddress = addressService.getUserAddressById(id);
        return userAddress.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a UserAddress
    
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
