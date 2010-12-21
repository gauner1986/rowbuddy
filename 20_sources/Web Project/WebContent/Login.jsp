<%@page import="de.rowbuddy.exceptions.NotLoggedInException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="de.rowbuddy.boundary.RowBuddyFacade"
	import="de.rowbuddy.entities.Member"
	import="javax.naming.InitialContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="rowBuddyDesign.css" type="text/css">
<link rel="SHORTCUT ICON" href="icons/favicon.ico" type="image/x-icon">
<title>Login</title>
</head>
<body>
<form action="Login.jsp" method="post">
<table align="center">
	<tr>
		<td>
		<table class="LoginMask" cellspacing="15">
			<tr>
				<td colspan="2"><img src="icons/logo.png" alt="Header" /></td>
			</tr>
			<tr>
				<td colspan="2" style="color: #d12f19;">
				<%
					InitialContext ic = new InitialContext();

					RowBuddyFacade rbf = (RowBuddyFacade) ic
							.lookup("java:global/Ear_Project/EJB_Project/RowBuddyFacade");

					String password = request.getParameter("password");
					String email = request.getParameter("email");

					if (!(email == null || password == null)) {
						try {
							rbf.login(email, password);
							session.setAttribute("rbf", rbf);
							response.sendRedirect("index.html?gwt.codesvr=127.0.0.1:9997");
							//response.sendRedirect("index.html");
						} catch (NotLoggedInException ex) {
				%> <%=ex.getMessage()%> <%
 	}
 	}
 %>
				
			</tr>
			<tr>
				<td align="right">Email:</td>
				<td align="left"><input type="text" name="email"
					style="width: 150px;" /></td>
			</tr>

			<tr>
				<td align="right">Passwort:</td>
				<td align="left"><input type="password" name="password"
					style="width: 150px;" /></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit"
					value="Login" class="buttonLogin buttonPositive" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td style="background: #efefef; text-align: center; font-size: 10px;">
		<a href="http://code.google.com/p/rowbuddy/">Open Source</a> <br />
		Participants: Daniel Chiaradia, Georg Fleischer, Sebastian Heineke,
		Jan Nikolai Trzeszkowski, Lydia Wall</td>
	</tr>
</table>
</form>
</body>
</html>