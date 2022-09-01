<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="inputFieldStyle.css">
	<link rel="stylesheet" href="body.css">
	<link rel="stylesheet" href="trueStorageStyle.css">

<!--   
	il collegamento alle pagine javaScript esterne non funziona se questa si trova in web inf
	<script src="/WEB-INF/ControllaCredenziali.js"></script>
-->

<title>Login</title>
</head>
<body>
<script type="text/javascript" src="ControllaCredenziali.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="Test.js"></script>
	<jsp:include page="/WEB-INF/header.jsp" />

	<form id="loginForm" class="inputFieldContainer inputList" action="LoginStorage" method="post">
		<fieldset>
			<legend>Login</legend>
			<!-- da rendere hidden quando potrò spostarmi dallo storage alla pagina protetta durante l'esecuzione -->
			<input type="hidden" name="action" value="login"><br>
					
			<label for="email"> Login:</label>
			<input type="text" name="email" id ="email" placeholder="enter Username" >
			<p id="emailError" class="ErrorParagraph"></p>			
			<br>
			<label for="password"> Password:</label>
			<input type="password" name="password" id="password" placeholder="enter Password" >
			<p id="passwordError" class="ErrorParagraph"></p>	
			<br>
			<input type="submit" name="submit" value="Submit">
			<input type="reset" value="Reset"/>
			
			
			<script>	
				$("#loginForm").on('submit', function(e) {

				    e.preventDefault(); // avoid to execute the actual submit of the form.

				    var form = $(this);
				    var actionUrl = form.attr('action');
				    
				    if(checkCredentialsLogin(form.attr("id"))){
					    $.ajax({
					        type: "POST",
					        url: actionUrl,
					        data: form.serialize(), // serializes the form's elements.
					        success: function(data)
					        {
					          if(data === "Wrong Email or Password"){
					          	alert(data);
					          }else{
					        	  $(location).attr('href', data);
					          }
					        }
					    });
				    }
				});
			</script>
		</fieldset>
	</form>
	
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>