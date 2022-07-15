package planetGaming.Carrello;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Catalog")

public class Catalog {
	
	  private ArrayList<CatalogItem> products;

	  
	  public Catalog() {
	    products = new ArrayList<CatalogItem>();
	  }

	  
	  public List<CatalogItem> getCatalog() {
	    return(products);
	  }

	  
	  public synchronized void addItem(VideogiocoBean videogioco) {
	    CatalogItem product;
	    for(int i=0; i<products.size(); i++) {
	      product = (CatalogItem)products.get(i);
	      if (product.getItemID() == videogioco.getCodice_prodotto()) {
	        return;
	      }
	    }
	    CatalogItem newProduct = new CatalogItem(videogioco);
	    products.add(newProduct);
	  }

	
	  
	 
	  
	  
		 
}
