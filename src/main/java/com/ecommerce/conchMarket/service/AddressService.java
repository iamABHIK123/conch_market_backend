package com.ecommerce.conchMarket.service;


import java.util.List;
import java.util.Optional;

import com.ecommerce.conchMarket.utility.UserAddress;

public interface AddressService {
    UserAddress saveUserAddress(UserAddress userAddress);
    List<UserAddress> getAllUserAddresses();
    Optional<UserAddress> getUserAddressById(Long id);
    UserAddress updateUserAddress(UserAddress userAddress);
    void deleteUserAddress(Long id);
}
