package com.ShopShoe.dto;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LogDTO {

	private long id;
	private String name_method;
	private String content;
	private long idUser;
	private String nameUser;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+7")
	private Date  actionDate;

}
