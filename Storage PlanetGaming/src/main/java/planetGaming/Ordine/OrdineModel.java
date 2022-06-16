package planetGaming.Ordine;

import java.sql.SQLException;
import java.util.Collection;

public interface OrdineModel {
	public void doSave (OrdineBean ordine) throws SQLException;
	
	public boolean doDelete (int codice) throws SQLException;
	
	public OrdineBean doRetrieveByKey(int codice) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll(String order) throws SQLException;

}
