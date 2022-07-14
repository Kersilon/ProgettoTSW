package planetGaming.Carrello;

import javax.servlet.annotation.WebServlet;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;


@WebServlet("/CatalogItem")

public class CatalogItem {
		  private int itemID;
		  private String shortDescription;
		  private String longDescription;
		  private double cost;
		  private int numItem;
		  private String photo;

		  public CatalogItem(VideogiocoBean videogioco) {
		    setItemID(videogioco.getCodiceProdotto());
		    setShortDescription(videogioco.getNome() + " - " + videogioco.getPiattaforma() + " di " + videogioco.getSviluppatore());
		    setLongDescription(videogioco.getDescrizione());
		    setCost(videogioco.getCosto());
		    setNumItem(videogioco.getCopie());
		    setPhoto(videogioco.getFoto());
		  }
		    
		  public int getItemID() {
		    return(itemID);
		  }

		  protected void setItemID(int itemID) {
		    this.itemID = itemID;
		  }

		  public String getShortDescription() {
		    return(shortDescription);
		  }

		  protected void setShortDescription(String shortDescription) {
		    this.shortDescription = shortDescription;
		  }

		  public String getLongDescription() {
		    return(longDescription);
		  }

		  protected void setLongDescription(String longDescription) {
		    this.longDescription = longDescription;
		  }

		  public double getCost() {
		    return(cost);
		  }

		  protected void setCost(double cost) {
		    this.cost = cost;
		  }
		  
		  public int getNumItem() {
			  return(numItem);
		  }
		  
		  protected void setNumItem(int numItem) {
			  this.numItem = numItem;
		  }
		  
		  public String getPhoto() {
			  return(photo);
		  }
		  
		  protected void setPhoto(String photo) {
			  this.photo = photo;
		  }

}
