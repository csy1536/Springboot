package com.springboot.jpa.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.lang.Nullable;

import com.springboot.jpa.group.ValidatorGroup1;
import com.springboot.jpa.group.ValidatorGroup2;
import com.springboot.jpa.validator.Telephone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	@NotBlank
	@NonNull
	private String name;
	
	@Positive(groups = ValidatorGroup2.class)
	private int price;
	
	@Min(value = 1, groups = ValidatorGroup1.class)
	@Max(value = 100, groups = ValidatorGroup1.class)
	private int stock;
	
	@Telephone
	private String telephone;

}
