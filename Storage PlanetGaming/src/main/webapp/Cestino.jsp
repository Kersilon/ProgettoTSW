<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		ps: se qualcosa che si trova in questa pagina non funziona potrebbe essere che il codice java è stato commentato in modo sbagliato cioè
		con tag html invece di usare i commenti java 
	-->
		
	<!-- vecchia tabella usata nello storage admin prima delle flexbox -->
<%-- 	<table>
		<thead>
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
	  	</thead>
	  <!-- contenuto tabella -->
	  	<tbody>
		
		
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
		  	</tr>
		</tbody>
	</table> --%>
	
	







	<!-- vecchia tabella usata nel carrello prima delle flexbox -->
<%-- 
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
			    </td>
		  	</tr>
				</table> --%>









<!-- tabella metodi di pagamento in paginaProtetta prima delle flexbox -->
<%-- 				<h2>Payment methods</h2>
	<table>
		<tr>
			<th>numero_carta</th>
			<th>ccv</th>
			<th>circuito</th>
			<th>scadenza</th>
			<th>codiceUtente</th>
			<th>nome_intestatario</th>
			<th>cognome_intestatario</th>
			<th>idCarta</th>
	  	</tr>
		  <!-- contenuto tabella -->
			<tr>
				<td><%=metodoPagamento.getNumero_carta()%></td>
				<td><%=metodoPagamento.getCcv()%></td>
				<td><%=metodoPagamento.getCircuito()%></td>
				<td><%=metodoPagamento.getScadenza()%></td>
				<td><%=metodoPagamento.getCodiceUtente()%></td>
				<td><%=metodoPagamento.getNome_intestatario()%></td>
				<td><%=metodoPagamento.getCognome_intestatario()%></td>
				<td><%=metodoPagamento.getIdCarta()%></td>
		  	</tr>
		  	
		  	</table> --%>





	
	
<!-- tabella indirizzi della pagina protetta prima delle flexBox	 -->
<%-- 					<h2>Addresses</h2>
			<table>
				<tr>
					<th>via</th>
					<th>cap</th>
					<th>citta</th>
					<th>provincia</th>
					<th>codiceUtente</th>
					<th>idIndirizzo</th>
			  	</tr>
				  <!-- contenuto tabella -->
					<tr>
						<td><%=indirizzo.getVia()%></td>
						<td><%=indirizzo.getCap()%></td>
						<td><%=indirizzo.getCitta()%></td>
						<td><%=indirizzo.getProvincia()%></td>
						<td><%=indirizzo.getCodice_utente()%></td>
						<td><%=indirizzo.getIdIndirizzo()%></td>
				  	</tr>
				  	</table> --%>
	
	
	
	
	
	
	
<!-- tabella dati utente in paginaProtetta prima delle flexBox	 -->
<%-- 				<table>
				<tr>
					<th>Name</th>
					<th>Surname</th>
					<th>Birth date</th>
					<th>Username</th>
					<th>Password
						
					</th>
					<th>Email</th>
					<th>Phone number</th>
					<th>UserId</th>
					<th>Admin?</th>
			  	</tr>
				  <!-- contenuto tabella -->
			  
					<tr>
						<td><%=datiUtente.getNome()%></td>
						<td><%=datiUtente.getCognome()%></td>
						<td><%=datiUtente.getDataNascita()%></td>
						<td><%=datiUtente.getNomeUtente()%></td>
						<td><div  class ="hide"><%=datiUtente.getPassword()%></div></td>
						<td><%=datiUtente.getEmail()%></td>
						<td><%=datiUtente.getTelefono()%></td>
						<td><%=datiUtente.getCodiceUtente()%></td>
						<td><%=datiUtente.isAMMINISTRATORE()%></td>
				  	</tr>
			</table> --%>
	
	
	
	
	
	
	
	
	
		
<!-- tabella utenti in Administrator page prima delle flex box	 -->
<%-- 	
				<table>
					<tr>
						<th>User code</th>
						<th>Fiscal code</th>
						<th>name</th>
						<th>surname</th>
						<th>Birth date</th>
						<th>Username</th>
						<th>Password</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Purchases</th>
						<th>is Administrator?</th>
						<th>Sing in date</th>
				  	</tr>
					  <!-- contenuto tabella -->
					  								<tr>
					<td><%=utente.getCodiceUtente()%></td>
					<td><%=utente.getCodiceFiscale()%></td>
					<td><%=utente.getNome()%></td>
					<td><%=utente.getCognome()%></td>
					<td><%=utente.getDataNascita()%></td>
					<td><%=utente.getNomeUtente()%></td>
					<td><%=utente.getPassword()%></td>
					<td><%=utente.getEmail()%></td>
					<td><%=utente.getTelefono()%></td>
					<td><%=utente.getAcquisti()%></td>
					<td><%=utente.isAMMINISTRATORE()%></td>
					<td><%=utente.getDataRegistrazione()%></td>
			  	</tr>
				</table> --%>
	
	
	
<!-- tabella ordini in AdministratorPage prima delle flex box -->	
<%-- 				<table>
					<tr>
						<th>idOrdine</th>
						<th>idUtente</th>
						<th>idModalitaPagamento</th>
						<th>idIndirizzo</th>
						<th>prezzoTotale</th>
						<th>data</th>
						<th>tracking</th>
				  	</tr>
					  <!-- contenuto tabella -->
					  	
				<tr>
					<td><%=ordine.getIdOrdine()%></td>
					<td><%=ordine.getIdUtente()%></td>
					<td><%=ordine.getIdModalitaPagamento()%></td>
					<td><%=ordine.getIdIndirizzo()%></td>
					<td><%=ordine.getPrezzoTotale()%></td>
					<td><%=ordine.getDataOrdine()%></td>
					<td><%=ordine.getTracking()%></td>

			  	</tr>
			
				</table>	 --%>
	
	



<!-- tabella pagina descrizione estesa prima delle flex box -->
<%-- <table>
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
	  
			<tr>
				<td><%=videogioco.getCodice_prodotto()%></td>
			    <td><%=videogioco.getNome()%></td>
			    <td><%=videogioco.getEdizione()%></td>
			    <td><%=videogioco.getDescrizione()%></td>
			    <td><%=videogioco.getPrezzo_vetrina()%></td>
			    <td><%=videogioco.getData_uscita()%></td>
			    <td><%=videogioco.getPiattaforma()%></td>
			    <td><%=videogioco.getConsole()%></td>
			    <td><%=videogioco.getSconto()%></td>
			    <td><%=videogioco.getCopie()%></td>
			    <td><%=videogioco.getSviluppatore()%></td>
			    <td><%=videogioco.getPubblisher()%></td>
			    <!-- uso Jquery per visualizzare l'immagine -->
			    <td><img src="${pageContext.request.contextPath}/immagini Videogiochi/<%=videogioco.getFoto()%>"></td>
		  	</tr>
	
	</table> --%>




	
	
	<!-- vecchio form di modifiy per storage admin, prima che usassi ajax -->
		<!--
	<br>
	<form id ="modifyForm" action="StorageControl" enctype="multipart/form-data" method="post">
		<fieldset>
			<legend>Modify</legend>
			<input type="hidden" name="action" value="modify"> 
			
			ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 						placeholder="enter ID"			><br>
			<p  class="ErrorParagraph"></p>
			
			Name: 			<input name="nome" 				type="text" 	maxlength="20" 						placeholder="enter name"		><br> 
			Edition: 		<input name="edizione" 			type="text" 	maxlength="20" 						placeholder="enter name"		><br> 
			Description: 	<input name="descrizione" 		type="text" 	maxlength="60" 						placeholder="enter description"	><br> 
			Price: 			<input name="prezzo_vetrina" 	type="text" 	maxlength="20" 						placeholder="enter name"		><br>
			<p  class="ErrorParagraph"></p>
			
			Date: 			<input name="data_uscita" 		type="text" 	maxlength="20"						placeholder="DD/MM/YYYY or DD-MM-YYYY"><br>
			<p  class="ErrorParagraph"></p>
			
			Platform: 		<input name="piattaforma" 		type="text" 	maxlength="20" 						placeholder="enter name"		><br>
			Console: 		<input name="console" 			type="text"	 	maxlength="20" 						placeholder="enter name"		><br>
			Sale: 			<input name="sconto" 			type="text" 	maxlength="20" 						placeholder="enter name"		><br>
			<p  class="ErrorParagraph"></p>
			
			Copy:			<input name="#copie" 			type="text" 	maxlength="20" 						placeholder="enter name"		><br>
			<p  class="ErrorParagraph"></p>
			
			Developer: 		<input name="Sviluppatore" 		type="text" 	maxlength="20" 						placeholder="enter name"		><br>
			Publisher: 		<input name="Pubblisher" 		type="text" 	maxlength="20" 						placeholder="enter name"		><br>
			Photo: 			<input name="foto"				type="file"    	maxlength="255"		class="file"									><br>
					
	
			<button type="button" id ="modifyButton" onclick ="checkModify('modifyForm')">modify</button>
			<input type="reset" value="Reset">
				
		</fieldset>
	</form>
	 -->
</body>
</html>