package planetGaming.MetodoPagamento;

import java.io.Serializable;

public class MetodoPagamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String numero_carta;
	String ccv;
	String circuito;
	String scadenza;
	int codice_utente;
	String nome_intestatario;
	String cognome_intestatario;

	public String getNumCarta() {
		return numero_carta;
	}

	public void setNumCarta(String numero_carta) {
		this.numero_carta = numero_carta;
	}
	
	public String getCcv() {
		return ccv;
	}
	
	public void setCcv(String ccv) {
		this.ccv = ccv;
	}
	
	public String getCircuito() {
		return circuito;
	}
	
	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}
	
	public String getScadenza() {
		return scadenza;
	}
	
	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}
	
	public int getCodiceUtente() {
		return codice_utente;
	}
	
	public void setCodiceUtente(int codice_utente) {
		this.codice_utente = codice_utente;
	}
	
	public String getNomeIntestatario() {
		return nome_intestatario;
	}
	
	public void setNomeIntestatario(String nome_intestatario) {
		this.nome_intestatario = nome_intestatario;
	}
	
	public String getCognomeIntestatario() {
		return cognome_intestatario;
	}
	
	public void setCognomeIntestatario(String cognome_intestatario) {
		this.cognome_intestatario = cognome_intestatario;
	}
	
	public String toString() {
		return numero_carta + " " + ccv + " " + circuito + " " + scadenza + " " + codice_utente + " " + nome_intestatario + " " + cognome_intestatario;
	}
}
