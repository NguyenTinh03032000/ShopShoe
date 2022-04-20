package com.ShopShoe.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.dto.LogDTO;
import com.ShopShoe.entity.LogEntity;
import com.ShopShoe.service.LogService;


@RestController
@RequestMapping("log")
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@GetMapping()
	public List<LogDTO> getAllLogProduct() {
		return (List<LogDTO>) logService.findAll();
	}
	/**
	 * 
	 * @param idProduct
	 * @return Log product
	 */
	@GetMapping("/{idProduct}")
	public List<LogDTO> getLogProductByUser(@PathVariable long idProduct){
		try {
			List<LogEntity> listLogEntity = logService.findByProductId(idProduct);
			List<LogDTO> listLogDto = new ArrayList<LogDTO>();
			for(int i=0; i< listLogEntity.size() ; i++) {
				
				LogDTO logdto = new LogDTO();
				logdto.setId(listLogEntity.get(i).getId());
				logdto.setName_method(listLogEntity.get(i).getName_method());
				logdto.setContent(listLogEntity.get(i).getContent());
				logdto.setIdProduct(listLogEntity.get(i).getProduct().getId());
				logdto.setIdUser(listLogEntity.get(i).getUser().getId());
				logdto.setNameUser(listLogEntity.get(i).getUser().getName());
				logdto.setAction_Date(listLogEntity.get(i).getAction_Date());
				listLogDto.add(logdto);
			}			
			return listLogDto;			
		} catch (Exception e) {
			return null;
		}	
	}
	
	@PostMapping()
	public String createLog(@RequestBody LogEntity log) {
		try {
			logService.save(log);
			return "Add successful";
		} catch (Exception e) {
			return "Error";
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteLogProduct(@PathVariable long id){
		try {
			LogEntity log = logService.getById(id);
			
			logService.delete(log);
			return "Delete successful";			
		} catch (Exception e) {
			return "Error";
		}	
	}
}
