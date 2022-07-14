package planetGaming.Videogioco;


import java.io.Serializable;
import java.sql.Date;


public class VideogiocoBean implements Serializable {

	//necessario se implementa serializable
	private static final long serialVersionUID = 1L;
	
	private Integer codice_prodotto;
	private String nome;
	private String edizione;
	private String descrizione;
	private double prezzo_vetrina;  
	private Date data_uscita;
	private String piattaforma; 
	private String Console; 
	private Integer sconto; 
	private Integer copie; 
	private String Sviluppatore; 
	private String Pubblisher;
	private String foto;

	

	public Integer getCodice_prodotto() {
		return codice_prodotto;
	}

	public void setCodice_prodotto(Integer codice_prodotto) {
		this.codice_prodotto = codice_prodotto;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getEdizione() {
		return edizione;
	}

	public void setEdizione(String edizione) {
		this.edizione = edizione;
	}

	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
	public double getPrezzo_vetrina() {
		return prezzo_vetrina;
	}

	public void setPrezzo_vetrina(double prezzo_vetrina) {
		this.prezzo_vetrina = prezzo_vetrina;
	}

	public Date getData_uscita() {
		return data_uscita;
	}

	public void setData_uscita(Date data_uscita) {
		this.data_uscita = data_uscita;
	}

	public String getPiattaforma() {
		return piattaforma;
	}

	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}

	public String getConsole() {
		return Console;
	}

	public void setConsole(String console) {
		Console = console;
	}

	public Integer getSconto() {
		return sconto;
	}

	public void setSconto(Integer sconto) {
		this.sconto = sconto;
	}

	public Integer getCopie() {
		return copie;
	}

	public void setCopie(Integer copie) {
		this.copie = copie;
	}

	public String getSviluppatore() {
		return Sviluppatore;
	}

	public void setSviluppatore(String Sviluppatore) {
		this.Sviluppatore = Sviluppatore;
	}

	public String getPubblisher() {
		return Pubblisher;
	}

	public void setPubblisher(String Pubblisher) {
		this.Pubblisher = Pubblisher;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String filePath) {
		this.foto = filePath;
	}
	
	
	
	
	//generare toString
}
	
	