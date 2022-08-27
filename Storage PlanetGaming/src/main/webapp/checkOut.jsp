<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*" import="planetGaming.MetodoPagamento.*" import="planetGaming.Indirizzo.*" import="planetGaming.Utente.*"%>

<%
	//Check user credentials
	Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
	
	if((isLogged == null) || isLogged.equals(false))
	{
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-form.jsp");
		dispatcher.forward(request, response);	
	}
	
	Collection<?> metodiPagamento = (Collection<?>) request.getSession().getAttribute("metodiPagamento");
	Collection<?> indirizzi = (Collection<?>) request.getSession().getAttribute("indirizzi");
	
%>

<!DOCTYPE html>
<html>
	<head>
	<link rel="stylesheet" href="trueStorageStyle.css">
	
	<meta charset="ISO-8859-1">
	<title>Protected Page</title>
	</head>
	<body>
		<script type="text/javascript" src="ControllaCredenziali.js"></script>
		<script type="text/javascript" src="HideShowButton.js"></script>
		<jsp:include page="/WEB-INF/header.jsp" />
		
		<h1>Welcome to the checkout</h1><br>
		<p>Here you can purchase products</p>
		<br><br>
		
		
		
		<%
				Iterator<?> it;
		%>
		
		
		
		<%
				if(metodiPagamento != null && !metodiPagamento.isEmpty()){
		%>
		
		<h2>Payment methods</h2>
		<table>
			<tr>
				<th>numero_carta</th>
				<th>ccv</th>
				<th>circuito</th>
				<th>scadenza</th>
				<th>codiceUtente</th>
				<th>nome_intestatario</th>
				<th>cognome_intestatario</th>
				<th>idCarta</th>
		  	</tr>
			  <!-- contenuto tabella -->
		  
			<%
		  		//scorre l'array metodi di pagamento e stampa gli elementi nella tabella
		  		it = metodiPagamento.iterator();
					while (it.hasNext()) {
						MetodoPagamentoBean metodoPagamento = (MetodoPagamentoBean) it.next();
			%>
				<tr>
					<td><%=metodoPagamento.getNumero_carta()%></td>
					<td><%=metodoPagamento.getCcv()%></td>
					<td><%=metodoPagamento.getCircuito()%></td>
					<td><%=metodoPagamento.getScadenza()%></td>
					<td><%=metodoPagamento.getCodiceUtente()%></td>
					<td><%=metodoPagamento.getNome_intestatario()%></td>
					<td><%=metodoPagamento.getCognome_intestatario()%></td>
					<td><%=metodoPagamento.getIdCarta()%></td>
			  	</tr>
				
		<%
					}
		%>
		
		<% 	
				}
		%>
		
		
		
		<% 	
				if(indirizzi != null && !indirizzi.isEmpty()){
		%>
		
				</table>
				
				<h2>Addresses</h2>
				<table>
					<tr>
						<th>via</th>
						<th>cap</th>
						<th>citta</th>
						<th>provincia</th>
						<th>codiceUtente</th>
						<th>idIndirizzo</th>
				  	</tr>
					  <!-- contenuto tabella -->
				  
					<%
				  		//scorre l'array indirizzi e stampa gli elementi nella tabella
				  			it = indirizzi.iterator();
							while (it.hasNext()) {
								IndirizzoBean indirizzo = (IndirizzoBean) it.next();
					%>
						<tr>
							<td><%=indirizzo.getVia()%></td>
							<td><%=indirizzo.getCap()%></td>
							<td><%=indirizzo.getCitta()%></td>
							<td><%=indirizzo.getProvincia()%></td>
							<td><%=indirizzo.getCodice_utente()%></td>
							<td><%=indirizzo.getIdIndirizzo()%></td>
					  	</tr>
						
				<%
							}
				%>
				
					</table>
		<% 	
				}
		%>
	
		<fieldset>
			<legend>Payment method and delivery address</legend>
			<form action="CartServlet" method="post">
				<input type="hidden" name="action" value="actualPurchase">
				<p>Card ID:		<input type="number" name="cardId" value="Insert card id" required></p>
				<p>Address ID:	<input type="number" name="AddressId" value="Insert address id" required></p>
				<input type="submit" value="Confirm">
			</form>
		</fieldset>
	</body>
</html>