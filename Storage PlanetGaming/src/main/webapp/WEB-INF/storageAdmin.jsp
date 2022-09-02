<%@ page import="java.util.*" import="planetGaming.Videogioco.*"%>


<% 
	//Check user credentials
	Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
	if((isAdmin == null) || (!isAdmin.booleanValue()))
	{
		//response.sendRedirect("./login-form.jsp");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-form.jsp");
		dispatcher.forward(request, response);	
	}
	
	//verifica se l'oggetto videogiochi è presente nella richiesta altrimenti passa il controllo alla servlet "StorageControl" che lo genera
	Collection<?> videogiochi = (Collection<?>) request.getSession().getAttribute("videogiochi"); //va bene anche "session" al posto di "request.getSession()"
	if(videogiochi == null) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/StorageControl");
		dispatcher.forward(request, response);
	}
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Storage Admin</title>
	
	<!-- <link rel="stylesheet" href="trueStorageStyle.css"> -->
	<link rel="stylesheet" href="Table.css">
	<link rel="stylesheet" href="PopUp.css">
	<link rel="stylesheet" href="productBox.css">
	<link rel="stylesheet" href="productFlexTable.css">
	<link rel="stylesheet" href="inputFieldStyle.css">
	<link rel="stylesheet" href="body.css">
	<link rel="stylesheet" href="trueStorageStyle.css">
</head>
<body>
<script type="text/javascript" src="ControllaCredenziali.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="photoHighlight.js"></script>
<script type="text/javascript" src="confirmDeleteModifyInsert.js"></script>


<jsp:include page="/WEB-INF/header.jsp" />

	<div class="popupContainer">
	  <span class="popupText" id="popup">Videogame Successfully deleted</span>
	</div>
	
	<div class="popupContainer">
	  <span class="popupText" id="popupModify">Videogame Successfully updated</span>
	</div>
	
	<div class="popupContainer">
	  <span class="popupText" id="popupInsert">Videogame Successfully inserted</span>
	</div>

	<h1>Storage Admin</h1>
	
	
	<%
	  		//scorre l'array videogiochi e stampa gli elementi nella tabella
	  		Iterator<?> it = videogiochi.iterator();
				while (it.hasNext()) {
					VideogiocoBean videogioco = (VideogiocoBean) it.next();
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
	<%
				}
	%>
	
	<script>highlight();</script>
	
			    
			 
		<div class="flex-container">
	<form id="insertForm" class="inputFieldContainer inputList" action="StorageControl" enctype="multipart/form-data" method="post">
		<fieldset>
			<legend>Insert</legend>
			<input type="hidden" name="action" value="insert"> 
			
			Name: 			<input name="nome" 					type="text" 	maxlength="20" required placeholder="enter name"><br> 
			Edition: 		<input name="edizione" 				type="text" 	maxlength="20" required placeholder="enter name"><br> 
			Description: 	<input name="descrizione" 			type="text" 	maxlength="60" required placeholder="enter name"><br> 
			Price: 			<input name="prezzo_vetrina" 		type="text" 	maxlength="20" required placeholder="enter name"><br>
			<p class="ErrorParagraph"></p>
			
			Date: 			<input name="data" 			type="date" 	maxlength="20" required placeholder="DD/MM/YYYY or DD-MM-YYYY" value=2020-08-20><br>
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
					
			<input type="submit" name="submit" value="Submit" />
			<input type="reset" value="Reset">
		</fieldset>
	</form>
	<script>insertOnSubmit("#insertForm", checkInsert);</script>
	
	
	

	<form id="deleteForm" class="inputFieldContainer inputList" action="StorageControl" method="post">
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
	






		<form id="modifyNomeId" class="inputFieldContainer inputList" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Name</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Name: 			<input name="nome" 				type="text" 	maxlength="20"	required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script> modifyOnSubmit("#modifyNomeId");</script>
		
		
		
		<form id="modifyEditionId" class="inputFieldContainer inputList" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Edition</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Edition: 		<input name="edizione" 			type="text" 	maxlength="20"	required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script> modifyOnSubmit("#modifyEditionId");</script>
		
		
		
		<form id="modifyDescriptionId" class="inputFieldContainer inputList" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Description</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Description: 	<input name="descrizione" 		type="text" 	maxlength="60"	required	placeholder="enter description"	><br> 
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script> modifyOnSubmit("#modifyDescriptionId");</script>
		
		
		
		<form id="modifyPriceId" class="inputFieldContainer inputList" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Price</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="text" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Price: 			<input name="prezzo_vetrina"	type="text" 	maxlength="20"	required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modify">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script> modifyOnSubmit("#modifyPriceId", checkPrice, 1);</script>
		
		
		
		<form id="modifyDateId" class="inputFieldContainer inputList" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Date</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Date: 			<input name="data" 				type="date" 	maxlength="20"	required	placeholder="DD/MM/YYYY or DD-MM-YYYY"><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modify">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script> modifyOnSubmit("#modifyDateId", checkDate, 1);</script>
		
		
		
		<form id="modifyPlatformId" class="inputFieldContainer inputList" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Platform</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Platform: 		<input name="piattaforma" 		type="text" 	maxlength="20"	required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script> modifyOnSubmit("#modifyPlatformId");</script>
		
		
		
		<form id="modifyConsoleId" class="inputFieldContainer inputList" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Console</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Console: 		<input name="console" 				type="text" 	maxlength="20" required placeholder="enter name"><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script> modifyOnSubmit("#modifyConsoleId");</script>
		
		
		
		<form id="modifySaleId" class="inputFieldContainer inputList" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Sale</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Sale: 			<input name="sconto" 				type="text" 	maxlength="20" required placeholder="enter name"><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script> modifyOnSubmit("#modifySaleId", checkSale, 1);</script>
		
		
		
		<form id="modifyCopyId" class="inputFieldContainer inputList" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Copy</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Copy:			<input name="#copie" 			type="text" 	maxlength="20"	required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script> modifyOnSubmit("#modifyCopyId", checkCopy, 1);</script>
		
		
		
		<form id="modifyDeveloperId" class="inputFieldContainer inputList" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Developer</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Developer: 		<input name="Sviluppatore" 		type="text" 	maxlength="20" 		required	placeholder="enter name"		><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script> modifyOnSubmit("#modifyDeveloperId");</script>
		
		
		
		<form id="modifyPublisherId" class="inputFieldContainer inputList" action="StorageControl" method="post">
				<fieldset>
					<legend>modify Publisher</legend>
					<input type="hidden" name="action" value="modify"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Publisher: 		<input name="Pubblisher" 			type="text" 	maxlength="20" required placeholder="enter name"><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script> modifyOnSubmit("#modifyPublisherId");</script>
		
		
		
		<form id="modifyPhotoId" class="inputFieldContainer inputList" action="StorageControl" enctype="multipart/form-data" method="post">
				<fieldset>
					<legend>modify Photo</legend>
					<input type="hidden" name="action" value="modifyPhoto"> 
					
					ID: 			<input name="codice_prodotto" 	type="number" 	maxlength="20" 	required	placeholder="enter ID"			><br>
					<p class="ErrorParagraph"></p>
					
					Photo: 			<input name="foto"	id="file"			type="file"    	maxlength="255"		class="file"	required><br>
					<p class="ErrorParagraph"></p>
									
					<button type="submit" value="modifica">modify</button>
					<input type="reset" value="Reset"/>
				</fieldset>
		</form>
		<script>modifyOnSubmit("#modifyPhotoId");</script>
		</div>

<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>