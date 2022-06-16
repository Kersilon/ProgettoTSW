<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="trueStorageStyle.css">

<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="ControllaCredenziali.js"></script>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<form action="LoginStorage" method="post">
		<fieldset>
			<legend>Registration</legend>
			<input type="hidden" name="action" value="registration">
			
			
			Name: 		<input name="nome" 			type="text" 						maxlength="20" placeholder="enter name"				><br>
			Surname: 	<input name="cognome" 		type="text" 						maxlength="20" placeholder="enter surname"			><br>
			Birth date: <input name="dataNascita" 	type ="text" 	id ="dataNascita" 	maxlength="20" placeholder="enter birth date"		><br>
			<p id="birthDateError" class="ErrorParagraph"></p>	
			
			Username: 	<input name="nomeUtente" 	type="text" 						maxlength="20" placeholder="enter username"			><br>
			Password: 	<input name="password" 		type="password"	id ="password" 		maxlength="20" placeholder="enter password"			><br>
			<p id="passwordError" class="ErrorParagraph"></p>
			
			Email: 		<input name="email" 		type="email" 	id ="email"  		maxlength="20" placeholder="enter email"			><br>
			<p id="emailError" class="ErrorParagraph"></p>	
			
			telephone: 	<input name="telefono" 		type="number"	id ="telefono" 		maxlength="20" placeholder="enter telephone number"	><br>
			<p id="phoneError" class="ErrorParagraph"></p>
			
			
			<button type="button" id ="RegistrationButton" onclick ="checkCredentialsRegistration()">Submit</button>
			<input type="reset" 	value="Reset"	/>
		</fieldset>
	</form>
	
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>