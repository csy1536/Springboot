package com.springboot.jpa.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.jpa.dto.ProductDTO;
import com.springboot.jpa.dto.ProductResponseDTO;


@Service
public class RestTemplateService {
	
	private Logger logger = LoggerFactory.getLogger(RestTemplateService.class);
	
	public String getName(String number) {
		URI uri = UriComponentsBuilder
					.fromUriString("http://localhost:9090")
					.path("/product")
					.queryParam("number", number)
					.encode()
					.build()
					.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		logger.info("[request uri] ::: {}", uri);
		
		return responseEntity.getBody();
	}
	
	public String getNameWithPathVariable(String number) {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/product/{number}")
				.encode()
				.build()
				.expand(number)
				.toUri();
	
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		logger.info("[request uri] ::: {}", uri);
		
		return responseEntity.getBody();
	}
	
	public String getNameWithParameter(String number) {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/product/param")
				.queryParam("number", number)
				.encode()
				.build()
				.toUri();
	
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		logger.info("[request uri] ::: {}", uri);
		
		return responseEntity.getBody();
	}
	
	public ResponseEntity<ProductResponseDTO> postWithParamAndBody(ProductDTO dto){
		URI uri = UriComponentsBuilder
					.fromUriString("http://localhost:9090")
					.path("/product/")
					.encode()
					.build()
					.toUri();
		
		RequestEntity<ProductDTO> requestEntity = RequestEntity
															.post(uri)
															.header("my-header", "post api test")
															.body(dto);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductResponseDTO> responseEntity = restTemplate.exchange(requestEntity, ProductResponseDTO.class);
		
		logger.info("[request uri] ::: {}", uri);
		
		return responseEntity;
	}
}
