package planetGaming.Utente;
import java.sql.SQLException;
import java.util.Collection;


public interface UtenteModel {
	public void doSave(UtenteBean utente) throws SQLException;

	public boolean doDelete(int codice) throws SQLException;

	//TODO non implementato
	public UtenteBean doRetrieveByKey(String email, String password) throws SQLException;
	
	Collection<UtenteBean> doRetrieveAll() throws SQLException;
	
	public void doUpdate(UtenteBean utente) throws SQLException;
}
