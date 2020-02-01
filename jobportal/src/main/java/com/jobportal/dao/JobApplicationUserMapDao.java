package com.jobportal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jobportal.DBConnection;
import com.jobportal.constant.ApplicationStatus;
import com.jobportal.request.ApplyJobRequest;
import com.jobportal.response.JobApplicantsResponse;
import com.jobportal.response.UserApplyiedJobResponse;

@Repository
public class JobApplicationUserMapDao {

	@Autowired
	DBConnection dbConnection;
	
	public void insert(ApplyJobRequest applyJobRequest) throws Exception {
		
		PreparedStatement preparedStatement = null;
		try {
			Connection con = dbConnection.getConnection();
			String sql = "insert into JobApplicationUserMap (jobId,userId,applicationStatus,appliyedDate,statusUpdateDate) VALUES(?,?,?,?,?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, applyJobRequest.getJobId());
			preparedStatement.setInt(2, applyJobRequest.getUserId());
			preparedStatement.setString(3,ApplicationStatus.APPLIYED.name());
			preparedStatement.setDate(4,Date.valueOf(LocalDate.now()));
			preparedStatement.setDate(5,Date.valueOf(LocalDate.now()));
			
			preparedStatement.executeUpdate();
			
		}
		finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			
		}
		
	}
	
	public List<UserApplyiedJobResponse> getListOfJobs(int userId) throws Exception {

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<UserApplyiedJobResponse> listOfMap = null;
		try {
			Connection con = dbConnection.getConnection();
			String sql = " SELECT j.jobDescription as jd ,c.companyName as company,j.id as jobId from JobApplicationUserMap as jaum INNER JOIN Job as j ON jaum.jobId = j.id "
					+ " INNER JOIN User u ON jaum.userId = u.id "
					+ " INNER JOIN JobCompanyMap jcm ON jaum.jobId = jcm.jobId "
					+ " INNER JOIN Company c ON c.id = jcm.companyId "
					+ " WHERE u.id=? ";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			rs = preparedStatement.executeQuery();
			listOfMap = new ArrayList<UserApplyiedJobResponse>();
			while(rs.next()) {
				
				UserApplyiedJobResponse userApplyiedJobResponse = new UserApplyiedJobResponse();
				userApplyiedJobResponse.setJobDescription(rs.getString("jd"));
				userApplyiedJobResponse.setCompany( rs.getString("company"));
				userApplyiedJobResponse.setJobId(rs.getInt("jobId"));
				listOfMap.add(userApplyiedJobResponse);
			}

		} finally {
			preparedStatement.close();
		}

		return listOfMap;
	}
	
	public void update(int jobId,int userId,String applicationStatus) throws Exception {

		PreparedStatement preparedStatement = null;
		try {
			Connection con = dbConnection.getConnection();
			String sql = "UPDATE JobApplicationUserMap set applicationStatus=?,statusUpdateDate=? where jobId=? and userId=? ";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, applicationStatus);
			preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
			preparedStatement.setInt(3, jobId);
			preparedStatement.setInt(4, userId);

			preparedStatement.executeUpdate();

		} finally {
			
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			
		}

	}
	
	public String get(int jobId,int userId) throws Exception {

		String status = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			Connection con = dbConnection.getConnection();
			String sql = "SELECT applicationStatus from JobApplicationUserMap where jobId=? and  userId=? ";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, jobId);
			preparedStatement.setInt(2, userId);

			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				status = rs.getString("applicationStatus");
			}

		} finally {
			preparedStatement.close();
		}
		
		return status;

	}
	
	public boolean isPresent(int jobId,int userId) throws Exception {

		boolean status = true;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			Connection con = dbConnection.getConnection();
			String sql = "SELECT count(*) as  count from JobApplicationUserMap where jobId=? and  userId=? ";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, jobId);
			preparedStatement.setInt(2, userId);

			rs = preparedStatement.executeQuery();
			int count = 0;
			if(rs.next()) {
				count = rs.getInt("count");
				status = count > 0 ? true : false;
			}

		} finally {
			
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			
		}
		
		return status;

	}
	
	public List<JobApplicantsResponse> getListOfApplicant(int jobId) throws Exception {

		String sqlQuery = " SELECT j.jobDescription as jd ,CONCAT(u.fname,\" \",u.lname) as name,u.mobileNo as mobileNo,u.email as email " + 
				" from JobApplicationUserMap as jaum INNER JOIN Job as j ON jaum.jobId = j.id " + 
				" INNER JOIN User as u ON jaum.userId = u.Id " + 
				 " where j.id=? ";
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<JobApplicantsResponse> listOfJobApplicants = new ArrayList<JobApplicantsResponse>();
		try {
			Connection con = dbConnection.getConnection();
			psmt = con.prepareStatement(sqlQuery);
			psmt.setInt(1, jobId);
			rs = psmt.executeQuery();
			while(rs.next()) {
				JobApplicantsResponse jobApplicantsResponse = new JobApplicantsResponse();
				jobApplicantsResponse.setJobDescription(rs.getString("jd"));
				jobApplicantsResponse.setMobileNo(rs.getLong("mobileNo"));
				jobApplicantsResponse.setName(rs.getString("name"));
				jobApplicantsResponse.setEmail(rs.getString("email"));
				
				listOfJobApplicants.add(jobApplicantsResponse);
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			
		}
		

		return listOfJobApplicants;
	}
}
