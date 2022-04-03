package com.ShopShoe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopShoe.entity.CartEntity;
import com.ShopShoe.entity.CartIndexEntity;
import com.ShopShoe.entity.ProductEntity;

@Repository
public interface CartIndexRepository extends JpaRepository<CartIndexEntity, Long>{

	CartIndexEntity findByProductAndCart(ProductEntity product, CartEntity cart);
	
	List<CartIndexEntity> findByCart(CartEntity cart);
	
}
