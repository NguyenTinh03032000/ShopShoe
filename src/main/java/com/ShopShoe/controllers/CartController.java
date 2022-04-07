package com.ShopShoe.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.entity.CartEntity;
import com.ShopShoe.entity.CartIndexEntity;
import com.ShopShoe.entity.ProductEntity;
import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.repository.CartIndexRepository;
import com.ShopShoe.repository.CartRepository;
import com.ShopShoe.repository.ProductRepository;
import com.ShopShoe.repository.UserRepository;
import com.ShopShoe.service.Ipml.UserDetailsImpl;

@RestController
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartIndexRepository cartIndexRepository;
	
	@GetMapping()
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public List<CartIndexEntity> getAllProductInCart() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl u = (UserDetailsImpl) authentication.getPrincipal();
		UserEntity currentUser = userRepository.getById(u.getId());
		
		CartEntity cart = cartRepository.findByUser(currentUser);
		if(cart != null) {
			List<CartIndexEntity> listIndex = cartIndexRepository.findByCart(cart);
			return listIndex;
		}
		return null;
	}
	
	//Add product in cart
	@PostMapping("/addProduct")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public String addToCart(@RequestParam("idProduct") String id,HttpServletRequest request) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetailsImpl u = (UserDetailsImpl) authentication.getPrincipal();
			UserEntity currentUser = userRepository.getById(u.getId());
			
			ProductEntity product = productRepository.getById(Long.parseLong(id));
			CartEntity cart = cartRepository.findByUser(currentUser);
			
			if(cart == null)
			{
				cart = new CartEntity();
				cart.setUser(currentUser);
				cart = cartRepository.save(cart);			
			}
			
			CartIndexEntity cartIndex = cartIndexRepository.findByProductAndCart(product, cart);
			
			if(cartIndex == null){    
				cartIndex = new CartIndexEntity();
				cartIndex.setCart(cart);
				cartIndex.setProduct(product);
				cartIndex.setAmount(1);
			}
			else{
				cartIndex.setAmount(cartIndex.getAmount()+1);
			}
			cartIndex = cartIndexRepository.save(cartIndex);
			return "success";
		} catch (Exception e) {
			return "Error";
		}
	}
	
	//update quanity product in cart
	@PostMapping("/changProductQuanity")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public String changQuanity(@RequestParam String id, @RequestParam String value) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl u = (UserDetailsImpl) authentication.getPrincipal();
		UserEntity currentUser = userRepository.getById(u.getId());
		
		CartEntity cartEntity = cartRepository.findByUser(currentUser);
		ProductEntity product = productRepository.getById(Long.parseLong(id));
		CartIndexEntity cartIndexEntity = cartIndexRepository.findByProductAndCart(product, cartEntity);
		cartIndexEntity.setAmount(Integer.parseInt(value));
		cartIndexEntity = cartIndexRepository.save(cartIndexEntity);
		return "Success";
	}
}
