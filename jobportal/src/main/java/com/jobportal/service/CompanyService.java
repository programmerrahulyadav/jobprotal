package com.jobportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Exception.InvalidRequest;
import com.jobportal.dao.CompanyDao;
import com.jobportal.request.CompanyCreateRequest;
import com.jobportal.response.ApplicantsApplyiedResponse;

@Service
public class CompanyService {

	@Autowired
	CompanyDao companyDao;
	
	public void insertService(CompanyCreateRequest companyCreateRequest) throws Exception {
		
		 int companyId = companyDao.getCompanyId(companyCreateRequest.getCompanyName());
		 if(companyId > 0) {
			 throw new InvalidRequest("Company Account already Exists", "400");
		 }
		companyDao.insert(companyCreateRequest);
	}
	
	public List<ApplicantsApplyiedResponse> getListApplicants(int companyId) throws Exception {

		return companyDao.getListApplicants(companyId);
	}
	
}
