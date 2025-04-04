package com.ecommerce.conchMarket.dto;

import java.math.BigDecimal;

public class PaymentDto {
	BigDecimal amount;
	String receipt;
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
}
