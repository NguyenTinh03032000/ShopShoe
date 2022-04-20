package com.ShopShoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ShopShoe.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long>{
	boolean existsByName(String name);
	
	@Query(value = "select * from product where product.id = ?1 ", nativeQuery = true)
	ProductEntity getId(long id);
}
