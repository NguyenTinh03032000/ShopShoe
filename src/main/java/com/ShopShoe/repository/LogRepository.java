package com.ShopShoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopShoe.entity.LogEntity;
@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long>{

}
