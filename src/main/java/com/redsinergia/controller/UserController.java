package com.redsinergia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redsinergia.dto.UserDto;
import com.redsinergia.hanler.ResponseHandler;
import com.redsinergia.service.UserService;
import com.redsinergia.util.Constants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveUser(@RequestBody UserDto UserDto) {
		try {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_EXITO, HttpStatus.OK, userService.saveUser(UserDto));
		} catch (Exception e) {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_ERROR_SERVIDOR, HttpStatus.NOT_FOUND, e.getMessage());		
		} 
	}
	
	@GetMapping("/valid")
	public ResponseEntity<Object> validUser(@RequestParam String username, @RequestParam String password) {
		try {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_EXITO, HttpStatus.OK, userService.validUser(username,password));
		} catch (Exception e) {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_ERROR_SERVIDOR, HttpStatus.NOT_FOUND, e.getMessage());		
		} 
	}
 
	

}
