package com.ecommerce.conchMarket.dto;

import lombok.Data;

@Data
public class CartItemDeleteDTO {
	private Long productId;
	private Long userId;
}
