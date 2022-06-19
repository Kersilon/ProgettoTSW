package planetGaming.MetodoPagamento;

import java.io.Serializable;

public class MetodoPagamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String numero_carta;
	String ccv;
	String circuito;
	String scadenza;
	int codiceUtente;
	String nome_intestatario;
	String cognome_intestatario;
	int idCarta;
	
	

	public MetodoPagamentoBean() {
		super();
	}



	public String getNumero_carta() {
		return numero_carta;
	}



	public void setNumero_carta(String numero_carta) {
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
		return codiceUtente;
	}



	public void setCodiceUtente(int codiceUtente) {
		this.codiceUtente = codiceUtente;
	}



	public String getNome_intestatario() {
		return nome_intestatario;
	}



	public void setNome_intestatario(String nome_intestatario) {
		this.nome_intestatario = nome_intestatario;
	}



	public String getCognome_intestatario() {
		return cognome_intestatario;
	}



	public void setCognome_intestatario(String cognome_intestatario) {
		this.cognome_intestatario = cognome_intestatario;
	}



	public int getIdCarta() {
		return idCarta;
	}



	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}

}	