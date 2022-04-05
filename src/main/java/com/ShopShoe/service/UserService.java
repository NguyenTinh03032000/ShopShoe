package com.ShopShoe.service;

import java.util.List;

import com.ShopShoe.entity.UserEntity;


public interface UserService {
	Iterable<UserEntity> findAll();
    List<UserEntity> search(String u);
    UserEntity findOne(long id);
    void save(UserEntity u);
    void delete(UserEntity u);
    UserEntity findId(long id);
}