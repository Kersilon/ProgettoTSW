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
			<button type="button" id ="LoginButton" onclick ="checkCredentialsLogin('loginForm')">Login</button>
			<input type="reset" value="Reset"/>
		</fieldset>
	</form>
	
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>