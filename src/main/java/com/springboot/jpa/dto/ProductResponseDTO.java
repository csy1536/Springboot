package com.springboot.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
	private Long number;
	private String name;
	private int price;
	private int stock;
}
