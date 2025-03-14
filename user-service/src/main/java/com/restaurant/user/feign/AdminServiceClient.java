package com.restaurant.user.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.restaurant.user.entity.MenuItem;

@FeignClient(name = "admin-service", path = "/api/admin") //Admin Service Name
public interface AdminServiceClient {

	
	@GetMapping //This should match the endpoint in Admin Service
	List<MenuItem> getMenu();
	
}
