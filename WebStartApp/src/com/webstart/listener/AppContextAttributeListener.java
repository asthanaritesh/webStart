package com.webstart.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.webstart.session.RequestLoggingFilter;

@WebListener
public class AppContextAttributeListener implements ServletContextAttributeListener {
	private final Logger logger = Logger.getLogger(AppContextAttributeListener.class);

    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
    	logger.info("ServletContext attribute added::{"+servletContextAttributeEvent.getName()+","+servletContextAttributeEvent.getValue()+"}");
    }

    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
    	logger.info("ServletContext attribute replaced::{"+servletContextAttributeEvent.getName()+","+servletContextAttributeEvent.getValue()+"}");
    }

    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
    	logger.info("ServletContext attribute removed::{"+servletContextAttributeEvent.getName()+","+servletContextAttributeEvent.getValue()+"}");
    }
	
}
