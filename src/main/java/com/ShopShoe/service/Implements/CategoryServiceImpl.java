package com.ShopShoe.service.Implements;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.entity.CategoryEntity;
import com.ShopShoe.repository.CategoryRepository;
import com.ShopShoe.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryRepository categoryRepository;
	
    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public CategoryEntity findOne(long id) {
        return categoryRepository.getById(id);
    }
    @Override
    public CategoryEntity save(CategoryEntity category) {
    	return categoryRepository.save(category);
    }
    @Override
    public void delete(CategoryEntity category) {
    	categoryRepository.delete(category);
    }
    @Override
    public List<CategoryEntity> findByName(String name) {
    	return categoryRepository.findByName(name);
    }
}