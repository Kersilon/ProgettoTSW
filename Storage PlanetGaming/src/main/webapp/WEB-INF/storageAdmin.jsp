<%@ page import="java.util.*" import="planetGaming.Videogioco.*"%>


<% 
	//Check user credentials
	//Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
	//if((adminRoles == null) || (!adminRoles.booleanValue()))
	//{
	//	response.sendRedirect("./login-form.jsp");
	//	return;
	//}
	
	//verifica se l'oggetto videogiochi è presente nella richiesta altrimenti passa il controllo alla servlet "StorageControl" che lo genera
	Collection<?> videogiochi = (Collection<?>) request.getAttribute("videogiochi");
	//if(videogiochi == null) {
	//	response.sendRedirect("StorageControl");	
	//	return;
	//}
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Storage Admin</title>

<link rel="stylesheet" href="trueStorageStyle.css">

</head>
<body>
<script type="text/javascript" src="ControllaCredenziali.js"></script>
<jsp:include page="header.jsp" />

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
			<tr>
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
			    <td><img src="${pageContext.request.contextPath}/immagini Videogiochi/<%=videogioco.getFoto()%>"></td>
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
			
			Date: 			<input name="data_uscita" 			type="text" 	maxlength="20" required placeholder="DD/MM/YYYY or DD-MM-YYYY"><br>
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
	<form action="StorageControl" method="post">
		<fieldset>
			<legend>Delete</legend>
			<input type="hidden" name="action" value="delete">
			
			Code: <input name="codice_prodotto" type="text" maxlength="20" required placeholder="enter code"><br>
			
			<input type="submit" value="Remove">
			<input type="reset" value="Reset">
		</fieldset>
	</form>
	
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
	
	<form action="Logout" method="get">
		<input type="submit" value="Logout"/>
	</form>

<jsp:include page="footer.jsp" />
</body>
</html>