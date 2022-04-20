package com.ShopShoe.service;

import java.util.List;
import java.util.Optional;

import com.ShopShoe.dto.UserDTO;
import com.ShopShoe.entity.UserEntity;


public interface UserService {
	List<UserDTO> findAll();
	List<UserDTO> searchKeyValue(String key,String value);
    List<UserEntity> search(String u);
    UserDTO getUserDTOById(long id);
    UserEntity getById(long id);
    void save(UserEntity u);
    void delete(UserEntity u);
    UserEntity findId(long id);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Optional<UserEntity> findById(long id);
}