package com.pesol.spring.config;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.pesol.spring.entity.User;
import com.pesol.spring.service.UserService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	private Logger logger = Logger.getLogger(CustomAuthenticationSuccessHandler.class.getName());

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		logger.info("\nIn CustomAuthenticationSuccessHandler.onAuthenticationSuccess(..)\n");
		
		String username = authentication.getName();
		logger.info("Username = " + username);
		
		User user = userService.findByUsernameJoinFetch(username);
		
		request.getSession().setAttribute("user", user);
		
		response.sendRedirect(request.getContextPath() + "/profile");
		
	}

}
