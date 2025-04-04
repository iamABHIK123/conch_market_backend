package com.ecommerce.conchMarket.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.conchMarket.service.CartItemService;
import com.ecommerce.conchMarket.service.CloudinarySerivceImplement;
import com.ecommerce.conchMarket.service.ProductService;
import com.ecommerce.conchMarket.utility.ImageUtils;
import com.ecommerce.conchMarket.utility.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.MediaType;
import java.sql.Timestamp;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private CloudinarySerivceImplement cldImg;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	@PostMapping(value = "/auth/admin/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> addProduct(
			 @RequestPart("product") Product product, 
	        @RequestPart("file") MultipartFile file) {
	    try {
	        Map<?, ?> cldData=cldImg.upload(file);
	        product.setImageUrl((String)cldData.get("secure_url"));
	        product.setImageId((String)cldData.get("public_id"));
	        productService.addProduct(product);
	        return ResponseEntity.ok("Product added successfully.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to add product: " + e.getMessage());
	    }
	}

//	@PostMapping("/auth/admin/products/updateProduct")
//	public ResponseEntity<String> updateProduct( @RequestPart("product") Product product, 
//	        @RequestPart(type="file",required=false) MultipartFile file) {
////		System.out.println("debug details"+product.getId());
////		 try {
////			 if (product.getId() == null) {
////		            return ResponseEntity.badRequest().body("Product ID is required for updating.");
////		        }
////		        productService.updateProduct(product);
////		        System.out.println("Update product details: "+product);
////		        return ResponseEntity.ok("Product updated successfully.");
////		    } catch (Exception e) {
////		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product: " + e.getMessage());
////		    }
//		 try {
//			 if(!file.isEmpty()) {
//				 Map<?, ?> cldData=cldImg.upload(file);
//			        product.setImageUrl((String)cldData.get("secure_url"));
//			        product.setImageId((String)cldData.get("public_id"));
//			 }
//		        
//		        productService.addProduct(product);
//		        return ResponseEntity.ok("Product added successfully.");
//		    } catch (Exception e) {
//		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//		                             .body("Failed to add product: " + e.getMessage());
//		    }
//	}
	
	@PutMapping(value = "/auth/admin/products/updateProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> updateProduct(
	        @RequestPart("product") Product product,
	        @RequestPart(name = "file", required = false) MultipartFile file) {

	    try {
	        // Check if a file is provided and not empty
	        if (file != null && !file.isEmpty()) {
	            // Upload the file to Cloudinary and get the response
	        	Product existingProduct = productService.getProductById(product.getId());  	    		
	        	cldImg.delete(existingProduct.getImageId());
	    	    
	            Map<?, ?> cldData = cldImg.upload(file);
	            product.setImageUrl((String) cldData.get("secure_url"));
	            product.setImageId((String) cldData.get("public_id"));
	        }

	        // Update the product in the database
	        productService.updateProduct(product);

	        return ResponseEntity.ok("Product updated successfully.");
	    } catch (Exception e) {
	        // Log the error for debugging
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Failed to update product: " + e.getMessage());
	    }
	}


	@DeleteMapping("/auth/admin/products/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
		System.out.println("Id id "+id);
		if(cartItemService.searchByProductId(id))cartItemService.deleteByProdId(id);		
		 System.out.println("Cart with product ID " + id + " has been deleted successfully.");
		 
		Product existingProduct = productService.getProductById(id); 
		
	    productService.deleteProductById(id);
	    System.out.println("product-"+existingProduct);
	    // Check if the image exists in Cloudinary before deleting it
	    if (existingProduct.getImageId() != null && !existingProduct.getImageId().isEmpty()) {
	        try {
	            // Check if the image exists in Cloudinary
	            Map<?, ?> imageDetails = cldImg.getImageDetails(existingProduct.getImageId());
	            System.out.println("Image details: " + imageDetails);

	            // If the image exists, delete it
	            cldImg.delete(existingProduct.getImageId());
	            System.out.println("Image with public_id " + existingProduct.getImageId() + " has been deleted successfully.");
	        } catch (RuntimeException e) {
	            // Handle the case where the image does not exist in Cloudinary
	            System.out.println("Image with public_id " + existingProduct.getImageId() + " does not exist in Cloudinary.");
	        }
	    } else {
	        System.out.println("No image associated with the product.");
	    }
	    System.out.println("Product with ID " + id + " has been deleted successfully.");
	    return ResponseEntity.ok("Product with ID " + id + " has been deleted successfully.");
	}
	
	@GetMapping("/products/category/{categoryName}")
	public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable String categoryName) {
		List<Product> products = productService.getAllProductsByCategory(categoryName);
		  // Print the product list
	    System.out.println("Products in category '" + categoryName + "': " + products);

		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		Product product=productService.getProductById(id);
		if(product!=null) {
			return ResponseEntity.ok(product); 
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/products/name/{name}")
	public ResponseEntity<Product> getProductByName(@PathVariable String name){
		Product product=productService.getProdudctByName(name);
		System.out.println("product details "+product);
		if(product!=null) {
			return ResponseEntity.ok(product); 
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/products/totalProducts")
	public ResponseEntity<Long> getTotalProducts(){
		return ResponseEntity.ok(productService.getProductCount());
	}
	
	@GetMapping("/products/totalCategories")
	public ResponseEntity<Long> getTotalCategories(){
		return ResponseEntity.ok(productService.getCategoryCount());
	}
}
