package com.ecommerce.conchMarket.utility;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "payment_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id; // Primary key

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "receipt", nullable = false)
	private String receipt;

	@Column(name = "provider", nullable = false)
	private String provider = "razorPay";  // Set default value in constructor or here

	// ----------------------RAZOR PAY SECTIONS-------------------------------------------
	@Column(name = "order_id", nullable = true)
	private Long orderId;  // Should match the type in OrderDetails

	@Column(name = "status", nullable = true)
	private String status;
	// -----------------------------------------------------------------

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

	@Column(name = "modified_at", nullable = true)
	private Timestamp modifiedAt;

	@ManyToOne
	@JoinColumn(name = "order_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_payment"))
	private OrderDetails orderDetails;
}
