package planetGaming;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import planetGaming.Ordine.OrdineBean;
import planetGaming.Ordine.OrdineDAO;
import planetGaming.Ordine.prodottoOrdineBean;
import planetGaming.Ordine.prodottoOrdineDAO;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		OrdineDAO daoTest = new OrdineDAO();
		Date min = Date.valueOf("2021-01-01");
		Date max = Date.valueOf("2022-01-01");
		
		try {
			for(OrdineBean bean : daoTest.doRetrieveAll(min, max)) {
				System.out.println(bean.getIdOrdine());
				System.out.println(bean.getDataOrdine());
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		*/
		 String text = "some text";

		 response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		 response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
		 response.getWriter().write(text);       // Write response body.
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
