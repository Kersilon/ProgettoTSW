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
<link rel="stylesheet" href="css/trueStorageStyle.css">
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
	
	<h1>Welcome to the user page</h1>
	<p>Here you can add and delete, payment methods and delivery addresses. And modify some personal data</p>
	
	<form class="inputFieldContainer inputList" action="UserInfo" method="get">
		<input type="hidden" name="action" value="ordini"> 
		<input type="submit" value="Ordini"/>
	</form>
	
	<%
			Iterator<?> it;
	%>
	
	
	
	<%
			if(metodiPagamento != null && !metodiPagamento.isEmpty()){
	%>
	
	
	

    	
    	
    	

	  		<h2>Paymenth methods</h2>
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
			}
	%>
	
	
	
	<% 	
			if(datiUtente != null){
	%>
			<h2>User's data</h2>
		    <div class="flex-container">
    		<!-- <div class="product-info"> -->
    			<p>UserId: <span class="idCell"><%=datiUtente.getCodiceUtente()%></span> | Name: <span class="nameCell"><%=datiUtente.getNome()%></span>  | Surname: <span class=""><%=datiUtente.getCognome()%></span></p>
    			<p>Birth date: <span class=""><%=datiUtente.getDataNascita()%></span> | Username: <span class=""><%=datiUtente.getNomeUtente()%></span> | Fiscal code: <span class=""><%=datiUtente.getCodiceFiscale()%></span></p>
    			<p><button type="button" id="showButton" onclick ="showText()">Show</button><button type="button" id="hideButton" onclick ="hideText()">Hide</button> Password: <span class="hide"><%=datiUtente.getPassword()%></span></p>
    			<p>Email: <span class=""><%=datiUtente.getEmail()%></span> | Phone number: <span class=""><%=datiUtente.getTelefono()%></span> | Admin?: <span class=""><%=datiUtente.isAMMINISTRATORE()%></span></p>
    		<!-- </div> -->
    	</div>
    			
	
	<% 	
			}
	%>
	
	<h2>Operetions</h2>
	<div class="flex-container">
	<form class="inputFieldContainer inputList" action="UserInfo" method="post">
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
	
	<form class="inputFieldContainer inputList" action="UserInfo" method="post">
		<fieldset>
			<legend>Delete delivery address</legend>
			<input type="hidden" name="action" value="removeAdress">
			<p>Address ID: <input name="addressId" type="number" maxlength="10" placeholder="Enter address id"></p>
			<input type="submit" value="delete Address">
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	<form id="addPymentMethodForm"class="inputFieldContainer inputList" action="UserInfo" method="post">
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
			Expiration date: 	<input name="data" 				type ="text" 	id="dataNascita"	maxlength="20" 	value ="2024-10-10" placeholder="enter expiration date"><br>
			<p id="birthDateError" class="ErrorParagraph"></p>
			
			<button type="button" id ="paymentMethodButton" onclick ="checkPaymentMethod('#addPymentMethodForm')">Save</button>
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
		<form class="inputFieldContainer inputList" action="UserInfo" method="post">
		<fieldset>
			<legend>Delete payment method</legend>
			<input type="hidden" name="action" value="removePaymentMethod">
			<p>Payment method ID: <input name="paymentMethodId" type="number" maxlength="10" placeholder="Enter payment method id"></p>
			<input type="submit" value="delete payment method">
			<input type="reset" value="Reset">
		</fieldset>
	</form>
	
	<form id="modifyUsername" class="inputFieldContainer inputList" action="UserInfo" method="post">
		<fieldset>
			<legend>modify Username</legend>
			<input type="hidden" name="action" value="modifyUserData">
			
			Username: 	<input name="nomeUtente" 	type="text" 						maxlength="20" placeholder="enter username"			><br>
			
			<input type="submit" value="Modify"/>
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	
	<form id="modifyPassword" class="inputFieldContainer inputList" action="UserInfo" method="post">
		<fieldset>
			<legend>modify Password</legend>
			<input type="hidden" name="action" value="modifyUserData">
			
			Password: 	<input name="password" 		type="password"				 		maxlength="20" placeholder="enter password"			><br>
			<p class="ErrorParagraph"></p>
							
			<button type="button" onclick ="checkModify('#modifyPassword', checkPassword)">Modify</button>
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	
	<form id="modifyPhone" class="inputFieldContainer inputList" action="UserInfo" method="post">
		<fieldset>
			<legend>modify Phone</legend>
			<input type="hidden" name="action" value="modifyUserData">
			
			telephone: 	<input name="telefono" 		type="number"						maxlength="20" placeholder="enter telephone number"	><br>
			<p class="ErrorParagraph"></p>
			
			<button type="button" onclick ="checkModify('#modifyPhone', checkPhone)">Modify</button>
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	</div>
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>