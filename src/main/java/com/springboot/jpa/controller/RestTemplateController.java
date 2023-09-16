package com.springboot.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.dto.ProductDTO;
import com.springboot.jpa.dto.ProductResponseDTO;
import com.springboot.jpa.service.RestTemplateService;

@RestController
@RequestMapping("/api/v1/crud-api")
public class RestTemplateController {
	
	@Autowired
	private RestTemplateService restTemplateService;

	@GetMapping
	public String getName(String number) {		
		return restTemplateService.getName(number);
	}
	
	@GetMapping("/pathVariable")
	public String getNameWithPathVariable(String number) {		
		return restTemplateService.getNameWithPathVariable(number);
	}
	
	@GetMapping("/param")
	public String getNameWithparam(String number) {		
		return restTemplateService.getNameWithParameter(number);
	}
	
	@PostMapping
	public ResponseEntity<ProductResponseDTO> postWithParamAndBody(@RequestBody ProductDTO dto){
		return restTemplateService.postWithParamAndBody(dto);
	}
}
