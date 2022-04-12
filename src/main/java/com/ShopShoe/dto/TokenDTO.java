package com.ShopShoe.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TokenDTO {

	private Long id;

	private Long id_user;
	
	private String token;
	
	private Date time_expired;
	
	private Date create_date;
	
	private Date update_date;

	public TokenDTO(Long id_user, String token, Date time_expired, Date createDate, Date updateDate) {
		super();
		this.id_user = id_user;
		this.token = token;
		this.time_expired = time_expired;
		this.create_date = createDate;
		this.update_date = updateDate;
	}
	
	
}
