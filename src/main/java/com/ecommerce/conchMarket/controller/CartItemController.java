package com.ecommerce.conchMarket.controller;

import com.ecommerce.conchMarket.Repository.CartItemRepo;
import com.ecommerce.conchMarket.dto.CartItemDeleteDTO;
import com.ecommerce.conchMarket.dto.CartItemRequest;
import com.ecommerce.conchMarket.service.CartItemService;
import com.ecommerce.conchMarket.service.ProductService;
import com.ecommerce.conchMarket.utility.CartItem;
import com.ecommerce.conchMarket.utility.Product;
import com.ecommerce.conchMarket.utility.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/cart")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CartItemRepo cartItemRepo;
	
	@Autowired
	private ProductService productService;

	@GetMapping("/items")
	public ResponseEntity<List<CartItem>> getAllCartItems() {
		List<CartItem> cartItems = cartItemService.getAllCartItems();
		Map<Long, CartItem> uniqueCartItems = new HashMap<>();

		for (CartItem cartItem : cartItems) {
			Long productId = cartItem.getProductId();

			if (uniqueCartItems.containsKey(productId)) {
				// If the product is already in the map, update the quantity
				CartItem existingCartItem = uniqueCartItems.get(productId);
				existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
			} else {
				// If not, add the cart item to the map
				uniqueCartItems.put(productId, cartItem);
			}
		}

		// Print the uniqueCartItems map
		System.out.println("Unique Cart Items:");
		for (Map.Entry<Long, CartItem> entry : uniqueCartItems.entrySet()) {
			Long productId = entry.getKey();
			CartItem cartItem = entry.getValue();
			System.out.println("Product ID: " + productId + ", Quantity: " + cartItem.getQuantity());
		}

		// Return only the unique cart items
		return ResponseEntity.ok(new ArrayList<>(uniqueCartItems.values()));
	}

	@PostMapping("/items")
	public ResponseEntity<CartItem> addOrUpdateCartItem(@RequestBody CartItemRequest request) {
		// Get the existing cart item for this user and productId
		CartItem existingCartItem = cartItemService.searchByProductIdAndUserId(request.getProductId(),
				request.getUser().getId());

		if (existingCartItem != null) {
			// If the item already exists, update its quantity
			existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
			System.out.println("existingCartItem != null - -----if is callled");
			CartItem updatedCartItem = cartItemService.addCartItem(existingCartItem); // Save updated cart item
			return ResponseEntity.ok(updatedCartItem);
		} else {
			// If the item doesn't exist, create a new cart item
			System.out.println("--------------- else is callled-------");
			CartItem newCartItem = new CartItem();
			newCartItem.setProductId(request.getProductId());
			newCartItem.setUser(request.getUser());
			newCartItem.setQuantity(request.getQuantity());
	System.out.println(newCartItem);

			CartItem savedCartItem = cartItemService.addCartItem(newCartItem); // Add the new cart item
			return ResponseEntity.ok(savedCartItem);
		}
	}

	@PostMapping("/items/quantity")
	public ResponseEntity<?> updateCartItemQuantity(@RequestBody CartItemRequest request) {
		// Get the existing cart item for this user and productId
		CartItem existingCartItem = cartItemService.searchByProductIdAndUserId(request.getProductId(),
				request.getUser().getId());

		if (existingCartItem != null) {
			// If the item already exists, update its quantity
			existingCartItem.setQuantity(request.getQuantity());
			System.out.println("existingCartItem != null - -----if is called");
			CartItem updatedCartItem = cartItemService.addCartItem(existingCartItem); // Save updated cart item
			return ResponseEntity.ok(updatedCartItem);
		} else {
			// If the item doesn't exist, return an error response
			System.out.println("Cart item not found for the given productId and userId");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Cart item not found for the given productId and userId");
		}
	}

	@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = { RequestMethod.GET,
			RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, allowCredentials = "true")
	@DeleteMapping("/items/{productId}/{userId}")
	public ResponseEntity<String> deleteCartItem(@PathVariable Long productId, @PathVariable Long userId) {
		try {
			cartItemRepo.deleteByProductIdAndUserId(productId, userId);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			System.out.println("Error during deletion: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete item");
		}
	}

	@GetMapping("/items/count")
	public ResponseEntity<Long> getTotalCartItems() {
		Long count = cartItemService.totalProductCount();
		System.out.println("------------------"+count);
		return ResponseEntity.ok(count);
	}

}
