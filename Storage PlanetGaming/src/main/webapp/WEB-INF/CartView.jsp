<%@ page import ="java.util.*" import = "CartItem.java" import = "planetGaming.Carrello.*" %>


<% 
	Collection<?> ordini = (Collection<?>) request.getAttribute("CartItem");
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Page</title>
</head>
<body>

<jsp:include page="/WEB-INF/header.jsp" />
<h1>Cart View</h1>
<p>Show the shopping cart</p><br>

<table>

   <tr>
   <th>Item ID</th>
   <th>Description</th>
   <th>Unit Cost</th>
   <th>Number</th>
   <th>Total Cost</th>
   </tr>

<%
	  		//scorre l'array ordini e stampa gli elementi nella tabella
	  		Iterator<?> it = ordini.iterator();
				while (it.hasNext()) {
					CartItem ordine = (CartItem) it.next();
	%>
	
	<tr>
	<td><%=ordine.getItemID()%></td>
	<td><%=ordine.getShortDescription()%></td>
	<td><%=ordine.getUnitCost()%></td>
	<td><%=ordine.getNumItems()%></td>
	<td><%=ordine.getTotalCost()%></td>
	<td><form action = "CartControl" method = "post">
	<input type = "number" min = "1" max = "<%ordine.getItem().getNumItem(); %>">
	<input type = "submit" value = "Elimina dal carrello">
	</form></td><!-- Inserire link dell'immagine nel pulsante elimina -->
	</tr>
<%  } %>
</table>
    
    <!--  Pulsanti "Aggiorna ordine" e "Procedi all'acquisto" -->
    <form action = "CartControl" method = "post">
    <input type = "submit" value = "Aggiorna Ordine">
    </form>
    <form action = "Indirizzo e pagamento" method = "post">
    <input onclick ="window.location.href = 'link gestione indirizzo e pagamento' "type ="submit" value = "Procedi all'Ordine">
    </form>

<jsp:include page="/WEB-INF/footer.jsp" />

</body>
</html>