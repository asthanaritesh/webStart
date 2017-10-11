<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Do Something Page</title>
</head>
<body>
<%
//allow access only if session exists
if(session.getAttribute("user") == null){
	response.sendRedirect("../HTMLs/loginWelcome.html");
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
<h3>Hi <%=userName %>, Do Something.</h3>
<br>
<form action="<%=response.encodeURL("../LogoutServlet") %>" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>