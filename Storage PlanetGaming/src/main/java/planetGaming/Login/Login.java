package planetGaming.Login;

import java.io.IOException;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String action = request.getParameter("action");
		
		if(action != null)
		{
			if(action.equalsIgnoreCase("registration"))
			{
				//se le credenziali risultano presenti nel DB allora significa che l'utente è già registrato e quindi viene rimandato alla pagina di login
				if(checkLogin(email, password))
				{
					response.sendRedirect(request.getContextPath()+"/login-form.jsp");
				//altrimenti si prendono i dati inviati con il form di registrazione e li si salva nel DB, effettuando quindi la registrazione
				}else {
					
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					response.sendRedirect(request.getContextPath()+"/login-form.jsp");
				}
			}
			if(action.equalsIgnoreCase("login"))
			{
				if(checkLogin(email, password))
				{
					request.getSession().setAttribute("isLogged", true);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/paginaProtetta.jsp");
					dispatcher.forward(request, response);
					
					//TODO capire perché... response.sendRedirect non va bene in questo contesto non so perché ma facendo così non funziona
					//response.sendRedirect(request.getContextPath()+"/WEB-INF/paginaProtetta.jsp");
				}else {
					response.sendRedirect(request.getContextPath()+"/login-form.jsp");
				}
			}
			if(action.equalsIgnoreCase("storage"))
			{
				if(checkLogin(email, password))
				{
					request.getSession().setAttribute("isLogged", true);
					
					if(utenteBean.isAMMINISTRATORE())
					{
						request.getSession().setAttribute("isAdmin", true);
					}
					
					//TODO in teoria l'utente dovrebbe poter accedere allo storage per utenti anche se non è loggato
					//sarebbe come visualizzare lo store di amazon, non devi essere per forza loggato per vederlo
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/StorageControl");
					dispatcher.forward(request, response);
				}else {
					response.sendRedirect(request.getContextPath()+"/login-form.jsp");
				}
			}
		}else {
			//se action è nullo significa che stiamo accedendo alla web app partendo da questa servlet e per tanto si dovrebbe essere rindirizzati
			//alla homepage
			response.sendRedirect(request.getContextPath()+"/login-form.jsp");
		}
/*		
		if(checkLogin(email, password))
		{
			request.getSession().setAttribute("isLogged", true);
			
			//se non è un admin va comunque allo storage ma non può effetuare operazioni sui prodotti ma solo visualizzarli
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
				//già sappiamo che non c'è un email e password corrrispondenti a quelle con cui vogliamo registrarci
				//ciò lo capiamo dal fatto che se siamo quì il checklogin è fallito
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//se vuoi usare invalidLogin.jsp quando si fallisce il login deve avvenire quì la redirezione
		//distinguendo la redirezione che avviene quando si effettua la registrazione
		//e quella che avviene quando si inseriscono credenziali di login errate
		response.sendRedirect(request.getContextPath()+"/login-form.jsp");
	}
*/
	}
}