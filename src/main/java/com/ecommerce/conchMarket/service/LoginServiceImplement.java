package com.ecommerce.conchMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerce.conchMarket.utility.User;
import com.ecommerce.conchMarket.Repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class LoginServiceImplement implements LoginService {

    @Autowired
    UserRepo userRepo;
      
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    @Override
    public User login(String email, String password) {
        // Fetch user by email
        User user = userRepo.findByEmail(email);
        // Check if user exists and if the password matches the encoded password
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

}
