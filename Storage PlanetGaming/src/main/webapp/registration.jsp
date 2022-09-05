<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="css/inputFieldStyle.css">
	<link rel="stylesheet" href="css/body.css">
	<link rel="stylesheet" href="css/trueStorageStyle.css">

<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="javaScript/ControllaCredenziali.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<jsp:include page="/WEB-INF/header.jsp" />


	<form id="registrationForm" action="LoginStorage" method="post">
		<fieldset>
			<legend>Registration</legend>
			<input type="hidden" name="action" value="registration">
			
			
			<ul class="inputFieldContainer inputList">
				<li>Name: 		<input name="nome" 			type="text" 						maxlength="20" placeholder="enter name"		value="a"		></li>
				<li>Surname: 	<input name="cognome" 		type="text" 						maxlength="20" placeholder="enter surname"		value="a"	></li>
				<li>Birth date: <input name="data" 			type ="text" 	id ="dataNascita" 	maxlength="20" placeholder="enter birth date" value="2022-09-05"		><p id="birthDateError" class="ErrorParagraph"></p></li>
				<li>fiscal Code:<input name="codiceFiscale" type="text" 						maxlength="16" placeholder="enter fiscalCode"	value="AAA"	></li>
				<li>Username: 	<input name="nomeUtente" 	type="text" 						maxlength="20" placeholder="enter username"		value="a"	></li>
				<li>Password: 	<input name="password" 		type="password"	id ="password" 		maxlength="20" placeholder="enter password"	value="a10"		> <p id="passwordError" class="ErrorParagraph"></p></li>
				<li>Email: 		<input name="email" 		type="email" 	id ="email"  		maxlength="20" placeholder="enter email"	value="a@gmail.com"		> <p id="emailError" class="ErrorParagraph"></p></li>
				<li>telephone: 	<input name="telefono" 		type="number"	id ="telefono" 		maxlength="20" placeholder="enter telephone number"	value="1234567890" > <p id="phoneError" class="ErrorParagraph"></p></li>
			</ul>
			
			<input type="submit" name="submit" value="Submit">
			<input type="reset" 	value="Reset">
			
		</fieldset>
	</form>


	<script>	
				$("#registrationForm").on('submit', function(e) {

				    e.preventDefault(); // avoid to execute the actual submit of the form.

				    var form = $(this);
				    var actionUrl = form.attr('action');
				    
				    if(checkCredentialsRegistration(form.attr("id"))){
					    $.ajax({
					        type: "POST",
					        url: actionUrl,
					        data: form.serialize(), // serializes the form's elements.
					        success: function(data)
					        {
					          if(data === "Email already used"){
					          	alert(data);
					          }else{
					        	  $(location).attr('href', data);
					          }
					        }
					    });
				    }
				});
			</script>
	
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>