package com.ecommerce.conchMarket.service;

import com.ecommerce.conchMarket.utility.User;

public interface LoginService {
    User login(String email, String password);
}
