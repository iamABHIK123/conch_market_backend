package com.ecommerce.conchMarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.conchMarket.utility.ImageData;

import java.util.Optional;

public interface StorageRepo extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}