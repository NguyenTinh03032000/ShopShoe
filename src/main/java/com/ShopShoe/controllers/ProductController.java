package com.ShopShoe.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.entity.ProductEntity;
import com.ShopShoe.service.ProductService;

@RestController
@RequestMapping("product")
@PreAuthorize("hasRole('ADMIN') or hasRole('SALESMAN')")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping()
	public List<ProductEntity> getProduct() {
		return (List<ProductEntity>) productService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<ProductEntity> getProductById(@PathVariable(value = "id") Long id) {
		return productService.findById(id);
	}

	@PostMapping()
	public String createProduct(@RequestBody ProductEntity product) {
		try {
			if(productService.existsByName(product.getName())){
				return "Product already exist";
			}else {
				productService.save(product);
				return "Add product successful";
			}
		} catch (Exception e) {
			return "Error";
		}
	}

	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable Long id){
		try {
			ProductEntity product = productService.getById(id);

			productService.delete(product);
			return "Delete product successful";
		} catch (Exception e) {
			return "Error";
		}
	}

	@PutMapping(value ="/{id}")
	public ProductEntity updateProduct(@PathVariable(value = "id") Long id,@Valid @RequestBody ProductEntity productDetails) {
		try {
			return productService.findById(id)
					.map(product ->{
						product.setName(productDetails.getName());
						product.setPrice(productDetails.getPrice());
						product.setDescription(productDetails.getDescription());
						product.setBrand(productDetails.getBrand());
						product.setCategory(productDetails.getCategory());
						return productService.save(product);
					})
					.orElseGet(()->{
						productDetails.setId(id);
						return productService.save(productDetails);
					});
		} catch (Exception e) {
		}
		return null;
	}
}
