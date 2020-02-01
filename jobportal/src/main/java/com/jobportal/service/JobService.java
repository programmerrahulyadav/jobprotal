package com.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Exception.InvalidRequest;
import com.jobportal.dao.CompanyDao;
import com.jobportal.dao.JobCompanyMapDao;
import com.jobportal.dao.JobDao;
import com.jobportal.request.JobCreateRequest;

@Service
public class JobService {

	@Autowired
	JobDao jobDao;

	@Autowired
	CompanyDao companyDao;

	@Autowired
	JobCompanyMapDao jobCompanyMapDao;

	public void insertService(JobCreateRequest jobCreateRequest) throws Exception {

		
		 int companyId = companyDao.getCompanyId(jobCreateRequest.getCompanyId());
		if (companyId ==  0) {
			throw new InvalidRequest("please create account for the Company","400");
		} 

		int jobId = jobDao.insert(jobCreateRequest);
		jobCompanyMapDao.insert(companyId, jobId);
	}
	
}
