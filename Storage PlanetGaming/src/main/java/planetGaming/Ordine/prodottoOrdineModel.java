package planetGaming.Ordine;

import java.sql.SQLException;
import java.util.Collection;

public interface prodottoOrdineModel {
public void doSave (prodottoOrdineBean prodottoOrdine) throws SQLException;
	
	public boolean doDelete (int codice) throws SQLException;
	
	public prodottoOrdineBean doRetrieveByKey(int codice) throws SQLException;
	
	public Collection<prodottoOrdineBean> doRetrieveAll(String order) throws SQLException;
	
	public Collection<prodottoOrdineBean> doRetrieveAll(int idOrdine) throws SQLException;

}
