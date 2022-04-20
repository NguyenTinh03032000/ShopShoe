package com.ShopShoe.Mapper;

import org.mapstruct.Mapper;

import com.ShopShoe.dto.LogDTO;
import com.ShopShoe.entity.LogEntity;

@Mapper(componentModel = "spring")
public interface LogMapper {
	LogDTO logEntityToLogDTO(LogEntity logEntity);
}
