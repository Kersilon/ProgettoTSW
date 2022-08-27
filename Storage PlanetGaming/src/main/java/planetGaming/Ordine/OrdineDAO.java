package planetGaming.Ordine;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;


public class OrdineDAO implements OrdineModel{
	
	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			
			ds = (DataSource) envCtx.lookup("jdbc/planetgaming");
		}
		
	 catch (NamingException e) {
		System.out.println("Error:" + e.getMessage());
	}
}
	
	private static final String TABLE_NAME = "ordine";
	prodottoOrdineDAO prodottoOrdineDao = new prodottoOrdineDAO();
	
	@Override
	public synchronized void doSave(OrdineBean ordine) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String querySQL = "INSERT INTO " + OrdineDAO.TABLE_NAME
				+ " (idUtente, idModalitaPagamento, idIndirizzo, prezzoTotale, data, tracking) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(querySQL);
			preparedStatement.setInt(	1, ordine.getIdUtente());
			preparedStatement.setInt(	2, ordine.getIdModalitaPagamento());
			preparedStatement.setInt(	3, ordine.getIdIndirizzo());
			preparedStatement.setInt(	4, ordine.getPrezzoTotale());
			preparedStatement.setDate(5, ordine.getDataOrdine());
			preparedStatement.setString(6, ordine.getTracking());
			
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
		
		for(prodottoOrdineBean po: ordine.getProdottiOrdine()) {
			po.setIdOrdine(doRetrieveLast().getIdOrdine());
			prodottoOrdineDao.doSave(po);
		}
		
	}
	

	@Override
	public synchronized OrdineBean doRetrieveByKey(int codice) throws SQLException {
		//instauro connessione e realizzazione prepared statement
 		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		OrdineBean ordineBean;
		
		String querySQL = "SELECT * FROM "+OrdineDAO.TABLE_NAME+" WHERE idOrdine=?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			
			// inserisci qu� il contentuo
			ordineBean = new OrdineBean(); 
			preparedStatement.setInt(1, codice);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				ordineBean.setIdOrdine(rs.getInt("idOrdine"));
				ordineBean.setIdUtente(rs.getInt("idUtente"));
				ordineBean.setIdModalitaPagamento(rs.getInt("idModalitaPagamento"));
				ordineBean.setIdIndirizzo(rs.getInt("idIndirizzo"));
				ordineBean.setPrezzoTotale(rs.getInt("prezzoTotale"));
				ordineBean.setDataOrdine(rs.getDate("data"));
				ordineBean.setTracking(rs.getString("tracking"));
				
				ordineBean.setProdottiOrdine(prodottoOrdineDao.doRetrieveAll(ordineBean.getIdOrdine()));
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
		
		return ordineBean;
}
	
@Override
public OrdineBean doRetrieveLast() throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	OrdineBean ordineBean;
	
	String querySQL = "select * from planetgaming.ordine ORDER BY idOrdine DESC LIMIT 1"; 
	
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(querySQL);
		
		// inserisci qu� il contentuo
		ordineBean = new OrdineBean(); 
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next())
		{
			ordineBean.setIdOrdine(rs.getInt("idOrdine"));
			ordineBean.setIdUtente(rs.getInt("idUtente"));
			ordineBean.setIdModalitaPagamento(rs.getInt("idModalitaPagamento"));
			ordineBean.setIdIndirizzo(rs.getInt("idIndirizzo"));
			ordineBean.setPrezzoTotale(rs.getInt("prezzoTotale"));
			ordineBean.setDataOrdine(rs.getDate("data"));
			ordineBean.setTracking(rs.getString("tracking"));
			
			ordineBean.setProdottiOrdine(prodottoOrdineDao.doRetrieveAll(ordineBean.getIdOrdine()));
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
	
	return ordineBean;
}

	@Override
	public synchronized boolean doDelete(int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineDAO.TABLE_NAME + " WHERE CODICE = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, codice);

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
	public synchronized Collection<OrdineBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();
		
		String querySQL = "select * from "+ OrdineDAO.TABLE_NAME;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			
			//parte centrale del codice
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean ordine = new OrdineBean();

				ordine.setIdOrdine((rs.getInt("idOrdine")));
				ordine.setIdUtente((rs.getInt("idUtente")));
				ordine.setIdModalitaPagamento((rs.getInt("idModalitaPagamento")));
				ordine.setIdIndirizzo((rs.getInt("idIndirizzo")));
				ordine.setPrezzoTotale((rs.getInt("prezzoTotale")));
				ordine.setDataOrdine((rs.getDate("data")));
				ordine.setTracking((rs.getString("tracking")));
				
				ordine.setProdottiOrdine(prodottoOrdineDao.doRetrieveAll(ordine.getIdOrdine()));
				ordini.add(ordine);
			}
			
			return ordini;
			
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

	
	
	public synchronized Collection<OrdineBean> doRetrieveAllByTotal(int idUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();
		
		String querySQL = "select * from "+ OrdineDAO.TABLE_NAME +" where idUtente = ? order by prezzoTotale";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			preparedStatement.setInt(1, idUtente);
			
			//parte centrale del codice
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean ordine = new OrdineBean();

				ordine.setIdOrdine((rs.getInt("idOrdine")));
				ordine.setIdUtente((rs.getInt("idUtente")));
				ordine.setIdModalitaPagamento((rs.getInt("idModalitaPagamento")));
				ordine.setIdIndirizzo((rs.getInt("idIndirizzo")));
				ordine.setPrezzoTotale((rs.getInt("prezzoTotale")));
				ordine.setDataOrdine((rs.getDate("data")));
				ordine.setTracking((rs.getString("tracking")));
				
				ordine.setProdottiOrdine(prodottoOrdineDao.doRetrieveAll(ordine.getIdOrdine()));
				ordini.add(ordine);
			}
			
			return ordini;
			
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
	public Collection<OrdineBean> doRetrieveAll(int idUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();
		
		String querySQL = "select * from "+ OrdineDAO.TABLE_NAME +" where idUtente = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			preparedStatement.setInt(1, idUtente);
			
			//parte centrale del codice
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean ordine = new OrdineBean();

				ordine.setIdOrdine((rs.getInt("idOrdine")));
				ordine.setIdUtente((rs.getInt("idUtente")));
				ordine.setIdModalitaPagamento((rs.getInt("idModalitaPagamento")));
				ordine.setIdIndirizzo((rs.getInt("idIndirizzo")));
				ordine.setPrezzoTotale((rs.getInt("prezzoTotale")));
				ordine.setDataOrdine((rs.getDate("data")));
				ordine.setTracking((rs.getString("tracking")));
				
				ordine.setProdottiOrdine(prodottoOrdineDao.doRetrieveAll(ordine.getIdOrdine()));
				ordini.add(ordine);
			}
			
			return ordini;
			
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
	public Collection<OrdineBean> doRetrieveAll(Date min, Date max) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();
		
		String querySQL = "SELECT * FROM "+ OrdineDAO.TABLE_NAME +" where data >= ? && data <= ? order by data";
		
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			preparedStatement.setDate(1, min);
			preparedStatement.setDate(2, max);
			
			//parte centrale del codice
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean ordine = new OrdineBean();

				ordine.setIdOrdine((rs.getInt("idOrdine")));
				ordine.setIdUtente((rs.getInt("idUtente")));
				ordine.setIdModalitaPagamento((rs.getInt("idModalitaPagamento")));
				ordine.setIdIndirizzo((rs.getInt("idIndirizzo")));
				ordine.setPrezzoTotale((rs.getInt("prezzoTotale")));
				ordine.setDataOrdine((rs.getDate("data")));
				ordine.setTracking((rs.getString("tracking")));
				
				ordine.setProdottiOrdine(prodottoOrdineDao.doRetrieveAll(ordine.getIdOrdine()));
				ordini.add(ordine);
			}
			
			return ordini;
			
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
