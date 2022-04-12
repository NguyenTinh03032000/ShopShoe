package com.ShopShoe.dto;

import java.util.Set;

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
	private String username;
	private String email;
	private String password;
	private long scores;
	private Set<String> role;
}
