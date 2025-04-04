package com.ecommerce.conchMarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.conchMarket.service.ForgotPasswordService;


@RestController
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody String emailId) {
        try {
            forgotPasswordService.sendPasswordResetEmail(emailId);
            return ResponseEntity.ok("Password reset email sent successfully.");
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Error sending password reset email."+e.getMessage());
        }
    }
}
