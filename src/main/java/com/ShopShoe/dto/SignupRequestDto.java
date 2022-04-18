package com.ShopShoe.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
	private String name;
	
	private String address;
	
	private String phone_number;
	
	@NotEmpty(message = "Invalid username")
	private String username;
	
	@Email(message = "Invalid email")
	private String email;
	
	@NotEmpty(message = "Missing password")
	@Min(value = 8, message = "Password must be 8 characters or more")
	private String password;

	private long scores;
	
	private Set<String> role;
}
