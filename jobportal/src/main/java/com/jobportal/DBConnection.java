package com.jobportal;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConnection {

	private  BasicDataSource ds = null;
	public DBConnection() {
		
		this.ds = new BasicDataSource();
		this.ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		this.ds.setUrl("jdbc:mysql://localhost:3306/jobportal");
        this.ds.setUsername("root");
        this.ds.setPassword("root");
        this.ds.setMinIdle(5);
        this.ds.setMaxIdle(10);
        this.ds.setMaxOpenPreparedStatements(100);
	}
	
	public  Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }
	 
	
	
}
