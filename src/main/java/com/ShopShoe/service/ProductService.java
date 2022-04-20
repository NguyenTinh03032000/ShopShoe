package com.ShopShoe.service;

import java.util.Optional;

import com.ShopShoe.dto.ProductDTO;
import com.ShopShoe.entity.ProductEntity;


public interface ProductService {
	Iterable<ProductDTO> findAll();
	Optional<ProductEntity> findById(long id);
	ProductEntity getById(long id);
    ProductEntity save(ProductEntity u);
    void delete(ProductEntity u);
    Boolean existsByName(String name);
}