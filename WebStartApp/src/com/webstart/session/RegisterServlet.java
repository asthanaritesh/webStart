package com.webstart.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.apache.log4j.Logger;

import com.webstart.dao.UserBean;

@WebServlet(name = "Register", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(RegisterServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspname = request.getParameter("jspname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String role = request.getParameter("role");
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
		if(age == 0){
			errorMsg = "Age can't be null or empty.";
		}
		if(role == null || role.equals("")){
			errorMsg = "Role can't be null or empty.";
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
			SessionFactory factory = null;
			try {
				Configuration cfg=new Configuration();
				cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file 
				cfg.addClass(com.webstart.dao.UserBean.class);
			    //creating session factory object  
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
				factory = cfg.buildSessionFactory(serviceRegistry);
			}
	        catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
				logger.error("Hibernate Initialization error");
	            throw new ExceptionInInitializerError(ex);
	        }
			//creating session object  
			Session session_hbn=factory.openSession();  
			//creating transaction object  
			Transaction trans_hbn=session_hbn.beginTransaction();  
			
			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
			HttpSession session = request.getSession();
			String oldEmail="", oldName="", oldCountry="";
			int oldAge = 0;
			if(jspname.equals("AccountInfoUpdation")) {
				UserBean ub = (UserBean) session.getAttribute("User");
				oldEmail = ub.getEmail();
				oldName = ub.getName();
				oldAge = ub.getAge();
				oldCountry = ub.getCountry();
			}
			PreparedStatement ps = null;
			try {
				/*if(jspname.equals("Registration"))
					ps = con.prepareStatement("insert into Users(name,email,age,country,role,password) values (?,?,?,?,?,?)");
				else if(jspname.equals("AccountInfoUpdation")) 
					ps = con.prepareStatement("update Users SET name=?,email=?,age=?,country=? where email = ?");
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setInt(3, Integer.parseInt(age));
				ps.setString(4, country);
				if(jspname.equals("Registration")) {
					ps.setString(5, role);
					ps.setString(6, password);
				}
				else ps.setString(5, oldEmail);
				ps.execute();*/
				if(jspname.equals("Registration")) {
					UserBean user = new UserBean();
					user.setName(name);
					user.setEmail(email);
					user.setAge(age);
					user.setCountry(country);
					user.setRole(role);
					user.setPassword(password);
					session_hbn.save(user);
				}
				else if(jspname.equals("AccountInfoUpdation")) {
					Query selectQuery = session_hbn.createQuery("from UserBean where email =:curEmail");
					selectQuery.setParameter("curEmail", oldEmail);
					List<UserBean> list = selectQuery.list();
					UserBean userObj = (UserBean)session_hbn.load(UserBean.class, list.get(0).getId());
					//UserBean userObj = (UserBean)obj;
					if(!name.equals(oldName)) userObj.setName(name);
					if(!email.equals(oldEmail)) userObj.setEmail(email);
					if(age != oldAge) userObj.setAge(age);
					if(!country.equals(oldCountry)) userObj.setCountry(country);
				}
				trans_hbn.commit();
				session_hbn.close();
				
				if(jspname.equals("Registration")) 
					logger.info("User registered with email="+email);
				else logger.info("User Account Info updated with email="+email);
				
				//forward to login page to login
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/HTMLs/Login.html");
				PrintWriter out= response.getWriter();
				if(jspname.equals("Registration"))
					out.println("<font color=green>Registration successful, please login below.</font>");
				else if(jspname.equals("AccountInfoUpdation"))
					out.println("<font color=green>Updation successful, please login below.</font>");
				rd.include(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Database connection problem");
				throw new ServletException("DB Connection problem.");
			}
		}		
	}
}
