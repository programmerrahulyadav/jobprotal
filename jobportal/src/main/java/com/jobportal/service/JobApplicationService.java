package com.jobportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Exception.InvalidRequest;
import com.jobportal.constant.ApplicationStatus;
import com.jobportal.dao.JobApplicationUserMapDao;
import com.jobportal.dao.JobDao;
import com.jobportal.dao.UserDao;
import com.jobportal.request.ApplyJobRequest;
import com.jobportal.response.JobApplicantsResponse;

@Service
public class JobApplicationService {

	@Autowired
	JobApplicationUserMapDao jobApplicationUserMapDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	JobDao jobDao;
	
	
	
	public void insertService(ApplyJobRequest applyJobRequest) throws Exception {
		
		
		int userId  = userDao.getUserId(applyJobRequest.getUserId());
		int jobId  = jobDao.getJobId(applyJobRequest.getJobId());
		if(userId == 0 || jobId == 0) {
			 
			throw new InvalidRequest("userId or jobId is not correct", "400");
		}
		if(jobApplicationUserMapDao.isPresent(jobId, userId)) {
			
			throw new InvalidRequest("you have already appliyed to the job", "400");
		}
		
		jobApplicationUserMapDao.insert(applyJobRequest);
	}
	
	public void updateStatus(int jobId,int userId,String applicationStatus) throws Exception {
		
		
		if(!ApplicationStatus.contains(applicationStatus)) {
			throw new InvalidRequest("invalid application status", "400");
		}
		
		if (!jobApplicationUserMapDao.isPresent(jobId, userId)) {

			throw new InvalidRequest("Not applyied for the jobs", "400");
		}

		jobApplicationUserMapDao.update( jobId, userId, applicationStatus);
	}
	
	public String getStatus(int jobId, int userId) throws Exception {
		
		userId  = userDao.getUserId(userId);
		jobId  = jobDao.getJobId(jobId);
		if(userId == 0 || jobId == 0) {
			 
			throw new InvalidRequest("userId or jobId is not correct", "400");
		}
		
		if (!jobApplicationUserMapDao.isPresent(jobId, userId)) {

			throw new InvalidRequest("Not applyied for the jobs", "400");
		}

		return jobApplicationUserMapDao.get(jobId, userId);
	}
	
	public List<JobApplicantsResponse> getApplicants(int jobId) throws Exception {

		if(jobDao.getJobId(jobId) == 0) {
			throw new InvalidRequest("jobId does not exist","400");
		}
		
		List<JobApplicantsResponse> lisOfJobApplicantsResponse = jobApplicationUserMapDao.getListOfApplicant(jobId);
		if(lisOfJobApplicantsResponse.size() == 0) {
			
			throw new InvalidRequest("No one has Appliyed for the job", "400");
		}
		
		return lisOfJobApplicantsResponse;

	}
}
