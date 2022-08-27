<%@ page import="java.util.*" import="planetGaming.Ordine.*"%>


<% 	
	Collection<?> carrello = (Collection<?>) request.getSession().getAttribute("cart");
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
	
	<% if(carrello != null && !carrello.isEmpty()){ %>
	<table>
		<tr>
			<th>Id product</th>
			<th>Id order</th>
			<th>id videogame</th>
			<th>Videogame's name</th>
			<th>Total price</th>
			<th>Total discount</th>
			<th>Quantity</th>
			<th>Photo</th>
	  	</tr>
	  <!-- contenuto tabella -->
	  
	<%
	  		//scorre l'array videogiochi e stampa gli elementi nella tabella
	  		Iterator<?> it = carrello.iterator();
				while (it.hasNext()) {
					prodottoOrdineBean prodotto = (prodottoOrdineBean) it.next();
	%>
		
			<tr id="<%=prodotto.getIdProdottoOrdine()%>">
				<td class="idCell"			><%=prodotto.getIdProdottoOrdine()%>							</td>
				<td class="idOrderCell"		><%=prodotto.getIdOrdine()%>									</td>
				<td class="idVideogameCell"	><%=prodotto.getIdVideogioco()%>								</td>
			    <td class="nameCell"		><%=prodotto.getNomeVideogioco()%>								</td>
			    <td class="priceCell"		><%=prodotto.getPrezzoAcquisto()%>								</td>
			    <td class="scontoCell"		><%=prodotto.getScontoAcquisto()%>								</td>
			    <td class="copiesCell"		><%=prodotto.getQuantitaAcquisto()%>							</td>
			    <!-- uso Jquery per visualizzare l'immagine -->
			    <td class="fotoCell"	><img id="fotoName<%=prodotto.getIdProdottoOrdine()%>" src="./immagini Videogiochi/<%=prodotto.getFoto()%>">	</td>
			    <td>
			    	<form action="CartServlet" method="post">
			    		<input type="hidden" name="action" value="removeFromCart">
			    		<input type="hidden" name="deleteFromCart" value=<%=prodotto.getIdVideogioco()%>>
			    		<input type="submit" value="Remove from cart">
			    	</form>
			    </td>
		  	</tr>
			
	<%
				}
	}
	%>
	
	</table>

	
	<form action="CartServlet" method="post">
		<input type="hidden" name="action" value="Purchase">
		<input type="submit" value="Purchase">
	</form>
	
	<script>highlight();</script>
</body>
</html>