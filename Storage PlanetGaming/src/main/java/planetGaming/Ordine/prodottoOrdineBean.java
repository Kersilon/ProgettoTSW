package planetGaming.Ordine;

import java.io.Serializable;

import planetGaming.Videogioco.VideogiocoBean;

public class prodottoOrdineBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final int iva = 10;
	
	private int idProdottoOrdine;
	private int idOrdine;
	private int idVideogioco;
	private String nomeVideogioco;
	private double prezzoAcquisto;
	private double scontoAcquisto;
	private int quantitaAcquisto;
	private String foto;
	
	
	
	public prodottoOrdineBean() {
		super();
	}
	
	public prodottoOrdineBean(VideogiocoBean videogioco) {
		this.idVideogioco = videogioco.getCodice_prodotto();
		this.nomeVideogioco = videogioco.getNome();
		this.prezzoAcquisto = videogioco.getPrezzo_vetrina() - videogioco.getSconto();
		this.scontoAcquisto = videogioco.getSconto();
		this.quantitaAcquisto = 1;
		this.foto = videogioco.getFoto();
	}
	
	public void removeProdotto() {
		decreasePrezzoAcquisto();
		decreaseQuantitaAcquisto();
	}
	
	public void addProdotto() {
		increasePrezzoAcquisto();
		increaseQuantitaAcquisto();
	}
	
	public void increaseQuantitaAcquisto() {
		this.quantitaAcquisto++;
	}
	
	public void decreaseQuantitaAcquisto() {
		this.quantitaAcquisto--;
	}
	
	public void increasePrezzoAcquisto() {
		this.prezzoAcquisto += this.prezzoAcquisto;
		this.scontoAcquisto += this.scontoAcquisto;
	}
	
	public void decreasePrezzoAcquisto() {
		this.prezzoAcquisto -= (this.prezzoAcquisto/this.quantitaAcquisto);
		this.scontoAcquisto -= (this.scontoAcquisto/this.quantitaAcquisto);
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
}


