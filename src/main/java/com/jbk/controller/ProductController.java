package com.jbk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entity.Product;
import com.jbk.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	ProductService service;
	private List<Product> list;
	
	
	@PostMapping(value = "/save-product")
	public ResponseEntity<Boolean> saveProduct(@RequestBody Product product){
		boolean isAdded = service.saveProduct(product);
		if(isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		}else {
			//throw custom exception ( ResourceAlreadyExistsExceptio(String msg) )
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.ALREADY_REPORTED);
		}
	}
	
	@GetMapping(value="/get-product-by-id")
	public ResponseEntity<Product> getProductById(@RequestParam String productId){
		Product product = service.getProductById(productId);
		if(product!=null) {
			return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<Product>(product, HttpStatus.ALREADY_REPORTED);
		}
	}
	
	@GetMapping(value="/get-all-products")
	public ResponseEntity<List<Product>> getAllProducts(){
		list= service.getAllProducts();
		return new ResponseEntity<List<Product>>(list, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping(value="/delete-product-by-id")
	public ResponseEntity<Boolean> deleteProductById(@RequestParam String productId){
		boolean isDeleted = service.deleteProductById(productId);
		if(isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.ALREADY_REPORTED);
		}
	}
	
	@PutMapping(value = "/update-product")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product){
		boolean isAdded = service.saveProduct(product);
		if(isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.ALREADY_REPORTED);
		}
	}
}
