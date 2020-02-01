package com.jobportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.request.JobCreateRequest;
import com.jobportal.response.CreateResponse;
import com.jobportal.service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	JobService jobService;
	
	@Autowired
	CreateResponse createResponse;
	
	@PostMapping
	@RequestMapping("/create")
	public ResponseEntity<String> create(@RequestBody JobCreateRequest jobCreateRequest) {
		try {
			System.out.println("..............hello in job service......");
			jobService.insertService(jobCreateRequest);
			return createResponse.createSucessResponse();
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse.createFailureResponse(e);
		}
		
	}
	
}
