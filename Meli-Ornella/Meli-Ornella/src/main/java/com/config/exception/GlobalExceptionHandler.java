package com.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.logging.*;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> todosErrores(Exception ex, WebRequest req) {
		logger.warning(ex.getMessage());
		return new ResponseEntity("Error " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		logger.warning(ex.getMessage());
		return ResponseEntity.badRequest().body("ERROR = utilice un id correcto");
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<String> badRequestExceptionHandler(BadRequestException ex) {
		logger.warning(ex.getMessage());
		return ResponseEntity.badRequest().body("PETICION INCORRECTA");
	}
}
