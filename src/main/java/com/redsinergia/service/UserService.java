package com.redsinergia.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.redsinergia.dto.UserDto;
import com.redsinergia.model.User;
import com.redsinergia.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public User saveUser(UserDto userDto) {
		
		if(userRepository.existsByUsername(userDto.getUser())){
			return null;
		}
		User user = new User();
		user.setUsername(userDto.getUser());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setErased(false);
		return userRepository.save(user);
	}
	
	public Optional<User> validUser(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
}
