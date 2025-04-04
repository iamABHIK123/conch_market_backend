//package com.ecommerce.conchMarket.utility;
//
//import java.util.Set;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.sql.Timestamp;
//import java.util.Collection;
//import java.util.HashSet;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.PreUpdate;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "users")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class User implements UserDetails  {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true, length = 45)
//    private String email;
//
//    @Column(nullable = false, length = 64)
//    private String password;
//
//    @Column(name = "first_name", nullable = false, length = 20)
//    private String firstName;
//
//    @Column(name = "last_name", nullable = false, length = 20)
//    private String lastName;
//
//
//    @Column(name = "created_at", nullable = false)
//    private Timestamp createdAt;
//
//    @Column(name = "modified_at", nullable = true)
//    private Timestamp modifiedAt;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<UserAddress> userAddresses;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<UserPayment> userPayments;
//    
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<CartItem> cartItems = new HashSet<>();
//    
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//        name = "user_roles",
//        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
//    )
//    private Set<Role> roles = new HashSet<>();
//    
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = new Timestamp(System.currentTimeMillis());
//        modifiedAt = new Timestamp(System.currentTimeMillis());
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        modifiedAt = new Timestamp(System.currentTimeMillis());
//    }
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//    
//}


package com.ecommerce.conchMarket.utility;

import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.sql.Timestamp;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "modified_at", nullable = true)
    private Timestamp modifiedAt;
    
    @Column(name = "role", nullable = false, length = 20)
    private String role="USER";

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAddress> userAddresses;

//    @OneToOne
//    @JoinColumn(name = "refresh_token_id")  // You can change this name to match your actual column name in the table
//    private RefreshToken refreshToken;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserPayment> userPayments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//        name = "user_roles",
//        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
//    )
//    private Set<Role> roles = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        modifiedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedAt = new Timestamp(System.currentTimeMillis());
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//        return authorities;
//    }

    @Override
    public String getUsername() {
        return email;  // Since username is usually the email in many systems
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Implement logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Implement logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Implement logic if needed
    }

    @Override
    public boolean isEnabled() {
        return true;  // Implement logic if needed
    }
// interface-GrantedAuthority ,implementation of GrantAuthorities is SimpleGrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.role)); // Example: ROLE_USER or ROLE_ADMIN
        System.out.println(authorities);
        return authorities;
    }

}

