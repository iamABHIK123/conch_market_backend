package com.ecommerce.conchMarket.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@Component
public class JwtService {

	public static String SECRET_KEY = "qwertyuiopasdfghjklzxcvbnmqwertyui"; // Replace with a strong key

	public JwtService() {

		try {
			// Convert the existing SECRET_KEY string into Base64 encoding
			SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
			System.out.println("Encoded Secret Key: " + SECRET_KEY);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// Generate token with given user name
	public String generateToken(String userName, String userType) {
		System.out.println("GENERATE TOKEN FUN IS CALLED");
		Map<String, Object> claims = new HashMap<>();
		claims.put("userType", userType);
		return createToken(claims, userName);
	}

	// Create a JWT token with specified claims and subject (user name)
	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Token valid for 30 minutes
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	// Get the signing key for JWT token
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// Extract the username from the token
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// Extract the expiration date from the token
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	// Extract a claim from the token
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// Extract all claims from the token
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	// Check if the token is expired
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	// Validate the token against user details and expiration
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// Extract the user role (userType) from the token
	public String extractUserRole(String token) {
		return extractClaim(token, claims -> claims.get("userType", String.class));
	}

}
