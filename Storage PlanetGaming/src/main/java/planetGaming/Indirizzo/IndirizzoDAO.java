package planetGaming.Indirizzo;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class IndirizzoDAO implements IndirizzoModel{
	
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/-----");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private static final String TABLE_NAME = "indirizzo";
	
	@Override
	public synchronized void doSave(IndirizzoBean indirizzo) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + IndirizzoDAO.TABLE_NAME
				+ " (VIA, CAP, CITTA) VALUES (?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, indirizzo.getVia());
			preparedStatement.setInt(2, Integer.parseInt(indirizzo.getCap()));
			preparedStatement.setString(3, indirizzo.getCitta());
			preparedStatement.setInt(4, indirizzo.getCodice_utente());

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
	public synchronized IndirizzoBean doRetrieveByKey(String via, String cap, int codiceUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		IndirizzoBean bean = new IndirizzoBean();

		String selectSQL = "SELECT * FROM " + IndirizzoDAO.TABLE_NAME + " WHERE VIA = ? AND CAP = ? AND codiceUtente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, via);
			preparedStatement.setInt(2, Integer.parseInt(cap));
			preparedStatement.setInt(3, codiceUtente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setVia(rs.getString("VIA"));
				bean.setCap(rs.getString("CAP"));
				bean.setCitta(rs.getString("CITTA"));
				bean.setCodice_utente(rs.getInt("codiceUtente"));
			
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
	public synchronized boolean doDelete(String via, String cap, int codiceUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + IndirizzoDAO.TABLE_NAME + " WHERE VIA = ? AND CAP = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, via);
			preparedStatement.setInt(2, Integer.parseInt(cap));
			preparedStatement.setInt(3, codiceUtente);

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
	public synchronized Collection<IndirizzoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<IndirizzoBean> indirizzi = new LinkedList<IndirizzoBean>();

		String selectSQL = "SELECT * FROM " + IndirizzoDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				IndirizzoBean bean = new IndirizzoBean();

				bean.setVia(rs.getString("VIA"));
				bean.setCap(rs.getString("CAP"));
				bean.setCitta(rs.getString("CITTA"));
				bean.setCodice_utente(rs.getInt("codiceUtente"));
				indirizzi.add(bean);
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
		return indirizzi;
	}

}
