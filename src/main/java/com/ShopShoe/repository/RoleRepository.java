package com.ShopShoe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ShopShoe.common.ERole;
import com.ShopShoe.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{
	Optional<RoleEntity> findByName(ERole name);
	
	Boolean existsByName(ERole name);
	
	@Query(value = "select * from roles where roles.id = ?1 ", nativeQuery = true)
	RoleEntity findId(long id);
}
