package com.springboot.jpa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.springboot.jpa.dto.ProductDTO;
import com.springboot.jpa.entity.ProductEntity;

@Repository
@Mapper
public interface ProductMapper {
	public List<ProductEntity> getMbProductById(@Param("id") Long id);
	
	public List<ProductEntity> getMbProductByParam(ProductDTO dto);
}
