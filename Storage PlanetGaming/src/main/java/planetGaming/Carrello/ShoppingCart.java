package planetGaming.Carrello;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

@WebServlet("/ShoppingCart")
public class ShoppingCart {
	 private ArrayList<CartItem> itemsOrdered;

	  
	  public ShoppingCart() {
	    itemsOrdered = new ArrayList<CartItem>();
	  }

	  
	  public List<CartItem> getItemsOrdered() {
	    return(itemsOrdered);
	  }

	  
	  public synchronized void addItem(String itemID) {
	    CartItem order;
	    for(int i=0; i<itemsOrdered.size(); i++) {
	      order = (CartItem)itemsOrdered.get(i);
	      if (order.getItemID().equals(itemID)) {
	        order.incrementNumItems();
	        return;
	      }
	    }
	    CartItem newOrder = new CartItem(Catalog.getItem(itemID));
	    itemsOrdered.add(newOrder);
	  }

	
	  
	  public synchronized void setNumOrdered(String itemID,
	                                         int numOrdered) {
	    CartItem order;
	    for(int i=0; i<itemsOrdered.size(); i++) {
	      order = (CartItem)itemsOrdered.get(i);
	      if (order.getItemID().equals(itemID)) {
	        if (numOrdered <= 0) {
	          itemsOrdered.remove(i);
	        } else {
	          order.setNumItems(numOrdered);
	        }
	        return;
	      }
	    }
	    CartItem newOrder =
	      new CartItem(Catalog.getItem(itemID));
	    itemsOrdered.add(newOrder);
	  }
	  
	  
}
