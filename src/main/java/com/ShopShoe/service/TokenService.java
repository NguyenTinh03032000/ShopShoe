package com.ShopShoe.service;

import com.ShopShoe.entity.TokenEntity;



public interface TokenService {
	Iterable<TokenEntity> findAll();
    TokenEntity findOne(long id);
    void save(TokenEntity u);
    void delete(TokenEntity u);
    TokenEntity findId(long id);
    TokenEntity findByUserId(long id);
}