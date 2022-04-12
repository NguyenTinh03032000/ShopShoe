package com.ShopShoe.service.Implements;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.entity.TokenEntity;
import com.ShopShoe.repository.TokenRepository;
import com.ShopShoe.service.TokenService;


@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
    private TokenRepository tokenRepository;
	
    @Override
    public List<TokenEntity> findAll() {
        return tokenRepository.findAll();
    }
    @Override
    public TokenEntity findOne(long id) {
        return tokenRepository.getById(id);
    }
    @Override
    public void save(TokenEntity u) {
    	tokenRepository.save(u);
    }
    @Override
    public void delete(TokenEntity u) {
    	tokenRepository.delete(u);
    }
    @Override
    public TokenEntity findId(long id) {
    	return tokenRepository.findId(id);
    }
    @Override
    public TokenEntity findByUserId(long id) {
        return tokenRepository.findByUserId(id);
    }
}