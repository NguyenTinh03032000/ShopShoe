package com.ShopShoe.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint{
	
	private static final Logger Logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse respone,
			AuthenticationException authException) throws IOException, ServletException{
		
		Logger.error("Unauthorized error: {}", authException.getMessage());
		respone.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}
