package com.jobportal.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jobportal.Exception.InvalidRequest;
import com.jobportal.constant.ApplicationStatus;
import com.jobportal.dao.JobApplicationUserMapDao;
import com.jobportal.dao.JobDao;
import com.jobportal.dao.UserDao;

@Component
public class JobApplicationValidator {

	@Autowired
	UserDao userDao;
	
	@Autowired
	JobDao jobDao;
	
	@Autowired
	JobApplicationUserMapDao jobApplicationUserMapDao;
	
	public void isUserIdJobIdPresent(int userId,int jobId) throws Exception {
		
		 userId  = userDao.getUserId(userId);
		 jobId  = jobDao.getJobId(jobId);
		if(userId == 0 || jobId == 0) {
			 
			throw new InvalidRequest("userId or jobId is not correct", "400");
		}
	}
	
	public void isEntryPresent(int userId,int jobId,String operations) throws  Exception {
		
		if(operations.equalsIgnoreCase("INSERT")) {
			
			if(jobApplicationUserMapDao.isPresent( userId, jobId)) {
				
				throw new InvalidRequest("you have already appliyed to the job", "400");
			}
		}else {
			
			if (!jobApplicationUserMapDao.isPresent(jobId, userId)) {

				throw new InvalidRequest("Not applyied for the jobs", "400");
			}
		}
		
	}
	
	public void validStatus(String status) throws  Exception {
		
		if(!ApplicationStatus.contains(status)) {
			throw new InvalidRequest("invalid application status", "400");
		}
		
	}
	
	public void isJobPresent(int jobId) throws  Exception {
		
		if(jobDao.getJobId(jobId) == 0) {
			throw new InvalidRequest("jobId does not exist","400");
		}
	}
}
