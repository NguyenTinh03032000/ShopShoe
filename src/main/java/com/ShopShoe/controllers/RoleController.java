package com.ShopShoe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.dto.RoleDTO;
import com.ShopShoe.service.RoleService;

@RestController
@RequestMapping("role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping()
	public List<RoleDTO> getAllRole() {
		return (List<RoleDTO>) roleService.findAll();
	}
	
	@GetMapping("/{id}")
	public RoleDTO getRoleById(@PathVariable(value = "id") Integer id) {
		return roleService.getById(id);
	}
}
