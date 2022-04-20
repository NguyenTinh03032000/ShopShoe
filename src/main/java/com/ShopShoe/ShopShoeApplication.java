package com.ShopShoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.ShopShoe.entity.DocumentEntity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
@EnableConfigurationProperties(DocumentEntity.class)
public class ShopShoeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopShoeApplication.class, args);
	}
}
