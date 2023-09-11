package com.springboot.jpa.dao;

import java.util.List;

import com.springboot.jpa.entity.ProductEntity;

public interface ProductDAO {
	
	ProductEntity insertProduct(ProductEntity product);
	
	ProductEntity selectProduct(Long number);
	
	List<ProductEntity> getProductAll();
	
	ProductEntity updateProeuctName(Long number, String name) throws Exception;
	
	void deleteProduct(Long number) throws Exception;

}
