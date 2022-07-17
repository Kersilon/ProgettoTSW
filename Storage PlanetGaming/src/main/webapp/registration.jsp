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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<form id="registrationForm" action="LoginStorage" method="post">
		<fieldset>
			<legend>Registration</legend>
			<input type="hidden" name="action" value="registration">
			
			
			Name: 		<input name="nome" 			type="text" 						maxlength="20" placeholder="enter name"				value="Ers"><br>
			Surname: 	<input name="cognome" 		type="text" 						maxlength="20" placeholder="enter surname"			value="ilio"><br>
			Birth date: <input name="dataNascita" 	type ="text" 	id ="dataNascita" 	maxlength="20" placeholder="enter birth date"		value="2010-10-10"><br>
			<p id="birthDateError" class="ErrorParagraph"></p>	
			
			Username: 	<input name="nomeUtente" 	type="text" 						maxlength="20" placeholder="enter username"			value="Kers"><br>
			Password: 	<input name="password" 		type="password"	id ="password" 		maxlength="20" placeholder="enter password"			value="12345"><br>
			<p id="passwordError" class="ErrorParagraph"></p>
			
			Email: 		<input name="email" 		type="email" 	id ="email"  		maxlength="20" placeholder="enter email"			value="ersilio@gmail.com"><br>
			<p id="emailError" class="ErrorParagraph"></p>	
			
			telephone: 	<input name="telefono" 		type="number"	id ="telefono" 		maxlength="20" placeholder="enter telephone number"	value="12345678901"><br>
			<p id="phoneError" class="ErrorParagraph"></p>
			
			
			<input type="submit" name="submit" value="Submit" />
			<input type="reset" 	value="Reset"	/>
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