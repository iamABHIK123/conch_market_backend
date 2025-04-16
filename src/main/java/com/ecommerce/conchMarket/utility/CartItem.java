package com.ecommerce.conchMarket.utility;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cart_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id; // Primary key

	@Column(name = "product_id", nullable = false)
	private Long productId;

	@Column(name = "quantity", nullable = false)
	public Long quantity;

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Column(name = "modified_at", nullable = true)
	private Timestamp modifiedAt;

	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_cart"))
	@JsonIgnore
	private User user;

//	@ManyToOne
//	@JoinColumn(name = "product_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_cart_product"))
//	private Product product;

	@PrePersist
	protected void onCreate() {
		createdAt = new Timestamp(System.currentTimeMillis());
		modifiedAt = new Timestamp(System.currentTimeMillis());
	}
	// Other fields and methods as needed
}
