package com.ShopShoe.dto;

import java.sql.Date;

import com.ShopShoe.entity.ProductEntity;
import com.ShopShoe.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LogDTO {

	private long id;
	
	private String name_action;
	private String name_method;
	private String content;
	
	private UserEntity user;
	
	private ProductEntity product;
	
	private Date actionDate;

}
