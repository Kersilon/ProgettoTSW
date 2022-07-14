<%@ page import = "java.util.*" import = "planetGaming.Indirizzo.*" import = "planetGaming.MetodoPagamento.*"%>

<%
	//Check user credentials
	Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
	
	if((isLogged == null) || isLogged.equals(false))
	{
		response.sendRedirect("./login-form.jsp");
	}
	
	UtenteBean utente = request.getSession().getAttribute("utente");
	int codiceUtente = utente.getCodiceUtente();
	//Collection<?> indirizzi = richiamare oggetto DAO.doRetrievebyUser(codiceUtente);
	//Ricavare lista indirizzi e lista pagamenti
 %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestione indirizzo e pagamento</title>
</head>
<body>
<script type="text/javascript" src="ControllaCredenziali.js"></script>
<jsp:include page="/WEB-INF/header.jsp" />
<h1>Gestione indirizzo e pagamento</h1>


<!-- <form>
Seleziona l'indirizzo:

 -->


</form>

</body>
</html>