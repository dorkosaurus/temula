package com.temula.dataprovider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response.Status;

import com.temula.location.LocationResource;
import com.temula.location.Space;

public class JDBCDataProvider {
	static final Logger logger = Logger.getLogger(JDBCDataProvider.class.getName());

	ResourceBundle bundle = null;
	protected ResourceBundle getResourceBundle(){
		if(this.bundle!=null)return bundle;
		this.bundle=ResourceBundle.getBundle("temulaspace");
		return this.bundle;
	}

	
	protected Connection getConnection(){
		ResourceBundle bundle = this.getResourceBundle();
		String connectionURL = (String)bundle.getObject("connection_url");
		String connectionUserName = (String)bundle.getObject("connection_username");
		String connectionPassword = (String)bundle.getObject("connection_password");
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(connectionURL,connectionUserName,connectionPassword);
		}
		catch(SQLException e){
			logger.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	public List<Space> get(Space attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	public Status put(Space t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Status post(List<Space> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Status delete(Space t) {
		// TODO Auto-generated method stub
		return null;
	}

}
