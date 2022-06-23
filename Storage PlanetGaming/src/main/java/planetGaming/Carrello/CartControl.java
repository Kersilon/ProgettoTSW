package planetGaming.Carrello;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/CartControl")

public class CartControl extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;

    public CartControl() {
    	super();
    }
    
    @SuppressWarnings("null")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	ShoppingCart cart = (ShoppingCart)req.getSession().getAttribute("shoppingCart");
		if(cart == null) {
			cart = new ShoppingCart();
			req.getSession().setAttribute("shoppingCart", cart);
		}
    	String action = req.getParameter("action");
    	CatalogItem item = (CatalogItem) req.getAttribute("cartItem");
    			CartItem cartItem = new CartItem(item);
    	try {
    		
    	
    	if(action != null) {
    		
    		if(action.equalsIgnoreCase("insert")) {
                
    			
    			cart.addItem(cartItem.getItemID());
    						
    	
    		}
    		
    	}
    	
    	else if(action.equalsIgnoreCase("delete")){
    		
    		cart.setNumOrdered(cartItem.getItemID(), 0);
    	
    	    }
    	
    	
    	
    	else if(action.equalsIgnoreCase("modify")) {
    		
    		cart.setNumOrdered(cartItem.getItemID(), Integer.parseInt(req.getParameter("numItem")));
    	    }
    	
    	}
    
    	
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    }

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
