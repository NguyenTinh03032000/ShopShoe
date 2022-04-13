package com.ShopShoe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ShopShoe.entity.LogEntity;
@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long>{
	@Query(value = "select * from log where log.id_product = ?1 ", nativeQuery = true)
	List<LogEntity> findByProductId(long id);
}
