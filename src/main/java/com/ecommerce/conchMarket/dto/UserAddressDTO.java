package com.ecommerce.conchMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDTO {
	private Long id;
    private String addressLine;
    private String city;
    private String postalCode;
    private String country;
    private String region;
    private String phoneNo;
}
