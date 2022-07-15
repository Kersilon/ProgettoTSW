package planetGaming.MetodoPagamento;

import java.sql.SQLException;
import java.util.Collection;

public interface MetodoPagamentoModel {
	public void doSave(MetodoPagamentoBean metodoPagamento) throws SQLException;

	public boolean doDelete(int numCarta) throws SQLException;

	//TODO non implementato
	public MetodoPagamentoBean doRetrieveByKey(int numCarta) throws SQLException;
	
	public Collection<MetodoPagamentoBean> doRetrieveAll(String order) throws SQLException; //order = ordine in cui vengono prese le entry del DB

    public Collection<MetodoPagamentoBean> doRetrieveByUser(int codiceUtente) throws SQLException;
}
