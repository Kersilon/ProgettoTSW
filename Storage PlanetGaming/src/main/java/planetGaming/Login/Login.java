package planetGaming.Login;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import planetGaming.Utente.*;
import planetGaming.StorageControl;	//questo import serve per far funzionare il dispatcher altrimenti non vede la servlet StorageControl

@WebServlet("/LoginStorage")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static UtenteModel utenteDao = new UtenteDAO();
	UtenteBean utenteBean;
	
	
	
	public Login() {
		super();
	}
	
	
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=	request.getParameter("email");
		String password=request.getParameter("password");
		String action = request.getParameter("action");
		
		System.out.println("entro nella servlet");
		
		
		
		if(action != null)
		{
			System.out.println("action ha un valore");
			
			if(action.equalsIgnoreCase("registration"))
			{
				//se le credenziali risultano presenti nel DB allora significa che l'utente � gi� registrato e quindi viene informato di ci�
				if(checkLogin(email, password))
				{
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("Email already used");
				//altrimenti si prendono i dati inviati con il form di registrazione e li si salva nel DB, effettuando quindi la registrazione
				}else {
					
					utenteBean.setNome(			request.getParameter("nome"));
					utenteBean.setCognome(		request.getParameter("cognome"));
					utenteBean.setDataNascita(Date.valueOf(request.getParameter("data")));
					utenteBean.setCodiceFiscale(		request.getParameter("codiceFiscale"));
					utenteBean.setNomeUtente(	request.getParameter("nomeUtente"));
					utenteBean.setPassword(		request.getParameter("password"));
					utenteBean.setEmail(		request.getParameter("email"));
					utenteBean.setTelefono(		request.getParameter("telefono"));
					
					try {
						utenteDao.doSave(utenteBean);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("./login-form.jsp");
				}
				
				
				
			}
			if(action.equalsIgnoreCase("login"))
			{
				if(checkLogin(email, password))
				{
					request.getSession().setAttribute("isLogged", true);
					request.getSession().setAttribute("userId", utenteBean.getCodiceUtente());
					
					if(utenteBean.isAMMINISTRATORE())
					{
						request.getSession().setAttribute("isAdmin", true);
					}
					
					//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserInfo");
					//dispatcher.forward(request, response);
					response.setContentType("text/plain");  						// Set content type of the response so that jQuery knows what it can expect.
					response.setCharacterEncoding("UTF-8"); 						// You want world domination, huh?
					response.getWriter().write("./UserInfo");      					// Write response body
					
					//TODO capire perch�... response.sendRedirect non va bene in questo contesto non so perch� ma facendo cos� non funziona
					//response.sendRedirect(request.getContextPath()+"/WEB-INF/paginaProtetta.jsp");
				}else {
					System.out.println("arrivo qu�");
					//response.sendRedirect(request.getContextPath()+"/login-form.jsp");
					response.setContentType("text/plain");  						// Set content type of the response so that jQuery knows what it can expect.
					response.setCharacterEncoding("UTF-8"); 						// You want world domination, huh?
					response.getWriter().write("Wrong Email or Password");      	// Write response body.
				}
			}
			/*
			if(action.equalsIgnoreCase("storage"))
			{
				if(checkLogin(email, password))
				{
					try {
						utenteBean = utenteDao.doRetrieveByKey(email, password);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					request.getSession().setAttribute("isLogged", true);
					request.getSession().setAttribute("userId", utenteBean.getCodiceUtente());
					
					if(utenteBean.isAMMINISTRATORE())
					{
						request.getSession().setAttribute("isAdmin", true);
					}
					
					//TODO in teoria l'utente dovrebbe poter accedere allo storage per utenti anche se non � loggato
					//sarebbe come visualizzare lo store di amazon, non devi essere per forza loggato per vederlo
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/StorageControl");
					dispatcher.forward(request, response);
				}else {
					response.sendRedirect(request.getContextPath()+"/login-form.jsp");
				}
			}
			*/
			
			
			
		}else {
			//se action � nullo significa che stiamo accedendo alla web app partendo da questa servlet e per tanto si dovrebbe essere rindirizzati
			//alla homepage
//			response.sendRedirect(request.getContextPath()+"/login-form.jsp");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-form.jsp");
			dispatcher.forward(request, response);
		}
/*		
		if(checkLogin(email, password))
		{
			request.getSession().setAttribute("isLogged", true);
			
			//se non � un admin va comunque allo storage ma non pu� effetuare operazioni sui prodotti ma solo visualizzarli
			if(utenteBean.isAMMINISTRATORE())
			{
				request.getSession().setAttribute("isAdmin", true);
			}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/StorageControl");
			dispatcher.forward(request, response);
					
			//usando questo al posto del dispatcher non funziona "response.sendRedirect(request.getContextPath()+"/WEB-INF/protected.jsp")";
			//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/protected.jsp");
			//dispatcher.forward(request, response);
				
		}else{
			//se il login non va a buon fine allora o vuoi registrarti oppure hai sbagliato le credenziali
			if(action != null && action.equals("Registration"))
			{
				//gi� sappiamo che non c'� un email e password corrrispondenti a quelle con cui vogliamo registrarci
				//ci� lo capiamo dal fatto che se siamo qu� il checklogin � fallito
				//quindi possiamo procedere all'inserimento dei dati nel database
				utenteBean.setNome(request.getParameter("nome"));
				utenteBean.setCognome(request.getParameter("cognome"));
				utenteBean.setDataNascita(request.getParameter("dataNascita"));
				utenteBean.setNomeUtente(request.getParameter("nomeUtente"));
				utenteBean.setPassword(request.getParameter("password"));
				utenteBean.setEmail(request.getParameter("email"));
				utenteBean.setTelefono(request.getParameter("telefono"));
				
				try {
					utenteDao.doSave(utenteBean);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		//se vuoi usare invalidLogin.jsp quando si fallisce il login deve avvenire qu� la redirezione
		//distinguendo la redirezione che avviene quando si effettua la registrazione
		//e quella che avviene quando si inseriscono credenziali di login errate
		response.sendRedirect(request.getContextPath()+"/login-form.jsp");
	}
*/
	}
	
	
	
	//controlla le credenziali inserite nel form di login con quelle presenti nel DB
	private boolean checkLogin(String email, String password){
		//ciao
		try {
			utenteBean = utenteDao.doRetrieveByKey(email, password);
			if(utenteBean.getEmail() != null && utenteBean.getPassword() != null && utenteBean.getEmail().equals(email) && utenteBean.getPassword().equals(password) && !utenteBean.getEmail().equals(""))
				return true;
		
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		return false;
		
	}
}