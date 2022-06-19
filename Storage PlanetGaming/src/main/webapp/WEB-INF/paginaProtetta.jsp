<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	//Check user credentials
	Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
	
	if((isLogged == null) || isLogged.equals(false))
	{
		response.sendRedirect("./login-form.jsp");
		//return;	//TODO cosa fa effettivamente questo return?
	}
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="trueStorageStyle.css">

<meta charset="ISO-8859-1">
<title>Protected Page</title>
</head>
<body>
	<jsp:include page="/WEB-INF/header.jsp" />

	<h1>Welcome to the user page</h1><br>
	
	<form action="UserInfo" method="post">
		<fieldset>
			<legend>delivery address</legend>
			<input type="hidden" name="action" value="">
			
			Name: 			<input name="name" 			type="text" 						maxlength="20" 	placeholder="enter name"><br>
			Surname: 		<input name="surname" 		type="text" 						maxlength="20" 	placeholder="enter surname"><br>
			Fiscal code: 	<input name="fiscalCode" 	type="text"		id="fiscalCode"		maxlength="20" 	placeholder="enter fiscal code"><br>
			<p id="fiscalCodeError"></p>
			
			Address: 		<input name="address" 		type="text" 	id="address"		maxlength="20" 	placeholder="enter address"><br>
			<p id="addressError"></p>
			
			Locality: 		<input name="locality" 		type="text" 						maxlength="20"	placeholder="enter locality"><br>
			Nation: 		<input name="nation" 		type="text" 						maxlength="20"	placeholder="enter nation"><br>
			State/Province: <input name="state" 		type="text" 						maxlength="20" 	placeholder="enter state"><br>
			City: 			<input name="city" 			type="text" 						maxlength="20" 	placeholder="enter city"><br>
			telephone: 		<input name="telephone" 	type="number" 	id="telephone" 		maxlength="20" 	placeholder="enter telephone number"><br>
			<p id="phoneError"></p>
			
			<button type="button" id ="addressButton" onclick ="">Save</button>
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	<form action="UserInfo" method="post">
		<fieldset>
			<legend>payment method</legend>
			<input type="hidden" name="action" value="addPaymentMethod">
			<input type="hidden" name="UserId" value="">
			
			Card number: 		<input name="cardNumber" 		type="text" 	id="cardNumber" 	maxlength="20" placeholder="enter card number"><br>
			<p id="cardNumberError"></p>
			
			ccv: 				<input name="ccv" 				type="text" 	id="ccv" 			maxlength="20" placeholder="enter ccv"><br>
			circuito: 			<input name="circuit" 			type="text"				 			maxlength="20" placeholder="enter card circuit"><br>
			
			Name: 				<input name="name" 				type="text"				 			maxlength="20" placeholder="enter name on the card"><br>
			Cognome: 			<input name="surname" 			type="text"				 			maxlength="20" placeholder="enter surname on the card"><br>
			Expiration date: 	<input name="expirationDate" 	type ="text" 	id="expirationDate"	maxlength="20" placeholder="enter expiration date"><br>
			<p id="expirationDateError"></p>
			
			<!--  <button type="button" id ="paymentMethodButton" onclick ="">Save</button>-->
			<input type="submit" value="Submit">
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	<form action="Logout" method="get">
		<input type="submit" value="Logout"/>
	</form>
	
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>