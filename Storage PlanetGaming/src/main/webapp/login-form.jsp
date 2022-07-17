<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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

	<form id="loginForm" action="LoginStorage" method="post">
		<fieldset>
			<legend>Login</legend>
			<!-- da rendere hidden quando potrò spostarmi dallo storage alla pagina protetta durante l'esecuzione -->
			<input name="action" value="login"><br>
					
			<label for="email"> Login:</label>
			<input type="text" name="email" id ="email" placeholder="enter Username" value="ersilio@gmail.com">
			<p id="emailError" class="ErrorParagraph"></p>			
			<br>
			<label for="password"> Password:</label>
			<input type="password" name="password" id="password" placeholder="enter Password" value="12345">
			<p id="passwordError" class="ErrorParagraph"></p>	
			<br>
			<!--
			<button type="button" id ="LoginButton" onclick ="checkCredentialsLogin('loginForm')">Login</button>
			!-->
			<!--
			<button type="button" id ="LoginButton" onclick ="LoginFail('#LoginButton', '#emailError')">Login</button> 
			!-->
			<input type="submit" name="submit" value="Submit" />
			<input type="reset" value="Reset"/>
			
			
			<script>
			<!-- Test 
				$(document).on("submit", "#loginForm", function(event) {
	   				var $form = $(this);
	   				//if(checkCredentialsLogin($form.attr("id"))){
	
		    			$.post($form.attr("action"), $form.serialize(), function(response) {
		    				//setTimeout(alert(response), 1000);
		    				
		    				$(#emailError).text(response);
		    			});
	   				//}
	
	    			event.preventDefault(); // Important! Prevents submitting the form.
				});
				-->
				
				$("#loginForm").onsubmitn('submit', function(e) {

				    e.preventDefault(); // avoid to execute the actual submit of the form.

				    var form = $(this);
				    var actionUrl = form.attr('action');
				    
				    $.ajax({
				        type: "POST",
				        url: actionUrl,
				        data: form.serialize(), // serializes the form's elements.
				        success: function(data)
				        {
				          alert(data); // show response from the php script.
				        }
				    });
				    
				});
			</script>
		</fieldset>
	</form>
	
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>