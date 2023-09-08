package com.springboot.jpa.dao;

import com.springboot.jpa.entity.ProductEntity;

public interface ProductDAO {
	
	ProductEntity insertProduct(ProductEntity product);
	
	ProductEntity selectProduct(Long number);
	
	ProductEntity updateProeuctName(Long number, String name) throws Exception;
	
	void deleteProduct(Long number) throws Exception;

}
