package com.ShopShoe.service.Implements;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopShoe.dto.LogDTO;
import com.ShopShoe.entity.LogEntity;
import com.ShopShoe.repository.LogRepository;
import com.ShopShoe.service.LogService;


@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
    private LogRepository logRepository;
	
    @Override
    public List<LogDTO> findAll() {
    	List<LogDTO> listDTO = new ArrayList<LogDTO>();
    	List<LogEntity> listLogEntity = logRepository.findAll();
    	for(int i=0 ; i<listLogEntity.size() ; i++) {
    		LogDTO logdto = new LogDTO();
			logdto.setId(listLogEntity.get(i).getId());
			logdto.setName_method(listLogEntity.get(i).getName_method());
			logdto.setContent(listLogEntity.get(i).getContent());
			logdto.setIdProduct(listLogEntity.get(i).getProduct().getId());
			logdto.setIdUser(listLogEntity.get(i).getUser().getId());
			logdto.setNameUser(listLogEntity.get(i).getUser().getName());
			logdto.setAction_Date(listLogEntity.get(i).getAction_Date());
			listDTO.add(logdto);
    	}
    	return listDTO;
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