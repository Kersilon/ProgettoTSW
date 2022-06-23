<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" import="planetGaming.Ordine.*"%>

<%
	//Check user credentials
	Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
	
	if((isLogged == null) || isLogged.equals(false))
	{
		response.sendRedirect("./login-form.jsp");
	}
	
	Collection<?> ordini = (Collection<?>) request.getSession().getAttribute("ordini");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders</title>
</head>
<body>
	<script type="text/javascript" src="ControllaCredenziali.js"></script>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<h1>Welcome to the user page</h1><br>
	<p>Here you can add, delete, payment methods and delivery addresses</p>
	<br><br>
	
	<h2>Payment methods</h2>
	<table>
		<tr>
			<th>id Order</th>
			<th>Videogames</th>
	  	</tr>
	  	
		  <!-- contenuto tabella -->
		  
		<%
	  		//scorre l'array ordini e stampa gli elementi nella tabella
	  		Iterator<?> it = ordini.iterator();
				while (it.hasNext()) {
					OrdineBean ordine = (OrdineBean) it.next();
		%>
			<tr>
				<td><%=ordine.getIdOrdine()%></td>
		  	</tr>
			
	<%
				}
	%>

	</table>
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>