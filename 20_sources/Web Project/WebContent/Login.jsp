<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="de.rowbuddy.business.RowBuddyFacade"
	import="de.rowbuddy.entities.Member"
	import="javax.naming.InitialContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="rowBuddyDesign.css" type="text/css">
<title>Login</title>
</head>
<body>
<%
	InitialContext ic = new InitialContext();

	RowBuddyFacade rbf = (RowBuddyFacade) ic.lookup("java:global/Ear_Project/EJB_Project/RowBuddyFacade");

	String password = request.getParameter("password");
	String email = request.getParameter("email");

	if (!(email == null || password == null)) {
		Member member = new Member();
		member.setEmail(email);
		member.setPassword(password);
		rbf.login(member);
		session.setAttribute("rbf",rbf);
        response.sendRedirect("welcomeGWT.html?gwt.codesvr=127.0.0.1:9997");
%>
<%
	} else {
%>
<form action="Login.jsp" method="post">E-Mail: <input type="text"
	name="email" /><br>
Passwort: <input type="password" name="password" /><br>
<input type="submit" value="Login" class="buttonLogin buttonPositive"/></form>
<%
	}
%>
</body>
</html>