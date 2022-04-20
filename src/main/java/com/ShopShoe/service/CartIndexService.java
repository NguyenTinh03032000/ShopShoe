package com.ShopShoe.service;

import java.util.List;

import com.ShopShoe.dto.CartIndexDTO;
import com.ShopShoe.entity.CartEntity;
import com.ShopShoe.entity.CartIndexEntity;
import com.ShopShoe.entity.ProductEntity;

public interface CartIndexService {
	Iterable<CartIndexEntity> findAll();
    CartIndexEntity findOne(long id);
    CartIndexEntity save(CartIndexEntity u);
    void delete(CartIndexEntity u);
	CartIndexEntity findByProductAndCart(ProductEntity product, CartEntity cart);
	List<CartIndexDTO> findByCart(CartEntity cart);
}