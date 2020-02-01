package com.jobportal.request;

public class CompanyCreateRequest {

	protected String companyName;
	protected String companyType;
	protected int companyStrength;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public int getCompanyStrength() {
		return companyStrength;
	}
	public void setCompanyStrength(int companyStrength) {
		this.companyStrength = companyStrength;
	}
	
	
}
