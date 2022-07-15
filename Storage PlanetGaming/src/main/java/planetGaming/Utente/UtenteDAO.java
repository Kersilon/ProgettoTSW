package planetGaming.Utente;

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

import planetGaming.Ordine.OrdineBean;
import planetGaming.Ordine.OrdineDAO;


public class UtenteDAO implements UtenteModel {
	private static DataSource ds;
	private static final String TABLE_NAME = "utente";

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
	public void doSave(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String querySQL = "INSERT INTO " + UtenteDAO.TABLE_NAME
				+ "(nome, cognome, dataNascita, nomeUtente, password, email, `#telefono`) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(querySQL);
			
			// inserisci quì il contentuo
			preparedStatement.setString(1, utente.getNome());
			preparedStatement.setString(2, utente.getCognome());
			preparedStatement.setDate(3, utente.getDataNascita());
			preparedStatement.setString(4, utente.getNomeUtente());
			preparedStatement.setString(5, utente.getPassword());
			preparedStatement.setString(6, utente.getEmail());
			preparedStatement.setString(7, utente.getTelefono());
			
			preparedStatement.executeUpdate();
			connection.commit();
			
			//test
			int x;
			
			x = preparedStatement.executeUpdate();
			
			if(x == 0) {
				System.out.println("l'SQL statement non ha restituito nulla");
			}else
			{
				System.out.println("Valore restituito");
			}
			//fine test
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String querySQL = ""; //TODO query sql con "?"
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			
			// inserisci quì il contentuo
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

	//TODO accertarsi che sia questo il nome della tabella nel DB

	
	@Override
	public synchronized UtenteBean doRetrieveByKey(String email, String password) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			UtenteBean utenteBean = new UtenteBean();
			String selectSQL = "SELECT * FROM " + UtenteDAO.TABLE_NAME + " WHERE email=? AND Password=?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					utenteBean.setNome(rs.getString("Nome"));
					utenteBean.setCognome(rs.getString("Cognome"));
					utenteBean.setDataNascita(rs.getDate("dataNascita"));
					utenteBean.setNomeUtente(rs.getString("nomeUtente"));
					utenteBean.setPassword(password);
					utenteBean.setEmail(email);
					utenteBean.setTelefono(rs.getString("#telefono"));
					utenteBean.setCodiceUtente(rs.getInt("codiceUtente"));
					utenteBean.setAMMINISTRATORE(rs.getBoolean("AMMINISTRATORE"));
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
			return utenteBean;
		}
	
		public synchronized UtenteBean doRetrieveByKey(int idUtente) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			UtenteBean utenteBean = new UtenteBean();
			String selectSQL = "SELECT * FROM " + UtenteDAO.TABLE_NAME + " WHERE codiceUtente=?";
	
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, idUtente);
				ResultSet rs = preparedStatement.executeQuery();
	
				while (rs.next()) {
					utenteBean.setNome(rs.getString("Nome"));
					utenteBean.setCognome(rs.getString("Cognome"));
					utenteBean.setDataNascita(rs.getDate("dataNascita"));
					utenteBean.setNomeUtente(rs.getString("nomeUtente"));
					utenteBean.setPassword(rs.getString("password"));
					utenteBean.setEmail(rs.getString("email"));
					utenteBean.setTelefono(rs.getString("#telefono"));
					utenteBean.setCodiceUtente(rs.getInt("codiceUtente"));
					utenteBean.setAMMINISTRATORE(rs.getBoolean("AMMINISTRATORE"));
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
			return utenteBean;
	}

		@Override
		public void doUpdate(UtenteBean utente) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String querySQL = "UPDATE " +UtenteDAO.TABLE_NAME+ " SET nome=?, cognome=?, dataNascita=?, nomeUtente=?, password=?, email=?, `#telefono`=?, `#acquisti`=?, AMMINISTRATORE=?, dataRegistrazione=? WHERE codiceUtente=?";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(querySQL);
				
				// inserisci quì il contentuo
				preparedStatement.setString(1, utente.getNome());
				preparedStatement.setString(2, utente.getCognome());
				preparedStatement.setDate(3, utente.getDataNascita());
				preparedStatement.setString(4, utente.getNomeUtente());
				preparedStatement.setString(5, utente.getPassword());
				preparedStatement.setString(6, utente.getEmail());
				preparedStatement.setString(7, utente.getTelefono());
				preparedStatement.setInt(8, utente.getAcquisti());
				preparedStatement.setInt(9, utente.getAMMINISTRATORE());
				preparedStatement.setDate(10, utente.getDataRegistrazione());
				preparedStatement.setInt(11, utente.getCodiceUtente());
				
				preparedStatement.executeUpdate();
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
		public Collection<UtenteBean> doRetrieveAll() throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			Collection<UtenteBean> utenti = new LinkedList<UtenteBean>();
			
			String querySQL = "select * from "+ UtenteDAO.TABLE_NAME;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(querySQL);
				
				//parte centrale del codice
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					UtenteBean utente = new UtenteBean();

					utente.setCodiceUtente(rs.getInt("codiceUtente"));
					utente.setNome(rs.getString("nome"));
					utente.setCognome(rs.getString("cognome"));
					utente.setDataNascita(rs.getDate("dataNascita"));;
					utente.setNomeUtente(rs.getString("nomeUtente"));
					utente.setPassword(rs.getString("password"));
					utente.setEmail(rs.getString("email"));
					utente.setTelefono(rs.getString("#telefono"));
					utente.setAcquisti(rs.getInt("#acquisti"));
					utente.setAMMINISTRATORE(rs.getBoolean("AMMINISTRATORE"));
					utente.setDataRegistrazione(rs.getDate("dataRegistrazione"));
					
					utenti.add(utente);
				}
				
				return utenti;
				
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
		public Collection<UtenteBean> doRetrieveAll(int idUtente) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			Collection<UtenteBean> utenti = new LinkedList<UtenteBean>();
			
			String querySQL = "select * from "+ UtenteDAO.TABLE_NAME+" where codiceUtente = ?";;
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(querySQL);
				preparedStatement.setInt(1, idUtente);
				
				//parte centrale del codice
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					UtenteBean utente = new UtenteBean();

					utente.setCodiceUtente(rs.getInt("codiceUtente"));
					utente.setNome(rs.getString("nome"));
					utente.setCognome(rs.getString("cognome"));
					utente.setDataNascita(rs.getDate("dataNascita"));;
					utente.setNomeUtente(rs.getString("nomeUtente"));
					utente.setPassword(rs.getString("password"));
					utente.setEmail(rs.getString("email"));
					utente.setTelefono(rs.getString("#telefono"));
					utente.setAcquisti(rs.getInt("#acquisti"));
					utente.setAMMINISTRATORE(rs.getBoolean("AMMINISTRATORE"));
					utente.setDataRegistrazione(rs.getDate("dataRegistrazione"));
					
					utenti.add(utente);
				}
				
				return utenti;
				
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
