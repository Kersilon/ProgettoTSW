<%@ page import = "java.util.*" import = "Catalog.java" import = "planetGaming.Carrello.*"  %>

<% Collection<?> products = (Collection<?>) request.getAttribute("CatalogItem"); 
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catalog</title>
</head>
<body>

<jsp:include page="/WEB-INF/header.jsp" />
<h1>Catalog View</h1>
<p>Show the catalog!</p><br>


<%

      Iterator<?> it = products.iterator();
      while(it.hasNext()){
    	  CatalogItem item = (CatalogItem) it.next();
 %>   	  
     
     <!--  inserire foto e link pagina prodotto -->
     <h2><a href = "link pagina prodotto"><% item.getShortDescription();%></a></h2>
     <p><% item.getLongDescription();%></p>
     <br><br>
<% 

}
      %>  

<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>