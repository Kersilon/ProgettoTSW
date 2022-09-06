<%@ page import="java.util.*" import="planetGaming.Videogioco.*"%>

<%
		VideogiocoBean videogioco = (VideogiocoBean) request.getAttribute("paginaVideogioco");
%>

<div id="<%=videogioco.getCodice_prodotto()%>" class="flex-container">
    		<div class="product-buttons">
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
	    		<div class="product-info">
	    			<p>ID:<span class="idCell"><%=videogioco.getCodice_prodotto()%></span> | Name: <span class="nameCell"><%=videogioco.getNome()%></span>  | Edition: <span class="editionCell"><%=videogioco.getEdizione()%></span></p>
	    			<p>Description: <span class="descriptionCell"><%=videogioco.getDescrizione()%></span> | Price: <span class="priceCell"><%=videogioco.getPrezzo_vetrina()%></span> | Date: <span class="dateCell"><%=videogioco.getData_uscita()%></span></p>
	    			<p>Platform: <span class="platformCell"><%=videogioco.getPiattaforma()%></span> | Console: <span class="consoleCell"><%=videogioco.getConsole()%></span> | Sale: <span class="scontoCell"><%=videogioco.getSconto()%></span></p>
	    			<p>Copy: <span class="copiesCell"><%=videogioco.getCopie()%></span> | Developer: <span class="developerCell"><%=videogioco.getSviluppatore()%></span> | Publisher: <span class="publisherCell"><%=videogioco.getPubblisher()%></span></p>
	    		</div>
	    		
	    		<img id="fotoName<%=videogioco.getCodice_prodotto()%>" class="product-image fotoCell" src="./immagini Videogiochi/<%=videogioco.getFoto()%>">
    		</div>