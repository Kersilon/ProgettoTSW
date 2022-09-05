<%@ page import="java.util.*" import="planetGaming.Videogioco.*"%>
<% 	
	//verifica se l'oggetto videogiochi è presente nella richiesta altrimenti passa il controllo alla servlet "StorageControl" che lo genera
	Collection<?> videogiochi = (Collection<?>) request.getSession().getAttribute("videogiochi"); //va bene anche "session" al posto di "request.getSession()"
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Storage</title>

<link rel="stylesheet" href="css/trueStorageStyle.css">
<link rel="stylesheet" href="css/PopUp.css">
<link rel="stylesheet" href="css/productBox.css">
<link rel="stylesheet" href="css/searchBar.css">
<link rel="stylesheet" href="css/storage.css">
<link rel="stylesheet" href="css/form.css">
<link rel="stylesheet" href="css/body.css">
</head>
<body>
<script type="text/javascript" src="javaScript/ControllaCredenziali.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="javaScript/photoHighlight.js"></script>
<script type="text/javascript" src="javaScript/confirmDeleteModifyInsert.js"></script>

<jsp:include page="/WEB-INF/header.jsp" />
	<h1 class="title">Storage</h1> 
	
<div class="searchBar">
	<form class="inputFieldContainer inputList" action="StorageControl" method="get">
		<input type="hidden" name="action" value="showVideogame">
		<p><input type="text" name="videogameTitle" placeholder="videogame's title"></p>
		<input type="submit" value="Search Videogame"/>
	</form>
		<form class="inputFieldContainer inputList" action="StorageControl" method="get">
		<input type="submit" value="Reset"/>
	</form>
</div>
	
	<%
		if(videogiochi != null && !videogiochi.isEmpty()){
	%>
	<div class="box-of-boxes">
	<%
	  		//scorre l'array videogiochi e stampa gli elementi nella tabella
	  		Iterator<?> it = videogiochi.iterator();
				while (it.hasNext()) {
					VideogiocoBean videogioco = (VideogiocoBean) it.next();
	%>

	<div class="box-catalogo box-border bg-image box-text">
		<img class="videogame-image" src="./immagini Videogiochi/<%=videogioco.getFoto()%>" alt=>
		
		<form class="product-form" action="StorageControl" method="post">
			<input type="hidden" name="action" value="ExtendedDescription"> 
			<input type="hidden" name="codice_prodotto" value=<%=videogioco.getCodice_prodotto()%>> 
			<input type="submit" value="<%=videogioco.getNome()%>">
		</form>
		
		<form class="product-form" action="CartServlet" method="post">
			<input type="hidden" name="action" value=addToCart>
			<input type="hidden" name="insertIntoCart" value=<%=videogioco.getCodice_prodotto()%>>
			<input type="submit" value="Add to cart">
		</form>
	</div>
			
	<%
				}
		}else{
	%>
		<p>We can't find the game you are searching for!</p>
	<%
		}
	%>
	
		<script>highlight();</script>
	</div>


<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>