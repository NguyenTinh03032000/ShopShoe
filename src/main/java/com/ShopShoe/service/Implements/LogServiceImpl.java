package com.ShopShoe.service.Implements;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.entity.LogEntity;
import com.ShopShoe.repository.LogRepository;
import com.ShopShoe.service.LogService;


@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
    private LogRepository logRepository;
	
    @Override
    public List<LogEntity> findAll() {
        return logRepository.findAll();
    }
    @Override
    public Optional<LogEntity> findById(long id) {
        return logRepository.findById(id);
    }
    @Override
    public List<LogEntity> findByProductId(long id) {
        return logRepository.findByProductId(id);
    }
    @Override
    public LogEntity getById(long id) {
        return logRepository.getById(id);
    }
    @Override
    public LogEntity save(LogEntity log) {
    	return logRepository.save(log);
    }
    @Override
    public void delete(LogEntity log) {
    	logRepository.delete(log);
    }
}