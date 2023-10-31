package com.config.exception;

public class ResourceNotFoundException extends Exception {
	
	private int resourceId;
	
	public ResourceNotFoundException() {
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message, Object resourceI) {
		super(message);
		this.resourceId = (int) resourceI;
	}
	
	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public int getResourceId() {
		return resourceId;
	}
}
