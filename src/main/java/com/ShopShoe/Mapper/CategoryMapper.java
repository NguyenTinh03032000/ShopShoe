package com.ShopShoe.Mapper;

import org.mapstruct.Mapper;

import com.ShopShoe.dto.CategoryDTO;
import com.ShopShoe.entity.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryDTO categoryToCategoryDTO(CategoryEntity categoryEntity);
}
