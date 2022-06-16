package planetGaming.Indirizzo;

import java.sql.SQLException;
import java.util.Collection;

public interface IndirizzoModel {

	public void doSave(IndirizzoBean indirizzo) throws SQLException;
	
	public boolean doDelete(String via, String cap) throws SQLException;
	
	public IndirizzoBean doRetrieveByKey(String via, String cap) throws SQLException;
	
	public Collection<IndirizzoBean> doRetrieveAll(String order) throws SQLException;
}
