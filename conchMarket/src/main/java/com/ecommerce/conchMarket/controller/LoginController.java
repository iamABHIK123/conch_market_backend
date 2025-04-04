package com.ecommerce.conchMarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.conchMarket.service.LoginService;
import com.ecommerce.conchMarket.utility.User;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User user, HttpSession session) { // Directly inject HttpSession
		User loggedInUser = loginService.login(user.getEmail(), user.getPassword());
		System.out.println(loggedInUser);
		if (loggedInUser != null) {
			session.setAttribute("userEmail", loggedInUser.getEmail());
			session.setAttribute("userPassword", loggedInUser.getPassword());
			return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logoutUser(HttpSession session) { // Directly inject HttpSession
		if (session != null) {
			session.invalidate();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/session")
	public ResponseEntity<User> getSessionUser(HttpSession session) { // Directly inject HttpSession
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
