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
	<link rel="stylesheet" href="body.css">
	<link rel="stylesheet" href="productFlexTable.css">
</head>
<body>
<script type="text/javascript" src="ControllaCredenziali.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="photoHighlight.js"></script>
<script type="text/javascript" src="confirmDeleteModifyInsert.js"></script>

<jsp:include page="/WEB-INF/header.jsp" />

	<h1>Cart</h1>
	
	<% if(carrello != null && !carrello.isEmpty()){ %>

	  
	<%
	  		//scorre l'array videogiochi e stampa gli elementi nella tabella
	  		Iterator<?> it = carrello.iterator();
				while (it.hasNext()) {
					prodottoOrdineBean prodotto = (prodottoOrdineBean) it.next();
	%>
		
			
		<div id="<%=prodotto.getIdProdottoOrdine()%>" class="flex-container">
    		<div class="product-buttons">
		 		<form class="product-form" action="StorageControl" method="post">
					<input type="hidden" name="action" value="ExtendedDescription"> 
					<input type="hidden" name="codice_prodotto" value=<%=prodotto.getIdProdottoOrdine()%>> 
					<input type="submit" value="<%=prodotto.getNomeVideogioco()%>">
				</form>
				<form action="CartServlet" method="post">
			    		<input type="hidden" name="action" value="removeFromCart">
			    		<input type="hidden" name="deleteFromCart" value=<%=prodotto.getIdVideogioco()%>>
			    		<input type="submit" value="Remove from cart">
			    </form>
    		</div>
    		<div class="product-info">
    			<p>Id product:<span class="idCell"><%=prodotto.getIdProdottoOrdine()%></span> | Id order: <span class="idOrderCell"><%=prodotto.getIdOrdine()%></span>  | id videogame: <span class="idVideogameCell"><%=prodotto.getIdVideogioco()%></span></p>
    			<p>Videogame's name: <span class="nameCell"><%=prodotto.getNomeVideogioco()%></span> | Total price: <span class="priceCell"><%=prodotto.getPrezzoAcquisto()%></span> | Total discount: <span class="scontoCell"><%=prodotto.getScontoAcquisto()%></span></p>
    			<p>Quantity: <span class="copiesCell"><%=prodotto.getQuantitaAcquisto()%></span></p>
    		</div>
    		<img id="fotoName<%=prodotto.getIdProdottoOrdine()%>" class="product-image fotoCell" src="./immagini Videogiochi/<%=prodotto.getFoto()%>">
    	</div>
			
			
			
			
			

	<%
				}
	%>
	

	<%
	}else{ 
	%>
	
		<p>Nothing to see here... yet!</p>
	<%
	}
	%>

	<form action="CartServlet" method="post">
		<input type="hidden" name="action" value="Purchase">
		<input type="submit" value="Purchase">
	</form>
	
	<script>highlight();</script>
</body>
</html>