package com.jobportal.response;

public class ApplicantsApplyiedResponse implements JobProtalResponse{

	protected String jobDiscription;
	protected int userId;
	protected String fName;
	protected String lName;
	protected String companyName;
	protected String errorResponse;
	protected int errorCode;
	
	public String getJobDiscription() {
		return jobDiscription;
	}
	public void setJobDiscription(String jobDiscription) {
		this.jobDiscription = jobDiscription;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(String errorResponse) {
		this.errorResponse = errorResponse;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	@Override
	public String toString() {
		String result = "{"+
				"\"jobDiscription\":"+
				"\""
				+ this.jobDiscription
				+"\","
				+ "\"userId\":\""
				+ this.userId
				+"\","
				+"\"fName\":\""
				+ this.fName
				+"\""
				+",\"lName\":\""
				+ this.lName
				+"\","
				+"\"companyName\":\""
				+ this.companyName
				+ "\"}";
		return result;
	}
	
	
}
