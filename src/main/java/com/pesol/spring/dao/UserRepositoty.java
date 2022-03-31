package com.pesol.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pesol.spring.entity.User;

public interface UserRepositoty extends JpaRepository<User, Integer> {

	@Query("FROM User u WHERE u.username = ?1")
	User findByUsername(String username);

	@Query("FROM User u JOIN FETCH u.roles WHERE u.username = ?1")
	User findByUsernameJoinFetch(String username);
	
}
