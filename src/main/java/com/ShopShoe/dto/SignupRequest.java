package com.ShopShoe.dto;

import java.util.Set;

import lombok.Data;
@Data
public class SignupRequest {
	private String name;
	private String address;
	private String phone_number;
	private String username;
	private String email;
	private String password;
	private long scores;
	private Set<String> role;
}
