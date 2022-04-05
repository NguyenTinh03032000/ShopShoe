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
import com.ShopShoe.repository.ProductRepository;

@RestController
@RequestMapping("product")
@PreAuthorize("hasRole('ADMIN') or hasRole('SALESMAN')")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;

	//get all product
	@GetMapping()
	public List<ProductEntity> getProduct() {
		return (List<ProductEntity>) productRepository.findAll();
	}

	//get product by id
	@GetMapping("/{id}")
	public Optional<ProductEntity> getProductById(@PathVariable(value = "id") Long id) {
		return productRepository.findById(id);
	}

	@PostMapping()
	public String createProduct(@RequestBody ProductEntity product) {
		try {
			if(productRepository.existsByName(product.getName())){
				return "Product already exist";
			}else {
				productRepository.save(product);
				return "Add product successful";
			}
		} catch (Exception e) {
			return "Error";
		}
	}

	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable Long id){
		try {
			ProductEntity product = productRepository.getById(id);

			productRepository.delete(product);
			return "Delete product successful";
		} catch (Exception e) {
			return "Error";
		}
	}

	@PutMapping(value ="/{id}")
	public ProductEntity updateProduct(@PathVariable(value = "id") Long id,@Valid @RequestBody ProductEntity productDetails) {
		try {
			return productRepository.findById(id)
					.map(product ->{
						product.setName(productDetails.getName());
						product.setPrice(productDetails.getPrice());
						product.setDescription(productDetails.getDescription());
						product.setBrand(productDetails.getBrand());
						product.setCategory(productDetails.getCategory());
						return productRepository.save(product);
					})
					.orElseGet(()->{
						productDetails.setId(id);
						return productRepository.save(productDetails);
					});
		} catch (Exception e) {
		}
		return null;
	}
}
