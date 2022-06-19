package planetGaming.Indirizzo;

import java.io.Serializable;

public class IndirizzoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String via;
	String cap;
	String citta;
	String provincia;
	int codice_utente;
	int idIndirizzo;
	
	public IndirizzoBean() {	
	}
	
	
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getIdIndirizzo() {
		return idIndirizzo;
	}

	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public int getCodice_utente() {
		return codice_utente;
	}

	public void setCodice_utente(int codice_utente) {
		this.codice_utente = codice_utente;
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
