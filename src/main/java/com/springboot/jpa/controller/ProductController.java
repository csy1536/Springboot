package com.springboot.jpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.dto.ChangeProductNameDTO;
import com.springboot.jpa.dto.ProductDTO;
import com.springboot.jpa.dto.ProductResponseDTO;
import com.springboot.jpa.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "product", description = "product 조회, 수정, 삭제, 추가")
@RestController
@RequestMapping("/product")
public class ProductController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@Operation(summary = "get product", description = "product 정보 가져오기")
	@GetMapping("/")
	public ResponseEntity<ProductResponseDTO> getProduct(Long number){
		ProductResponseDTO result = productService.getProduct(number);
		return new ResponseEntity<ProductResponseDTO>(result, HttpStatus.OK);
		
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
	public ResponseEntity<ProductResponseDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO){
		ProductResponseDTO result = productService.saveProduct(productDTO);
		return new ResponseEntity<ProductResponseDTO>(result, HttpStatus.OK);
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

	@Operation(summary = "runtimeException 호출", description = "runtimeException 호출 테스트")
	@GetMapping("/exception")
	public void getRuntimeException() {
		throw new RuntimeException("getRuntimeException 메서드 호출");
	}
	
/* Controller내 ExceptionHandler 생성 */
//	@ExceptionHandler(value = RuntimeException.class)
//	public ResponseEntity<Map<String,String>> handleException(RuntimeException e, HttpServletRequest request){
//		HttpHeaders resHeader = new HttpHeaders();
//		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//		
//		LOGGER.error("Advice 내 handlerException 호출, {}, {}", request.getRequestURI(), e.getMessage());
//		
//		Map<String, String> map = new HashMap<>();
//		map.put("error type", httpStatus.getReasonPhrase());
//		map.put("code", "400");
//		map.put("message", e.getMessage());
//		
//		return new ResponseEntity<Map<String,String>>(map, resHeader, httpStatus);
//		
//	}
}
