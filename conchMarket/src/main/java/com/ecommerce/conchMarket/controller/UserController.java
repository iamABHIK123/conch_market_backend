package com.ecommerce.conchMarket.controller;

import com.ecommerce.conchMarket.Repository.UserRepo;
import com.ecommerce.conchMarket.utility.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class UserController {

	private final UserRepo userRepo;

	@Autowired
	public UserController(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	// Example endpoint to get a user by email
	@GetMapping("/{email}")
	@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
	public User getUserByEmail(@PathVariable String email) {
		System.out.println(userRepo.findByEmail(email));
		return userRepo.findByEmail(email);
	}

	// Add more endpoints as needed for CRUD operations or other actions

}
