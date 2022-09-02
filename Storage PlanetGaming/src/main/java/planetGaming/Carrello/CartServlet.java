package planetGaming.Carrello;

import java.io.IOException;
import java.sql.Date;
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

import planetGaming.Ordine.OrdineBean;
import planetGaming.Ordine.OrdineDAO;
import planetGaming.Ordine.prodottoOrdineBean;
import planetGaming.Utente.UtenteBean;
import planetGaming.Videogioco.VideogiocoBean;
import planetGaming.Videogioco.VideogiocoDAO;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action != null)
		{
			LinkedList<prodottoOrdineBean> cart = (LinkedList<prodottoOrdineBean>) request.getSession().getAttribute("cart");
			if(cart == null) {
				cart = new LinkedList<prodottoOrdineBean>();
				request.getSession().setAttribute("cart", cart);
			}
			
			
			
			if(action.equalsIgnoreCase("addToCart")){
				VideogiocoDAO videogiocoDao = new VideogiocoDAO();
				VideogiocoBean videogioco = null;
				prodottoOrdineBean prodotto = null;
				boolean added = false;
				
				try {
					videogioco = videogiocoDao.doRetrieveByKey(Integer.parseInt(request.getParameter("insertIntoCart")));
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				prodotto = new prodottoOrdineBean(videogioco);
				for(prodottoOrdineBean po: cart) {
					if(po.getIdVideogioco() == prodotto.getIdVideogioco()) {
						po.addProdotto();
						added = true;
					}
				}
				
				if(!added) {
					cart.add(prodotto);
				}
			}
			
			
			
			else if(action.equalsIgnoreCase("showCart")){
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cartCheck.jsp");
				dispatcher.forward(request, response);
			}
			
			
			
			else if(action.equalsIgnoreCase("removeFromCart")) {
				int idProduct = Integer.parseInt(request.getParameter("deleteFromCart"));
				
				if(!cart.isEmpty()) {
					
					for(prodottoOrdineBean po: cart) {
						if(po.getIdVideogioco() == idProduct) {
							if(po.getQuantitaAcquisto() >= 2) {
								po.removeProdotto();
							}else {
								cart.remove(po);
							}
						}
					}
				}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cartCheck.jsp");
				dispatcher.forward(request, response);
			}
			
			
			else if(action.equalsIgnoreCase("Purchase")) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/checkOut.jsp");
				dispatcher.forward(request, response);
			}
			
			
			else if(action.equalsIgnoreCase("actualPurchase")) {
				if(request.getSession().getAttribute("userId") == null) {
					System.out.println("entrato");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-form.jsp");
					dispatcher.forward(request, response);
				}
				
				System.out.println("andato avanti");
				OrdineBean ordine;
				OrdineDAO ordineDao = new OrdineDAO();
				
				ordine = new OrdineBean(cart);
				ordine.setIdUtente((int) request.getSession().getAttribute("userId"));
				ordine.setIdModalitaPagamento(Integer.valueOf(request.getParameter("cardId")));
				ordine.setIdIndirizzo(Integer.valueOf(request.getParameter("AddressId")));
				ordine.setDataOrdine(new Date(System.currentTimeMillis()));
				ordine.setTracking("1");
				
				try {
					ordineDao.doSave(ordine);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
		if((isAdmin == null) || (!isAdmin.booleanValue())) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/storageUtente.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/storageAdmin.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	

}
