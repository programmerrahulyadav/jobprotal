package com.jobportal.request;

public class JobCreateRequest {

	protected String jobDescription;
	protected String skills;
	protected int minExpRequired;
	protected String industryRelatedTo;
	protected String companyName;
	
	
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public int getMinExpRequired() {
		return minExpRequired;
	}
	public void setMinExpRequired(int minExpRequired) {
		this.minExpRequired = minExpRequired;
	}
	public String getIndustryRelatedTo() {
		return industryRelatedTo;
	}
	public void setIndustryRelatedTo(String industryRelatedTo) {
		this.industryRelatedTo = industryRelatedTo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
