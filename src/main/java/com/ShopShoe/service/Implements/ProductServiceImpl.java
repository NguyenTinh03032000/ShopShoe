package com.ShopShoe.service.Implements;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.Mapper.ProductMapper;
import com.ShopShoe.dto.ProductDTO;
import com.ShopShoe.entity.ProductEntity;
import com.ShopShoe.repository.ProductRepository;
import com.ShopShoe.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
    private ProductMapper productMapper;
	
    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().
        		map(productMapper :: productEntityToProductDTO).collect(Collectors.toList());
    }
    @Override
    public Optional<ProductEntity> findById(long id) {
        return productRepository.findById(id);
    }
    @Override
    public ProductEntity getById(long id) {
        return productRepository.getId(id);
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