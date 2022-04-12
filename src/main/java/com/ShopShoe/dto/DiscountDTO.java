package com.ShopShoe.dto;

import java.sql.Date;

import com.ShopShoe.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DiscountDTO {

	private long id;
	
	private String name_discount;
	
	private Date date_discount;
	
	private int percent_discount;

	private ProductEntity product;
}
