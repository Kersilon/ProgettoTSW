package planetGaming.Utente;

import java.io.Serializable;

public class UtenteBean implements Serializable {

	//necessario se implementa serializable
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private String dataNascita;
	private String nomeUtente;
	private String password;
	private String email;
	private String telefono;
	private int codiceUtente;
	private boolean AMMINISTRATORE;

	
	public boolean isAMMINISTRATORE() {
		return AMMINISTRATORE;
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

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
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
