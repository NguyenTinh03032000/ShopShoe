package com.ShopShoe.dto;

import java.util.List;

import lombok.Data;

@Data

public class JwtResponseDto {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;

	public JwtResponseDto(String accessToken, Long id, String username, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}