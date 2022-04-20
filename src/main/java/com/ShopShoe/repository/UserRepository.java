package com.ShopShoe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ShopShoe.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	Optional<UserEntity> findByUsername(String username);
	
	@Query(value = "select * from users where users.name like %?1%", nativeQuery = true)
	List<UserEntity> findByNameContaining(String q);
	
	@Query(value = "select * from users where users.address like %?1%", nativeQuery = true)
	List<UserEntity> findByAddressContaining(String q);
	
	@Query(value = "select * from users where users.email like %?1%", nativeQuery = true)
	List<UserEntity> findByEmailContaining(String q);
	
	@Query(value = "select * from users where users.phone_number like %?1%", nativeQuery = true)
	List<UserEntity> findByPhoneContaining(String q);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	@Query(value = "select * from users where users.id = ?1 ", nativeQuery = true)
	UserEntity findId(long id);
	
	List<UserEntity> findAllByOrderByUsernameAsc();
	
}
