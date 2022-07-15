<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*" import="planetGaming.Videogioco.*"%>
    
<%
		VideogiocoBean videogioco = (VideogiocoBean) request.getAttribute("paginaVideogioco");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/header.jsp" />
	
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
	
	</table>
	
	<form action="StorageControl" method="post">
		<input type="submit" value="Previous page"/>
	</form>
	
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>