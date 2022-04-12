package com.ShopShoe.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.entity.RoleEntity;
import com.ShopShoe.service.RoleService;

@RestController
@RequestMapping("role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping()
	public List<RoleEntity> getAllRole() {
		return (List<RoleEntity>) roleService.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<RoleEntity> getRoleById(@PathVariable(value = "id") Integer id) {
		return roleService.findById(id);
	}
}
