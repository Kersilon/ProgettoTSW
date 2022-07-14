package planetGaming.Utente;

import java.io.Serializable;
import java.sql.Date;

public class UtenteBean implements Serializable {

	//necessario se implementa serializable
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String nomeUtente;
	private String password;
	private String email;
	private String telefono;
	private int acquisti;
	private int codiceUtente;
	private Date dataRegistrazione;
	private boolean AMMINISTRATORE;

	
	
	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public int getAcquisti() {
		return acquisti;
	}
	
	public void setAcquisti(int acquisti) {
		this.acquisti = acquisti;
	}
	
	public boolean isAMMINISTRATORE() {
		return AMMINISTRATORE;
	}
	
	public int getAMMINISTRATORE() {
		if(this.isAMMINISTRATORE()) {
			return 1;
		}else {
			return 0;
		}
	}
	public void setAMMINISTRATORE(boolean aMMINISTRATORE) {
		AMMINISTRATORE = aMMINISTRATORE;
	}
	
	public int getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(int codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
