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

import com.ShopShoe.dto.CartIndexDTO;
import com.ShopShoe.entity.CartEntity;
import com.ShopShoe.entity.CartIndexEntity;
import com.ShopShoe.entity.ProductEntity;
import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.service.CartIndexService;
import com.ShopShoe.service.CartService;
import com.ShopShoe.service.ProductService;
import com.ShopShoe.service.UserService;
import com.ShopShoe.service.Implements.UserDetailsImpl;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CartIndexService cartIndexService;
	
	/**
	 * @Param : None
	 * @return list cart index in cart
	 */
	
	@GetMapping()
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public List<CartIndexDTO> getAllProductInCart() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl u = (UserDetailsImpl) authentication.getPrincipal();
		UserEntity currentUser = userService.getById(u.getId());
		
		CartEntity cart = cartService.findByUser(currentUser);
		if(cart != null) {
			List<CartIndexDTO> listIndex = cartIndexService.findByCart(cart);
			return listIndex;
		}
		return null;
	}
	
	/**
	 * @Content add product to cart
	 * @param id product
	 * @param request
	 * @return
	 */
	@PostMapping("/addProduct")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public String addToCart(@RequestParam("idProduct") String id,HttpServletRequest request) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetailsImpl u = (UserDetailsImpl) authentication.getPrincipal();
			UserEntity currentUser = userService.getById(u.getId());
			
			ProductEntity product = productService.getById(Long.parseLong(id));
			CartEntity cart = cartService.findByUser(currentUser);
			
			if(cart == null)
			{
				cart = new CartEntity();
				cart.setUser(currentUser);
				cart = cartService.save(cart);			
			}
			
			CartIndexEntity cartIndex = cartIndexService.findByProductAndCart(product, cart);
			
			if(cartIndex == null){    
				cartIndex = new CartIndexEntity();
				cartIndex.setCart(cart);
				cartIndex.setProduct(product);
				cartIndex.setAmount(1);
			}
			else{
				cartIndex.setAmount(cartIndex.getAmount()+1);
			}
			cartIndex = cartIndexService.save(cartIndex);
			return "success";
		} catch (Exception e) {
			return "Error";
		}
	}
	
	/**
	 * @Content change number product in cart
	 * @param id product
	 * @param value
	 * @return
	 */
	@PostMapping("/changProductQuanity")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public String changQuanity(@RequestParam String id, @RequestParam String value) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl u = (UserDetailsImpl) authentication.getPrincipal();
		UserEntity currentUser = userService.getById(u.getId());
		
		CartEntity cartEntity = cartService.findByUser(currentUser);
		ProductEntity product = productService.getById(Long.parseLong(id));
		CartIndexEntity cartIndexEntity = cartIndexService.findByProductAndCart(product, cartEntity);
		cartIndexEntity.setAmount(Integer.parseInt(value));
		cartIndexEntity = cartIndexService.save(cartIndexEntity);
		return "Success";
	}
}
