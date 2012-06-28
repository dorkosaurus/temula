package com.temula.dataprovider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import junit.framework.TestCase;

public class TestDataProvider extends TestCase {
	static final Logger logger = Logger.getLogger(JDBCDataProvider.class.getName());

	public void testResourceBundle(){
		JDBCDataProvider provider = new JDBCDataProvider();
		
		ResourceBundle bundle = provider.getResourceBundle();
		assertNotNull("resource bundle was null",bundle);

		String connectionURL = (String)bundle.getObject("connection_url");
		assertNotNull("connection_url property returned null from the resource bundle",connectionURL);

		String connectionUserName = (String)bundle.getObject("connection_username");
		assertNotNull("connection_username property returned null from the resource bundle",connectionURL);

		String connectionPassword = (String)bundle.getObject("connection_password");
		assertNotNull("connection_password property returned null from the resource bundle",connectionURL);

		
	}
	
	public void testValidConnection(){
		JDBCDataProvider provider = new JDBCDataProvider();
		Connection conn = provider.getConnection();
		assertNotNull(conn);
		try{
			conn.close();
		}
		catch(SQLException e){
			fail("problem closing connection");
			e.printStackTrace();
		}
	}
	
}
