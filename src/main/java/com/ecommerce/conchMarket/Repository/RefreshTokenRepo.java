package com.ecommerce.conchMarket.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.conchMarket.utility.RefreshToken;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Long> {
	Optional<RefreshToken> findByToken(String token);
	
	@Query("SELECT rt.id FROM RefreshToken rt WHERE rt.user.id = :userId")
    Optional<Long> findRefreshTokenIdByUserId(@Param("userId") Long userId);
	
}
