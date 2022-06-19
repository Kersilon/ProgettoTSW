package planetGaming.MetodoPagamento;

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

import planetGaming.Videogioco.VideogiocoBean;
import planetGaming.Videogioco.VideogiocoDAO;

public class MetodoPagamentoDAO implements MetodoPagamentoModel {
private static final String TABLE_NAME = "pagamento";
//inizializzazione collegamento DB
	private static DataSource ds;

		static {
			try {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");

				ds = (DataSource) envCtx.lookup("jdbc/planetgaming");

			} catch (NamingException e) {
				System.out.println("Error:" + e.getMessage());
			}
		}
	@Override
	public void doSave(MetodoPagamentoBean metodoPagamento) throws SQLException {
	//instauro connessione e realizzazione prepared statement
 		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String querySQL = "INSERT INTO " + MetodoPagamentoDAO.TABLE_NAME
				+ " (numero_carta, ccv, scadenza, circuito, codiceUtente, nome_intestatario, cognome_intestatario) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)"; //TODO query sql con "?"
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			
			//complazione preparedStatement ed esecuzione
			preparedStatement.setString(1, metodoPagamento.getNumCarta());
			preparedStatement.setString(2, metodoPagamento.getCcv());
			preparedStatement.setString(3, metodoPagamento.getScadenza());
			preparedStatement.setString(4, metodoPagamento.getCircuito());
			preparedStatement.setInt(	5, metodoPagamento.getCodiceUtente());
			preparedStatement.setString(6, metodoPagamento.getNomeIntestatario());
			preparedStatement.setString(7, metodoPagamento.getCognomeIntestatario());
			//TODO preparedStatement.set per tutti gli attributi
			
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
	public boolean doDelete(int numCarta) throws SQLException {
	//instauro connessione e realizzazione prepared statement
 		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result;
		
		String querySQL = "DELETE FROM " + MetodoPagamentoDAO.TABLE_NAME + " WHERE NUMERO_CARTA = ?"; //TODO query sql con "?"
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			
			//complazione preparedStatement ed esecuzione
			preparedStatement.setInt(1, numCarta);
			
			result = preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		return (result != 0);
	}

	@Override
	public MetodoPagamentoBean doRetrieveByKey(int numCarta) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		 MetodoPagamentoBean bean = new MetodoPagamentoBean();

		String selectSQL = "SELECT * FROM " + MetodoPagamentoDAO.TABLE_NAME + " WHERE NUMERO_CARTA = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, numCarta);
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNumCarta(rs.getString("numero_carta"));
				bean.setCcv(rs.getString("ccv"));
				bean.setCircuito(rs.getString("circuito"));
				bean.setScadenza(rs.getString("scadenza"));
				bean.setCodiceUtente(rs.getInt("codice_utente"));
				bean.setNomeIntestatario(rs.getString("nome_intestatario"));
				bean.setCognomeIntestatario(rs.getString("cognome_intestatario"));
				
			
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	@Override
	public Collection<MetodoPagamentoBean> doRetrieveAll(String order) throws SQLException {
		//instauro connessione e realizzazione prepared statement
 		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<MetodoPagamentoBean> metodiPagamento = new LinkedList<MetodoPagamentoBean>();
		
		String querySQL = "select * from "+ MetodoPagamentoDAO.TABLE_NAME; //TODO se non funziona controlla che gli spazi nella stringa siano corretti
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			
			//parte centrale del codice
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				MetodoPagamentoBean metodoPagamento = new MetodoPagamentoBean();

				metodoPagamento.setNumCarta(rs.getString("numero_carta"));
				metodoPagamento.setCcv((rs.getString("ccv")));		
				metodoPagamento.setCircuito((rs.getString("circuito")));
				metodoPagamento.setScadenza((rs.getString("scadenza")));
				metodoPagamento.setCodiceUtente((rs.getInt("codice_utente")));
				metodoPagamento.setNomeIntestatario((rs.getString("nome_intestatario")));
				metodoPagamento.setCognomeIntestatario((rs.getString("cognome_intestatario")));
				
				
				metodiPagamento.add(metodoPagamento);
			}
			
			return metodiPagamento;
			
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
