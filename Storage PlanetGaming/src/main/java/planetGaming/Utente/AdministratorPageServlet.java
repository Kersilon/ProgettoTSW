package planetGaming.Utente;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import planetGaming.Ordine.OrdineBean;
import planetGaming.Ordine.OrdineDAO;

/**
 * Servlet implementation class AdministratorPageServlet
 */
@WebServlet("/AdministratorPageServlet")
public class AdministratorPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdministratorPageServlet() {
        super();
    }

    
    
    
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdineDAO ordineDao = new OrdineDAO();
		Collection<OrdineBean> ordiniUtenti = new LinkedList<OrdineBean>();
		
		UtenteDAO utenteDao = new UtenteDAO();
		Collection<UtenteBean> utenti = new LinkedList<UtenteBean>();
		
		String action = request.getParameter("action");
		
		Integer ordersByUserId, userId;
		Date min;
		Date max;
		
		
		
		if(action != null) {
			
				if(action.equals("clean")) {
					request.getSession().removeAttribute("ordiniUtenti");
					request.getSession().removeAttribute("utenti");
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AdministratorPage.jsp");
					dispatcher.forward(request, response);
				}
			
				
				
				if(action.equals("ShowOrders")) {
					try {
						ordiniUtenti = ordineDao.doRetrieveAll("ASC");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					request.getSession().setAttribute("ordiniUtenti", ordiniUtenti);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AdministratorPage.jsp");
					dispatcher.forward(request, response);
				}
				
				
			
				else if(action.equals("ShowOrdersByUser")) {
					//System.out.println(request.getParameter("OrdersByUserId"));
					ordersByUserId = Integer.parseInt(request.getParameter("OrdersByUserId"));
					
					try {
						ordiniUtenti = ordineDao.doRetrieveAll(ordersByUserId);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					request.getSession().setAttribute("ordiniUtenti", ordiniUtenti);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AdministratorPage.jsp");
					dispatcher.forward(request, response);
				}
				
				
				
				else if(action.equals("ShowOrdersByDate")) {
					min = Date.valueOf(request.getParameter("DateMin"));
					max = Date.valueOf(request.getParameter("DateMax"));
					
					try {
						ordiniUtenti = ordineDao.doRetrieveAll(min, max);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					request.getSession().setAttribute("ordiniUtenti", ordiniUtenti);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AdministratorPage.jsp");
					dispatcher.forward(request, response);
				}
				
				
				
				else if(action.equals("ShowUsers")) {
					try {
						utenti = utenteDao.doRetrieveAll();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					request.getSession().setAttribute("utenti", utenti);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AdministratorPage.jsp");
					dispatcher.forward(request, response);
				}
				
				
				
				else if(action.equals("ShowUser")) {
					userId = Integer.parseInt(request.getParameter("UserId"));
					
					try {
						utenti = utenteDao.doRetrieveAll(userId);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					request.getSession().setAttribute("utenti", utenti);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AdministratorPage.jsp");
					dispatcher.forward(request, response);
				}
		}
		
		
		
		else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AdministratorPage.jsp");
			dispatcher.forward(request, response);
		}
	}
}
