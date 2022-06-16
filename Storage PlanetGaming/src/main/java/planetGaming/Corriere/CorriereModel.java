package planetGaming.Corriere;

import java.sql.SQLException;
import java.util.Collection;

public interface CorriereModel {

	public void doSave(CorriereBean corriere) throws SQLException;

	public boolean doDelete(String targa) throws SQLException;

	public CorriereBean doRetrieveByKey(String targa) throws SQLException;
	
	public Collection<CorriereBean> doRetrieveAll(String order) throws SQLException;
}
