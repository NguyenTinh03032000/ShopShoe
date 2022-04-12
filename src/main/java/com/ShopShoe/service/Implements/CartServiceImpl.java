package com.ShopShoe.service.Implements;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.entity.CartEntity;
import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.repository.CartRepository;
import com.ShopShoe.service.CartService;


@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
    private CartRepository cartRepository;
	
    @Override
    public List<CartEntity> findAll() {
        return cartRepository.findAll();
    }
    @Override
    public CartEntity findOne(long id) {
        return cartRepository.getById(id);
    }
    @Override
    public CartEntity save(CartEntity cart) {
    	return cartRepository.save(cart);
    }
    @Override
    public void delete(CartEntity cart) {
    	cartRepository.delete(cart);
    }
    @Override
    public CartEntity findByUser(UserEntity u) {
    	return cartRepository.findByUser(u);
    }
}