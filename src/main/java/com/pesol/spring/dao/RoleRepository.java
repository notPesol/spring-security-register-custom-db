package com.pesol.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pesol.spring.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	@Query("FROM Role r WHERE r.name = ?1")
	Role findByName(String name);
}
