package com.laptrinhjavaweb.customexception;

public class FieldRequireException extends RuntimeException {
	public FieldRequireException(String errorMessage) {
		super(errorMessage);
	}
}
