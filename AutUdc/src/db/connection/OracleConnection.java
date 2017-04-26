package db.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import autudc.logger.Logger;
import autudc.logger.LoggerFactory;

public class OracleConnection implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = null;
	static private String dataSourceName = null;
	private Statement stmt;
	private ResultSet rs;
	private Connection connection = null;
	String MP_Log = "";
	String poolConnessione = "";
	
	public OracleConnection(String utente) {
		super();
		MP_Log = utente;
		poolConnessione = System.getProperty("DD_CHCI0_JNDIDATASOURCENAME");
		try {
			if (logger == null) {
				String pkgName = this.getClass().getPackage().getName();
				logger = LoggerFactory.getLogger(pkgName);
			}
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
    	logger.debug("OracleConnection");
	}

	public void initConn() {
		if (dataSourceName == null) {
			Properties cfgProp = new Properties();
			dataSourceName = cfgProp.getProperty(poolConnessione);
			dataSourceName = poolConnessione;
		}
		try {
			Context initialContext = new InitialContext();
			DataSource dataSource = (DataSource) initialContext.lookup(dataSourceName);
			connection = dataSource.getConnection();
		} catch (NamingException ne) {
			logger.error(MP_Log + " OracleConnection.initConn NamingException "	+ ne.getMessage());
		} catch (SQLException se) {
			logger.error(MP_Log + " OracleConnection.initConn SQLException "	+ se.getMessage());
		}		
	}

	public void close() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				// conn.commit(); ATTENZIONE: all'interno di transazioni
				// distribuite non si deve fare il commit!
				connection.close();
			}
		} catch (SQLException e) {
			logger.error(MP_Log + " OracleConnection.close SQLException "	+ e.getMessage());
		}		
	}

	public void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			logger.error(MP_Log + " OracleConnection.close SQLException " 	+ e.getMessage());
		}
	}

	public ResultSet GetQuery(String sql) throws Exception {
		stmt = getConnection().createStatement();
		String sqlQueryString = sql;
		rs = stmt.executeQuery(sqlQueryString);
		return rs;
	}

	public boolean executeQuery(String sql) throws Exception {
		stmt = getConnection().createStatement();
		return stmt.execute(sql);
	}
	
	/** *********************** * */
	/*
	 * USATA SOLO LA EXECUTE QUERY PERCHE' IL CODICE E' AVANTI NELLO SVILUPPO SE
	 * SI FA UPDATE E DELETE CHE NON RESTITUISCO UN RESULT SET LO SI IGNORA DOPO
	 * AVER CHIANMATO LA GetQuery
	 * 
	 */

	public boolean Insert(String sql) {
		try {
			stmt = getConnection().createStatement();
			String sqlQueryString = sql;
			stmt.executeUpdate(sqlQueryString);
			return true;
		} catch (Exception e) {
			logger.error(MP_Log + " OracleConnection.close Exception " + e.getMessage());
			return false;
		}
	}

	// Questo tipo di insert restituisce l'exception
	public void insert(String sql) throws Exception {
		stmt = getConnection().createStatement();
		String sqlQueryString = sql;
		stmt.executeUpdate(sqlQueryString);
		stmt.close();
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStmt() {
		return stmt;
	}
}