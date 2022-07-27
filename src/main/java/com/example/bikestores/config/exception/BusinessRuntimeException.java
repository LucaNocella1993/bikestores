package com.example.bikestores.config.exception;

public class BusinessRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String status;
	private final String error;
	private final String message;

	public BusinessRuntimeException(String status, String error, String message) {
		this.status = status;
		this.error = error;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
