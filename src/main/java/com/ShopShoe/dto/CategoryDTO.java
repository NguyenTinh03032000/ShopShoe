package com.ShopShoe.dto;

import java.util.List;

import com.ShopShoe.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDTO {

	private long id;
	
	private String name;

	private List<ProductEntity> listProduct;
}
