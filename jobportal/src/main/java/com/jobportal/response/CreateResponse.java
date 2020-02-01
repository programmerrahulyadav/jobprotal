package com.jobportal.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.jobportal.Exception.InvalidRequest;


@Component
public class CreateResponse {

	
	public ResponseEntity<String> createSucessResponse() {
		
		String val = "{\"status\" : 200}";
		
		return new ResponseEntity<String>(val, HttpStatus.OK);
	}
	
	public ResponseEntity<String> createSucessResponse(String output) {

		String val = "{\"data\" : \"output\" , \"status\" : 200}";
		 val = val.replace("output", output).toString();

		return new ResponseEntity<String>(val, HttpStatus.OK);
	}
	
	
	public ResponseEntity<String> createSucessResponse(List<?> list) {
		
		
		String val = "{\"data\" : list ,\"status\" : 200}";
		val= val.replace("list", list.toString()).toString();
		return new ResponseEntity<String>(val, HttpStatus.OK);
	}
	
	public ResponseEntity<String> createFailureResponse(Exception e) {

		String val = "{\"error\" : \"msg\",\"status\" : statusCode}";
		String statusCode = "";
		if(e instanceof InvalidRequest) {
			InvalidRequest ir= (InvalidRequest) e;
			statusCode = ir.getStatusCode();
		}else {
			statusCode = e.getStackTrace()[0].getLineNumber()+"";
		}
		val = val.replace("msg", e.getMessage()).toString();
		val = val.replace("statusCode", statusCode).toString();
		return new ResponseEntity<String>(val, HttpStatus.BAD_REQUEST);
	}
	
}
