package com.example.bikestores.exception;

import com.example.bikestores.config.exception.BusinessRuntimeException;

public class ParameterNotFoundException extends BusinessRuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String STATUS = "404";
	private static final String ERROR = "NOT_FOUND";

	public ParameterNotFoundException(String message) {
		super(STATUS, ERROR, message);
	}
}
