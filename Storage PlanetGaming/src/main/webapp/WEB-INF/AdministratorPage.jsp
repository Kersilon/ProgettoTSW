<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" %>

<%
	//Check user credentials
	Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
	
	if((isLogged == null) || isLogged.equals(false))
	{
		//response.sendRedirect("./login-form.jsp");
	}
	
	Collection<?> ordiniUtenti = (Collection<?>) request.getSession().getAttribute("ordiniUtenti");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Administrator Page</title>
</head>
<body>
	<form action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowOrders"> 
		<input type="submit" value="Orders"/>
	</form>
	
	<form action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowOrdersByUser"> 
		<input type="number" name="OrdersByUserId">
		<input type="submit" value="User's Orders"/>
	</form>
	
	
		<% 	
			if(ordiniUtenti != null){
		%>
			<p>
				TEST: <%=ordiniUtenti.isEmpty()%>
			</p>

		<%	
			}	
		%>

		
</body>
</html>