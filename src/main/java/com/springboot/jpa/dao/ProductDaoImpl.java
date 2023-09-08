package com.springboot.jpa.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.jpa.entity.ProductEntity;
import com.springboot.jpa.repository.ProductRepository;

@Component
public class ProductDaoImpl implements ProductDAO {
	
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductDaoImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ProductEntity insertProduct(ProductEntity product) {
		ProductEntity saveProduct = productRepository.save(product);
		return saveProduct;
	}

	@Override
	public ProductEntity selectProduct(Long number) {
		ProductEntity selectProduct = productRepository.getById(number);
		return selectProduct;
	}

	@Override
	public ProductEntity updateProeuctName(Long number, String name) throws Exception {
		Optional<ProductEntity> selectProduct = productRepository.findById(number);
		
		ProductEntity updateProduct;
		
		if(selectProduct.isPresent()) {
			ProductEntity product = selectProduct.get();
			
			product.setName(name);
			product.setUpdateAt(LocalDateTime.now());
			
			updateProduct = productRepository.save(product);
		}else {
			throw new Exception();
		}
		
		return updateProduct;
	}

	@Override
	public void deleteProduct(Long number) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
