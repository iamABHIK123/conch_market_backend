package com.ecommerce.conchMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.conchMarket.utility.User;
import com.ecommerce.conchMarket.Repository.UserRepo;

@Service
public class LoginServiceImplement implements LoginService {

    @Autowired
    UserRepo userRepo;

    @Override
    public User login(String email, String password) {
        User user = userRepo.findByEmail(email);
        System.out.println(user);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
