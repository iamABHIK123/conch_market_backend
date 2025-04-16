package com.ecommerce.conchMarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.conchMarket.dto.JwtResponseDTO;
import com.ecommerce.conchMarket.dto.LogoutReqDTO;
import com.ecommerce.conchMarket.dto.RefreshTokenDTO;
import com.ecommerce.conchMarket.service.JwtService;
import com.ecommerce.conchMarket.service.LoginService;
import com.ecommerce.conchMarket.service.RefreshTokenService;
import com.ecommerce.conchMarket.utility.RefreshToken;
import com.ecommerce.conchMarket.utility.User;

import java.util.Base64;
//import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponseDTO> loginUser(@RequestBody User user) { 
		    System.out.println(user.getPassword());
			User loggedInUser = loginService.login(user.getEmail(),user.getPassword());

	        if (loggedInUser != null) {
	        	// Generate REFRESH JWT token
	        	RefreshToken token=refreshTokenService.createRefreshToken(loggedInUser.getEmail());
	        	System.out.println("refreshToken : "+token);
	            // Generate JWT token
	            String accessToken = jwtService.generateToken(loggedInUser.getEmail(),loggedInUser.getRole(),loggedInUser.getId());
	            String userType = loggedInUser.getRole();
	            System.out.println("accessToken : "+accessToken);
	            JwtResponseDTO responseDTO = JwtResponseDTO.builder()
	                    .accessToken(accessToken)
	                    .token(token.getToken())
	                    .userType(userType)
	                    .build();
	            
	            return ResponseEntity.ok(responseDTO);
	            //return ResponseEntity.ok(JwtResponseDTO);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	        }
	}
	
	
	@PostMapping("/auth/refreshToken")
	public JwtResponseDTO refreshToken(@RequestBody RefreshTokenDTO refreshToken){
		return refreshTokenService.findByToken(refreshToken.getToken())
				.map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUser)
				.map(user->{
					String accessToken=jwtService.generateToken(user.getUsername(),user.getRole(),user.getId());
					return JwtResponseDTO.builder()
							.accessToken(accessToken)
							.token(refreshToken.getToken())
							.userType(user.getRole())
							.build();
				}).orElseThrow(()->new RuntimeException("Refresh token is not in DB"));
	}
	
}
