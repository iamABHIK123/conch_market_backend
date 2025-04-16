package com.ecommerce.conchMarket.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import com.ey.springboot3security.filter.JwtAuthFilter;
//import com.ey.springboot3security.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ecommerce.conchMarket.service.UserServiceImplement;
import com.ecommerce.conchMarket.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity // This notation is used to say spring ,the default config won't be used
					// here.Instead we wil use custom cofig
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter authFilter;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceImplement(); // Ensure UserInfoService implements UserDetailsService
	}

	// Disable CSRF-> for stateless APIs, when no need of session and cookie based
	// login ,
	// AS WE DEAl WITH JWT,SO NO NEED OF CSRF, USING JWT WE CAN GET THE CREDENTIAL
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/addresses","/products","/new-items/**","/top-products/**","/api/categories/**","/api/subcategories/**","/cloudinary/upload","/login","/users/getAll","/auth/refreshToken", "/signup", "/auth/logout","/products/category/**","/products/totalCategories",
                		"/products/totalProducts","/users/totalUsers","/cart/**","/cart/items/user/**","cart/items/count/**").permitAll()
                .requestMatchers("/auth/user/**").hasAuthority("ROLE_USER")
                .requestMatchers("/auth/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated() // Protect all other endpoints
            )
            .sessionManagement(sess -> sess
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No sessions
            )
            .authenticationProvider(authenticationProvider()) // Custom authentication provider
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        // Custom Logout Configuration
//        	.logout(logout -> logout
//            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")) // Handle POST logout requests
//            .logoutSuccessHandler((request, response, authentication) -> {
//                response.setStatus(HttpServletResponse.SC_OK); // Send HTTP 200 response
//                response.getWriter().write("{\"message\": \"Logout successful\"}"); // Send JSON response
//                response.getWriter().flush();
//            })
//            .invalidateHttpSession(true)
////            .deleteCookies("JSESSIONID") // Remove session cookies
//            .clearAuthentication(true)
//        );

        return http.build();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Password encoding
	}

	// this fun retrieves data from the above two funs
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
