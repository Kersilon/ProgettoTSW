package planetGaming.Utente;
import java.sql.SQLException;

import planetGaming.Videogioco.VideogiocoBean;

public interface UtenteModel {
	public void doSave(UtenteBean utente) throws SQLException;

	public boolean doDelete(int codice) throws SQLException;

	//TODO non implementato
	public UtenteBean doRetrieveByKey(String email, String password) throws SQLException;
	
	//non credo servirà
	//public Collection</*TODO tipo*/> doRetrieveAll(String order/*TODO da modificare*/) throws SQLException;
	
	public void doUpdate(UtenteBean utente) throws SQLException;
}
