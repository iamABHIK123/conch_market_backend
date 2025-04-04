package com.ecommerce.conchMarket.utility;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "order_details", 
       uniqueConstraints = {
           @UniqueConstraint(name = "order_index", columnNames = "id"),
           @UniqueConstraint(name = "customer_order_index", columnNames = {"id", "user_id"})
       })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id; // Primary key

    @Column(name = "user_id", nullable = true)
    private Long userId;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "modified_at", nullable = true)
    private Timestamp modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false,
                foreignKey = @ForeignKey(name = "fk_shopping_user_order"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "payment_id", insertable = false, updatable = false,
                foreignKey = @ForeignKey(name = "fk_order_payment"))
    private PaymentDetails paymentDetails;

    // Other fields and methods as needed
}
