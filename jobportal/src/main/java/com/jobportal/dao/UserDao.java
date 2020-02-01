package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jobportal.DBConnection;
import com.jobportal.request.UserCreateRequest;

@Repository
public class UserDao {

	
	
	@Autowired
	DBConnection dbConnection;
	
	
	public void insert(UserCreateRequest userCreateRequest) throws SQLException {
		
		String sqlQuery = "insert into User(fname, lname, age,location,mobileNo,email,role,experience) " +
                "values (?,?,?,?,?,?,?,?)";
		 PreparedStatement prepareStatement = null;
		try {
			
			 Connection con= dbConnection.getConnection();
			 prepareStatement = con.prepareStatement(sqlQuery);
			 prepareStatement.setString(1, userCreateRequest.getfName());
			 prepareStatement.setString(2, userCreateRequest.getlName());
			 prepareStatement.setInt(3, userCreateRequest.getAge());
			 prepareStatement.setString(4, userCreateRequest.getLocation());
			 prepareStatement.setLong(5, userCreateRequest.getMobileNumber());
			 prepareStatement.setString(6, userCreateRequest.getEmailId());
			 prepareStatement.setString(7, userCreateRequest.getRole());
			 prepareStatement.setInt(8, userCreateRequest.getExperience());
			 
			 prepareStatement.execute();
			 
		}finally {
			if(prepareStatement != null) {
				prepareStatement.close();
			}
		}
		
	}
	
	public int getUserId(int userId) throws Exception {

		String sqlQuery = "SELECT id from User where id=?";
		int id = 0;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			Connection con = dbConnection.getConnection();
			psmt = con.prepareStatement(sqlQuery);
			psmt.setInt(1, userId);
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
	
	public boolean isUserPresent(long mobileNo,String emailNo) throws Exception {

		String sqlQuery = "SELECT id from User where mobileNo=? and email=? ";
		boolean present = false;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			Connection con = dbConnection.getConnection();
			psmt = con.prepareStatement(sqlQuery);
			psmt.setLong(1, mobileNo);
			psmt.setString(2, emailNo);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				present = true;
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			
			if(psmt != null) {
				psmt.close();
			}
		}

		return present;
	}
}
