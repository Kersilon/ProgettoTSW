package planetGaming.Corriere;

import java.io.Serializable;



public class CorriereBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String targa;
	
	public CorriereBean() {
		targa = "";
	}

	public String getTarga() {
		return targa;
	}
	
	public void setTarga(String targa) {
		this.targa = targa;
	}
	
	@Override
	public String toString() {
		return targa;
	}
}
