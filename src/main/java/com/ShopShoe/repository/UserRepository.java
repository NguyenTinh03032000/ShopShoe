package com.ShopShoe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopShoe.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	Optional<UserEntity> findByUsername(String username);
	
	List<UserEntity> findByNameContaining(String q);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}
