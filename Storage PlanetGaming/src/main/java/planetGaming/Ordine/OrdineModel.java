package planetGaming.Ordine;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

public interface OrdineModel {
	public void doSave (OrdineBean ordine) throws SQLException;
	
	public boolean doDelete (int codice) throws SQLException;
	
	public OrdineBean doRetrieveByKey(int codice) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll(String order) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll(int idUtente) throws SQLException;
	
	//query per retriveAll in base alla data e ordinato per data
	//SELECT * FROM planetgaming.ordine where data >= "2021/1/1" && data <= "2022/1/1" order by data;
	public Collection<OrdineBean> doRetrieveAll(Date min, Date max) throws SQLException;

}
