package com.springboot.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.dto.ProductDTO;
import com.springboot.jpa.dto.ProductResponseDTO;
import com.springboot.jpa.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "product", description = "product 조회, 수정, 삭제, 추가")
@RestController
@RequestMapping("/product")
public class ProductController {
	
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@Operation(summary = "get product", description = "product 정보 가져오기")
	@GetMapping("/")
	public ResponseEntity<ProductResponseDTO> getProduct(Long number){
		ProductResponseDTO result = productService.getProduct(number);
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@Operation(summary = "save product", description = "product 정보 저장하기")
	@PostMapping("/")
	public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductDTO productDTO){
		ProductResponseDTO result = productService.saveProduct(productDTO);
		return new ResponseEntity(result, HttpStatus.OK);
	}

}
