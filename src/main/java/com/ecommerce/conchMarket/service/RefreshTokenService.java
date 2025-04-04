package com.ecommerce.conchMarket.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.conchMarket.Repository.RefreshTokenRepo;
import com.ecommerce.conchMarket.Repository.UserRepo;
import com.ecommerce.conchMarket.utility.RefreshToken;
import com.ecommerce.conchMarket.utility.User;

@Service
public class RefreshTokenService {
	@Autowired
	private RefreshTokenRepo refreshTokenRepo;
	@Autowired
	private UserRepo userRepo;
	
	public RefreshToken createRefreshToken(String username) {
		RefreshToken rf=RefreshToken.builder()
		.user(userRepo.findByEmail(username))
		.token(UUID.randomUUID().toString())
		.expiryDate(Instant.now().plusMillis(1000*60*3))
		.build();
		
		return refreshTokenRepo.save(rf);
	}
	
	public ResponseEntity<Void> deleteRefreshToken(String userName) {
	    // Find the refresh token by the provided token
		User user =userRepo.findByEmail(userName);
		Optional<Long> rt=refreshTokenRepo.findRefreshTokenIdByUserId(user.getId());
		
		if(rt.isPresent()) {
			refreshTokenRepo.deleteById(rt.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	public Optional<RefreshToken> findByToken(String token){
		System.out.println(refreshTokenRepo.findByToken(token));
		return refreshTokenRepo.findByToken(token);
	}
	
	public RefreshToken verifyExpiration(RefreshToken token){
		if(token.getExpiryDate().compareTo(Instant.now())<0) {
			refreshTokenRepo.delete(token);
			throw new RuntimeException(token.getToken()+"Refresh token was expired");
		}
		return token;
	}
	
}
