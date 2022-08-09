package com.example.ProductManagement.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ProductNotFoundException extends RuntimeException {
	
	private static final long serialVersionUId=1;
	public ProductNotFoundException(String message) {
		super (message);
	}
	public  ProductNotFoundException(String message,Throwable throwable) {
		super (message,throwable);
	}
	
	

}
