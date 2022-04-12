package com.ShopShoe.dto;

import com.ShopShoe.entity.CartEntity;
import com.ShopShoe.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartIndexDTO {

	private long id;
	
	private int amount;
	
	private CartEntity cart;
	
	private ProductEntity product;
}
