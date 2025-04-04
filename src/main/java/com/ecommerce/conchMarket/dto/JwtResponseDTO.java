package com.ecommerce.conchMarket.dto;

import java.time.Instant;

import com.ecommerce.conchMarket.utility.RefreshToken;
import com.ecommerce.conchMarket.utility.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {
	private String accessToken;
	private String token;
	private String userType;
}
