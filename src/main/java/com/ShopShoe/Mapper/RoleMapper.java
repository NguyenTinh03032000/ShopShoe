package com.ShopShoe.Mapper;

import org.mapstruct.Mapper;

import com.ShopShoe.dto.RoleDTO;
import com.ShopShoe.entity.RoleEntity;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	RoleDTO roleEntityToRoleDTO(RoleEntity roleEntity);
	
}
