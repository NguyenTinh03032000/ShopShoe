package com.ShopShoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ShopShoe.entity.TokenEntity;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long>{
	
	Boolean existsByToken(String token);
	
	@Query(value = "select * from user_token where user_token.id_user = ?1", nativeQuery = true)
	TokenEntity findByUserId(Long id);
	
	@Query(value = "select * from user_token where user_token.id = ?1", nativeQuery = true)
	TokenEntity findId(Long id);
}
