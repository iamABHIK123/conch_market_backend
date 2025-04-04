//package com.ecommerce.conchMarket.service;
//
//import java.math.BigDecimal;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.ecommerce.conchMarket.Repository.PaymentRepo;
//import com.ecommerce.conchMarket.utility.OrderDetails;
//import com.ecommerce.conchMarket.utility.PaymentDetails;
//import com.razorpay.RazorpayClient;
//import com.razorpay.Order;
//@Service
//public class OrderService {
//	
////	@Autowired
////	private PaymentDetails pd;
//	
//	@Autowired
//	PaymentRepo paymentRepo;
//	
//	@Value("${razorpay.key.id}")
//	private String razorPayKey;
//	
//	@Value("${razorpay.secret.key}")
//	private String razorPaySecret;
//	
//	private RazorpayClient client;
//	
//	public PaymentDetails createOrder( BigDecimal amount,String email) throws Exception {
//		System.out.println(amount+"--create order function---"+email);
//		PaymentDetails pd=new PaymentDetails();
//		pd.setAmount(amount);
//		pd.setReceipt(email);
//		JSONObject orderReq = new JSONObject();
//		
//		orderReq.put("amount",600000);
//		orderReq.put("currency","INR");
//		orderReq.put("receipt",pd.getReceipt());
//		
//		this.client =new RazorpayClient(razorPayKey,razorPaySecret);
//		
//		// create order in razorpay
//		Order razorPayOrder = client.orders.create(orderReq);
//		
//		pd.setOrderId(332345789L);// this is long 
//		pd.setStatus(razorPayOrder.get("status"));
//		
//		paymentRepo.save(pd);
//		
//		return pd;
//	}
//	
//	public void updateOrder() {
//		
//	}
//}
