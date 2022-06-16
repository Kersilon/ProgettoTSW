package planetGaming.MetodoPagamento;

import java.sql.SQLException;

public interface MetodoPagamentoModel {
	public void doSave(MetodoPagamentoBean metodoPagamento) throws SQLException;

	public boolean doDelete(int numCarta) throws SQLException;

	//TODO non implementato
	public MetodoPagamentoBean doRetrieveByKey(int numCarta) throws SQLException;
	
	//non credo servirà
	//public Collection<Bean> doRetrieveAll(String order) throws SQLException; //order = ordine in cui vengono prese le entry del DB
}
