package planetGaming.Videogioco;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VideogiocoDAO implements VideogiocoModel {
	
	private static final String TABLE_NAME = "videogioco";
	
	//TODO connessione DB tramite DataSource
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
	public void doSave(VideogiocoBean videogioco) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		FileSupport fs = new FileSupport();
		
		String insertSQL = "INSERT INTO " + VideogiocoDAO.TABLE_NAME
				+ "(nome, edizione, descrizione, prezzo_vetrina, data_uscita, piattaforma, Console, sconto, `#copie`, Sviluppatore, Pubblisher, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //TODO query sql con "?" che va ad aggiornare gli attributi di videogioco
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, videogioco.getNome());	//l'indice è 1 perché è il primo attributo inserito nella query insertSQL
			preparedStatement.setString(2, videogioco.getEdizione());
			preparedStatement.setString(3, videogioco.getDescrizione());
			preparedStatement.setDouble(4, videogioco.getPrezzo_vetrina());
			preparedStatement.setDate(5, videogioco.getData_uscita());
			preparedStatement.setString(6, videogioco.getPiattaforma());
			preparedStatement.setString(7, videogioco.getConsole());
			preparedStatement.setInt(8, videogioco.getSconto());
			preparedStatement.setInt(9, videogioco.getCopie());
			preparedStatement.setString(10, videogioco.getSviluppatore());
			preparedStatement.setString(11, videogioco.getPubblisher());
			preparedStatement.setString(12, videogioco.getFoto());
			
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
	public boolean doDelete(int seriale) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM "+VideogiocoDAO.TABLE_NAME+" WHERE codice_prodotto= ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setInt(1, seriale);
			
			result = preparedStatement.executeUpdate(); //restituisce 0 se non è andato a buon fine
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0); //se è andata bene e quindi result diverso da 0 restituisce true altrimenti false
	}

	@Override
	public VideogiocoBean doRetrieveByKey(int seriale) throws SQLException {
		//instauro connessione e realizzazione prepared statement
 		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		VideogiocoBean videogiocoBean;
		
		String querySQL = "SELECT * FROM "+VideogiocoDAO.TABLE_NAME+" WHERE codice_prodotto=?"; //TODO query sql con "?"
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			
			// inserisci quì il contentuo
			videogiocoBean = new VideogiocoBean(); 
			preparedStatement.setInt(1, seriale);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				videogiocoBean.setCodice_prodotto(rs.getInt("codice_prodotto"));
				videogiocoBean.setNome(rs.getString("nome"));
				videogiocoBean.setEdizione(rs.getString("edizione"));
				videogiocoBean.setDescrizione(rs.getString("descrizione"));
				videogiocoBean.setPrezzo_vetrina(rs.getDouble("prezzo_vetrina"));
				videogiocoBean.setData_uscita(rs.getDate("data_uscita"));
				videogiocoBean.setPiattaforma(rs.getString("piattaforma"));
				videogiocoBean.setConsole(rs.getString("Console"));
				videogiocoBean.setSconto(rs.getInt("sconto"));
				videogiocoBean.setCopie(rs.getInt("#copie"));
				videogiocoBean.setSviluppatore(rs.getString("Sviluppatore"));
				videogiocoBean.setPubblisher(rs.getString("Pubblisher"));
				videogiocoBean.setFoto(rs.getString("foto"));
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
		
		return videogiocoBean;
	}

	@Override
	public Collection<VideogiocoBean> doRetrieveAll(String order) throws SQLException {
		//instauro connessione e realizzazione prepared statement
 		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String querySQL = "select * from "+ VideogiocoDAO.TABLE_NAME; //TODO se non funziona controlla che gli spazi nella stringa siano corretti
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			
			//parte centrale del codice
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				VideogiocoBean videogioco = new VideogiocoBean();

				videogioco.setCodice_prodotto(rs.getInt("codice_prodotto"));
				videogioco.setNome(rs.getString("nome"));
				videogioco.setEdizione(rs.getString("edizione"));
				videogioco.setDescrizione(rs.getString("descrizione"));
				videogioco.setPrezzo_vetrina(rs.getDouble("prezzo_vetrina"));
				videogioco.setData_uscita(rs.getDate("data_uscita"));
				videogioco.setPiattaforma(rs.getString("piattaforma"));
				videogioco.setConsole(rs.getString("console"));
				videogioco.setSconto(rs.getInt("sconto"));
				videogioco.setCopie(rs.getInt("#copie"));
				videogioco.setSviluppatore(rs.getString("Sviluppatore"));
				videogioco.setPubblisher(rs.getString("Pubblisher"));
				videogioco.setFoto(rs.getString("foto"));
				
				videogiochi.add(videogioco);
			}
			
			return videogiochi;
			
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
	public void doUpdate(VideogiocoBean videogioco) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String querySQL = "UPDATE " +VideogiocoDAO.TABLE_NAME+ " SET nome=?, edizione=?, descrizione=?, prezzo_vetrina=?, data_uscita=?, piattaforma=?, sconto=?, console=?, `#copie`=?, Sviluppatore=?, Pubblisher=?, foto=? WHERE codice_prodotto=?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(querySQL);
			
			// inserisci quì il contentuo
			preparedStatement.setString(1, videogioco.getNome());	//l'indice è 1 perché è il primo attributo inserito nella query insertSQL
			preparedStatement.setString(2, videogioco.getEdizione());
			preparedStatement.setString(3, videogioco.getDescrizione());
			preparedStatement.setDouble(4, videogioco.getPrezzo_vetrina());
			preparedStatement.setDate(5, videogioco.getData_uscita());
			preparedStatement.setString(6, videogioco.getPiattaforma());
			preparedStatement.setString(7, videogioco.getConsole());
			preparedStatement.setInt(8, videogioco.getSconto());
			preparedStatement.setInt(9, videogioco.getCopie());
			preparedStatement.setString(10, videogioco.getSviluppatore());
			preparedStatement.setString(11, videogioco.getPubblisher());
			preparedStatement.setString(12, videogioco.getFoto());
			preparedStatement.setInt(13, videogioco.getCodice_prodotto());
			
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

}
