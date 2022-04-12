package com.ShopShoe.dto;

import com.ShopShoe.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
	
	private long id;
	
	private Double total_money;
	
	private UserEntity user;
}
