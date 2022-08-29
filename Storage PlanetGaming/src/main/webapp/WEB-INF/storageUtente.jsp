<%@ page import="java.util.*" import="planetGaming.Videogioco.*"%>


<% 	
	//verifica se l'oggetto videogiochi è presente nella richiesta altrimenti passa il controllo alla servlet "StorageControl" che lo genera
	Collection<?> videogiochi = (Collection<?>) request.getSession().getAttribute("videogiochi"); //va bene anche "session" al posto di "request.getSession()"
	if(videogiochi == null) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/StorageControl");
		dispatcher.forward(request, response);	
	}
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Storage</title>

<link rel="stylesheet" href="trueStorageStyle.css">
<link rel="stylesheet" href="PopUp.css">
<link rel="stylesheet" href="storage.css">
</head>
<body>
<script type="text/javascript" src="ControllaCredenziali.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="photoHighlight.js"></script>
<script type="text/javascript" src="confirmDeleteModifyInsert.js"></script>

<jsp:include page="/WEB-INF/header.jsp" />




	<h1>Storage</h1> 
	<%
	  		//scorre l'array videogiochi e stampa gli elementi nella tabella
	  		Iterator<?> it = videogiochi.iterator();
				while (it.hasNext()) {
					VideogiocoBean videogioco = (VideogiocoBean) it.next();
	%>

	<div class="box-catalogo box-border bg-image box-text">
	<img class="videogame-image" src="./immagini Videogiochi/<%=videogioco.getFoto()%>" alt=>
		<form action="StorageControl" method="post">
			<input type="hidden" name="action" value="ExtendedDescription"> 
			<input type="hidden" name="codice_prodotto" value=<%=videogioco.getCodice_prodotto()%>> 
			<input type="submit" value="<%=videogioco.getNome()%>">
		</form>
		
		<form action="CartServlet" method="post">
			<input type="hidden" name="action" value=addToCart>
			<input type="hidden" name="insertIntoCart" value=<%=videogioco.getCodice_prodotto()%>>
			 <input type="submit" value="Add to cart">
		</form>
</div>
			
	<%
				}
	%>
	
	<script>highlight();</script>
	
	<form action="CartServlet" method="post">
		<input type="hidden" name="action" value="showCart">
		<input type="submit" value="Show cart">
	</form>


<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>