<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<fieldset>
		<legend>Index</legend>
		<a href="login-form.jsp"> <input type="button" value="Login"> </a>
		<a href="registration.jsp"> <input type="button" value="Sign in"> </a>
		
		<form action="StorageControl" method="get">
			<input type="submit" value="Homepage"/>
		</form>
		
		<form action="UserInfo" method="get">
			<input type="submit" value="User page"/>
		</form>
	</fieldset>
</body>
</html>