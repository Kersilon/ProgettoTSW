package planetGaming.Carrello;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/OrderPage")
public class OrderPage extends HttpServlet{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    ShoppingCart cart;
	    synchronized(session) {
	      cart = (ShoppingCart)session.getAttribute("shoppingCart");
	      // I nuovi visitatori avranno un carrello vuoto,
	      // altrimenti manterranno il loro carrello già esistente.
	      if (cart == null) {
	        cart = new ShoppingCart();
	        session.setAttribute("shoppingCart", cart);
	      }
	      String itemID = request.getParameter("itemID");
	      if (itemID != null) {
	        String numItemsString =
	          request.getParameter("numItems");
	        if (numItemsString == null) {
	          // Se la richiesta specifica un ID ma non una quantità,
	          // allora i clienti saranno giunti qui utilizzano un pulsante "Aggiungi al carrello"
	          // nella pagina del catalogo.
	          cart.addItem(itemID);
	        } else {
	          // Se la richiesta specifica un ID e una quantità, allora
	          // i clienti saranno giunti qui attraverso un pulsante "Aggiorna Ordine"
	          // dopo aver modificato il numero di item dell'ordine.
	          // Specificando come numItem il numero 0, vuol dire che
	          // item è stato eliminato dal carrello.
	          int numItems;
	          try {
	            numItems = Integer.parseInt(numItemsString);
	          } catch(NumberFormatException e) {
	            numItems = 1;
	          }
	          cart.setNumOrdered(itemID, numItems);
	        }
	      }
	    }
	    // Mostrare lo stato dell'ordine
	   
	    synchronized(session) {
	    	 response.setContentType("text/html");
	    	    PrintWriter out = response.getWriter();
	      List<CartItem> itemsOrdered = cart.getItemsOrdered();
	      if (itemsOrdered.size() == 0) {
	        out.println("<H2><I>Non sono presenti articoli nel carrello.</I></H2>"); //modificare
	      } else {
	        // Se è presente almeno un ordine nel carrello, visuaizzare la table
	        // degli items ordinati.
	        out.println
	          ("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
	           "<TR BGCOLOR=\"#FFAD00\">\n" +
	           "  <TH>Item ID<TH>Description\n" +
	           "  <TH>Unit Cost<TH>Number<TH>Total Cost"); //modificare
	        CartItem order;
	        // Arrotondare con due decimali, inserire 
	        // il simbolo dell'euro (o della valuta corrente nello Stato del cliente), 
	        // secondo il formato locale.
	        NumberFormat formatter =
	          NumberFormat.getCurrencyInstance();
	        // Per ogni inserimento nel carrello, far
	        // mostrare nella riga della table ID, descrizione, costo
	        // per item, quantità, e costo totale.
	        // Inserire la quantità in un textfield che l'user
	        // può modificare, con un pulsante "Aggiorna Ordine" vicino ad esso,
	        // che riconduce alla stessa pagina
	        // ma specificando un numero diverso di item.
	        for(int i=0; i<itemsOrdered.size(); i++) {
	          order = (CartItem)itemsOrdered.get(i);
	          out.println
	            ("<TR>\n" +
	             "  <TD>" + order.getItemID() + "\n" +
	             "  <TD>" + order.getShortDescription() + "\n" +
	             "  <TD>" +
	             formatter.format(order.getUnitCost()) + "\n" +
	             "  <TD>" +
	             "<FORM>\n" +  // Invia all'URL corrente
	             "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\"\n" +
	             "       VALUE=\"" + order.getItemID() + "\">\n" +
	             "<INPUT TYPE=\"TEXT\" NAME=\"numItems\"\n" +
	             "       SIZE=3 VALUE=\"" + 
	             order.getNumItems() + "\">\n" +
	             "<SMALL>\n" +
	             "<INPUT TYPE=\"SUBMIT\"\n "+
	             "       VALUE=\"Aggiorna Ordine\">\n" +
	             "</SMALL>\n" +
	             "</FORM>\n" +
	             "  <TD>" +
	             formatter.format(order.getTotalCost())); //da modificare visualizzazione a schermo
	        }
	        String checkoutURL =
	          response.encodeURL("Checkout.html");
	        // Pulsante "Procedi all'Acquisto" sotto la table
	        out.println
	          ("</TABLE>\n" +
	           "<FORM ACTION=\"" + checkoutURL + "\">\n" +
	           "<BIG><CENTER>\n" +
	           "<INPUT TYPE=\"SUBMIT\"\n" +
	           "       VALUE=\"Procedi al Checkout\">\n" +
	           "</CENTER></BIG></FORM>"); //da modificare visualizzazione a schermo
	      }
	      out.println("</BODY></HTML>");
	    }
	  }
}

