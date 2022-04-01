package com.ShopShoe.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "log")
public class LogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name_action;
	private String name_method;
	private String content;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="id_product")
	private ProductEntity product;
	
	private Date actionDate;

}
