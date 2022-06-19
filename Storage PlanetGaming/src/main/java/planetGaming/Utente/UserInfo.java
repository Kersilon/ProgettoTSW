package planetGaming.Utente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import planetGaming.Indirizzo.IndirizzoBean;
import planetGaming.Indirizzo.IndirizzoDAO;
import planetGaming.MetodoPagamento.MetodoPagamentoBean;
import planetGaming.MetodoPagamento.MetodoPagamentoDAO;

/**
 * Servlet implementation class UserInfo
 */
@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action;
		MetodoPagamentoBean metodoPagamentoBean;
		MetodoPagamentoDAO 	metodoPagamentoDao;
		
		IndirizzoBean 	indirizzoBean;
		IndirizzoDAO	indirizzoDao;
		
		Collection<MetodoPagamentoBean> metodiPagamento, buffer;
		Iterator it;
		
		
		metodoPagamentoDao = new MetodoPagamentoDAO();
		indirizzoDao = new IndirizzoDAO();
		
		action = request.getParameter("action");
		if(action != null) {
			
			if(action.equals("addPaymentMethod")) {
				metodoPagamentoBean = new MetodoPagamentoBean();
				
				metodoPagamentoBean.setNumero_carta(			request.getParameter("cardNumber"));
				metodoPagamentoBean.setCcv(						request.getParameter("ccv"));
				metodoPagamentoBean.setCircuito(				request.getParameter("circuit"));
				metodoPagamentoBean.setScadenza(				request.getParameter("expirationDate"));
				metodoPagamentoBean.setCodiceUtente((Integer) 	request.getSession().getAttribute("userId"));
				metodoPagamentoBean.setNome_intestatario(		request.getParameter("name"));
				metodoPagamentoBean.setCognome_intestatario(	request.getParameter("surname"));
				
				try {
					metodoPagamentoDao.doSave(metodoPagamentoBean);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/paginaProtetta.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("addAddress")) {
				indirizzoBean = new IndirizzoBean();
				
				indirizzoBean.setVia(						request.getParameter("address"));
				indirizzoBean.setCap(						request.getParameter("cap"));
				indirizzoBean.setCitta(						request.getParameter("city"));
				indirizzoBean.setCodice_utente((Integer) 	request.getSession().getAttribute("userId"));
				indirizzoBean.setProvincia(  				request.getParameter("province")); 
				
				try {
					indirizzoDao.doSave(indirizzoBean);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/paginaProtetta.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("showPaymentMethods")) {

			}
			
			if(action.equals("showAddresses")) {
				
			}
			
			//prende e inserisce nella request tutti i metodi di pagamento dell'utente
			//TODO sostituire doRetriveAll con doRetriveByKey
			request.removeAttribute("metodiPagamento");
			
			buffer = new LinkedList<MetodoPagamentoBean>();
			metodiPagamento = new LinkedList<MetodoPagamentoBean>();
			
			try {
				buffer = metodoPagamentoDao.doRetrieveAll("ASC");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//presi tutti metodi di pagamento scorriamo la lista temporanea, di metodi di pagamento e per ognuno di essi
			//se questo ha il codice utente uguale a quello dell'utente attualmente loggato
			//questo viene inserito nella lista di metodi che sarà poi effettivamente salvata nella sessione
			for(MetodoPagamentoBean mp : buffer) {
				if(mp.getCodiceUtente() == (Integer) request.getSession().getAttribute("userId")) {
					metodiPagamento.add(mp);
				}
			}
			
			request.setAttribute("metodiPagamento", metodiPagamento);
	
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/paginaProtetta.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
