package planetGaming.Recensione;

import java.io.Serializable;

public class RecensioneBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	int id;

	public RecensioneBean() {
		id = -1;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return id + "";
	}
}
