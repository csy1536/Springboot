package com.springboot.jpa.controller;

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

import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.springboot.jpa.dto.ChangeProductNameDTO;
import com.springboot.jpa.dto.ProductDTO;
import com.springboot.jpa.dto.ProductResponseDTO;
import com.springboot.jpa.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Delegate;

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
	
	@Operation(summary = "get product", description = "mybatis를 이용한 product 정보 가져오기")
	@GetMapping("/mb/byId")
	public ResponseEntity<ProductResponseDTO> getMbProductById(Long id){
		List<ProductResponseDTO> result = productService.getMbProductById(id);
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@Operation(summary = "get products", description = "mybatis를 이용한 product 정보 가져오기")
	@GetMapping("/mb/all")
	public ResponseEntity<ProductResponseDTO> getMbProducts(ProductDTO param){
		
		List<ProductResponseDTO> result = productService.getProductByParam(param);
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@Operation(summary = "get products", description = "모든 product 정보 가져오기")
	@GetMapping("/all")
	public ResponseEntity<List<ProductResponseDTO>> getProducts(){
		List<ProductResponseDTO> list = productService.getProducts();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@Operation(summary = "save product", description = "product 정보 저장하기")
	@PostMapping("/")
	public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductDTO productDTO){
		ProductResponseDTO result = productService.saveProduct(productDTO);
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@Operation(summary = "modify proudct name", description = "product name 변경하기")
	@PutMapping("/")
	public ResponseEntity<ProductResponseDTO> modifyProduct(@RequestBody ChangeProductNameDTO dto) throws Exception{
		ProductResponseDTO result = productService.changeProductName(dto.getNumber(), dto.getName());
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@Operation(summary = "delete product", description = "product 삭제하기")
	@DeleteMapping("/")
	public ResponseEntity<String> deleteProduct(Long number) throws Exception{
		productService.deleteProduct(number);
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
	}

}
