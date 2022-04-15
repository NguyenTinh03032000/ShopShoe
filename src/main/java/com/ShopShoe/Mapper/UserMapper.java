package com.ShopShoe.Mapper;

import org.mapstruct.Mapper;

import com.ShopShoe.dto.UserDTO;
import com.ShopShoe.entity.UserEntity;

@Mapper
public interface UserMapper {
	UserDTO userEntityToUserDTO(UserEntity userEntity);
}
