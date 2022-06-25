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
import planetGaming.Ordine.*;

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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action;
		int userId;
		
		MetodoPagamentoBean metodoPagamentoBean;
		MetodoPagamentoDAO 	metodoPagamentoDao;
		
		IndirizzoBean 	indirizzoBean;
		IndirizzoDAO	indirizzoDao;
		
		OrdineBean ordineBean;
		OrdineDAO ordineDao;
		
		
		Collection<MetodoPagamentoBean> metodiPagamento, bufferMetodiPagamento;
		
		Collection<IndirizzoBean> indirizzi, bufferIndirizzi;
		
		Collection<OrdineBean> ordini, bufferOrdini;
		
		Collection<prodottoOrdineBean> prodottiOrdine, bufferProdottiOrdine;
		
		
		
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
			//TODO inserire qualcosa tra gli apici di equals

			if(action.equals("")) {
				bufferOrdini = new LinkedList<OrdineBean>();
				ordini = new LinkedList<OrdineBean>();
				
				bufferProdottiOrdine = new LinkedList<prodottoOrdineBean>();
				prodottiOrdine = new LinkedList<prodottoOrdineBean>();
				
				ordineDao = new OrdineDAO();
				ordineBean = new OrdineBean();
				
				
				userId = (Integer) request.getSession().getAttribute("userId");
				
				
				try {
					//TODO finire di sistemare il dao ordine
					bufferOrdini = ordineDao.doRetrieveAll("ASC"); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				for(OrdineBean ord: bufferOrdini) {
					if(ord.getIdUtente() == userId) {
						ordineBean.notSetProdottiOrdine(ord);
						ordineBean.setProdottiOrdine(new LinkedList<prodottoOrdineBean>());
						
						bufferProdottiOrdine = ord.getProdottiOrdine();
						for(prodottoOrdineBean pOrd: bufferProdottiOrdine) {
							
							if(pOrd.getIdOrdine() == ord.getIdOrdine()){
								ordineBean.getProdottiOrdine().add(pOrd);
							}
						}
						
						ordini.add(ord);
					}
				}
				
				request.getSession().setAttribute("ordini", ordini);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/paginaOrdini.jsp");
				dispatcher.forward(request, response);
			}
			
			
			
			//TODO eseguire la visualizzazione di tutti i metodi di pagamento solo se si preme un pulsante
			//prende e inserisce nella request tutti i metodi di pagamento dell'utente
			//request.getSession().removeAttribute("metodiPagamento");
			
			bufferMetodiPagamento = new LinkedList<MetodoPagamentoBean>();
			metodiPagamento = new LinkedList<MetodoPagamentoBean>();
			
			try {
				bufferMetodiPagamento = metodoPagamentoDao.doRetrieveAll("ASC");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//presi tutti metodi di pagamento scorriamo la lista temporanea, di metodi di pagamento e per ognuno di essi
			//se questo ha il codice utente uguale a quello dell'utente attualmente loggato
			//questo viene inserito nella lista di metodi che sarà poi effettivamente salvata nella sessione
			for(MetodoPagamentoBean mp : bufferMetodiPagamento) {
				if(mp.getCodiceUtente() == (Integer) request.getSession().getAttribute("userId")) {
					metodiPagamento.add(mp);
				}
			}
			
			request.getSession().setAttribute("metodiPagamento", metodiPagamento);
			
			
			
			
			//TODO eseguire la visualizzazione di tutti gli indirizzi solo se si preme un pulsante
			//prende e inserisce nella request tutti gli indirizzi dell'utente
			//request.getSession().removeAttribute("indirizzi");
			
			bufferIndirizzi = new LinkedList<IndirizzoBean>();
			indirizzi = new LinkedList<IndirizzoBean>();
			
			try {
				bufferIndirizzi = indirizzoDao.doRetrieveAll("ASC");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//presi tutti gli indirizzi scorriamo la lista temporanea, di indirizzi e per ognuno di essi
			//se questo ha il codice utente uguale a quello dell'utente attualmente loggato
			//questo viene inserito nella lista di indirizzi che sarà poi effettivamente salvata nella sessione
			for(IndirizzoBean ind : bufferIndirizzi) {
				if(ind.getCodice_utente() == (Integer) request.getSession().getAttribute("userId")) {
					indirizzi.add(ind);
				}
			}
			
			request.getSession().setAttribute("indirizzi", indirizzi);
			
			
			
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/paginaProtetta.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
