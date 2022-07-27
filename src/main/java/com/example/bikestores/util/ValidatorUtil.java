package com.example.bikestores.util;

public class ValidatorUtil {

	private ValidatorUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static Boolean exist(String str) {
		return str != null && !str.trim().isEmpty();
	}

}
