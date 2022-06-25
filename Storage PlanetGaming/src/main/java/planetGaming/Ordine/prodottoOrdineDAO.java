package planetGaming.Ordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class prodottoOrdineDAO implements prodottoOrdineModel {
	
	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			
			ds = (DataSource) envCtx.lookup("jdbc/planetgaming");
		}catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
	 	}
	}
	
	private static final String TABLE_NAME = "inclusione";

	@Override
	public void doSave(prodottoOrdineBean prodottoOrdine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String querySQL = "INSERT INTO " + prodottoOrdineDAO.TABLE_NAME
				+ " (idOrdine, idVideogioco, nome, prezzoAcquisto, scontoAcquisto, quantitaAcquisto, iva) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			preparedStatement.setInt(1, prodottoOrdine.getIdOrdine());
			preparedStatement.setInt(2, prodottoOrdine.getIdVideogioco());
			preparedStatement.setString(3, prodottoOrdine.getNomeVideogioco());
			preparedStatement.setDouble(4, prodottoOrdine.getPrezzoAcquisto());
			preparedStatement.setDouble(5, prodottoOrdine.getScontoAcquisto());
			preparedStatement.setInt(6, prodottoOrdine.getQuantitaAcquisto());
			preparedStatement.setDouble(7, prodottoOrdine.getIva());
			
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	@Override
	public boolean doDelete(int codice) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public prodottoOrdineBean doRetrieveByKey(int codice) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<prodottoOrdineBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<prodottoOrdineBean> prodottiOrdine = new LinkedList<prodottoOrdineBean>();
		
		String querySQL = "select * from "+ prodottoOrdineDAO.TABLE_NAME;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			
			//parte centrale del codice
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				prodottoOrdineBean prodottoOrdine = new prodottoOrdineBean();

				prodottoOrdine.setIdProdottoOrdine(((rs.getInt("idProdottiOrdine"))));
				prodottoOrdine.setIdOrdine(((rs.getInt("idOrdine"))));
				prodottoOrdine.setIdVideogioco(((rs.getInt("idVideogioco"))));
				prodottoOrdine.setNomeVideogioco(((rs.getString("nome"))));
				prodottoOrdine.setPrezzoAcquisto(((rs.getDouble("prezzoAcquisto"))));
				prodottoOrdine.setScontoAcquisto(((rs.getDouble("scontoAcquisto"))));
				prodottoOrdine.setQuantitaAcquisto(((rs.getInt("quantitaAcquisto"))));
				prodottoOrdine.setIva(((rs.getDouble("iva"))));
				
				prodottiOrdine.add(prodottoOrdine);
			}
			return prodottiOrdine;
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}

	}

}
