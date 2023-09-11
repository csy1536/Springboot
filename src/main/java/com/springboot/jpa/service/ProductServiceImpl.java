package com.springboot.jpa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.dao.ProductDAO;
import com.springboot.jpa.dto.ProductDTO;
import com.springboot.jpa.dto.ProductResponseDTO;
import com.springboot.jpa.entity.ProductEntity;
import com.springboot.jpa.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductDAO productDAO;
	
	@Autowired
	public ProductMapper productMapper;
	
	@Autowired
	public ProductServiceImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public ProductResponseDTO getProduct(Long number) {
		//Entity > ProductResponseDTO
		ProductEntity productEntity = productDAO.selectProduct(number);
		ProductResponseDTO productDto = ProductResponseDTO.builder()
									.name(productEntity.getName())
									.price(productEntity.getPrice())
									.number(productEntity.getNumber())
									.stock(productEntity.getStock())
									.build();
		return productDto;
	}

	@Override
	public ProductResponseDTO saveProduct(ProductDTO productDTO) {
		// ProductDTO > ProductEntity
		ProductEntity productEntity = ProductEntity.builder()
										.name(productDTO.getName())
										.stock(productDTO.getStock())
										.price(productDTO.getPrice())
										.createAt(LocalDateTime.now())
										.build();
		
		//Entity > DTO
		ProductEntity saveProductEntity = productDAO.insertProduct(productEntity);
		ProductResponseDTO productResponseDTO = ProductResponseDTO.builder()
													.name(saveProductEntity.getName())
													.number(saveProductEntity.getNumber())
													.price(saveProductEntity.getPrice())
													.stock(saveProductEntity.getStock())
													.build();
		return productResponseDTO;
	}

	@Override
	public ProductResponseDTO changeProductName(Long number, String name) throws Exception {
		// Entity > DTO
		ProductEntity updateEntity = productDAO.updateProeuctName(number, name);
		ProductResponseDTO productResponseDTO = ProductResponseDTO.builder()
									.number(updateEntity.getNumber())
									.name(updateEntity.getName())
									.price(updateEntity.getPrice())
									.stock(updateEntity.getStock())
									.build();
		return productResponseDTO;
	}

	@Override
	public void deleteProduct(Long number) throws Exception {
		productDAO.deleteProduct(number);
		
	}

	@Override
	public List<ProductResponseDTO> getProducts() {
		List<ProductEntity> products = productDAO.getProductAll();
		List<ProductResponseDTO> result = new ArrayList<>();
		
		for (ProductEntity product : products) {
			ProductResponseDTO dto  = ProductResponseDTO.builder()
										.name(product.getName())
										.number(product.getNumber())
										.price(product.getPrice())
										.stock(product.getPrice())
										.build();
			result.add(dto);
		}
		
		return result;
	}

	//mybatis를 이용한 데이터 가져오기
	@Override
	public List<ProductResponseDTO> getMbProductById(Long id) {
		List<ProductEntity> products = productMapper.getMbProductById(id);
		
		List<ProductResponseDTO> result = new ArrayList<>();
		
		for (ProductEntity product : products) {
			ProductResponseDTO dto  = ProductResponseDTO.builder()
										.name(product.getName())
										.number(product.getNumber())
										.price(product.getPrice())
										.stock(product.getPrice())
										.build();
			result.add(dto);
		}
		
		return result;
	}

	@Override
	public List<ProductResponseDTO> getProductByParam(ProductDTO param) {
		List<ProductEntity> products = productMapper.getMbProductByParam(param);
		
		List<ProductResponseDTO> result = new ArrayList<>();
		for (ProductEntity product : products) {
			ProductResponseDTO dto  = ProductResponseDTO.builder()
										.name(product.getName())
										.number(product.getNumber())
										.price(product.getPrice())
										.stock(product.getStock())
										.build();
			result.add(dto);
		}
		
		return result;
	}

}
