package com.ShopShoe.Mapper;

import org.mapstruct.Mapper;

import com.ShopShoe.dto.ProductDTO;
import com.ShopShoe.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductDTO productEntityToProductDTO(ProductEntity productEntity);
}
