<%@ page import="java.util.*" import="planetGaming.Videogioco.*"%>


<% 
	//Check user credentials
	Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
	if((isAdmin == null) || (!isAdmin.booleanValue()))
	{
		response.sendRedirect("./login-form.jsp");
	}
	
	//verifica se l'oggetto videogiochi è presente nella richiesta altrimenti passa il controllo alla servlet "StorageControl" che lo genera
	Collection<?> videogiochi = (Collection<?>) request.getSession().getAttribute("videogiochi"); //va bene anche "session" al posto di "request.getSession()"
	if(videogiochi == null) {
		response.sendRedirect("StorageControl");	
	}
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Storage Admin</title>

<link rel="stylesheet" href="trueStorageStyle.css">
<link rel="stylesheet" href="PopUp.css">

</head>
<body>
<script type="text/javascript" src="ControllaCredenziali.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="confirmDeleteModifyInsert.js"></script>
<jsp:include page="/WEB-INF/header.jsp" />

	<div class="popupContainer">
	  <span class="popupText" id="popup">Videogame Successfully deleted</span>
	</div>
	
	<div class="popupContainer">
	  <span class="popupText" id="popupModify">Videogame Successfully updated</span>
	</div>

	<h1>Welcome to the Storage Page</h1>
	<p>Here you can add, delete, remove element from the database</p>
	<br><br>
	
	<table>
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
	  
	<%
	  		//scorre l'array videogiochi e stampa gli elementi nella tabella
	  		Iterator<?> it = videogiochi.iterator();
				while (it.hasNext()) {
					VideogiocoBean videogioco = (VideogiocoBean) it.next();
	%>
		
			<tr id="<%=videogioco.getCodice_prodotto()%>">
				<td><%=videogioco.getCodice_prodotto()%></td>
			    <td><%=videogioco.getNome()%></td>
			    <td><%=videogioco.getEdizione()%></td>
			    <td>
			    	<%=videogioco.getDescrizione()%>
			    	<form action="StorageControl" method="post">
			    		<input type="hidden" name="action" value="ExtendedDescription"> 
						<input type="hidden" name="codice_prodotto" value=<%=videogioco.getCodice_prodotto()%>> 
						<input type="submit" value="More...">
					</form>
			    </td>
			    <td><%=videogioco.getPrezzo_vetrina()%></td>
			    <td><%=videogioco.getData_uscita()%></td>
			    <td><%=videogioco.getPiattaforma()%></td>
			    <td><%=videogioco.getConsole()%></td>
			    <td><%=videogioco.getSconto()%></td>
			    <td><%=videogioco.getCopie()%></td>
			    <td><%=videogioco.getSviluppatore()%></td>
			    <td><%=videogioco.getPubblisher()%></td>
			    <!-- uso Jquery per visualizzare l'immagine -->
			    <td><img src="./immagini Videogiochi/<%=videogioco.getFoto()%>"></td>
		  	</tr>
			
	<%
				}
	%>
	
	</table>
	
			    
			 
	<br>
	<form id="insertForm" action="StorageControl" enctype="multipart/form-data" method="post">
		<fieldset>
			<legend>Insert</legend>
			<input type="hidden" name="action" value="insert"> 
			
			Name: 			<input name="nome" 					type="text" 	maxlength="20" required placeholder="enter name"><br> 
			Edition: 		<input name="edizione" 				type="text" 	maxlength="20" required placeholder="enter name"><br> 
			Description: 	<input name="descrizione" 			type="text" 	maxlength="60" required placeholder="enter name"><br> 
			Price: 			<input name="prezzo_vetrina" 		type="text" 	maxlength="20" required placeholder="enter name"><br>
			<p class="ErrorParagraph"></p>
			
			Date: 			<input name="data" 			type="date" 	maxlength="20" required placeholder="DD/MM/YYYY or DD-MM-YYYY"><br>
			<p class="ErrorParagraph"></p>
			
			Platform: 		<input name="piattaforma" 			type="text" 	maxlength="20" required placeholder="enter name"><br>
			Console: 		<input name="console" 				type="text" 	maxlength="20" required placeholder="enter name"><br>
			Sale: 			<input name="sconto" 				type="text" 	maxlength="20" required placeholder="enter name"><br>
			<p class="ErrorParagraph"></p>
			
			Copy: 			<input name="#copie" 				type="text" 	maxlength="20" required placeholder="enter name"><br>
			<p  class="ErrorParagraph"></p>
			
			Developer: 		<input name="Sviluppatore" 			type="text" 	maxlength="20" required placeholder="enter name"><br>
			Publisher: 		<input name="Pubblisher" 			type="text" 	maxlength="20" required placeholder="enter name"><br>
	
			<!-- mi sembra eccessivo rendere la foto un campo required -->
			Photo: 			<input class="file" type="file" name="foto"  placeholder="" maxlength="255"><br>
					
			<button type="button" id ="insertButton" onclick ="checkInsert('insertForm')">insert</button>
			<input type="reset" value="Reset">
		</fieldset>
	</form>
	
	<br>
	<form id="deleteForm" action="StorageControl" method="post">
		<fieldset>
			<legend>Delete</legend>
					<input  name="action" 			type="hidden" 	value="delete">
			
			Code:	<input	name="codice_prodotto" 	type="text" 					maxlength="20" required placeholder="enter code"><br>
			<p class="ErrorParagraph"></p>
			
			<!--  <button type="button" id ="deleteButton" onclick ="checkIdToDelete('deleteForm')">delete</button>-->
			<input type="submit" name="submit" value="Submit" />
			<input type="reset" value="Reset">
		</fieldset>
	</form>
	
	<script>deleteOnSubmit();</script>
	
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
		<form id="modifyNomeId" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Name</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					Name: 			<input name="nome" 				type="text" 	maxlength="20"	required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		
		<form id="modifyEditionId" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Edition</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					Edition: 		<input name="edizione" 			type="text" 	maxlength="20"	required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		
		<form id="modifyDescriptionId" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Description</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					Description: 	<input name="descrizione" 		type="text" 	maxlength="60"	required	placeholder="enter description"	><br> 
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		
		
		
		<form id="modifyPriceId" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Price</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Price: 			<input name="prezzo_vetrina" 	type="text" 	maxlength="20"	required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="button" id ="modifyButton" onclick ="checkModify('modifyPriceId', checkPrice)">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		
		<script> modifyOnSubmit("#modifyPriceId", checkPrice, 1);</script>
		
		
		
		<form id="modifyDateId" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Date</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					Date: 			<input name="data_uscita" 		type="text" 	maxlength="20"	required	placeholder="DD/MM/YYYY or DD-MM-YYYY"><br>
					<p class="ErrorParagraph"></p>
									
					<button type="button" id ="modifyButton" onclick ="checkModify('modifyDateId', checkDate)">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		
		<form id="modifyPlatformId" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Platform</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					Platform: 		<input name="piattaforma" 		type="text" 	maxlength="20"	required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		
		<form id="modifyConsoleId" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Console</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					Console: 		<input name="console" 				type="text" 	maxlength="20" required placeholder="enter name"><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		
		<form id="modifySaleId" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Sale</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					Sale: 			<input name="sconto" 				type="text" 	maxlength="20" required placeholder="enter name"><br>
					<p class="ErrorParagraph"></p>
									
					<button type="button" id ="modifyButton" onclick ="checkModify('modifySaleId', checkSale)">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		
		<form id="modifyCopyId" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Copy</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					Copy:			<input name="#copie" 			type="text" 	maxlength="20"	required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="button" id ="modifyButton" onclick ="checkModify('modifyCopyId', checkCopy)">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		
		<form id="modifyDeveloperId" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Developer</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					Developer: 		<input name="Sviluppatore" 		type="text" 	maxlength="20" 		required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		
		<form id="modifyPublisherId" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Publisher</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					Publisher: 		<input name="Pubblisher" 			type="text" 	maxlength="20" required placeholder="enter name"><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		
		<form id="modifyPhotoId" action="StorageControl" enctype="multipart/form-data" method="post">
				<fieldset>
					<legend>modify Photo</legend>
					<input type="hidden" name="action" value="modifyPhoto"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					Photo: 			<input name="foto"				type="file"    	maxlength="255"		class="file"	required><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
	
	<form action="Logout" method="get">
		<input type="submit" value="Logout"/>
	</form>

<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>