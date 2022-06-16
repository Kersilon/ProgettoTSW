package planetGaming.Recensione;

import java.sql.SQLException;
import java.util.Collection;

public interface RecensioneModel {
	
	public void doSave(RecensioneBean recensione) throws SQLException;
	
	public boolean doDelete(int id) throws SQLException;
	
	public RecensioneBean doRetrieveByKey(int id) throws SQLException;
	
	public Collection<RecensioneBean> doRetrieveAll(String order) throws SQLException;

}
