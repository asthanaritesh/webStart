package com.webstart.listener;

import java.io.File;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.webstart.db.DBConnectionManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

@WebListener
public class AppContextListener implements ServletContextListener {
	private final Logger logger = Logger.getLogger(AppContextListener.class);
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	
    	String url = ctx.getInitParameter("DBURL");
    	String u = ctx.getInitParameter("DBUSER");
    	String p = ctx.getInitParameter("DBPWD");
    	
    	//initialize log4j
    	String log4jConfig = ctx.getInitParameter("log4j-config");
    	if(log4jConfig == null){
    		System.err.println("No log4j-config init param, initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
    	}else {
			String webAppPath = ctx.getRealPath("/");
			String log4jProp = webAppPath + log4jConfig;
			File log4jConfigFile = new File(log4jProp);
			if (log4jConfigFile.exists()) {
				System.out.println("Initializing log4j with: " + log4jProp);
				DOMConfigurator.configure(log4jProp);
			} else {
				System.err.println(log4jProp + " file not found, initializing log4j with BasicConfigurator");
				BasicConfigurator.configure();
			}
		}
    	
    	//create database connection from init parameters and set it to context
    	DBConnectionManager dbManager = null;
		try {
			dbManager = new DBConnectionManager(url, u, p);
			ctx.setAttribute("DBConnection", dbManager.getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
    	logger.info("Database connection initialized successfully.");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	DBConnectionManager DBConnection = (DBConnectionManager) ctx.getAttribute("DBConnection");
    	try {
    		DBConnection.closeConnection();
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	logger.info("Database connection closed for Application.");    	
    }
	
}