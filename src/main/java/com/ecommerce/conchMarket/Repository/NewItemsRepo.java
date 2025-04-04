package com.ecommerce.conchMarket.Repository;
import com.ecommerce.conchMarket.utility.NewItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewItemsRepo extends JpaRepository<NewItems, Long> {
    Optional<NewItems> findByProductId(Long productId);
}
