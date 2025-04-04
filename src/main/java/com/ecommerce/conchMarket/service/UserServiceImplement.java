//package com.ecommerce.conchMarket.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import com.ecommerce.conchMarket.utility.User;
//import com.ecommerce.conchMarket.Repository.UserRepo;
//
//@Service
//public class UserServiceImplement implements UserService {
//
//	@Autowired
//	UserRepo userRepo;
//
//	@Autowired
//	private PasswordEncoder encoder;
//	
//	@Override
//	public User add(User user) {
//		// TODO Auto-generated method stub
//		user.setPassword(encoder.encode(user.getPassword()));
//		return userRepo.save(user);
//	}
//
//	@Override
//	public List<User> get() {
//		// TODO Auto-generated method stub
//		return userRepo.findAll();
//	}
//
//	@Override
//    public User get(String email) {
//        return userRepo.findByEmail(email);
//    }
//
//	
//}


package com.ecommerce.conchMarket.service;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerce.conchMarket.utility.User;
import com.ecommerce.conchMarket.Repository.UserRepo;

@Service
public class UserServiceImplement implements UserService,UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    private BCryptPasswordEncoder bpencoder= new BCryptPasswordEncoder(4);
    
    @Override
    public User add(User user) {
        // Check if email already exists
//        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
//        if (existingUser.isPresent()) {
//            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
//        }

        // Encode password before saving
        user.setPassword(bpencoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public List<User> get() {
        return userRepo.findAll();
    }

    @Override
    public User get(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }
        return user;
    }

    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        userRepo.deleteById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());

        if (!userDetails.getPassword().equals(user.getPassword())) {
            user.setPassword(bpencoder.encode(userDetails.getPassword()));
        }

        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities() // Assuming `getAuthorities()` is implemented in your `User` class
        );
    }

	@Override
	public Long totalUser() {
		// TODO Auto-generated method stub
		return userRepo.count();
	}

}

