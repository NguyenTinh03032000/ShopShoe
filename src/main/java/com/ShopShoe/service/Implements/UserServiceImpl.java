package com.ShopShoe.service.Implements;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.Mapper.UserMapper;
import com.ShopShoe.dto.UserDTO;
import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.repository.UserRepository;
import com.ShopShoe.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private UserMapper userMapper;
	
    @Override
    public List<UserDTO> findAll() {
        return  (List<UserDTO>) userRepository.findAllByOrderByUsernameAsc().stream().
        		map(userMapper :: userEntityToUserDTO).collect(Collectors.toList());
    }
    @Override
    public List<UserEntity> search(String name) {
        return userRepository.findByNameContaining(name);
    }
    @Override
    public UserEntity getById(long id) {
        return userRepository.findId(id);
    }
    @Override
    public UserDTO getUserDTOById(long id) {
        return userMapper.userEntityToUserDTO(userRepository.findId(id)) ;
    }
    @Override
    public Optional<UserEntity> findById(long id) {
        return userRepository.findById(id);
    }
    @Override
    public UserEntity save(UserEntity u) {
    	return userRepository.save(u);
    }
    @Override
    public void delete(UserEntity u) {
    	userRepository.delete(u);
    }
    @Override
    public UserEntity findId(long id) {
    	return userRepository.findId(id);
    }
    @Override
    public Boolean existsByUsername(String username) {
    	return userRepository.existsByUsername(username);
    }
    @Override
    public Boolean existsByEmail(String email) {
    	return userRepository.existsByEmail(email);
    }
    @Override
    public List<UserDTO> searchKeyValue(String key,String value) {
    	if(key.equals("name")) {
    		return userMapper.listUserEntityToUserDTO(userRepository.findByNameContaining(value));
    	}else if(key.equals("address")){
    		return userMapper.listUserEntityToUserDTO(userRepository.findByAddressContaining(value));
		}else if(key.equals("email")){
    		return userMapper.listUserEntityToUserDTO(userRepository.findByEmailContaining(value));
		}else if(key.equals("phone")){
    		return userMapper.listUserEntityToUserDTO(userRepository.findByPhoneContaining(value));
		}
    	return null;
    }
}