package com.fco.sales.services.exceptions;

public class UserOrPassworIncorretException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserOrPassworIncorretException(String msg) {
		super(msg);
	}
	
	public UserOrPassworIncorretException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	

}
