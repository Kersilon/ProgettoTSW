package planetGaming.Ordine;

import java.io.Serializable;
import java.util.Collection;


public class OrdineBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idOrdine;
	private int idUtente;
	private int idModalitaPagamento;
	private int idIndirizzo;
	private int prezzoTotale;
	private String dataOrdine;
	private String tracking;
	
	private Collection<prodottoOrdineBean> prodottiOrdine;
	
	
	public OrdineBean() {
		super();
	}
	
	
	public Collection<prodottoOrdineBean> getProdottiOrdine() {
		return prodottiOrdine;
	}
	public void setProdottiOrdine(Collection<prodottoOrdineBean> prodottiOrdine) {
		this.prodottiOrdine = prodottiOrdine;
	}
	public int getIdModalitaPagamento() {
		return idModalitaPagamento;
	}
	public void setIdModalitaPagamento(int idModalitaPagamento) {
		this.idModalitaPagamento = idModalitaPagamento;
	}
	public int getIdIndirizzo() {
		return idIndirizzo;
	}
	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}
	public String getTracking() {
		return tracking;
	}
	public void setTracking(String tracking) {
		this.tracking = tracking;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public int getPrezzoTotale() {
		return prezzoTotale;
	}
	public void setPrezzoTotale(int prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	public String getDataOrdine() {
		return dataOrdine;
	}
	public void setDataOrdine(String dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	
	
	
}
