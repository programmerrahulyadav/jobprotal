package com.jobportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.request.ApplyJobRequest;
import com.jobportal.response.CreateResponse;
import com.jobportal.service.JobApplicationService;

@RestController
@RequestMapping("/application")
public class JobApplicationController {

	@Autowired
	JobApplicationService jobApplicationService;
	
	@Autowired
	CreateResponse createResponse;
	
	@PostMapping
	@RequestMapping("/user/apply")
	public ResponseEntity<String> userApplication(@RequestBody ApplyJobRequest applyJobRequest) {
		
		
		try {
			jobApplicationService.insertService(applyJobRequest);
			return createResponse.createSucessResponse();
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse.createFailureResponse(e);
		}
		
	}
	
	@PutMapping
	@RequestMapping("/update/status")
	public ResponseEntity<String> updateStatus(@RequestHeader("jobId") int jobId,@RequestHeader("userId") int userId,@RequestHeader("status") String applicationStatus) {
		
		try {
			jobApplicationService.updateStatus(jobId,userId,applicationStatus);
			return createResponse.createSucessResponse();
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse.createFailureResponse(e);
		}
	}
	
	@GetMapping
	@RequestMapping("/get/status")
	public ResponseEntity<String> updateStatus(@RequestHeader("jobId") int jobId,@RequestHeader("userId") int userId) {
		
		
		try {
			
			 return createResponse.createSucessResponse(jobApplicationService.getStatus(jobId,userId));
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse.createFailureResponse(e);
		}
		
	}
	
	@GetMapping
	@RequestMapping("/get/applicants/list")
	public ResponseEntity<String> getapplicantList(@RequestHeader("jobId") int jobId) {
		try {
			 
			 return createResponse.createSucessResponse(jobApplicationService.getApplicants(jobId));
		} catch (Exception e) {
			
			e.printStackTrace();
			return createResponse.createFailureResponse(e);
		}
		
	}
	
}
