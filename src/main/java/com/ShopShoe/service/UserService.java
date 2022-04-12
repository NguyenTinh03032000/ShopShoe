package com.ShopShoe.service;

import java.util.List;
import java.util.Optional;

import com.ShopShoe.entity.UserEntity;


public interface UserService {
	Iterable<UserEntity> findAll();
    List<UserEntity> search(String u);
    UserEntity getById(long id);
    void save(UserEntity u);
    void delete(UserEntity u);
    UserEntity findId(long id);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Optional<UserEntity> findById(long id);
}