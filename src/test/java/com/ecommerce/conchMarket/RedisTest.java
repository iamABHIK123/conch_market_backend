package com.ecommerce.conchMarket;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Disabled
	@Test
	void testSendMail() {
		redisTemplate.opsForValue().set("email","akp@gmail.com" );
		Object email=redisTemplate.opsForValue().get("email");
	}
	
}

