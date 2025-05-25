package com.redsinergia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redsinergia.dto.AccountContactDto;
import com.redsinergia.dto.AccountDto;
import com.redsinergia.hanler.ResponseHandler;
import com.redsinergia.service.AccountService;
import com.redsinergia.util.Constants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService accountService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveAccount(@RequestBody AccountDto accountDto) {
		try {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_EXITO, HttpStatus.OK, accountService.saveAccount(accountDto));
		} catch (Exception e) {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_ERROR_SERVIDOR, HttpStatus.NOT_FOUND, e.getMessage());		
		} 
	}
	
	@PostMapping("/save-contact")
	public ResponseEntity<Object> saveAccountContact(@RequestBody AccountContactDto accountContactDto) {
		try {
			accountService.saveAccountContact(accountContactDto.getIdUser(), accountContactDto.getNumberAccount());
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_EXITO, HttpStatus.OK, null);
		} catch (Exception e) {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_ERROR_SERVIDOR, HttpStatus.NOT_FOUND, e.getMessage());		
		} 
	}
	
	@GetMapping("/my-account")
	public ResponseEntity<Object> mysAccount(@RequestParam Integer idUser) {
		try {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_EXITO, HttpStatus.OK, accountService.getMyAccount(idUser));
		} catch (Exception e) {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_ERROR_SERVIDOR, HttpStatus.NOT_FOUND, e.getMessage());		
		} 
	}
	
	@GetMapping("/my-balance")
	public ResponseEntity<Object> myBalance(@RequestParam Integer idAcount) {
		try {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_EXITO, HttpStatus.OK, accountService.getBalance(idAcount));
		} catch (Exception e) {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_ERROR_SERVIDOR, HttpStatus.NOT_FOUND, e.getMessage());		
		} 
	}

}
