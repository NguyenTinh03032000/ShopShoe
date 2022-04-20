package com.ShopShoe.service;

import java.util.List;
import java.util.Optional;

import com.ShopShoe.dto.LogDTO;
import com.ShopShoe.entity.LogEntity;


public interface LogService {
	List<LogDTO> findAll();
    Optional<LogEntity> findById(long id);
    LogEntity getById(long id);
    LogEntity save(LogEntity log);
    void delete(LogEntity log);
	List<LogEntity> findByProductId(long id);
}