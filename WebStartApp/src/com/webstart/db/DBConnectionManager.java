package com.webstart.db;

import java.sql.Connection;

public class DBConnectionManager {

	private String dbURL;
	private String user;
	private String password;
	private Connection con;
	
	public DBConnectionManager(String url, String u, String p){
		this.dbURL=url;
		this.user=u;
		this.password=p;
		//TODO: create db connection now
		
	}
	
	public Connection getConnection(){
		return this.con;
	}
	
	public void closeConnection(){
		//TODO: close DB connection here
	}
}