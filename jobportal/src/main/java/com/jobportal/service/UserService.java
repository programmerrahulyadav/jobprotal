package com.jobportal.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Exception.InvalidRequest;
import com.jobportal.dao.JobApplicationUserMapDao;
import com.jobportal.dao.UserDao;
import com.jobportal.request.UserCreateRequest;
import com.jobportal.response.UserApplyiedJobResponse;
import com.mysql.cj.util.StringUtils;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	JobApplicationUserMapDao jobApplicationUserMapDao;
	
	public void insertService(UserCreateRequest userCreateRequest) throws Exception {

		if (StringUtils.isNullOrEmpty(userCreateRequest.getEmailId())) {
			throw new InvalidRequest("EmailId Can't be empty", "400");
		}
		if (userDao.isUserPresent(userCreateRequest.getMobileNumber(), userCreateRequest.getEmailId())) {

			throw new InvalidRequest("User already Present", "400");

		}
		userDao.insert(userCreateRequest);

	}
	
	public List<UserApplyiedJobResponse> getJobsApplyied(int userId) throws Exception {

		if (userDao.getUserId(userId) == 0) {
			throw new InvalidRequest("Please create user account", "400");
		}

		List<UserApplyiedJobResponse> listOfUserApplyiedJobResponse = jobApplicationUserMapDao.getListOfJobs(userId);
		
		if (listOfUserApplyiedJobResponse.size() == 0) {
			throw new InvalidRequest("No job has been applied by the user", "400");
		}
		return jobApplicationUserMapDao.getListOfJobs(userId);

	}
	
}
