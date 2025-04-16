package com.ecommerce.conchMarket.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecommerce.conchMarket.utility.UserAddress;

@Repository
public interface AddressRepo extends JpaRepository<UserAddress, Long> {
	@Query(value = "SELECT * FROM user_address WHERE user_id = :user_id", nativeQuery = true)
	List<UserAddress> findAddressById(@Param("user_id") Long userId);
}

