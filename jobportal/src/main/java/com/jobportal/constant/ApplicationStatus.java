package com.jobportal.constant;

public enum ApplicationStatus {

	APPLIYED("APPLIYED"),
	VIWED("VIWED"),
	SHORTLISTED("SHORTLISTED"),
	NOTSUITABLE("NOTSUITABLE");
	
	private ApplicationStatus(String status) {
		// TODO Auto-generated constructor stub
		this.status = status;
	}
	
	private String status;
	
	public String getStatus() {
        return this.status;
    }
	
	public static boolean contains(String s)
	  {
	      if (s.equals(APPLIYED.name()) || s.equals(VIWED.name()) || s.equals(SHORTLISTED.name()) || s.equals(NOTSUITABLE.name())) {
	    	  return true;
	      }
	        
	      return false;
	  }

}
