package com.ShopShoe.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ShopShoe.Mapper.BaseMapper;

public abstract class BaseService<T> implements IService<T>{
	
	@Autowired
	protected BaseMapper<T> mapper;
	
	@Override
	public void delete(T entity) {};
	
	@Override
	public T save(T entity) {
		return entity;
	};
	
	
}