package planetGaming.Utente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		MetodoPagamentoDAO metodoPagamentoDao;
		
		metodoPagamentoDao = new MetodoPagamentoDAO();
		
		action = request.getParameter("action");
		if(action != null) {
			
			if(action.equals("addPaymentMethod")) {
				metodoPagamentoBean = new MetodoPagamentoBean();
				
				metodoPagamentoBean.setNumCarta(request.getParameter("cardNumber"));
				metodoPagamentoBean.setCcv(request.getParameter("ccv"));
				metodoPagamentoBean.setCircuito(request.getParameter("circuit"));
				metodoPagamentoBean.setScadenza(request.getParameter("expirationDate"));
				metodoPagamentoBean.setCodiceUtente((Integer) request.getSession().getAttribute("userId"));
				metodoPagamentoBean.setNomeIntestatario(request.getParameter("name"));
				metodoPagamentoBean.setCognomeIntestatario(request.getParameter("surname"));
				
				try {
					metodoPagamentoDao.doSave(metodoPagamentoBean);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(action.equals("addAddress")) {
				
			}
			
			if(action.equals("showPaymentMethods")) {
				
			}
			
			if(action.equals("showAddresses")) {
				
			}
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
