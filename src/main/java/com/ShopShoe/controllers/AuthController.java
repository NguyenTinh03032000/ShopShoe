package com.ShopShoe.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.common.ERole;
import com.ShopShoe.common.JwtUtils;
import com.ShopShoe.dto.JwtResponseDto;
import com.ShopShoe.dto.LoginRequestDto;
import com.ShopShoe.dto.MessageResponseDto;
import com.ShopShoe.dto.SignupRequestDto;
import com.ShopShoe.entity.RoleEntity;
import com.ShopShoe.entity.TokenEntity;
import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.repository.RoleRepository;
import com.ShopShoe.repository.TokenRepository;
import com.ShopShoe.repository.UserRepository;
import com.ShopShoe.service.Ipml.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("account")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	//Signin for all
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequestDto loginRequest){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponseDto(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}
	
	@GetMapping("/logout")
	public String logoutUser(){
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetailsImpl u = (UserDetailsImpl) authentication.getPrincipal();
			UserEntity currentUser = userRepository.findId(u.getId());

			TokenEntity tokenEntity = tokenRepository.findByUserId(currentUser.getId());
			tokenEntity.setToken("");
			tokenEntity.setTime_expired(null);
			tokenEntity.setCreate_date(null);
			tokenEntity.setUpdate_date(null);
			tokenRepository.save(tokenEntity);
			return "Logout successfuly";
		} catch (Exception e) {
			return "Logout fail";
		}
	}
	
	//signup for admin
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/signupAdmin")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequestDto signupRequest){
		if(userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseDto("Error: Username is already taklen!"));
			
		}
		
		if(userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseDto("Error: Email is already in use!"));
		}
		
		UserEntity user = new UserEntity(
				signupRequest.getName(),
				signupRequest.getAddress(),
				signupRequest.getPhone_number(),
				signupRequest.getUsername(),
				signupRequest.getEmail(),
				encoder.encode(signupRequest.getPassword()),
				signupRequest.getScores()
				);
		
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
		userRepository.save(user);
		
		return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));
	}
	
	//signup for customer
	@PostMapping("/signupCustomer")
	public ResponseEntity<?> registerUserByCustomer(@Validated @RequestBody SignupRequestDto signupRequest){
		if(userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseDto("Error: Username is already taklen!"));

		}

		if(userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseDto("Error: Email is already in use!"));
		}

		UserEntity user = new UserEntity(
				signupRequest.getName(),
				signupRequest.getAddress(),
				signupRequest.getPhone_number(),
				signupRequest.getUsername(),
				signupRequest.getEmail(),
				encoder.encode(signupRequest.getPassword()),
				signupRequest.getScores()
		);

		Set<String> strRole = signupRequest.getRole();
		Set<RoleEntity> roles = new HashSet<>();

		if(strRole == null) {
			RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(customerRole);
		}else {
			strRole.forEach(role ->{
				switch (role) {

					default:
						RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(customerRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));
	}
}

