package com.pesol.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pesol.spring.dao.RoleRepository;
import com.pesol.spring.dao.UserRepositoty;
import com.pesol.spring.entity.Role;
import com.pesol.spring.entity.User;
import com.pesol.spring.model.UserModel;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepositoty userRepositoty;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User findByUsername(String username) {
		
		return userRepositoty.findByUsername(username);
	}
	
	@Override
	public void save(UserModel userModel) {
		User user = new User();
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setEmail(userModel.getEmail());
		user.setUsername(userModel.getUsername());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		
		Role role = roleRepository.findByName("ROLE_USER");
		
		user.setRoles(List.of(role));
		// save to db
		userRepositoty.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepositoty.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		System.out.println("On loadUserByUsername(..) >>> Username = " + user.getUsername());
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), 
				user.getRoles().stream()
						.map(role -> new SimpleGrantedAuthority(role.getName()))
						.collect(Collectors.toList()));
	}

	@Override
	public User findByUsernameJoinFetch(String username) {
		
		return userRepositoty.findByUsernameJoinFetch(username);
	}

}
