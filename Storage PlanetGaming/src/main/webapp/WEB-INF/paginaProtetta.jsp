<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*" import="planetGaming.MetodoPagamento.*" import="planetGaming.Indirizzo.*" import="planetGaming.Utente.*"%>

<%
	//Check user credentials
	Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
	
	if((isLogged == null) || isLogged.equals(false))
	{
		//response.sendRedirect("./login-form.jsp");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-form.jsp");
		dispatcher.forward(request, response);
	}
	
	Collection<?> metodiPagamento = (Collection<?>) request.getSession().getAttribute("metodiPagamento");
	Collection<?> indirizzi = (Collection<?>) request.getSession().getAttribute("indirizzi");
	UtenteBean datiUtente = (UtenteBean) request.getSession().getAttribute("datiUtente");
	
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
	
	<h1>Welcome to the user page</h1><br>
	<p>Here you can add, delete, payment methods and delivery addresses</p>
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
	
	
	
	<% 	
			if(datiUtente != null){
	%>
	
			<h2>User's data</h2>
			<table>
				<tr>
					<th>Name</th>
					<th>Surname</th>
					<th>Birth date</th>
					<th>Username</th>
					<th>Password
						<button type="button" id ="ShowButton" onclick ="show()">Show</button>
						<button type="button" id ="HideButton" onclick ="hide()">Hide</button>
					</th>
					<th>Email</th>
					<th>Phone number</th>
					<th>UserId</th>
					<th>Admin?</th>
			  	</tr>
				  <!-- contenuto tabella -->
			  
					<tr>
						<td><%=datiUtente.getNome()%></td>
						<td><%=datiUtente.getCognome()%></td>
						<td><%=datiUtente.getDataNascita()%></td>
						<td><%=datiUtente.getNomeUtente()%></td>
						<td><div  class ="hide"><%=datiUtente.getPassword()%></div></td>
						<td><%=datiUtente.getEmail()%></td>
						<td><%=datiUtente.getTelefono()%></td>
						<td><%=datiUtente.getCodiceUtente()%></td>
						<td><%=datiUtente.isAMMINISTRATORE()%></td>
				  	</tr>
			</table>		
	
	<% 	
			}
	%>
	
	
	<form action="UserInfo" method="post">
		<fieldset>
			<legend>delivery address</legend>
			<input type="hidden" name="action" value="addAddress">
			
			Name: 			<input name="name" 			type="text" 						maxlength="20" 	value ="io" placeholder="enter name"><br>
			Surname: 		<input name="surname" 		type="text" 						maxlength="20" 	value ="oi" placeholder="enter surname"><br>
			<!--  TODO da spostare nei dati dell'utente; 	Fiscal code: 	<input name="fiscalCode" 	type="text"		id="fiscalCode"		maxlength="20" 	placeholder="enter fiscal code"><br>
			<p id="fiscalCodeError"></p>-->
			
			Address: 		<input name="address" 		type="text" 	id="address"		maxlength="20" 	value ="via" placeholder="enter address"><br>
			cap: 			<input name="cap" 			type="text" 	id="cap"			maxlength="20" 	value ="10203" placeholder="enter cap"><br>
			<p id="capError" class="ErrorParagraph"></p>
			
			Province: 		<input name="province" 		type="text" 	id="province"		maxlength="20" 	value ="SA" placeholder="enter state"><br>
			<p id="provinceError" class="ErrorParagraph"></p>
			
			City: 			<input name="city" 			type="text" 	id="city"			maxlength="20" 	value ="Salerno" placeholder="enter city"><br>
			<p id="cityError" class="ErrorParagraph"></p>
			
			<button type="button" id ="addressButton" onclick ="checkDeliveryAddress()">Save</button>
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	<form action="UserInfo" method="post">
		<fieldset>
			<legend>payment method</legend>
			<input type="hidden" name="action" value="addPaymentMethod">
			
			Card number: 		<input name="cardNumber" 		type="text" 	id="cardNumber" 	maxlength="20" 	value ="4023101120223033" placeholder="enter card number"><br>
			<p id="cardNumberError" class="ErrorParagraph"></p>
			
			ccv: 				<input name="ccv" 				type="text" 	id="ccv" 			maxlength="20" 	value ="123" placeholder="enter ccv"><br>
			<p id="ccvError" class="ErrorParagraph"></p>
			
			circuito: 			<input name="circuit" 			type="text"				 			maxlength="20" 	value ="VISA" placeholder="enter card circuit"><br>
			<!--<p id="" class="ErrorParagraph"></p>-->
			
			Name: 				<input name="name" 				type="text"				 			maxlength="20" 	value ="io" placeholder="enter name on the card"><br>
			Cognome: 			<input name="surname" 			type="text"				 			maxlength="20" 	value ="oi" placeholder="enter surname on the card"><br>
			Expiration date: 	<input name="dataNascita" 		type ="text" 	id="dataNascita"	maxlength="20" 	value ="2024-10-10" placeholder="enter expiration date"><br>
			<p id="birthDateError" class="ErrorParagraph"></p>
			
			<button type="button" id ="paymentMethodButton" onclick ="checkPaymentMethod()">Save</button>
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	<form id="modifyUsername" action="UserInfo" method="post">
		<fieldset>
			<legend>modify Username</legend>
			<input type="hidden" name="action" value="modifyUserData">
			
			Username: 	<input name="nomeUtente" 	type="text" 						maxlength="20" placeholder="enter username"			><br>
			
			<input type="submit" value="Modify"/>
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	
	<form id="modifyPassword" action="UserInfo" method="post">
		<fieldset>
			<legend>modify Password</legend>
			<input type="hidden" name="action" value="modifyUserData">
			
			Password: 	<input name="password" 		type="password"				 		maxlength="20" placeholder="enter password"			><br>
			<p class="ErrorParagraph"></p>
							
			<button type="button" onclick ="checkModify('modifyPassword', checkPassword)">Modify</button>
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	
	<form id="modifyPhone" action="UserInfo" method="post">
		<fieldset>
			<legend>modify Phone</legend>
			<input type="hidden" name="action" value="modifyUserData">
			
			telephone: 	<input name="telefono" 		type="number"						maxlength="20" placeholder="enter telephone number"	><br>
			<p class="ErrorParagraph"></p>
			
			<button type="button" onclick ="checkModify('modifyPhone', checkPhone)">Modify</button>
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	<form action="UserInfo" method="get">
		<input type="hidden" name="action" value="ordini"> 
		<input type="submit" value="Ordini"/>
	</form>
	
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>