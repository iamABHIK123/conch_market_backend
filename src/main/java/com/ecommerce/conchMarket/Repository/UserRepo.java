package com.ecommerce.conchMarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.conchMarket.utility.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
//    User findByEmail(String email);
	@Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
}
