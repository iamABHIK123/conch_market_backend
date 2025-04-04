package com.ecommerce.conchMarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.conchMarket.utility.OrderDetails;

@Repository
public interface OrderRepo extends JpaRepository<OrderDetails, Long> {
}
