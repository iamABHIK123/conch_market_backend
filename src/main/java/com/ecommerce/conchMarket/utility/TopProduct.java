package com.ecommerce.conchMarket.utility;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Table(name = "top_product ")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id; // Primary key

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_topProduct_product"))
    private Product product;

    @Column(name = "added_at", nullable = false)
    private Timestamp addedAt;

    @PrePersist
    protected void onCreate() {
        addedAt = new Timestamp(System.currentTimeMillis());
    }
}
