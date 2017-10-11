package com.github.foreyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.foreyer.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserName(String username);
	
}
