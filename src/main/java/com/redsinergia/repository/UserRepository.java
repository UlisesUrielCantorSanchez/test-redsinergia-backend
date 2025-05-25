package com.redsinergia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redsinergia.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public boolean existsByUsername(String email);
	
	public Optional<User> findByUsernameAndPassword(String username, String password);

}
