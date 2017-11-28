<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" 
    import="com.webstart.dao.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Account Information Page</title>
</head>
<body>
<%
//allow access only if session exists
if(session.getAttribute("User") == null){
	response.sendRedirect("../HTMLs/Welcome.html");
}
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
%>
<h3>Hi <%=userName %>, This is your Account Information:</h3><br>
<%UserBean ub = (UserBean) session.getAttribute("User");%>

<form action="/WebStartApp/RegisterServlet" method="post">
 <h3>Name: <input type="text" name="name" value=<%=ub.getName()%> class="field left"></h3>
 <h3>Email: <input type="text" name="email" value=<%=ub.getEmail()%> class="field left"></h3>
 <h3>Role: <input type="text" name="role" value=<%=ub.getRole()%> readonly class="field left"></h3>
 <h3>Age: <input type="text" name="age" value=<%=ub.getAge()%> class="field left"></h3>
 <h3>Country: <input type="text" name="country" value=<%=ub.getCountry()%> class="field left"></h3>
 <input type="hidden" name="jspname" value="AccountInfoUpdation" />
 <h3><input type="submit" value="Update Information"><h3><br><br><br>
</form>

<input type="submit" value="Logout" >
</form>
</body>
</html>