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
</head>
<body>
<script type="text/javascript" src="ControllaCredenziali.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="photoHighlight.js"></script>
<script type="text/javascript" src="confirmDeleteModifyInsert.js"></script>

<jsp:include page="/WEB-INF/header.jsp" />

	<h1>Welcome to the Storage Page</h1>
	<p>Here you can view videogames and add them to the cart</p>
	<br><br>
	
	<table>
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Edition</th>
			<th>Description</th>
			<th>Price</th>
			<th>Date</th>
			<th>Platform</th>
			<th>Console</th>
			<th>Sale</th>
			<th>Copy</th>
			<th>Developer</th>
			<th>Publisher</th>
			<th>Photo</th>
	  	</tr>
	  <!-- contenuto tabella -->
	  
	<%
	  		//scorre l'array videogiochi e stampa gli elementi nella tabella
	  		Iterator<?> it = videogiochi.iterator();
				while (it.hasNext()) {
					VideogiocoBean videogioco = (VideogiocoBean) it.next();
	%>
		
			<tr id="<%=videogioco.getCodice_prodotto()%>">
				<td class="idCell"			><%=videogioco.getCodice_prodotto()%>							</td>
			    <td class="nameCell"		><%=videogioco.getNome()%>										</td>
			    <td class="editionCell"		><%=videogioco.getEdizione()%>									</td>
			    <td class="descriptionCell"	>
			    	<%=videogioco.getDescrizione()%>
			    	<form action="StorageControl" method="post">
			    		<input type="hidden" name="action" value="ExtendedDescription"> 
						<input type="hidden" name="codice_prodotto" value=<%=videogioco.getCodice_prodotto()%>> 
						<input type="submit" value="More...">
					</form>
			    </td>
			    <td class="priceCell"		><%=videogioco.getPrezzo_vetrina()%>									</td>
			    <td class="dateCell"		><%=videogioco.getData_uscita()%>										</td>
			    <td class="platformCell"	><%=videogioco.getPiattaforma()%>										</td>
			    <td class="consoleCell"		><%=videogioco.getConsole()%>											</td>
			    <td class="scontoCell"		><%=videogioco.getSconto()%>											</td>
			    <td class="copiesCell"		><%=videogioco.getCopie()%>												</td>
			    <td class="developerCell"	><%=videogioco.getSviluppatore()%>										</td>
			    <td class="publisherCell"	><%=videogioco.getPubblisher()%>										</td>
			    <!-- uso Jquery per visualizzare l'immagine -->
			    <td class="fotoCell"	><img id="fotoName<%=videogioco.getCodice_prodotto()%>" src="./immagini Videogiochi/<%=videogioco.getFoto()%>">	</td>
			    <td>
			    	<form action="CartServlet" method="post">
			    		<input type="hidden" name="action" value=addToCart>
			    		<input type="hidden" name="insertIntoCart" value=<%=videogioco.getCodice_prodotto()%>>
			    		<input type="submit" value="Add to cart">
			    	</form>
			    </td>
		  	</tr>
			
	<%
				}
	%>
	
	</table>
	
	<script>highlight();</script>
	
	<form action="CartServlet" method="post">
		<input type="hidden" name="action" value="showCart">
		<input type="submit" value="Show cart">
	</form>

<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>