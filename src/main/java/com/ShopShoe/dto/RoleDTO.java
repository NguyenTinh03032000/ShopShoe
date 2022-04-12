package com.ShopShoe.dto;

import com.ShopShoe.common.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RoleDTO {

	private Integer id;
	
	private ERole name;
}
