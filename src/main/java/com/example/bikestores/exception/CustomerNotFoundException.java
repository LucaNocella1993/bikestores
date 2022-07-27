package com.example.bikestores.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.example.bikestores.config.exception.BusinessRuntimeException;

public class CustomerNotFoundException extends BusinessRuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String STATUS = "404";
	private static final String ERROR = NOT_FOUND.getReasonPhrase();

	public CustomerNotFoundException(String message) {
		super(STATUS, ERROR, message);
	}
}
