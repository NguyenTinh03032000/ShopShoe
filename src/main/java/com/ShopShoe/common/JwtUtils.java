package com.ShopShoe.common;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ShopShoe.entity.TokenEntity;
import com.ShopShoe.repository.TokenRepository;
import com.ShopShoe.service.Implements.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	
	private static final Logger Logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${bezkoder.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	public String generateJwtToken(Authentication authentication) {
		
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		
		String token =  Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
		Long idUser = userPrincipal.getId();
		Date time_exporeDate = new Date((new Date()).getTime() + jwtExpirationMs);
		Date createDate = new Date((new Date()).getTime());
		TokenEntity tokenEntity = new TokenEntity(idUser,token,time_exporeDate,createDate,createDate);
		
		TokenEntity tokenEntity2 = tokenRepository.findByUserId(idUser);
		if(tokenEntity2 != null) {
			tokenEntity2.setToken(token);
			tokenEntity2.setTime_expired(time_exporeDate);
			tokenEntity2.setCreate_date(createDate);
			tokenEntity2.setUpdate_date(createDate);
			tokenRepository.save(tokenEntity2);
		}else {
			tokenRepository.save(tokenEntity);
		}
		
		return token;
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		}catch (SignatureException e) {
			Logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch(MalformedJwtException e) {
			Logger.error("Invalid JWT token: {}", e.getMessage());
		}
		catch(ExpiredJwtException e) {
			Logger.error("JWT token is expired: {}", e.getMessage());
		}
		catch(UnsupportedJwtException e) {
			Logger.error("JWT token is unsupported: {}", e.getMessage());
		}
		catch(IllegalArgumentException e) {
			Logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
}
