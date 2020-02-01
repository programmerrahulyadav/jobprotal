package com.jobportal.controller;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.jobportal.request.CompanyCreateRequest;
import com.jobportal.response.CreateResponse;
import com.jobportal.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@Autowired
	CreateResponse createResponse;
	
	@PostMapping
	@RequestMapping("/create")
	public ResponseEntity<String> create(@RequestBody CompanyCreateRequest companyCreateRequest) {
		
		
		try {
			companyService.insertService(companyCreateRequest);
			return createResponse.createSucessResponse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return createResponse.createFailureResponse(e);
		}
		
	}
	
	@GetMapping
	@RequestMapping("/get/job/application")
	public ResponseEntity<String> getListOfJobApplied(@RequestHeader("companyId") int companyId) {

		
		try {
			return createResponse.createSucessResponse(companyService.getListApplicants(companyId));

		} catch (Exception e) {
			e.printStackTrace();
			return  createResponse.createFailureResponse(e);
		}

	}
}
