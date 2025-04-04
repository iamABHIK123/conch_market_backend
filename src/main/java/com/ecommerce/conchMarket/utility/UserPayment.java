package com.ecommerce.conchMarket.utility;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Entity
@Table(name = "user_payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id; // Primary key

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "account_no", nullable = false)
    private String accountNo;

    @Column(name = "expiry", nullable = false)
    private Date expiry;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false,
                foreignKey = @ForeignKey(name = "fk_user_payment_user"))
    private User user;

    // Other fields and methods as needed
}
