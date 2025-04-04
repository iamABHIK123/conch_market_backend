package com.ecommerce.conchMarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.conchMarket.dto.LogoutReqDTO;
import com.ecommerce.conchMarket.service.RefreshTokenService;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class LogoutController {
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@PostMapping("/auth/logout")
	public ResponseEntity<Void> logoutUser(@RequestBody LogoutReqDTO logoutReqDTO) { 
	    try {
	    	System.out.println("logout api is called");
	        // Call the deleteRefreshToken method to delete the refresh token
	        refreshTokenService.deleteRefreshToken(logoutReqDTO.getEmail());

	        // Log and print status (you can also use a logger for better production code)
	        System.out.println("Refresh token deleted successfully for user: " + logoutReqDTO.getEmail());

	        // Return a success response with HTTP 200 OK status
	        return new ResponseEntity<>(HttpStatus.OK);

	    } catch (RuntimeException e) {
	        // If an error occurs (e.g., token not found), log the error and return a 404 response
	        System.err.println("Error: " + e.getMessage());
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if token was not found
	    }
	}
}
