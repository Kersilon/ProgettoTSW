<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" import="planetGaming.Ordine.*" import="planetGaming.Utente.*"%>

<%
	//Check user credentials
	Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
	
	if(isAdmin == null || isAdmin.equals(false))
	{
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-form.jsp");
		dispatcher.forward(request, response);
	}
	
	Collection<?> ordiniUtenti = (Collection<?>) request.getSession().getAttribute("ordiniUtenti");
	Collection<?> utenti = (Collection<?>) request.getSession().getAttribute("utenti");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="inputFieldStyle.css">
<link rel="stylesheet" href="body.css">
<link rel="stylesheet" href="trueStorageStyle.css">
<link rel="stylesheet" href="productFlexTable.css">

<meta charset="ISO-8859-1">
<title>Administrator Page</title>
</head>
<body>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<h1>Welcome to the Administrator page</h1>
	
	
	<div class="flex-container">
	<form class="inputFieldContainer inputList" action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="clean"> 
		<input type="submit" value="Clean all"/>
	</form>
	
	<form class="inputFieldContainer inputList" action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowOrders"> 
		<input type="submit" value="Orders"/>
	</form>
	
	<form class="inputFieldContainer inputList" action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowOrdersByUser"> 
		<p>User id: <input type="number" name="OrdersByUserId" required></p>
		<input type="submit" value="User's Orders"/>
	</form>
	
	<form class="inputFieldContainer inputList" action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowOrdersByDate"> 
		<p>Date inferior limit: <input type="date" name="DateMin" required></p>
		<p>Date superior limit: <input type="date" name="DateMax" required></p>
		<input type="submit" value="Orders by date"/>
	</form>
	
	<form class="inputFieldContainer inputList" action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowUsers"> 
		<input type="submit" value="Users"/>
	</form>
	
	<form class="inputFieldContainer inputList" action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowUser">
		<p>User id: <input type="number" name="UserId" required></p>
		<input type="submit" value="User by id"/>
	</form>
	
	<form class="inputFieldContainer inputList" action="AdministratorPageServlet" method="get">
		<input type="hidden" name="action" value="ShowUserByFilter">
		<p>User's fiscal code: <input type="text" name="UserFiscalCode"></p>
		<p>User's name: <input type="text" name="UserName"></p>
		<p>User's surname: <input type="text" name="UserSurname"></p>
		<input type="submit" value="Search user"/>
	</form>
	</div>

	
	<% 	
			if(ordiniUtenti != null && !ordiniUtenti.isEmpty()){
	%>
	
	
				<h2>Orders</h2>

	  
	<%
		  		//scorre l'array metodi di pagamento e stampa gli elementi nella tabella
		  		Iterator<?> it = ordiniUtenti.iterator();
					while (it.hasNext()) {
						OrdineBean ordine = (OrdineBean) it.next();
	%>
	
		    <div id="<%=ordine.getIdOrdine()%>" class="flex-container">
	    			<p>idOrdine:<span class="idCell"><%=ordine.getIdOrdine()%></span> | idUtente: <span class=""><%=ordine.getIdUtente()%></span>  | idModalitaPagamento: <span class=""><%=ordine.getIdModalitaPagamento()%></span></p>
	    			<p>idIndirizzo: <span class=""><%=ordine.getIdIndirizzo()%></span> | prezzoTotale: <span class=""><%=ordine.getPrezzoTotale()%></span> | data: <span class=""><%=ordine.getDataOrdine()%></span></p>
	    			<p>tracking: <span class=""><%=ordine.getTracking()%></span></p>
       	</div>
	
	<%
				}
	%>

	<%	
			}	
	%>
	
	
	
	<% 	
			if(utenti != null && !utenti.isEmpty()){
	%>

	  		<h2>Utenti</h2>
	<%
		  		//scorre l'array metodi di pagamento e stampa gli elementi nella tabella
		  		Iterator<?> it = utenti.iterator();
					while (it.hasNext()) {
						UtenteBean utente = (UtenteBean) it.next();
	%>
	
	
			
		    <div id="<%=utente.getCodiceUtente()%>" class="flex-container">
	    			<p>UserId:<span class="idCell"><%=utente.getCodiceUtente()%></span> | Fiscal code: <span class=""><%=utente.getCodiceFiscale()%></span>  | name: <span class=""><%=utente.getNome()%></span></p>
	    			<p>surname: <span class=""><%=utente.getCognome()%></span> | Birth date: <span class=""><%=utente.getDataNascita()%></span> | Username: <span class=""><%=utente.getNomeUtente()%></span></p>
	    			<p>Password: <span class=""><%=utente.getPassword()%></span> | Email: <span class=""><%=utente.getEmail()%></span> | Phone: <span class=""><%=utente.getTelefono()%></span></p>
	    			<p>Purchases: <span class=""><%=utente.getAcquisti()%></span> | is Administrator?: <span class=""><%=utente.isAMMINISTRATORE()%></span> | Sing in date: <span class=""><%=utente.getDataRegistrazione()%></span></p>
    		</div>

			
	<%
				}
	%>

	<%	
			}	
	%>
				
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>