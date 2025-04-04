package com.ecommerce.conchMarket.service;
import java.util.List;
import com.ecommerce.conchMarket.utility.User;

public interface UserService {
	User add(User user);
	List<User> get();
	User get(String username);
	Long totalUser();
}
