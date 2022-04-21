package com.ShopShoe.service.Implements;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.Mapper.RoleMapper;
import com.ShopShoe.common.ERole;
import com.ShopShoe.dto.RoleDTO;
import com.ShopShoe.entity.RoleEntity;
import com.ShopShoe.repository.RoleRepository;
import com.ShopShoe.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
	private RoleMapper roleMapper;
	
    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream().
        		map(roleMapper :: roleEntityToRoleDTO).collect(Collectors.toList());
    }
    @Override
    public RoleDTO getById(Integer id) {
        return roleMapper.roleEntityToRoleDTO(roleRepository.findId(id));
    }
    @Override
    public Optional<RoleEntity> findById(Integer id) {
        return roleRepository.findById(id);
    }
    @Override
    public RoleEntity save(RoleEntity u) {
    	return roleRepository.save(u);
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