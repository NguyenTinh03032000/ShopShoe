package com.ShopShoe.dto;

import javax.validation.constraints.NotEmpty;

import com.ShopShoe.common.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

	private Integer id;
	
	@NotEmpty(message = "Name cannot be empty")
	private ERole name;
}
