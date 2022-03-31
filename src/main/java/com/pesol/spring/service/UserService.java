package com.pesol.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pesol.spring.entity.User;
import com.pesol.spring.model.UserModel;

public interface UserService extends UserDetailsService{
	
	User findByUsername(String username);

	void save(UserModel userModel);

	User findByUsernameJoinFetch(String username);
}
