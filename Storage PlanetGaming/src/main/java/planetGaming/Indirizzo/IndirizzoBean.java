package planetGaming.Indirizzo;

import java.io.Serializable;

public class IndirizzoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String via;
	String cap;
	String citta;
	
	public IndirizzoBean() {
		via = "";
		cap = "";
		citta = "";
		
	}
	
	public String getVia() {
		return via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	public String getCap() {
		return cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String toString() {
		return via + " " +cap + " " + citta;
	}
	
}
