package com.ShopShoe.service.Implements;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.Mapper.CartIndexMapper;
import com.ShopShoe.dto.CartIndexDTO;
import com.ShopShoe.entity.CartEntity;
import com.ShopShoe.entity.CartIndexEntity;
import com.ShopShoe.entity.ProductEntity;
import com.ShopShoe.repository.CartIndexRepository;
import com.ShopShoe.service.CartIndexService;


@Service
public class CartIndexServiceImpl implements CartIndexService {
	
	@Autowired
    private CartIndexRepository cartIndexRepository;
	
	@Autowired
    private CartIndexMapper cartIndexMapper;
	
    @Override
    public List<CartIndexEntity> findAll() {
        return cartIndexRepository.findAll();
    }
    @Override
    public CartIndexEntity findOne(long id) {
        return cartIndexRepository.getById(id);
    }
    @Override
    public CartIndexEntity save(CartIndexEntity cartIndex) {
    	return cartIndexRepository.save(cartIndex);
    }
    @Override
    public void delete(CartIndexEntity cartIndex) {
    	cartIndexRepository.delete(cartIndex);
    }
    @Override
    public CartIndexEntity findByProductAndCart(ProductEntity product,CartEntity cart) {
    	return cartIndexRepository.findByProductAndCart(product,cart);
    }
    @Override
    public List<CartIndexDTO> findByCart(CartEntity cart) {
    	return cartIndexRepository.findByCart(cart).stream().
    			map(cartIndexMapper :: cartIndexEntityToCartIndexDto).collect(Collectors.toList());
    }
}