<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
	<head>
		<link rel="stylesheet" href="css/body.css">
		<style>
			.errorContainer{
			    display: flex;
			    flex-flow: column wrap;
			    justify-content: center;
			    align-items: center;
			    text-align: center;
			}
			
			.errorStatus{
				font-size: 100px;
			}
			
			.errorDescription{
				font-size: 30px;
			}
			
		</style>
		<title>Something went wrong!</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/header.jsp" />

		<div class="errorContainer">
		<h1 class="errorStatus"><%=response.getStatus()%></h1>
		
		<%if(response.getStatus() == 404){%>
			<h3 class="errorDescription">The page you searched for isn't here anymore!</h3>
			
		<%}else if(response.getStatus() == 500){ %>
			<h3 class="errorDescription">There is a problem with the server! This content is temporarily unavailable</h3>
			
		<%}else if(response.getStatus() == 503){ %> 
			<h3 class="errorDescription">Our server is temporary unavailable! Plese come back later</h3>
			
		<%}else{ %>
			<h3 class="errorDescription">An error has occurred! Please try again later!</h3>
		<%} %>
		
		<p>use the buttons at the top of the page to go to other pages</p>
		</div>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>