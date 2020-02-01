package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jobportal.DBConnection;
import com.jobportal.request.JobCreateRequest;

@Repository
public class JobDao {

	@Autowired
	DBConnection  dbConnection;
	
	public int insert(JobCreateRequest jobCreateRequest) throws SQLException {
		
		String sql = " insert into Job (jobDescription,skill,minExpReqired,industryRelatedTo) VALUES(?,?,?,?)";
		PreparedStatement  preparedStatement = null;
		int jobId = 0;
		try {
			
			Connection con = dbConnection.getConnection();
			preparedStatement = con.prepareStatement(sql,1);
			
			preparedStatement.setString(1, jobCreateRequest.getJobDescription());
			preparedStatement.setString(2, jobCreateRequest.getSkills());
			preparedStatement.setInt(3, jobCreateRequest.getMinExpRequired());
			preparedStatement.setString(4, jobCreateRequest.getIndustryRelatedTo());
			
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			jobId = 0;
			if(rs.next()) {
				
				jobId = rs.getInt(1);
			}
			
		} finally {
			
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			
		}
		
		
		return jobId;
		
	}
	
	public void delete(int jobId) throws Exception {
		
		String sql = " DELETE from Job where id in(?)";
		PreparedStatement  preparedStatement  = null;
		try {
			Connection con = dbConnection.getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, jobId);
			preparedStatement.executeUpdate();
			
		} finally {

			preparedStatement.close();
		}
		
		
		
	}
	
	public int getJobId(int jobId) throws Exception {

		String sqlQuery = "SELECT id from Job where id=?";
		int id = 0;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			Connection con = dbConnection.getConnection();
			psmt = con.prepareStatement(sqlQuery);
			psmt.setInt(1, jobId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			
		}

		return id;
	}
	
	
}
