package com.ecommerce.conchMarket.utility;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id; // Primary key

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageUrl=null;
    @Column(name = "image_id", nullable = false)
    private String imageId=null;

//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    @Column(name = "imagedata", nullable = true,length =100000)
//    private byte[] imageData;
    
    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

//    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
//    private TopProduct topProduct;
//    @JsonIgnore
//    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
//    private NewItems newItems;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "modified_at", nullable = true)
    private Timestamp modifiedAt;

    @Column(name = "discount_id", nullable = true)
    private Long discountId=1L;

    @ManyToOne
    @JoinColumn(name = "discount_id", insertable = false, updatable = false,
                foreignKey = @ForeignKey(name = "fk_product_discount"))
    private Discount discount;
    
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        modifiedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedAt = new Timestamp(System.currentTimeMillis());
    }
    // Other fields and methods as needed
}
