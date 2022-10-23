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
		<link rel="stylesheet" href="css/inputFieldStyle.css">
		<link rel="stylesheet" href="css/body.css">
		<link rel="stylesheet" href="css/trueStorageStyle.css">
		<link rel="stylesheet" href="css/productFlexTable.css">
	
	<meta charset="ISO-8859-1">
	<title>Protected Page</title>
	</head>
	<body>
		<script type="text/javascript" src="javaScript/ControllaCredenziali.js"></script>
		<script type="text/javascript" src="javaScript/HideShowButton.js"></script>
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
		  
			<%
		  		//scorre l'array metodi di pagamento e stampa gli elementi nella tabella
		  		it = metodiPagamento.iterator();
					while (it.hasNext()) {
						MetodoPagamentoBean metodoPagamento = (MetodoPagamentoBean) it.next();
			%>
			
				<div id="<%=metodoPagamento.getIdCarta()%>" class="flex-container">
	    		<!-- <div class="product-info"> -->
	    			<p>CardId: <span class="idCell"><%=metodoPagamento.getIdCarta()%></span> | cardNumber: <span class=""><%=metodoPagamento.getNumero_carta()%></span>  | ccv: <span class=""><%=metodoPagamento.getCcv()%></span></p>
	    			<p>Circuit: <span class=""><%=metodoPagamento.getCircuito()%></span> | expiration date: <span class=""><%=metodoPagamento.getScadenza()%></span> | UserId: <span class=""><%=metodoPagamento.getCodiceUtente()%></span></p>
	    			<p>accountholder's name: <span class="platformCell"><%=metodoPagamento.getNome_intestatario()%></span> | accountholder's surname: <span class=""><%=metodoPagamento.getCognome_intestatario()%></span></p>
	    		<!-- </div> -->
    			</div>
				
		<%
					}
		%>
		
		<% 	
				}else{
		%>
				<p>There is no paymenth method registered, go to your user page to add a new one</p>
		<% 			
				}
		%>
		
		

		<% 	
				if(indirizzi != null && !indirizzi.isEmpty()){
		%>
				  		<h2>Delivery addresses</h2>
					<%
				  		//scorre l'array indirizzi e stampa gli elementi nella tabella
				  			it = indirizzi.iterator();
							while (it.hasNext()) {
								IndirizzoBean indirizzo = (IndirizzoBean) it.next();
					%>
					
						<div id="<%=indirizzo.getIdIndirizzo()%>" class="flex-container">
				    		<!-- <div class="product-info"> -->
				    			<p>AddressId: <span class="idCell"><%=indirizzo.getIdIndirizzo()%></span> | Street: <span class=""><%=indirizzo.getVia()%></span>  | cap: <span class=""><%=indirizzo.getCap()%></span></p>
				    			<p>City: <span class=""><%=indirizzo.getCitta()%></span> | province: <span class=""><%=indirizzo.getProvincia()%></span> | UserId: <span class=""><%=indirizzo.getCodice_utente()%></span></p>
				    		<!-- </div> -->
						</div>
						
				<%
							}
				%>
				
		<% 	
				}else{
					%>
					<p>There is no delivery address registered, go to your user page to add a new one</p>
			<% 			
					}
			%>
	
		<fieldset>
			<legend>Payment method and delivery address</legend>
			<form class="inputFieldContainer inputList" action="CartServlet" method="post">
				<input type="hidden" name="action" value="actualPurchase">
				<p>Card ID:		<input type="number" name="cardId" 		placeholder="Insert card id" 	required></p>
				<p>Address ID:	<input type="number" name="AddressId" 	placeholder="Insert address id" required></p>
				<input type="submit" value="Confirm">
			</form>
		</fieldset>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>