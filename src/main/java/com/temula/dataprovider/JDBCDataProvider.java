package com.temula.dataprovider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response.Status;

import com.temula.location.Space;
import com.temula.location.SpaceAvailability;
import com.temula.location.SpaceAvailabiltyQuery;

public class JDBCDataProvider {
	static final Logger logger = Logger.getLogger(JDBCDataProvider.class.getName());

	ResourceBundle bundle = null;
	static boolean loadedDriver=false;

	protected ResourceBundle getResourceBundle(){
		if(this.bundle!=null)return bundle;
		this.bundle=ResourceBundle.getBundle("temulaspace");
		return this.bundle;
	}

	private static void loadDriver(){
		if(loadedDriver)return;
		try {
			loadedDriver=true;
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch ( java.lang.ClassNotFoundException e ) {
			logger.warning("MySQL JDBC Driver not found ... ");
		}		
	}
	
	
	protected Connection getConnection(){
		loadDriver();
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
	
	
	
	public List<SpaceAvailability> get(SpaceAvailabiltyQuery query) {
		Connection conn = this.getConnection();
		String sql = 
			"select " +
			"v_space_availability.*," +
			"space.spc_name " +
			"from v_space_availability " +
			"join space on v_space_availability.space_id=space.spc_id;";
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<SpaceAvailability> al = new ArrayList<SpaceAvailability>();
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null && rs.next()){
				SpaceAvailability sa = new SpaceAvailability();
				sa.setNumDoubleACRooms(rs.getInt("num_double_ac"));
				sa.setNumNonACRooms(rs.getInt("num_non_ac"));
				sa.setNumSingleACRooms(rs.getInt("num_single_ac"));
				sa.setRupeesDoubleACRoom(rs.getFloat("rupees_double_ac"));
				sa.setRupeesNonACRoom(rs.getFloat("rupees_non_ac"));
				sa.setRupeesSingleACRoom(rs.getFloat("rupees_single_ac"));
				sa.setSpaceId(rs.getInt("space_id"));
				sa.setSpaceName(rs.getString("spc_name"));
				sa.setWeek(rs.getDate("week"));
				al.add(sa);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null)rs.close();
				rs=null;
				if(ps!=null)ps.close();
				ps=null;
				if(conn!=null)conn.close();conn=null;
			}
			catch (SQLException e){
				e.printStackTrace();
			}
		}
		return al;
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
