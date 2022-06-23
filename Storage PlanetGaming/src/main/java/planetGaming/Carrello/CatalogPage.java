package planetGaming.Carrello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CatalogPage")
public abstract class CatalogPage extends HttpServlet{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CatalogItem[] items;
	  private String[] itemIDs;
	  private String title;

	
	  
	  protected void setItems(String[] itemIDs) {
	    this.itemIDs = itemIDs;
	    items = new CatalogItem[itemIDs.length];
	    for(int i=0; i<items.length; i++) {
	      items[i] = Catalog.getItem(itemIDs[i]);
	    }
	  }

	  
	  protected void setTitle(String title) {
	    this.title = title;
	  }

	  
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
	    if (items == null) {
	      response.sendError(HttpServletResponse.SC_NOT_FOUND,
	                         "Non ci sono elementi.");
	      return;
	    }
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String docType =
	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	      "Transitional//EN\">\n";
	    out.println(docType +
	                "<HTML>\n" +
	                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
	                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                "<H1 ALIGN=\"CENTER\">" + title + "</H1>");  //da controllare e modificare 
	    CatalogItem item;
	    for(int i=0; i<items.length; i++) {
	      out.println("<HR>");
	      item = items[i];
	      // Restituisce errore se la sottoclasse lista un ItemID 
	      // che non è presente nel catalogo.
	      if (item == null) {
	        out.println("<FONT COLOR=\"RED\">" +
	                    "ID dell'item sconosciuto." + itemIDs[i] +
	                    "</FONT>"); //modificare colore
	      } else {
	        out.println();
	        String formURL =
	          "OrderPage";
	        // Passa l'URL che fa riferimento all'encode.
	        formURL = response.encodeURL(formURL);
	        out.println
	          ("<FORM ACTION=\"" + formURL + "\">\n" +
	           "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\" " +
	           "       VALUE=\"" + item.getItemID() + "\">\n" +
	           "<H2>" + item.getShortDescription() +
	           " ($" + item.getCost() + ")</H2>\n" +
	           item.getLongDescription() + "\n" +
	           "<P>\n<CENTER>\n" +
	           "<INPUT TYPE=\"SUBMIT\" " +
	           "VALUE=\"Aggiungi al Carrello\">\n" +
	           "</CENTER>\n<P>\n</FORM>"); //da modificare
	      }
	    }
	    out.println("<HR>\n</BODY></HTML>");
	  }

}
