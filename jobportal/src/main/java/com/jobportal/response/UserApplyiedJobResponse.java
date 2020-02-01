package com.jobportal.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserApplyiedJobResponse {

	private String jobDescription;
	private String company;
	private int jobId;
	
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
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
