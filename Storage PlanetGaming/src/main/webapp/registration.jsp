<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="inputFieldStyle.css">
	<link rel="stylesheet" href="body.css">
	<link rel="stylesheet" href="trueStorageStyle.css">

<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="ControllaCredenziali.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<jsp:include page="/WEB-INF/header.jsp" />


	<form id="registrationForm" action="LoginStorage" method="post">
		<fieldset>
			<legend>Registration</legend>
			<input type="hidden" name="action" value="registration">
			
			
			<ul class="inputFieldContainer inputList">
				<li>Name: 		<input name="nome" 			type="text" 						maxlength="20" placeholder="enter name"				></li>
				<li>Surname: 	<input name="cognome" 		type="text" 						maxlength="20" placeholder="enter surname"			></li>
				<li>Birth date: <input name="data" 			type ="text" 	id ="dataNascita" 	maxlength="20" placeholder="enter birth date"		><p id="birthDateError" class="ErrorParagraph"></p></li>
				<li>fiscal Code:<input name="codiceFiscale" type="text" 						maxlength="16" placeholder="enter fiscalCode"		></li>
				<li>Username: 	<input name="nomeUtente" 	type="text" 						maxlength="20" placeholder="enter username"			></li>
				<li>Password: 	<input name="password" 		type="password"	id ="password" 		maxlength="20" placeholder="enter password"			> <p id="passwordError" class="ErrorParagraph"></p></li>
				<li>Email: 		<input name="email" 		type="email" 	id ="email"  		maxlength="20" placeholder="enter email"			> <p id="emailError" class="ErrorParagraph"></p></li>
				<li>telephone: 	<input name="telefono" 		type="number"	id ="telefono" 		maxlength="20" placeholder="enter telephone number"	> <p id="phoneError" class="ErrorParagraph"></p></li>
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