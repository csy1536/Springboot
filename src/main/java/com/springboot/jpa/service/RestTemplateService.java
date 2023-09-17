package com.springboot.jpa.service;

import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
		ResponseEntity<String> responseEntity2 = restTemplate.getForEntity(uri, String.class);
		
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
		RestTemplate restTemplate = restTemplate();//new RestTemplate();
		ResponseEntity<ProductResponseDTO> responseEntity = restTemplate.exchange(requestEntity, ProductResponseDTO.class);
		
		logger.info("[request uri] ::: {}", uri);
		
		return responseEntity;
	}
	
	public RestTemplate restTemplate() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		
		//[방법1]
		HttpClient client = HttpClientBuilder.create()
								.setMaxConnTotal(500)
								.setMaxConnPerRoute(500)
								.build();
		
		//[방법2]
		CloseableHttpClient httpClient = HttpClients.custom()
											.setMaxConnTotal(500)
											.setMaxConnPerRoute(500)
											.build();
		
		factory.setHttpClient(httpClient); //HttpClient와 CloseableHttpClient의 차이
		factory.setConnectTimeout(2000);
		factory.setReadTimeout(5000);
		
		RestTemplate restTemplate = new RestTemplate(factory);
		
		return restTemplate;
	}
}
