package com.ShopShoe.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.dto.MessageResponseDto;
import com.ShopShoe.dto.ProductDTO;
import com.ShopShoe.entity.LogEntity;
import com.ShopShoe.entity.ProductEntity;
import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.service.LogService;
import com.ShopShoe.service.ProductService;
import com.ShopShoe.service.UserService;
import com.ShopShoe.service.Implements.UserDetailsImpl;

@RestController
@RequestMapping("product")
@PreAuthorize("hasRole('ADMIN') or hasRole('SALESMAN')")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private LogService logService;

	@Autowired
	private UserService userService;
	
	@GetMapping()
	public List<ProductDTO> getProduct() {
		return (List<ProductDTO>) productService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<ProductEntity> getProductById(@PathVariable(value = "id") Long id) {
		return productService.findById(id);
	}
	
	/**
	 * get User Current
	 */
	public UserEntity getUserCurrent() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl u = (UserDetailsImpl) authentication.getPrincipal();
		UserEntity currentUser = userService.findId(u.getId());
		return currentUser;
	}

	@PostMapping()
	public String createProduct(@RequestBody ProductEntity product) {
		try {
			if(productService.existsByName(product.getName())){
				return "Product already exist";
			}else {
				productService.save(product);
				LogEntity logEntity = new LogEntity();
				logEntity.setName_action("Add new product");
				logEntity.setName_method("POST");
				logEntity.setContent("Add product: "+product.getName());
				logEntity.setUser(getUserCurrent());
				logEntity.setProduct(product);
				Date createDate = new Date((new Date()).getTime());
				logEntity.setAction_Date(createDate);
				logService.save(logEntity);
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

	/**
	 * @Content update product
	 * @param id
	 * @param productDetails
	 * @return product
	 */
	@PutMapping(value ="/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable(value = "id") Long id,@Valid @RequestBody ProductEntity productDetails) {
		try {
			ProductEntity product = productService.getById(id);
			if(product != null) {
				product.setName(productDetails.getName());
				product.setPrice(productDetails.getPrice());
				product.setDescription(productDetails.getDescription());
				product.setBrand(productDetails.getBrand());
				product.setCategory(productDetails.getCategory());
				
				LogEntity logEntity = new LogEntity();
				logEntity.setName_action("Update product product");
				logEntity.setName_method("PUT");
				logEntity.setContent("Update product: "+product.getName());
				logEntity.setUser(getUserCurrent());
				logEntity.setProduct(product);
				Date Date = new Date((new Date()).getTime());
				logEntity.setAction_Date(Date);
				logService.save(logEntity);
				
				return ResponseEntity.ok(productService.save(product));
			}else {
				return ResponseEntity.ok(new MessageResponseDto("Update object not found"));
			}
			
			
			
//			return productService.findById(id)
//					.map(product ->{
//						product.setName(productDetails.getName());
//						product.setPrice(productDetails.getPrice());
//						product.setDescription(productDetails.getDescription());
//						product.setBrand(productDetails.getBrand());
//						product.setCategory(productDetails.getCategory());
//						return productService.save(product);
//					})
//					.orElseGet(()->{
//						productDetails.setId(id);
//						return productService.save(productDetails);
//					});
		} catch (Exception e) {
		}
		return null;
	}
}
