<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Invalid Login</title>
</head>
<body>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<h1>Sorry, you are not registered</h1>
	
	<form action="login-form.jsp" method="get">
		<input type="submit" value="Login"/>
	</form>

<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>