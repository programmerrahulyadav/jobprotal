package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jobportal.DBConnection;
import com.jobportal.request.CompanyCreateRequest;
import com.jobportal.response.ApplicantsApplyiedResponse;


@Repository
public class CompanyDao {

	@Autowired
	DBConnection dbConnection;
	
	public void insert(CompanyCreateRequest companyCreateRequest) throws SQLException {
		
		PreparedStatement prepareStatement = null;
		try {
			
			String sqlQuery = "insert into Company(companyName, companyType, companyStrength) " +
	                " values (?,?,?)";
			
			 Connection con= dbConnection.getConnection();
			 prepareStatement = con.prepareStatement(sqlQuery);
			 prepareStatement.setString(1, companyCreateRequest.getCompanyName());
			 prepareStatement.setString(2, companyCreateRequest.getCompanyType());
			 prepareStatement.setInt(3, companyCreateRequest.getCompanyStrength());
			 
			 prepareStatement.execute();
		} finally {
			// TODO: handle finally clause
			if(prepareStatement != null) {
				prepareStatement.close();
			}
		}
		 
	}
	
	public int getCompanyId(String companyName) throws Exception  {

		String sqlQuery = "select id from Company where companyName=? ";
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		int id = 0;
		try {
			
			Connection con = dbConnection.getConnection();
			prepareStatement = con.prepareStatement(sqlQuery);
			prepareStatement.setString(1, companyName);
			rs = prepareStatement.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
			}
		} 
		finally {
			
			if(rs != null) {
				rs.close();
			}
			if(prepareStatement != null) {
				prepareStatement.close();
			}
				
		}
		
		return id;
	}
	
	public int getCompanyId_V2(int companyId) throws Exception  {

		String sqlQuery = "select id from Company where id=? ";
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		int id = 0;
		try {
			
			Connection con = dbConnection.getConnection();
			prepareStatement = con.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, companyId);
			rs = prepareStatement.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
			}
		} 
		finally {
			
			if(rs != null) {
				rs.close();
			}
			if(prepareStatement != null) {
				prepareStatement.close();
			}
				
		}
		System.out.println(id);
		return id;
	}
	
	
	public List<ApplicantsApplyiedResponse> getListApplicants(int  companyId) throws Exception  {

		String sqlQuery = "select j.jobDescription as jd,u.id as userId,u.fname as fname,u.lname as lname,c.companyName as companyName from JobCompanyMap as jcm INNER JOIN Job j ON jcm.jobId=j.id "
				+ " INNER JOIN Company c ON jcm.companyId = c.id "
				+ " INNER JOIN JobApplicationUserMap jaum ON  jcm.jobId = jaum.jobId "
				+ " INNER JOIN User u  ON jaum.userId = u.id "
				+ " where c.id=?";
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		List<ApplicantsApplyiedResponse> list = null;
		try {
			
			Connection con = dbConnection.getConnection();
			prepareStatement = con.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, companyId);
			rs = prepareStatement.executeQuery();
			list = new ArrayList<ApplicantsApplyiedResponse>();
			while(rs.next()) {
				ApplicantsApplyiedResponse applicantsApplyiedResponse = new ApplicantsApplyiedResponse();
				applicantsApplyiedResponse.setUserId(rs.getInt("userId"));
				applicantsApplyiedResponse.setJobDiscription(rs.getString("jd"));
				applicantsApplyiedResponse.setfName(rs.getString("fname"));
				applicantsApplyiedResponse.setlName(rs.getString("lname"));
				applicantsApplyiedResponse.setCompanyName(rs.getString("companyName"));
				list.add(applicantsApplyiedResponse);
			}
			
			
		} 
		finally {
			
			if(rs != null) {
				rs.close();
			}
			if(prepareStatement != null) {
				prepareStatement.close();
			}
				
		}
		
		return list;
	}
}
