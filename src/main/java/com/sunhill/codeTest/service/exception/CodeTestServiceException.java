package com.sunhill.codeTest.service.exception;

public class CodeTestServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2481147250218699086L;

	private String message;
	
	public CodeTestServiceException(final String message) {
		super();
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
