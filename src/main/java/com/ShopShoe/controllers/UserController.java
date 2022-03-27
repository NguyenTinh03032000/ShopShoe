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
import com.ShopShoe.dto.MessageResponse;
import com.ShopShoe.dto.SignupRequest;
import com.ShopShoe.entity.RoleEntity;
import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.repository.RoleRepository;
import com.ShopShoe.repository.UserRepository;
import com.ShopShoe.service.UserService;


@RestController
@RequestMapping("user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	AuthController authController;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserEntity> getAllUser() {
		logger.info("===Information employee===");
		return (List<UserEntity>) userService.findAll();
	}
	
	@GetMapping("/{id}")
	public UserEntity getUserById(@PathVariable(value = "id") Long id) {
		return userService.findOne(id);
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> crateUser(@Validated @RequestBody SignupRequest signupRequest){
		return authController.registerUser(signupRequest);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteUser(@PathVariable long id) {
		try {
			UserEntity user = userService.findOne(id);
			
			userService.delete(user);
			return "Delete user successful";			
		} catch (Exception e) {
			return "Error";
		}	
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long id,@Valid @RequestBody SignupRequest signupRequest) {
		try {
			UserEntity user = userService.findOne(id);
			if(!user.getUsername().equals(signupRequest.getUsername())) {
				if(userRepository.existsByUsername(signupRequest.getUsername())) {
					return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Error: Username is already taklen!"));
					
				}
			}
			if(!user.getEmail().equals(signupRequest.getEmail())) {
				if(userRepository.existsByEmail(signupRequest.getEmail()) ) {
					return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Error: Email is already in use!"));
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
				RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(customerRole);
			}else {
				strRole.forEach(role ->{
					switch (role) {
					case "admin":
							RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(adminRole);
						break;
					
					case "salesman":
						RoleEntity saleRole = roleRepository.findByName(ERole.ROLE_SALESMAN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(saleRole);
						break;
						
					default:
						RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(customerRole);
					}
				});
			}
			
			user.setRoles(roles);
			
			userService.save(user);
			return ResponseEntity.ok(new MessageResponse("User update successfully!"));
		}catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse("User updae fail!"));
		}
	}
}
