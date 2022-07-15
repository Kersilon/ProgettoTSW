package planetGaming.Carrello;

import javax.servlet.annotation.WebServlet;

@WebServlet("/CartItem")
public class CartItem {

		  private CatalogItem item;
		  private int numItems;

		  public CartItem(CatalogItem item) {
		    setItem(item);
		    setNumItems(1);
		  }

		  public CatalogItem getItem() {
		    return(item);
		  }

		  protected void setItem(CatalogItem item) {
		    this.item = item;
		  }

		  public int getItemID() {
		    return(getItem().getItemID());
		  }

		  public String getShortDescription() {
		    return(getItem().getShortDescription());
		  }

		  public String getLongDescription() {
		    return(getItem().getLongDescription());
		  }

		  public double getUnitCost() {
		    return(getItem().getCost());
		  }
		  
		  public int getNumItems() {
		    return(numItems);
		  }

		  public void setNumItems(int n) {
		    this.numItems = n;
		  }

		  public void incrementNumItems() {
		    setNumItems(getNumItems() + 1);
		  }

		  public void cancelOrder() {
		    setNumItems(0);
		  }

		  public double getTotalCost() {
		    return(getNumItems() * getUnitCost());
		  }

}
