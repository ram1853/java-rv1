package com.grab.retail.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grab.retail.entities.Retail;
import com.grab.retail.service.RetailService;

@RestController
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	RetailService retailService;
	
	@GetMapping("/home")
	public String store() {
		
		return "welcome to grab store";
	}
	
	@GetMapping("/retails")
	public List<Retail> getRetailTypes() {
		
		return retailService.getRetailTypes();
	}
	
	@GetMapping("/retails/{retailId}")
	public Retail getRetail(@PathVariable int retailId) {
		
		return retailService.getRetail(retailId);
	}
	
	@PostMapping("/retails")
	public void addRetail(@RequestBody Retail retail) {
		
		retailService.addRetail(retail);
	}
	
	@DeleteMapping("/retails/{retailId}")
	public void deleteRetail(@PathVariable int retailId) {
		
		retailService.deleteRetail(retailId);
	}
}
