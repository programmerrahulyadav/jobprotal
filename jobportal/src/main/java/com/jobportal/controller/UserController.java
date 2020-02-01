package com.jobportal.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.request.UserCreateRequest;
import com.jobportal.response.CreateResponse;
import com.jobportal.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	UserService userService;
	
	@Autowired
	CreateResponse createResponse;
	
	@RequestMapping("/ping")
	public void ping() {
		System.out.println("ping : "+LocalDate.now());
		System.out.println("helllow boy...");
	}
	
	@PostMapping
	@RequestMapping("/create")
	public ResponseEntity<String> create(@RequestBody UserCreateRequest userCreateRequest) {

		try {
			userService.insertService(userCreateRequest);
			return createResponse.createSucessResponse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return createResponse.createFailureResponse(e);

		}

	}
	
	@GetMapping
	@RequestMapping("/get/jobs/applyied")
	public ResponseEntity<String> getJobsAppyied(@RequestHeader("userId") int userId) {

		try {
			return createResponse.createSucessResponse(userService.getJobsApplyied(userId));

		} catch (Exception e) {

			return createResponse.createFailureResponse(e);
		}

	}
	
}
