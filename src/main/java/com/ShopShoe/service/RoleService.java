package com.ShopShoe.service;

import java.util.Optional;

import com.ShopShoe.common.ERole;
import com.ShopShoe.dto.RoleDTO;
import com.ShopShoe.entity.RoleEntity;



public interface RoleService {
	Iterable<RoleDTO> findAll();
    RoleDTO getById(Integer id);
    RoleEntity save(RoleEntity role);
    void delete(RoleEntity role);
	Optional<RoleEntity> findByName(ERole name);
	Optional<RoleEntity> findById(Integer id);
}