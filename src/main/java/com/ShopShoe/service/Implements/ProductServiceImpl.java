package com.ShopShoe.service.Implements;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.entity.ProductEntity;
import com.ShopShoe.repository.ProductRepository;
import com.ShopShoe.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
    private ProductRepository productRepository;
	
    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }
    @Override
    public Optional<ProductEntity> findById(long id) {
        return productRepository.findById(id);
    }
    @Override
    public ProductEntity getById(long id) {
        return productRepository.getById(id);
    }
    @Override
    public ProductEntity save(ProductEntity product) {
    	return productRepository.save(product);
    }
    @Override
    public void delete(ProductEntity cart) {
    	productRepository.delete(cart);
    }
    @Override
    public Boolean existsByName(String name) {
    	return productRepository.existsByName(name);
    }
}