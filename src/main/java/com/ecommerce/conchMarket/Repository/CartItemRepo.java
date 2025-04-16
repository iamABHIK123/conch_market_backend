package com.ecommerce.conchMarket.Repository;
import com.ecommerce.conchMarket.utility.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
	List<CartItem> findByProductId(Long productId);
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM cart_item WHERE product_id = :product_id AND user_id = :user_id", nativeQuery = true)
	void deleteByProductIdAndUserId(@Param("product_id") Long productId, @Param("user_id") Long userId);
	
	@Query(value = "SELECT * FROM cart_item WHERE product_id = :product_id AND user_id = :user_id", nativeQuery = true)
	CartItem searchByProductIdAndUserId(@Param("product_id") Long productId, @Param("user_id") Long userId);
	
	@Query(value = "SELECT * FROM cart_item WHERE product_id = :product_id", nativeQuery = true)
	CartItem searchByProductId(@Param("product_id") Long productId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM cart_item WHERE product_id = :product_id ", nativeQuery = true)
	void deleteByProductId(@Param("product_id") Long productId);
	
	@Query(value = "SELECT * FROM cart_item WHERE user_id = :user_id", nativeQuery = true)
	List<CartItem> findProductsByUserId(@Param("user_id") Long userId);
	
	@Query(value = "SELECT sum(quantity) FROM cart_item WHERE user_id = :user_id", nativeQuery = true)
	Long findTotalProductsByUserId(@Param("user_id") Long userId);
}
