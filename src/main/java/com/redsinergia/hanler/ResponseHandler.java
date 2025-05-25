package com.redsinergia.hanler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	
	private String mensaje = "Se ha producido un error en el Servidor";

	public static ResponseEntity<Object> generarRespuesta(String mensaje, HttpStatus estatus, Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Mensaje", mensaje);
		map.put("Estatus", estatus.value());
		map.put("Datos", obj);
		return new ResponseEntity<Object>(map, estatus);
	}

	public String getRespuesta() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
