package com.ecommerce.conchMarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.conchMarket.utility.User;
import com.ecommerce.conchMarket.Repository.UserRepo;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public User add(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	public List<User> get() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
    public User get(String email) {
        return userRepo.findByEmail(email);
    }

	
}
