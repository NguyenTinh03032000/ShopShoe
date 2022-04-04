package com.ShopShoe.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.entity.CategoryEntity;
import com.ShopShoe.repository.CategoryRepository;


@RestController
@RequestMapping("category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping()
	public List<CategoryEntity> getAllCategory() {
		return (List<CategoryEntity>) categoryRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<CategoryEntity> getCategoryById(@PathVariable(value = "id") Long id) {
		return categoryRepository.findById(id);
	}
	
	@PostMapping()
	public String createCategory(@RequestBody CategoryEntity category) {
		try {
			categoryRepository.save(category);
			return "Add successful";
		} catch (Exception e) {
			return "Error";
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable long id){
		try {
			CategoryEntity category = categoryRepository.getById(id);
			
			categoryRepository.delete(category);
			return "Delete successful";			
		} catch (Exception e) {
			return "Error";
		}	
	}
}
