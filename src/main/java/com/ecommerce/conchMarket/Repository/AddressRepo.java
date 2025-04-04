package com.ecommerce.conchMarket.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.conchMarket.utility.UserAddress;

@Repository
public interface AddressRepo extends JpaRepository<UserAddress, Long> {
	
}

