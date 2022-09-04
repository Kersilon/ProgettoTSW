package planetGaming;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import planetGaming.Videogioco.*;

@WebServlet("/StorageControl")
@MultipartConfig
public class StorageControl extends HttpServlet {
	//necessario per le Servlet
	private static final long serialVersionUID = 1L;
	
	private static final String SAVE_DIR = "immagini Videogiochi";
	
	//static VideogiocoModel VgDao = new VideogiocoDAO();

	
	
	//costruttore
	public StorageControl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VideogiocoModel videogioco = new VideogiocoDAO();
		
		Boolean isAdmin;
		String action = req.getParameter("action");
		String fileName;
		FileSupport fs = new FileSupport();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		boolean ajaxFlag = false;
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/storageUtente.jsp");
		
		
		if(action != null)
		{
			if(action.equalsIgnoreCase("showVideogame")) {
				try {
					req.getSession().removeAttribute("videogiochi");
					req.getSession().setAttribute("videogiochi", videogioco.doRetrieveAllByTitle(req.getParameter("videogameTitle")));

				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				
				
			}else if(action.equalsIgnoreCase("insert"))
			{
				//prendo i valori passati dal form di inserimento e li inserisco in un bean il quale poi viene memorizzato nel DB
				VideogiocoBean videogiocoBean = new VideogiocoBean();
				
				//TODO deve settarsi automaticamente nel DB
				videogiocoBean.setCodice_prodotto(10);
				
				videogiocoBean.setNome(											req.getParameter("nome"));
				videogiocoBean.setEdizione(										req.getParameter("edizione"));
				videogiocoBean.setDescrizione(									req.getParameter("descrizione"));
				videogiocoBean.setPrezzo_vetrina(	Double.parseDouble(			req.getParameter("prezzo_vetrina")));
				
				try {
					videogiocoBean.setData_uscita(									new java.sql.Date(formatter.parse(req.getParameter("data")).getTime()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				videogiocoBean.setPiattaforma(									req.getParameter("piattaforma"));
				videogiocoBean.setConsole(										req.getParameter("console"));
				videogiocoBean.setSconto(			Integer.parseInt(			req.getParameter("sconto")));
				videogiocoBean.setCopie(			Integer.parseInt(			req.getParameter("#copie")));
				videogiocoBean.setSviluppatore(									req.getParameter("Sviluppatore"));
				videogiocoBean.setPubblisher(									req.getParameter("Pubblisher"));
				
				//memorizzio l'immagine in una cartella e memorizzo il percorso nel bean
				fileName = fs.fileAssembler(req.getParts(), req.getServletContext().getRealPath(""), SAVE_DIR);
				videogiocoBean.setFoto(fileName);
				
				
				
				try {
					videogioco.doSave(videogiocoBean);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String json = null;
				json = ObjectToJson(videogiocoBean);
					
				resp.setContentType("text/plain");  						// Set content type of the response so that jQuery knows what it can expect.
				resp.setCharacterEncoding("UTF-8"); 						// You want world domination, huh?	
				resp.getWriter().write(json);								// Write response body.
				ajaxFlag = true;
			
				
				
			}else if(action.equalsIgnoreCase("delete"))
			{
				try {
					videogioco.doDelete(Integer.parseInt(req.getParameter("codice_prodotto")));
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				resp.setContentType("text/plain");  						// Set content type of the response so that jQuery knows what it can expect.
				resp.setCharacterEncoding("UTF-8"); 						// You want world domination, huh?
				resp.getWriter().write("successful deletion");      		// Write response body.
				ajaxFlag = true;
			
				
				
			}else if(action.equalsIgnoreCase("modify"))
			{
				
				VideogiocoBean videogiocoBean = new VideogiocoBean();
				
				try {
					videogiocoBean = videogioco.doRetrieveByKey(Integer.parseInt(req.getParameter("codice_prodotto")));
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				if(req.getParameter("nome") != null && !req.getParameter("nome").equals(""))
				{
					videogiocoBean.setNome(req.getParameter("nome"));
				}
				
				
				if(req.getParameter("edizione") != null && !req.getParameter("edizione").equals(""))
				{
					videogiocoBean.setEdizione(req.getParameter("edizione"));
				}
				
				
				if(req.getParameter("descrizione") != null && !req.getParameter("descrizione").equals(""))
				{
					videogiocoBean.setDescrizione(req.getParameter("descrizione"));
				}

				
				if(req.getParameter("prezzo_vetrina") != null && !req.getParameter("prezzo_vetrina").equals(""))
				{
					videogiocoBean.setPrezzo_vetrina(Double.parseDouble(req.getParameter("prezzo_vetrina")));
				}
				
				
				if(req.getParameter("data") != null && !req.getParameter("data").equals(""))
				{
					try {
						videogiocoBean.setData_uscita(new java.sql.Date(formatter.parse(req.getParameter("data")).getTime()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				if(req.getParameter("piattaforma") != null && !req.getParameter("piattaforma").equals(""))
				{
					videogiocoBean.setPiattaforma(req.getParameter("piattaforma"));
				}
				
				
				if(req.getParameter("console") != null && !req.getParameter("console").equals(""))
				{
					videogiocoBean.setConsole(req.getParameter("console"));
				}
				
				
				if(req.getParameter("sconto") != null && !req.getParameter("sconto").equals(""))
				{
					videogiocoBean.setSconto(Integer.parseInt(req.getParameter("sconto")));
				}
				
				
				if(req.getParameter("#copie") != null && !req.getParameter("#copie").equals(""))
				{
					videogiocoBean.setCopie(Integer.parseInt(req.getParameter("#copie")));
				}
				
				
				if(req.getParameter("Sviluppatore") != null && !req.getParameter("Sviluppatore").equals(""))
				{
					videogiocoBean.setSviluppatore(req.getParameter("Sviluppatore"));
				}
				
				
				if(req.getParameter("Pubblisher") != null && !req.getParameter("Pubblisher").equals(""))
				{
					videogiocoBean.setPubblisher(req.getParameter("Pubblisher"));
				}			
							
				try {
					videogioco.doUpdate(videogiocoBean);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				//Conversione di un videogiocoBean in Json
				String json = null;
				json = ObjectToJson(videogiocoBean);
					
				resp.setContentType("text/plain");  						// Set content type of the response so that jQuery knows what it can expect.
				resp.setCharacterEncoding("UTF-8"); 						// You want world domination, huh?
				resp.getWriter().write(json);								// Write response body.
				ajaxFlag = true;
				
				
				
				
			}else if(action.equalsIgnoreCase("modifyPhoto")) {
				VideogiocoBean videogiocoBean = new VideogiocoBean();
				
				try {
					videogiocoBean = videogioco.doRetrieveByKey(Integer.parseInt(req.getParameter("codice_prodotto")));
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(req.getParts() != null && !req.getPart("foto").getSubmittedFileName().isBlank())
				{
					//memorizzio l'immagine in una cartella e memorizzo il percorso nel bean
					System.out.println(req.getServletContext().getRealPath(""));
					fileName = fs.fileAssembler(req.getParts(), req.getServletContext().getRealPath(""), SAVE_DIR);
					videogiocoBean.setFoto(fileName);
				}
				
				
				try {
					videogioco.doUpdate(videogiocoBean);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				String json = null;
				json = ObjectToJson(videogiocoBean);
					
				resp.setContentType("text/plain");  						// Set content type of the response so that jQuery knows what it can expect.
				resp.setCharacterEncoding("UTF-8"); 						// You want world domination, huh?	
				resp.getWriter().write(json);								// Write response body.
				ajaxFlag = true;

				
				
			}else if(action.equalsIgnoreCase("ExtendedDescription")) {
				
				VideogiocoBean videogiocoBean = new VideogiocoBean();
				
				try {
					videogiocoBean = videogioco.doRetrieveByKey(Integer.parseInt(req.getParameter("codice_prodotto")));
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				req.removeAttribute("paginaVideogioco");
				req.setAttribute("paginaVideogioco", videogiocoBean);
				
				
				dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/DescrizioneEstesa.jsp");
			}
		}else {
			try {
				req.getSession().removeAttribute("videogiochi");
				req.getSession().setAttribute("videogiochi", videogioco.doRetrieveAll(""));
				
			}catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
		}

		
		//se viene eseguito il dispatcher il contenuto della response viene sovrascitta con la pagina
		//con questo return impediamo che il dispatcher venga eseguito quando usiamo ajax
		if(ajaxFlag) { 
			return;
		}
		
		//resp.sendRedirect(req.getContextPath()+"/storageAdmin.jsp");
		
		//se l'utente è un Admin viene mandato allo storage.jsp che permette di fare le operazioni da admin
		//altrimenti se non è un admin viene mandato allo storage.jsp per utente semplice
		isAdmin = (Boolean) req.getSession().getAttribute("isAdmin");
		if((isAdmin != null) && isAdmin.booleanValue())
		{
			//System.out.println(req.getServletContext().getRealPath(""));
			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/storageAdmin.jsp");
		}
		
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	//Converte un videogiocoBean in Json 
	public String ObjectToJson(VideogiocoBean Object){	
		String json = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
		  json = mapper.writeValueAsString(Object);
		  //for debbugging 
		  System.out.println("ResultingJSONstring = " + json);
		   
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}
		
		return json;
	}
}
