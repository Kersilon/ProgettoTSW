package planetGaming.Ordine;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

public interface OrdineModel {
	public void doSave (OrdineBean ordine) throws SQLException;
	
	public boolean doDelete (int codice) throws SQLException;
	
	public OrdineBean doRetrieveByKey(int codice) throws SQLException;
	
	public OrdineBean doRetrieveLast() throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll(String order) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll(int idUtente) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll(Date min, Date max) throws SQLException;

	public Collection<OrdineBean> doRetrieveAllByTotal(int idUtente) throws SQLException;

}
