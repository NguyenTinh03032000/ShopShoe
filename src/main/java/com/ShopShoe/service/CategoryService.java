package com.ShopShoe.service;

import java.util.List;

import com.ShopShoe.entity.CategoryEntity;


public interface CategoryService {
	Iterable<CategoryEntity> findAll();
    CategoryEntity findOne(long id);
    CategoryEntity save(CategoryEntity u);
    void delete(CategoryEntity u);
	List<CategoryEntity> findByName(String name);
}