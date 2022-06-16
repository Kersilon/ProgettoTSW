package planetGaming.Ordine;

import java.io.Serializable;


public class OrdineBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	int codice;
	
	public OrdineBean() {
		codice = -1;
	}

	public int getCodice() {
		return codice;
	}
	
	public void setCodice(int codice) {
		this.codice = codice;
	}
	
	public String toString () {
		return codice + ".";
	}
}
