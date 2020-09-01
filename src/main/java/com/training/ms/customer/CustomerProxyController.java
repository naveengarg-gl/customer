package com.training.ms.customer;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.training.ms.customer.model.Cart;

@FeignClient(name="cart-service")
@RibbonClient(name="cart-service")
public interface CustomerProxyController {

	 @GetMapping("/cart-service/customer/{customerId}")
	  public Cart getCartDetail(@PathVariable Long customerId);
}
