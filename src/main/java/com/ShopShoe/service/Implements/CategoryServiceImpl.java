package com.ShopShoe.service.Implements;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.Mapper.CategoryMapper;
import com.ShopShoe.dto.CategoryDTO;
import com.ShopShoe.entity.CategoryEntity;
import com.ShopShoe.repository.CategoryRepository;
import com.ShopShoe.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().
        		map(categoryMapper :: categoryToCategoryDTO).collect(Collectors.toList());
    }
    @Override
    public Optional<CategoryEntity> findById(long id) {
        return categoryRepository.findById(id);
    }
    @Override
    public CategoryEntity getById(long id) {
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