package com.ecommerce.conchMarket.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.conchMarket.utility.TopProduct;

@Repository
public interface TopProductRepo extends JpaRepository<TopProduct, Long> {
	Optional<TopProduct> findByProductId(Long productId);
}