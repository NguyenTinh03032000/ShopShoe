package com.ShopShoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopShoe.entity.CartEntity;
import com.ShopShoe.entity.UserEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long>{
	
	CartEntity findByUser(UserEntity user);
	
}
