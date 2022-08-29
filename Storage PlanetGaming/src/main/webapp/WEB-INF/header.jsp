<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>

<link rel="stylesheet" href="headerStyle.css">
</head>
	<body>
		<div class="header">
			<ul>
				<li><a href="login-form.jsp"> <input type="button" value="Login"> </a></li>
				<li><a href="registration.jsp"> <input type="button" value="Sign in"> </a></li>
				<li>
					<form action="Logout" method="get">
						<input type="submit" value="Logout"/>
					</form>
				</li>		
				<li>
					<form action="StorageControl" method="get">
						<input type="submit" value="Homepage"/>
					</form>
				</li>
				<li>
					<form action="UserInfo" method="get">
						<input type="submit" value="User page"/>
					</form>
				</li>
			</ul>
		</div>
	</body>
</html>