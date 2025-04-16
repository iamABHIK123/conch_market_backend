package com.ecommerce.conchMarket.dto;
import com.ecommerce.conchMarket.utility.User;
import lombok.Data;

@Data
public class CartItemRequest {
    private Long productId;
    private Long quantity;
    private Long userId;
}
