package com.ShopShoe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
	
	private Long id;
	
	private String name;
	
	private String address;
	
	private String phone_number;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private long scores;
	
//	@JsonProperty("roles")
//	private Set<RoleDTO> roles = new HashSet<>();

	public UserDTO(String name,String address,String phone_number,String username, String email, String password,long scores) {
		this.name = name;
		this.address = address;
		this.phone_number = phone_number;
		this.username = username;
		this.email = email;
		this.password = password;
		this.scores = scores;
	}
}
