package com.jobportal.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JobApplicantsResponse {

	private String jobDescription;
	private String name;
	private long mobileNo;
	private String email;
	
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		String val = "";
		try {
			val = objectMapper.writeValueAsString(this).toString();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return val;
	}
}
