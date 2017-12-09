package com.webstart.session;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import com.webstart.dao.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("user");
		String password = request.getParameter("password");
		String errorMsg = null;
		if (email == null || email.equals("")) {
			errorMsg = "UserBean Email can't be null or empty";
		}
		else if (password == null || password.equals("")) {
			errorMsg = "Password can't be null or empty";
		}

		if (errorMsg != null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/HTMLs/Login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>" + errorMsg + "</font>");
			rd.include(request, response);
		} else {

			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(
						"select id,name,email,age,role,country from Users where email=? and password=? limit 1");
				ps.setString(1, email);
				ps.setString(2, password);
				rs = ps.executeQuery();

				if (rs != null && rs.next()) {
					Cookie loginCookie = new Cookie("user",rs.getString("name"));
					//setting cookie to expiry in 30 mins
					loginCookie.setMaxAge(30*60);
					response.addCookie(loginCookie);

					UserBean user = new UserBean(rs.getString("name"), rs.getString("email"), rs.getString("role"), rs.getInt("age"), rs.getString("country"), rs.getInt("id"));
					logger.info("User found with details=" + user);
					HttpSession session = request.getSession();
					session.setAttribute("User", user);
					response.sendRedirect(request.getContextPath()+"/JSPs/Home.jsp");
					;
				} else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/HTMLs/Login.html");
					PrintWriter out = response.getWriter();
					logger.error("User not found with email=" + email);
					out.println("<font color=red>No user found with given email id, please register first.</font>");
					rd.include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("Database connection problem");
				throw new ServletException("DB Connection problem.");
			} finally {
				try {
					rs.close();
					ps.close();
				} catch (SQLException e) {
					logger.error("SQLException in closing PreparedStatement or ResultSet");
				}
			}
		}
	}
}
