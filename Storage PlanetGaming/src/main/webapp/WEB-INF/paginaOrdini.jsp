<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" import="planetGaming.Ordine.*"%>

<%
	//Check user credentials
	Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
	
	if((isLogged == null) || isLogged.equals(false))
	{
		response.sendRedirect("./login-form.jsp");
	}
	
	Collection<?> ordini = (Collection<?>) request.getSession().getAttribute("ordini");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="trueStorageStyle.css">

<meta charset="ISO-8859-1">
<title>Orders</title>
</head>
<body>
	<script type="text/javascript" src="HideShowButton.js"></script>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<h1>Welcome to orders page</h1><br>
	<p>Here you can see all orders made</p>
	<br><br>
	
	<h2>Orders</h2>
	<table>
		<tr>
			<td>Order<td>
		</tr>
		<tr>
			<th>id Order</th>
			<th>id User</th>
			<th>id paymenth method</th>
			<th>id Address</th>
			<th>total price</th>
			<th>Order date</th>
			<th>tracking</th>
	  	</tr>
	  	
		  <!-- contenuto tabella -->
		  
		<%
	  		//scorre l'array ordini e stampa gli elementi nella tabella
	  		Iterator<?> it = ordini.iterator();
				while (it.hasNext()) {
					OrdineBean ordine = (OrdineBean) it.next();
		%>
					<tr>
						<td><%=ordine.getIdOrdine()%></td>
						<td><%=ordine.getIdUtente()%></td>
						<td><%=ordine.getIdModalitaPagamento()%></td>
						<td><%=ordine.getIdIndirizzo()%></td>
						<td><%=ordine.getPrezzoTotale()%></td>
						<td><%=ordine.getDataOrdine()%></td>
						<td><%=ordine.getTracking()%></td>
						<td>
							<button type="button" id ="ShowButton" onclick ="show()">Show products</button>
							<button type="button" id ="HideButton" onclick ="hide()">Hide products</button>
						</td>
						<td>
							<form action="Fattura" method="post">
								<input type="hidden" name="OrderId" value=<%=ordine.getIdOrdine()%>> 
								<input type="submit" value="Invoice">
							</form>
						</td>
		  			</tr>
 		
 						
		<tr class ="hide">
			<th>id product</th>
			<th>id Order</th>
			<th>id videogame</th>
			<th>id videgame's name</th>
			<th>price</th>
			<th>discount</th>
			<th>quantity</th>
			<th>iva</th>
			<th>Photo</th>
	  	</tr>
	<%
					Iterator<?> itProdotti = ordine.getProdottiOrdine().iterator();
					while (itProdotti.hasNext()) {
						prodottoOrdineBean prodottoOrdine = (prodottoOrdineBean) itProdotti.next();
	%>
						<tr  class ="hide">
							<td><%=prodottoOrdine.getIdProdottoOrdine()%></td>
							<td><%=prodottoOrdine.getIdOrdine()%></td>
							<td><%=prodottoOrdine.getIdVideogioco()%></td>
							<td><%=prodottoOrdine.getNomeVideogioco()%></td>
							<td><%=prodottoOrdine.getPrezzoAcquisto()%></td>
							<td><%=prodottoOrdine.getScontoAcquisto()%></td>
							<td><%=prodottoOrdine.getQuantitaAcquisto()%></td>
							<td><%=prodottoOrdine.getIva()%></td>
							<td><img src="./immagini Videogiochi/<%=prodottoOrdine.getFoto()%>"></td>
			  			</tr>
			 <%
					}
				}
			 %>
			  			

	</table>
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>