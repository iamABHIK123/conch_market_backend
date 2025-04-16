package com.ecommerce.conchMarket.filter;
import com.ecommerce.conchMarket.service.JwtService;
import com.ecommerce.conchMarket.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
// FOR EVERY REQ ,ONCEPERREQUENTFILTER WILL BE CALLED ONCE
    @Autowired
    private JwtService jwtService;

    @Autowired
    @Lazy
    private UserService userDetailsService;


    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // 🔥 Skip JWT validation for /auth/refreshToken
        if (requestURI.equals("/auth/refreshToken")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Retrieve the Authorization header
        String authHeader = request.getHeader("Authorization");
        System.out.println("Auth header is: " + authHeader);
        
        String token = null;
        String username = null;
        String userType = null;
        Long userId = null;
        // Check if the header starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Extract token
            try {
                username = jwtService.extractUsername(token); // Extract username from token
                userType = jwtService.extractUserRole(token); // Extract user type (role) from token
                userId = jwtService.extractUserId(token); // 👈 Extract 
                System.out.println("Extracted User Role: " + userId); 
            } catch (ExpiredJwtException e) {
                System.out.println("⚠️ JWT Expired: " + e.getMessage());
                //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 🔥 Explicitly set 401
                response.getWriter().write("Token Expired");
                response.getWriter().flush();
                return;
            }
        }

        // If the token is valid and no authentication is set in the context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.get(username);

            // Validate token and set authentication
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
                );
                // 🔥 Store user role in request attributes for controllers to use
                request.setAttribute("userType", userType);
                request.setAttribute("userId", userId);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

}

