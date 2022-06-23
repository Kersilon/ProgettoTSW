package planetGaming.Ordine;

import java.io.Serializable;
import java.util.Collection;

import planetGaming.Videogioco.VideogiocoBean;


public class OrdineBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idOrdine;
	private int idUtente;
	private int prezzoTotale;
	private String dataOrdine;
	private String tracking;
	private String modalitaPagamento;
	private Collection<VideogiocoBean> prodottiOrdine;
	
	
	public OrdineBean() {
		super();
	}
	
	
	
	public String getTracking() {
		return tracking;
	}
	public void setTracking(String tracking) {
		this.tracking = tracking;
	}
	public String getModalitaPagamento() {
		return modalitaPagamento;
	}
	public void setModalitaPagamento(String modalitaPagamento) {
		this.modalitaPagamento = modalitaPagamento;
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
	public Collection<VideogiocoBean> getProdottiOrdine() {
		return prodottiOrdine;
	}
	public void setProdottiOrdine(Collection<VideogiocoBean> prodottiOrdine) {
		this.prodottiOrdine = prodottiOrdine;
	}
	
	
	
}
