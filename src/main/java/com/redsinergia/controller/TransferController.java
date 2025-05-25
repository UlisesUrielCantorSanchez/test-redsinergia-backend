package com.redsinergia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redsinergia.dto.TransferDto;
import com.redsinergia.hanler.ResponseHandler;
import com.redsinergia.service.TransferService;
import com.redsinergia.util.Constants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
public class TransferController {
	
	private final TransferService transferService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveAccount(@RequestBody TransferDto transferDto) {
		try {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_EXITO, HttpStatus.OK, transferService.saveTransfer(transferDto));
		} catch (Exception e) {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_ERROR_SERVIDOR, HttpStatus.NOT_FOUND, e.getMessage());		
		} 
	}
	
	@GetMapping("/my-transfers")
	public ResponseEntity<Object> getMyTransfers(@RequestParam Integer idUser) {
		try {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_EXITO, HttpStatus.OK, transferService.getMyTransfers(idUser));
		} catch (Exception e) {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_ERROR_SERVIDOR, HttpStatus.NOT_FOUND, e.getMessage());		
		} 
	}
	
	@GetMapping("/grafic-transfers")
	public ResponseEntity<Object> graficTransfers(@RequestParam Integer idAccount) {
		try {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_EXITO, HttpStatus.OK, transferService.getGraficTransfers(idAccount));
		} catch (Exception e) {
			return ResponseHandler.generarRespuesta(Constants.RESPUESTA_ERROR_SERVIDOR, HttpStatus.NOT_FOUND, e.getMessage());		
		} 
	}
	

}
