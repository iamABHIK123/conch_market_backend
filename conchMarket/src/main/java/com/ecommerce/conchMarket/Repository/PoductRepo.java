package com.ecommerce.conchMarket.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.conchMarket.utility.Product;

@Repository
public interface PoductRepo extends JpaRepository<Product, Long> {
}
