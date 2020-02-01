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
import com.jobportal.validation.JobApplicationValidator;

@Service
public class JobApplicationService {

	@Autowired
	JobApplicationUserMapDao jobApplicationUserMapDao;
	
	@Autowired
	JobApplicationValidator jobApplicationValidator;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	JobDao jobDao;
	
	
	
	public void insertService(ApplyJobRequest applyJobRequest) throws Exception {
		
		
		jobApplicationValidator.isUserIdJobIdPresent(applyJobRequest.getUserId(), applyJobRequest.getJobId());
		jobApplicationValidator.isEntryPresent(applyJobRequest.getUserId(), applyJobRequest.getJobId(),"INSERT");
		jobApplicationUserMapDao.insert(applyJobRequest);
	}
	
	public void updateStatus(int jobId,int userId,String applicationStatus) throws Exception {
		
		
		jobApplicationValidator.validStatus(applicationStatus);
		jobApplicationValidator.isEntryPresent(userId, jobId,"UPDATE");
		jobApplicationUserMapDao.update( jobId, userId, applicationStatus);
	}
	
	public String getStatus(int jobId, int userId) throws Exception {
		
		jobApplicationValidator.isUserIdJobIdPresent(userId, jobId);
		jobApplicationValidator.isEntryPresent(userId, jobId,"GET");
		return jobApplicationUserMapDao.get(jobId, userId);
	}
	
	public List<JobApplicantsResponse> getApplicants(int jobId) throws Exception {

		List<JobApplicantsResponse> lisOfJobApplicantsResponse = jobApplicationUserMapDao.getListOfApplicant(jobId);
		if(lisOfJobApplicantsResponse.size() == 0) {
			
			throw new InvalidRequest("No one has Appliyed for the job", "400");
		}
		
		return lisOfJobApplicantsResponse;

	}
}
