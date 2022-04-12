package com.ShopShoe.dto;

import com.ShopShoe.entity.CategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private long id;

	private String name;
	private double price;
	private String description;
	private String brand;

	private CategoryEntity category;

}
