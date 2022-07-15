<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" import="planetGaming.Ordine.*" import="planetGaming.Utente.*"%>

<%
	//Check user credentials
	Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
	
	if(isAdmin == null || isAdmin.equals(false))
	{
		response.sendRedirect("./login-form.jsp");
	}
	
	Collection<?> ordiniUtenti = (Collection<?>) request.getSession().getAttribute("ordiniUtenti");
	Collection<?> utenti = (Collection<?>) request.getSession().getAttribute("utenti");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Administrator Page</title>
</head>
<body>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<h1>Welcome to the Administrator page</h1><br>
	<p></p>
	<br><br>
	
	
	
	<form action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="clean"> 
		<input type="submit" value="Clean all"/>
	</form>
	
	<form action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowOrders"> 
		<input type="submit" value="Orders"/>
	</form>
	
	<form action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowOrdersByUser"> 
		<p>User id: <input type="number" name="OrdersByUserId" required></p>
		<input type="submit" value="User's Orders"/>
	</form>
	
	<form action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowOrdersByDate"> 
		<p>Date inferior limit: <input type="date" name="DateMin" required></p>
		<p>Date superior limit: <input type="date" name="DateMax" required></p>
		<input type="submit" value="Orders by date"/>
	</form>
	
	<form action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowUsers"> 
		<input type="submit" value="Users"/>
	</form>
	
	<form action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowUser">
		<p>User id: <input type="number" name="UserId" required></p>
		<input type="submit" value="User by id"/>
	</form>
	

	
	<% 	
			if(ordiniUtenti != null && !ordiniUtenti.isEmpty()){
	%>
	
	
				<h2>Orders</h2>
				<table>
					<tr>
						<th>idOrdine</th>
						<th>idUtente</th>
						<th>idModalitaPagamento</th>
						<th>idIndirizzo</th>
						<th>prezzoTotale</th>
						<th>data</th>
						<th>tracking</th>
				  	</tr>
					  <!-- contenuto tabella -->
	  
	<%
		  		//scorre l'array metodi di pagamento e stampa gli elementi nella tabella
		  		Iterator<?> it = ordiniUtenti.iterator();
					while (it.hasNext()) {
						OrdineBean ordine = (OrdineBean) it.next();
	%>
				<tr>
					<td><%=ordine.getIdOrdine()%></td>
					<td><%=ordine.getIdUtente()%></td>
					<td><%=ordine.getIdModalitaPagamento()%></td>
					<td><%=ordine.getIdIndirizzo()%></td>
					<td><%=ordine.getPrezzoTotale()%></td>
					<td><%=ordine.getDataOrdine()%></td>
					<td><%=ordine.getTracking()%></td>

			  	</tr>
			
	<%
				}
	%>

				</table>
	<%	
			}	
	%>
	
	
	
	<% 	
			if(utenti != null && !utenti.isEmpty()){
	%>
	
	
				<h2>Utenti</h2>
				<table>
					<tr>
						<th>User code</th>
						<th>name</th>
						<th>surname</th>
						<th>Birth date</th>
						<th>Username</th>
						<th>Password</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Purchases</th>
						<th>is Administrator?</th>
						<th>Sing in date</th>
				  	</tr>
					  <!-- contenuto tabella -->
	  
	<%
		  		//scorre l'array metodi di pagamento e stampa gli elementi nella tabella
		  		Iterator<?> it = utenti.iterator();
					while (it.hasNext()) {
						UtenteBean utente = (UtenteBean) it.next();
	%>
				<tr>
					<td><%=utente.getCodiceUtente()%></td>
					<td><%=utente.getNome()%></td>
					<td><%=utente.getCognome()%></td>
					<td><%=utente.getDataNascita()%></td>
					<td><%=utente.getNomeUtente()%></td>
					<td><%=utente.getPassword()%></td>
					<td><%=utente.getEmail()%></td>
					<td><%=utente.getTelefono()%></td>
					<td><%=utente.getAcquisti()%></td>
					<td><%=utente.isAMMINISTRATORE()%></td>
					<td><%=utente.getDataRegistrazione()%></td>
			  	</tr>
			
	<%
				}
	%>

				</table>
	<%	
			}	
	%>
				
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>