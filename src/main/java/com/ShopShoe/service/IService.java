package com.ShopShoe.service;

public interface IService<T> {
	
	public void delete(T entity);
	
	public T save(T entity);
}
