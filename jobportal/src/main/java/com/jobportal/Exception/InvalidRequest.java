package com.jobportal.Exception;

public class InvalidRequest extends Exception{


	private static final long serialVersionUID = 1L;

	protected String statusCode;
	
	public InvalidRequest(String message,String statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	
}
