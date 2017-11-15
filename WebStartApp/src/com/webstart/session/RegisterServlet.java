package com.webstart.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.webstart.util.UserBean;

@WebServlet(name = "Register", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(RegisterServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspname = request.getParameter("jspname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		String errorMsg = null;
		if(email == null || email.equals("")){
			errorMsg = "Email ID can't be null or empty.";
		}
		if(jspname.equals("Registration") && (password == null || password.equals(""))){
			errorMsg = "Password can't be null or empty.";
		}
		if(name == null || name.equals("")){
			errorMsg = "Name can't be null or empty.";
		}
		if(country == null || country.equals("")){
			errorMsg = "Country can't be null or empty.";
		}
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/HTMLs/Register.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
			}
		else{
			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
			HttpSession session = request.getSession();
			UserBean ub = (UserBean) session.getAttribute("User");
			String oldEmail = ub.getEmail();
			PreparedStatement ps = null;
			try {
				if(jspname.equals("Registration"))
					ps = con.prepareStatement("insert into Users(name,email,country,password) values (?,?,?,?)");
				else if(jspname.equals("AccountInfoUpdation")) 
					ps = con.prepareStatement("update Users SET name=?,email=?,country=? where email = ?");
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, country);
				if(jspname.equals("Registration"))
					ps.setString(4, password);
				else ps.setString(4, oldEmail);
				ps.execute();
				
				logger.info("User registered with email="+email);
				
				//forward to login page to login
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/HTMLs/Login.html");
				PrintWriter out= response.getWriter();
				if(jspname.equals("Registration"))
					out.println("<font color=green>Registration successful, please login below.</font>");
				else if(jspname.equals("AccountInfoUpdation"))
					out.println("<font color=green>Updation successful, please login below.</font>");
				rd.include(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("Database connection problem");
				throw new ServletException("DB Connection problem.");
			}finally{
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error("SQLException in closing PreparedStatement");
				}
			}
		}		
	}
}
