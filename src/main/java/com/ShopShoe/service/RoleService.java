package com.ShopShoe.service;

import java.util.Optional;

import com.ShopShoe.common.ERole;
import com.ShopShoe.entity.RoleEntity;



public interface RoleService {
	Iterable<RoleEntity> findAll();
    RoleEntity getById(Integer id);
    void save(RoleEntity role);
    void delete(RoleEntity role);
	Optional<RoleEntity> findByName(ERole name);
	Optional<RoleEntity> findById(Integer id);
}