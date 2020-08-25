package com.grab.retail.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.grab.retail.utils.RetailErrorResponse;
import com.grab.retail.utils.RetailNotFoundException;

@ControllerAdvice
public class StoreExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<RetailErrorResponse> getRetailExceptions(RetailNotFoundException e){
		
		RetailErrorResponse response = new RetailErrorResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage(), System.currentTimeMillis());
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<RetailErrorResponse> getRetailExceptions(Exception e){
		
		RetailErrorResponse response = new RetailErrorResponse(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), System.currentTimeMillis());
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
}
