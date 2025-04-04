package com.ecommerce.conchMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.conchMarket.Repository.UserRepo;
import com.ecommerce.conchMarket.utility.User;


import java.util.UUID;

@Service
public class ForgotPasswordService {
    private static final long EXPIRE_TOKEN = 30; // Token expiration time in minutes

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepo userRepo;

    /**
     * Sends a password reset email to the user with the given email address.
     * Throws a RuntimeException if no user is found with the provided email.
     *
     * @param email The email address of the user requesting password reset.
     */
    public void sendPasswordResetEmail(String jsonEmail) {
//        User user = userRepo.findByEmail(email);
//        if (user == null) {
//            throw new RuntimeException("No user found with the given email.");
//        }
    	String email = jsonEmail.replaceAll("[{}\"]", "").split(":")[1].trim();
        String token = generateToken();
        String resetUrl = "http://localhost:5173/reset-password?token=" + token;

        String subject = "Password Reset Request";
        String content = "To reset your password, click the link below:\n" + resetUrl;

        // Send email using EmailService
        emailService.sendEmail(email, subject, content);
    }

    /**
     * Generates a unique token for password reset.
     *
     * @return A unique token string.
     */
    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
