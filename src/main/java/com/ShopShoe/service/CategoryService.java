package com.ShopShoe.service;

import java.util.List;
import java.util.Optional;

import com.ShopShoe.dto.CategoryDTO;
import com.ShopShoe.entity.CategoryEntity;


public interface CategoryService {
	Iterable<CategoryDTO> findAll();
    Optional<CategoryEntity> findById(long id);
    CategoryEntity getById(long id);
    CategoryEntity save(CategoryEntity u);
    void delete(CategoryEntity u);
	List<CategoryEntity> findByName(String name);
}