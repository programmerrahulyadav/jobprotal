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
	 
	public void insert(int companyId, int jobId) throws Exception {

		PreparedStatement preparedStatement = null;

		try {
			
			Connection con = dbConnection.getConnection();
			String sql = "INSERT INTO JobCompanyMap(companyId,jobId) VALUES(?,?) ";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, companyId);
			preparedStatement.setInt(2, jobId);

			preparedStatement.executeUpdate();
		} finally {
			
				if(preparedStatement!= null) {
					preparedStatement.close();
				}
		}

	}
}
