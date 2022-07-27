package com.example.bikestores.config.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessRuntimeException.class)
	public final ResponseEntity<Object> handleAllExceptions(BusinessRuntimeException ex) {

		logger.error(ex.getStatus() + ", " + ex.getError() + ", " + ex.getMessage(), ex);

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", ex.getStatus());
		body.put("error", ex.getError());
		body.put("message", ex.getMessage());	
		body.put("class", ex.getClass());
		body.put("timestamp", LocalDateTime.now());

		switch (ex.getStatus()) {
		case "401":
			return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
		case "403":
			return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
		case "404":
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		case "405":
			return new ResponseEntity<>(body, HttpStatus.METHOD_NOT_ALLOWED);
		default:
			return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		}  
	}
}