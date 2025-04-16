package com.ecommerce.conchMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.conchMarket.Repository.AddressRepo;
import com.ecommerce.conchMarket.dto.UserAddressDTO;
import com.ecommerce.conchMarket.utility.UserAddress;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplement implements AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Override
    public UserAddress saveUserAddress(UserAddress userAddress) {
        return addressRepo.save(userAddress);
    }

    @Override
    public List<UserAddress> getAllUserAddresses() {
        return addressRepo.findAll();
    }

    @Override
    public Optional<UserAddress> getUserAddressById(Long id) {
        return addressRepo.findById(id);
    }

    @Override
    public UserAddress updateUserAddress(UserAddress userAddress) {
        if (addressRepo.existsById(userAddress.getId())) {
            return addressRepo.save(userAddress);
        } else {
            return null; // or throw an exception if the address doesn't exist
        }
    }

    @Override
    public void deleteUserAddress(Long id) {
        addressRepo.deleteById(id);
    }
}
