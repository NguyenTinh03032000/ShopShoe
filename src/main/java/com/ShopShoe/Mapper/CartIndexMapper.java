package com.ShopShoe.Mapper;

import org.mapstruct.Mapper;

import com.ShopShoe.dto.CartIndexDTO;
import com.ShopShoe.entity.CartIndexEntity;

@Mapper(componentModel = "spring")
public interface CartIndexMapper {
	CartIndexDTO cartIndexEntityToCartIndexDto(CartIndexEntity cartIndexEntity);
}
