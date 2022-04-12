package com.ShopShoe.service.Implements;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.common.ERole;
import com.ShopShoe.entity.RoleEntity;
import com.ShopShoe.repository.RoleRepository;
import com.ShopShoe.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
    private RoleRepository roleRepository;
	
    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }
    @Override
    public RoleEntity getById(Integer id) {
        return roleRepository.getById(id);
    }
    @Override
    public Optional<RoleEntity> findById(Integer id) {
        return roleRepository.findById(id);
    }
    @Override
    public void save(RoleEntity u) {
    	roleRepository.save(u);
    }
    @Override
    public void delete(RoleEntity u) {
    	roleRepository.delete(u);
    }
    @Override
    public Optional<RoleEntity> findByName(ERole name) {
    	return roleRepository.findByName(name);
    }
}