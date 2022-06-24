package planetGaming.Ordine;

import java.io.Serializable;

public class prodottoOrdineBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idProdottoOrdine;
	private int idOrdine;
	private int idVideogioco;
	private String nomeVideogioco;
	private double prezzoAcquisto;
	private double scontoAcquisto;
	private int quantitaAcquisto;
	private double iva;
	
	
	public prodottoOrdineBean() {
		super();
	}
	
	
	public int getIdProdottoOrdine() {
		return idProdottoOrdine;
	}
	public void setIdProdottoOrdine(int idProdottoOrdine) {
		this.idProdottoOrdine = idProdottoOrdine;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public int getIdVideogioco() {
		return idVideogioco;
	}
	public void setIdVideogioco(int idVideogioco) {
		this.idVideogioco = idVideogioco;
	}
	public String getNomeVideogioco() {
		return nomeVideogioco;
	}
	public void setNomeVideogioco(String nomeVideogioco) {
		this.nomeVideogioco = nomeVideogioco;
	}
	public double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public void setPrezzoAcquisto(double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
	public double getScontoAcquisto() {
		return scontoAcquisto;
	}
	public void setScontoAcquisto(double scontoAcquisto) {
		this.scontoAcquisto = scontoAcquisto;
	}
	public int getQuantitaAcquisto() {
		return quantitaAcquisto;
	}
	public void setQuantitaAcquisto(int quantitaAcquisto) {
		this.quantitaAcquisto = quantitaAcquisto;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
}


