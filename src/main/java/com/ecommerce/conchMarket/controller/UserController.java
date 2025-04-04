package com.ecommerce.conchMarket.controller;

import com.ecommerce.conchMarket.Repository.UserRepo;
import com.ecommerce.conchMarket.service.UserServiceImplement;
import com.ecommerce.conchMarket.utility.User;
import com.ecommerce.conchMarket.utility.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class UserController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserServiceImplement userService;

	// Endpoint to create a new user
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			// Save user along with roles and other fields
			User savedUser = userRepo.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	// Endpoint to get a user by email
	@GetMapping("/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// Endpoint to get all users
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userRepo.findAll();
		return ResponseEntity.ok(users);
	}

	// Endpoint to update a user by email
	@PutMapping("/auth/admin/{email}")
	public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User updatedUser) {
		Optional<User> userOptional = Optional.ofNullable(userRepo.findByEmail(email));
		if (userOptional.isPresent()) {
			User existingUser = userOptional.get();
			// Update fields
			existingUser.setFirstName(updatedUser.getFirstName());
			existingUser.setLastName(updatedUser.getLastName());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setPassword(updatedUser.getPassword());
			existingUser.setRole(updatedUser.getRole()); // Update roles
			// existingUser.setRefreshToken(existingUser.getRefreshToken()); // Update
			// refresh token if needed

			// Save updated user
			User savedUser = userRepo.save(existingUser);
			return ResponseEntity.ok(savedUser);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/auth/admin/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		userRepo.deleteById(userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// Endpoint to delete a user by email
	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deleteUser(@PathVariable String email) {
		Optional<User> userOptional = Optional.ofNullable(userRepo.findByEmail(email));
		if (userOptional.isPresent()) {
			userRepo.deleteById(userOptional.get().getId());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Endpoint to get a user by ID (for admin panel if needed)
	@GetMapping("/id/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userRepo.findById(id);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}

	@GetMapping("/totalUsers")
	public ResponseEntity<Long> getTotalUser() {
		return ResponseEntity.ok(userService.totalUser());
	}
}
