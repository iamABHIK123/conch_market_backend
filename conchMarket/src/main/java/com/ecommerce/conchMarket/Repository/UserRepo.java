package com.ecommerce.conchMarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.conchMarket.utility.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
    User findByEmail(String email);
}
