package planetGaming.Ordine;

import java.io.Serializable;
import java.text.DecimalFormat;

import planetGaming.Videogioco.VideogiocoBean;

public class prodottoOrdineBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final int iva = 10;
	
	private int idProdottoOrdine;
	private int idOrdine;
	private int idVideogioco;
	private String nomeVideogioco;
	private double prezzoAcquisto;	//aka prezzoTotale
	private double scontoAcquisto;	//aka prezzoScontato
	private int quantitaAcquisto;
	private String foto;
	
	private double prezzoBase;
	private double sconto;
	private double scontoEuro;
	private double prezzoScontato;
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	
	public prodottoOrdineBean() {
		super();
	}
	
	public prodottoOrdineBean(VideogiocoBean videogioco) {
		this.idVideogioco = videogioco.getCodice_prodotto();
		this.nomeVideogioco = videogioco.getNome();
		this.quantitaAcquisto = 1;
		this.foto = videogioco.getFoto();
		
		this.prezzoBase = videogioco.getPrezzo_vetrina();
		this.sconto = videogioco.getSconto();
		this.scontoEuro = (this.prezzoBase*this.sconto)/100;
		this.prezzoScontato = (this.prezzoBase - this.scontoEuro);
		
		updateTotal();
	}
	
	public void removeProdotto() {
		this.quantitaAcquisto--;
		updateTotal();
	}
	
	public void addProdotto() {
		this.quantitaAcquisto++;
		updateTotal();
	}
	
	private void updateTotal() {
		this.prezzoAcquisto = (this.prezzoScontato*this.quantitaAcquisto);
		this.scontoAcquisto = (this.scontoEuro*this.quantitaAcquisto);
	}
	
	
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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
		this.prezzoBase = prezzoAcquisto;
		this.prezzoAcquisto = prezzoAcquisto;
	}
	public double getScontoAcquisto() {
		return scontoAcquisto;
	}
	public void setScontoAcquisto(double scontoAcquisto) {
		this.sconto = scontoAcquisto;
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
}


