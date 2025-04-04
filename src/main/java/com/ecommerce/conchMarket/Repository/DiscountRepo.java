package com.ecommerce.conchMarket.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.conchMarket.utility.Discount;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Long> {
}
