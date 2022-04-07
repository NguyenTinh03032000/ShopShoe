package com.ShopShoe.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_token")
public class TokenEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name = "id_user")
	private Long id_user;
	
	private String token;
	
	private Date time_expired;
	
	private Date create_date;
	
	private Date update_date;

	public TokenEntity(Long id_user, String token, Date time_expired, Date createDate, Date updateDate) {
		super();
		this.id_user = id_user;
		this.token = token;
		this.time_expired = time_expired;
		this.create_date = createDate;
		this.update_date = updateDate;
	}
	
	
}
