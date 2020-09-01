package com.training.ms.customer.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.training.ms.customer.CustomerProxyController;
import com.training.ms.customer.model.Cart;

import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerProxyController customerProxy;
	
	 @Autowired
	  private Environment environment;
	 
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/customer-service/customer/{customerId}")
	Cart getCustomerDetails(@PathVariable Long customerId)
	{
		Cart cart =  customerProxy.getCartDetail(customerId);
		//logger.info("Customer Service Port="+Integer.parseInt(environment.getProperty("local.server.port")));
		return cart;
	}
	
	@GetMapping("/customer-service-local/customer/{customerId}")
	Cart getCustomerDetailsLocal(@PathVariable Long customerId)
	{
		Map<String, Long> uriVariables = new HashMap<>();
		uriVariables.put("customerId", customerId);
		 logger.info("Customer Service Port="+Integer.parseInt(environment.getProperty("local.server.port")));
	 ResponseEntity<Cart> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8101/cart-service/customer/{customerId}", Cart.class,
				uriVariables);
		
		return responseEntity.getBody();
	}
	
	

}
