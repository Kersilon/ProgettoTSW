package planetGaming.Videogioco;

import java.sql.SQLException;
import java.util.Collection;

public interface VideogiocoModel {
	public void doSave(VideogiocoBean videogioco) throws SQLException;

	public boolean doDelete(int seriale) throws SQLException;

	//TODO non implementato
	public VideogiocoBean doRetrieveByKey(int seriale) throws SQLException;
	
	public Collection<VideogiocoBean> doRetrieveAll(String order) throws SQLException;
	
	public Collection<VideogiocoBean> doRetrieveAllByTitle(String order) throws SQLException;
	
	public void doUpdate(VideogiocoBean videogioco) throws SQLException;
}