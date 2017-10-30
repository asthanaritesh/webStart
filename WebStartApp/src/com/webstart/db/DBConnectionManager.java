package com.webstart.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

	private String dbURL;
	private String user;
	private String password;
	private Connection con;
	
	public DBConnectionManager(String url, String u, String p) throws ClassNotFoundException, SQLException{
		this.dbURL=url;
		this.user=u;
		this.password=p;
		
		//Class.forName("com.mysql.jdbc.Driver");
		this.con = DriverManager.getConnection(url, u, p);		
	}
	
	public Connection getConnection(){
		return this.con;
	}
	
	public void closeConnection() throws SQLException{
		con.close();
	}
}