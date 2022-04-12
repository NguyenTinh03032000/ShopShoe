package com.ShopShoe.service;

import com.ShopShoe.entity.CartEntity;
import com.ShopShoe.entity.UserEntity;


public interface CartService {
	Iterable<CartEntity> findAll();
    CartEntity findOne(long id);
    CartEntity save(CartEntity cart);
    void delete(CartEntity cart);
	CartEntity findByUser(UserEntity u);
}