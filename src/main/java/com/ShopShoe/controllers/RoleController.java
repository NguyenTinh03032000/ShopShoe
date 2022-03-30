package com.ShopShoe.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.entity.RoleEntity;
import com.ShopShoe.repository.RoleRepository;


@RestController
@RequestMapping("role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
	private static Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	//get all role
	@GetMapping()
	public List<RoleEntity> getAllRole() {
		logger.info("===Information Role===");
		return (List<RoleEntity>) roleRepository.findAll();
	}
	
	//get role by id
	@GetMapping("/{id}")
	public RoleEntity getRoleById(@PathVariable(value = "id") Integer id) {
		return roleRepository.getById(id);
	}
	
	@PostMapping()
	public String createRole(@RequestBody RoleEntity role) {
		try {
			if(roleRepository.existsByName(role.getName())){
				return "Role already exist";
			}else {
				roleRepository.save(role);
				return "Add role successful";
			}
		} catch (Exception e) {
			return "Error";
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteRole(@PathVariable Integer id){
		try {
			RoleEntity role = roleRepository.getById(id);
			
			roleRepository.delete(role);
			return "Delete role successful";			
		} catch (Exception e) {
			return "Error";
		}	
	}
	
	@PutMapping("/{id}")
	public String updateRole(@PathVariable(value = "id") Integer id,@Valid @RequestBody RoleEntity roleDetails) {
		try {
			RoleEntity role = roleRepository.getById(id);
			role.setName(roleDetails.getName());
			
			roleRepository.save(role);
			return "Update role successfuly";
		}catch (Exception e) {
			return "Error";
		}
	}
}
