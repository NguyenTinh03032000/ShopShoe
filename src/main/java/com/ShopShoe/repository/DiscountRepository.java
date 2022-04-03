package com.ShopShoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopShoe.entity.DiscountEntity;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long>{

}
