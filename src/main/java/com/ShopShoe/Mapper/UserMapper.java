package com.ShopShoe.Mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ShopShoe.dto.UserDTO;
import com.ShopShoe.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDTO userEntityToUserDTO(UserEntity userEntity);
	
	List<UserDTO> listUserEntityToUserDTO(List<UserEntity> userEntity);
}
