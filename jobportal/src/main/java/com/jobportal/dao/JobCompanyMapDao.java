package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jobportal.DBConnection;

@Repository
public class JobCompanyMapDao {

	@Autowired
	DBConnection dbConnection;
	 
	public void insert(int companyId, int jobId) {

		PreparedStatement preparedStatement = null;

		try {
			System.out.println("companyId : "+companyId);
			System.out.println("jobId : "+jobId);
			Connection con = dbConnection.getConnection();
			String sql = "INSERT INTO JobCompanyMap(companyId,jobId) VALUES(?,?) ";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, companyId);
			preparedStatement.setInt(2, jobId);

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
