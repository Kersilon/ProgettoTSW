package planetGaming.Utente;

import java.io.IOException;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministratorPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrdineDAO ordineDao = new OrdineDAO();
		Collection<OrdineBean> ordiniUtenti = new LinkedList<OrdineBean>();
		String action = request.getParameter("action");
		
		if(action != null) {
			
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
		}
		
		else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AdministratorPage.jsp");
			dispatcher.forward(request, response);
		}
	}
}
