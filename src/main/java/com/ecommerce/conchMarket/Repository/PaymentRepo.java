package com.ecommerce.conchMarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.conchMarket.utility.PaymentDetails;

public interface PaymentRepo extends JpaRepository<PaymentDetails, Long>  {

}
