package com.ShopShoe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.entity.CategoryEntity;
import com.ShopShoe.service.CategoryService;


@RestController
@RequestMapping("category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping()
	public List<CategoryEntity> getAllCategory() {
		return (List<CategoryEntity>) categoryService.findAll();
	}
	
	@GetMapping("/{id}")
	public CategoryEntity getCategoryById(@PathVariable(value = "id") Long id) {
		return categoryService.findOne(id);
	}
	
	@PostMapping()
	public String createCategory(@RequestBody CategoryEntity category) {
		try {
			categoryService.save(category);
			return "Add successful";
		} catch (Exception e) {
			return "Error";
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable long id){
		try {
			CategoryEntity category = categoryService.findOne(id);
			
			categoryService.delete(category);
			return "Delete successful";			
		} catch (Exception e) {
			return "Error";
		}	
	}
}
