package com.ecommerce.conchMarket.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class ProjectConfig {	
	@Bean
	public Cloudinary getCloundinary() {
		Map config=new HashMap();
		config.put("cloud_name", "dvtirm6xz");
		config.put("api_key", "626656883854559");
		config.put("api_secret", "7AF5drVajjutHv8ukX9W3sk6Au4");
		config.put("secure", true);
		return new Cloudinary(config);
	}

}
