<%@ page import ="java.util.*" import = "OrderPage.java" import = "planetGaming.Carrello.*" %>


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
<p>Show the shopping cart!</p><br>

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
	</tr>
<%  } %>
</table>

<jsp:include page="/WEB-INF/footer.jsp" />

</body>
</html>