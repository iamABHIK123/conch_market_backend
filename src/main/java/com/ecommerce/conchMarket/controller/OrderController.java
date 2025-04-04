package com.ecommerce.conchMarket.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.conchMarket.Repository.OrderRepo;
import com.ecommerce.conchMarket.dto.PaymentDto;
import com.ecommerce.conchMarket.utility.OrderDetails;
//import com.ecommerce.conchMarket.service.OrderService;
import com.ecommerce.conchMarket.utility.PaymentDetails;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;


//@RestController
//@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
//public class OrderController {
//
//	
////	@Autowired 
////	private OrderService os;
//	
//	@PostMapping("/create-order")
//	@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")	
//	public ResponseEntity<?> createOrder(@RequestBody PaymentDto pd) throws Exception{
//		//public String createOrder(@RequestBody PaymentDto pd) throws Exception{
//		System.out.println("pd value is here-"+pd.getAmount()+"--"+pd.getReceipt());
////		PaymentDetails createdOrder=os.createOrder(pd.getAmount(),pd.getReceipt());
//		
//		RazorpayClient client=new RazorpayClient("rzp_test_tVzt9bjKv6gWWk","xByJNeoFMx1k0W53zbHaT59z");
//		JSONObject orderReq = new JSONObject();
//		
//		orderReq.put("amount",600000);
//		orderReq.put("currency","INR");
//		orderReq.put("receipt",pd.getReceipt());
//			
//		Order razorPayOrder = client.orders.create(orderReq);
//		
//		Map<String, Object> response = new HashMap<>();
//        response.put("orderId", response.get("id"));
//        response.put("status", "created");
//		System.out.println(razorPayOrder);
//		
//		return ResponseEntity.ok(response);
//	 // return new  ResponseEntity<>(razorPayOrder,HttpStatus.CREATED);
//	//	return "success";
//	}
//	
//	@PostMapping("/handle-payment-callback")
//	public String handlePaymentCallback(@RequestParam Map<String,String> resPayload) {
//		System.out.println(resPayload);
//		return "success";
//	}
//}

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class OrderController {
	@Autowired
	OrderRepo or;
    @PostMapping("/create-order")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody PaymentDto pd) throws Exception {
        System.out.println("pd value is here - " + pd.getAmount() + "--" + pd.getReceipt());
        
        // Initialize Razorpay client
        RazorpayClient client = new RazorpayClient("rzp_test_vlzU641yFwMRUx","kQ3zWlq30XAFjEw3Jfr1S3bV");

        // Prepare order request
        JSONObject orderReq = new JSONObject();
        orderReq.put("amount", 600000); // Amount in paise
        orderReq.put("currency", "INR");
        orderReq.put("receipt", pd.getReceipt());

        // Create order with Razorpay
        Order razorPayOrder = client.orders.create(orderReq);
        
        OrderDetails od=new OrderDetails();        
        od.setOrderId(razorPayOrder.get("id"));
        od.setUserId(1L);
        od.setStatus(razorPayOrder.get("status"));
        od.setPaymentId(null);
        od.setReceipt(razorPayOrder.get("receipt"));
        this.or.save(od);
        
        // Prepare response with correct orderId mapping
        Map<String, Object> response = new HashMap<>();
        response.put("orderId", razorPayOrder.get("id"));
        response.put("status", "created");
        System.out.println(razorPayOrder);

        // Return JSON response
        return ResponseEntity.ok(response);
    }

    @PostMapping("/handle-payment-callback")
    public String handlePaymentCallback(@RequestParam Map<String, String> resPayload) {
        System.out.println(resPayload);
        return "success";
    }
}
