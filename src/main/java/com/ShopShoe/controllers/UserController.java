package com.ShopShoe.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.common.ERole;
import com.ShopShoe.dto.MessageResponseDto;
import com.ShopShoe.dto.SignupRequestDto;
import com.ShopShoe.dto.UserDTO;
import com.ShopShoe.entity.RoleEntity;
import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.service.RoleService;
import com.ShopShoe.service.UserService;


@RestController
@RequestMapping("user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	AuthController authController;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserDTO> getAllUser() {
		try {
			return (List<UserDTO>) userService.findAll();
		} catch (Exception e) {
			logger.error("Error", e);
		}
		return null;	
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public UserDTO getUserById(@PathVariable(value = "id") Long id) {
		return userService.getUserDTOById(id);
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> crateUser(@Validated @RequestBody SignupRequestDto signupRequest){
		return authController.registerUser(signupRequest);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteUser(@PathVariable long id) {
		try {
			UserEntity user = userService.getById(id);
			
			userService.delete(user);
			return "Delete user successful";			
		} catch (Exception e) {
			return "Error";
		}	
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long id,@Valid @RequestBody SignupRequestDto signupRequest) {
		try {
			UserEntity user = userService.findId(id);
			if(!user.getUsername().equals(signupRequest.getUsername())) {
				if(userService.existsByUsername(signupRequest.getUsername())) {
					return ResponseEntity
							.badRequest()
							.body(new MessageResponseDto("Error: Username is already taklen!"));
					
				}
			}
			if(!user.getEmail().equals(signupRequest.getEmail())) {
				if(userService.existsByEmail(signupRequest.getEmail()) ) {
					return ResponseEntity
							.badRequest()
							.body(new MessageResponseDto("Error: Email is already in use!"));
				}
			}
			user.setName(signupRequest.getName());
			user.setAddress(signupRequest.getAddress());
			user.setPhone_number(signupRequest.getPhone_number());
			user.setUsername(signupRequest.getUsername());
			user.setEmail(signupRequest.getEmail());
			user.setPassword(encoder.encode(signupRequest.getPassword()));
			user.setScores(signupRequest.getScores());
			
			Set<String> strRole = signupRequest.getRole();
			Set<RoleEntity> roles = new HashSet<>();
			
			if(strRole == null) {
				RoleEntity customerRole = roleService.findByName(ERole.ROLE_CUSTOMER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(customerRole);
			}else {
				strRole.forEach(role ->{
					switch (role) {
					case "admin":
							RoleEntity adminRole = roleService.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(adminRole);
						break;
					
					case "salesman":
						RoleEntity saleRole = roleService.findByName(ERole.ROLE_SALESMAN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(saleRole);
						break;
						
					default:
						RoleEntity customerRole = roleService.findByName(ERole.ROLE_CUSTOMER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(customerRole);
					}
				});
			}
			
			user.setRoles(roles);
			
			userService.save(user);
			return ResponseEntity.ok(new MessageResponseDto("User update successfully!"));
		}catch (Exception e) {
			return ResponseEntity.ok(new MessageResponseDto("User update fail!"));
		}
	}
	
	@GetMapping("/search")
	public List<UserDTO> search(@RequestParam("key") String key,@RequestParam("value") String value){
		return userService.searchKeyValue(key, value);
	}
}
