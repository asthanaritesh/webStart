package com.webstart.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Servlet Tutorial - Servlet Example
 */
@WebServlet(
		description = "Login Servlet", 
		urlPatterns = { "/LoginServlet" }, 
		initParams = { 
				@WebInitParam(name = "user", value = "default", description = "Email from webmaster"), 
				@WebInitParam(name = "password", value = "default")
		})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(LoginServlet.class);
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get request parameters for userID and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		
		//get servlet config init params
		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");
		//logging example
		log("User="+user+"::password="+pwd);
		logger.info("User="+user+"::password="+pwd);
		
		if(userID.equals(user) && password.equals(pwd)){
			//ctx.setAttribute is optional, just to show listener capability.
			ServletContext ctx = request.getServletContext();
			ctx.setAttribute("User", user);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("user", user);
			userName.setMaxAge(30*60);
			response.addCookie(userName);
			String encodedURL = response.encodeRedirectURL("JSPs/LoginSuccess.jsp");
			response.sendRedirect(encodedURL);			
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/HTMLs/loginWelcome.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong. Try Again.</font>");
			rd.include(request, response);			
		}
		
	}
}
