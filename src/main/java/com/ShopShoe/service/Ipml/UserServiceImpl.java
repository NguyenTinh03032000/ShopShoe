package com.ShopShoe.service.Ipml;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.repository.UserRepository;
import com.ShopShoe.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;
	
    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
    @Override
    public List<UserEntity> search(String name) {
        return userRepository.findByNameContaining(name);
    }
    @Override
    public UserEntity findOne(long id) {
        return userRepository.getById(id);
    }
    @Override
    public void save(UserEntity u) {
    	userRepository.save(u);
    }
    @Override
    public void delete(UserEntity u) {
    	userRepository.delete(u);
    }
    @Override
    public UserEntity findId(long id) {
    	return userRepository.findId(id);
    }
}