package com.springboot.jpa.service;

import java.util.List;

import com.springboot.jpa.dto.ProductDTO;
import com.springboot.jpa.dto.ProductResponseDTO;

public interface ProductService {

	ProductResponseDTO getProduct(Long number);
	
	ProductResponseDTO saveProduct(ProductDTO productDTO);
	
	ProductResponseDTO changeProductName(Long number, String name) throws Exception;
	
	void deleteProduct(Long number) throws Exception;
	
	List<ProductResponseDTO> getProducts();
	
	List<ProductResponseDTO> getMbProductById(Long id);
	
	List<ProductResponseDTO> getProductByParam(ProductDTO param);
}
