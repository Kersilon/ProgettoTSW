<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*" import="planetGaming.Videogioco.*"%>
    
<%
		VideogiocoBean videogioco = (VideogiocoBean) request.getAttribute("paginaVideogioco");
%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/body.css">
	<link rel="stylesheet" href="css/trueStorageStyle.css">
	<link rel="stylesheet" href="css/productFlexTable.css">
	<link rel="stylesheet" href="css/PopUp.css">
	
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript" src="javaScript/popup.js"></script>
	<script type="text/javascript" src="javaScript/photoHighlight.js"></script>

	<jsp:include page="/WEB-INF/header.jsp" />
	
	
	<script>
	$(document).ready(function(){
		takePopups();
	});
	</script>	
		<div id="sharedPopupsContainer">
	</div>
	
	
	<form action="StorageControl" method="post">
		<input type="submit" value="Previous page">
	</form>

		    <div id="<%=videogioco.getCodice_prodotto()%>" class="flex-container">
    		<div class="product-buttons">
				
				<form class="product-form addToCartForm" action="CartServlet" method="post">
					<input type="hidden" name="action" value=addToCart>
					<input type="hidden" name="ajax" value=true>
					<input type="hidden" name="insertIntoCart" value=<%=videogioco.getCodice_prodotto()%>>
					<input type="submit" value="Add to cart">
				</form>
			
    		</div>
	    		<div class="product-info">
	    			<p>ID:<span class="idCell"><%=videogioco.getCodice_prodotto()%></span> | Name: <span class="nameCell"><%=videogioco.getNome()%></span>  | Edition: <span class="editionCell"><%=videogioco.getEdizione()%></span></p>
	    			<p>Description: <span class="descriptionCell"><%=videogioco.getDescrizione()%></span> | Price: <span class="priceCell"><%=videogioco.getPrezzo_vetrina()%></span> | Date: <span class="dateCell"><%=videogioco.getData_uscita()%></span></p>
	    			<p>Platform: <span class="platformCell"><%=videogioco.getPiattaforma()%></span> | Console: <span class="consoleCell"><%=videogioco.getConsole()%></span> | Sale: <span class="scontoCell"><%=videogioco.getSconto()%></span></p>
	    			<p>Copy: <span class="copiesCell"><%=videogioco.getCopie()%></span> | Developer: <span class="developerCell"><%=videogioco.getSviluppatore()%></span> | Publisher: <span class="publisherCell"><%=videogioco.getPubblisher()%></span></p>
	    		</div>
	    		
	    		<img id="fotoName<%=videogioco.getCodice_prodotto()%>" class="product-image fotoCell" src="./immagini Videogiochi/<%=videogioco.getFoto()%>">
    		</div>
    		
    		<script>highlight();</script>
			<script>activatePopup();</script>
	
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>