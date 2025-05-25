package com.redsinergia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redsinergia.hanler.ResponseHandler;
import com.redsinergia.service.ContactService;
import com.redsinergia.util.Constants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
	
	private final ContactService contactService;
	
	@GetMapping("")
	public ResponseEntity<Object> validUser(@RequestParam Integer idUser) {
		try {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_EXITO, HttpStatus.OK, contactService.getContacts(idUser));
		} catch (Exception e) {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_ERROR_SERVIDOR, HttpStatus.NOT_FOUND, e.getMessage());		
		} 
	}

}
