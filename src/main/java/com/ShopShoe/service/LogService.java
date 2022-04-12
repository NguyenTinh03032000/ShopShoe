package com.ShopShoe.service;

import java.util.List;

import com.ShopShoe.entity.LogEntity;


public interface LogService {
	Iterable<LogEntity> findAll();
    List<LogEntity> search(String u);
    LogEntity findOne(long id);
    void save(LogEntity u);
    void delete(LogEntity u);
    LogEntity findId(long id);
}