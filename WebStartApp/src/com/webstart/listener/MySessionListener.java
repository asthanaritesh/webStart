package com.webstart.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

@WebListener
public class MySessionListener implements HttpSessionListener {
	private final Logger logger = Logger.getLogger(MySessionListener.class);
    public void sessionCreated(HttpSessionEvent sessionEvent) {
    	logger.info("Session Created:: ID="+sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    	logger.info("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }
	
}
