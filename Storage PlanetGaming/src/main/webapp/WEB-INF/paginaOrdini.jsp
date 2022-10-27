<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" import="planetGaming.Ordine.*"%>

<%
	//Check user credentials
	Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
	
	if((isLogged == null) || isLogged.equals(false))
	{
		//response.sendRedirect("./login-form.jsp");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("./login-form.jsp");
		dispatcher.forward(request, response);
	}
	
	Collection<?> ordini = (Collection<?>) request.getSession().getAttribute("paginaOrdini");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders</title>
<link rel="stylesheet" href="css/productFlexTable.css">
<link rel="stylesheet" href="css/inputFieldStyle.css">
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/trueStorageStyle.css">
</head>
<body>
	<script type="text/javascript" src="javaScript/HideShowButton.js"></script>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<h1>Welcome to orders page</h1><br>
	<p>Here you can see all orders made</p>
	<br><br>

	<form action="UserInfo" method="post">
		<input type="hidden" name="action" value="OrdersByTotal"> 
		<input type="submit" value="Orders by total">
	</form>

<%
	if(ordini != null && !ordini.isEmpty()){
%>
	  	
<h2>Orders</h2>
		  
		<%
	  		//scorre l'array ordini e stampa gli elementi nella tabella
	  		Iterator<?> it = ordini.iterator();
				while (it.hasNext()) {
					OrdineBean ordine = (OrdineBean) it.next();
		%>		
					<div class="flex-container">
						<div class="product-buttons">
							<button onclick="show(<%=ordine.getIdOrdine()%>)">mostra</button>
							<button onclick="hide(<%=ordine.getIdOrdine()%>)">nascondi</button>
							<form action="Fattura" method="post">
								<input type="hidden" name="OrderId" value=<%=ordine.getIdOrdine()%>> 
								<input type="submit" value="Invoice">
							</form>
    					</div>
		    				<p>idOrder: <span class="idCell"><%=ordine.getIdOrdine()%></span> | idUser: <span class=""><%=ordine.getIdUtente()%></span>  | id paymenth method: <span class=""><%=ordine.getIdModalitaPagamento()%></span></p>
		    				<p>id Address: <span class=""><%=ordine.getIdIndirizzo()%></span> | total price: <span class=""><%=ordine.getPrezzoTotale()%></span> | Order date: <span class=""><%=ordine.getDataOrdine()%> | tracking: <%=ordine.getTracking()%></span></p>
	       				</div>


 		

	<%
					
					Iterator<?> itProdotti = ordine.getProdottiOrdine().iterator();
					while (itProdotti.hasNext()) {
						prodottoOrdineBean prodottoOrdine = (prodottoOrdineBean) itProdotti.next();
	%>
					<div class="flex-container <%=ordine.getIdOrdine()%>">
		    			<p>id product:<span class="idCell"><%=prodottoOrdine.getIdProdottoOrdine()%></span> | id Order: <span class=""><%=prodottoOrdine.getIdOrdine()%></span>  | id videogame: <span class=""><%=prodottoOrdine.getIdVideogioco()%></span></p>
		    			<p>videgame's name: <span class=""><%=prodottoOrdine.getNomeVideogioco()%></span> | price: <span class=""><%=String.format("%.2f", prodottoOrdine.getPrezzoAcquisto()) + " euro"%></span> | discount: <span class=""><%=String.format("%.2f", prodottoOrdine.getScontoAcquisto()) + " euro"%></span></p>
		    			<p>quantity: <span class=""><%=prodottoOrdine.getQuantitaAcquisto()%></span> | iva: <span class=""><%=prodottoOrdine.getIva()%></span></p>
		    			<img id="fotoName<%=ordine.getIdOrdine()%>" class="product-image fotoCell" src="./immagini Videogiochi/<%=prodottoOrdine.getFoto()%>">
	       			</div>

			 <%
					}
				}
			 %>
	
<%
	}else{
%>
	<p>Nothing to see here... yet!</p>
<%
	}
%>
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>