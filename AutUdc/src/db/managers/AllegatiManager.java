package db.managers;

import java.sql.ResultSet;
import java.util.Vector;

import Utility.Utility;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import db.connection.OracleConnection;
import db.entities.Allegati;

public class AllegatiManager {
	
	private Logger logger = null;
	
	public AllegatiManager() {
		super();
		try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("Costruttore AllegatiManager()");
	}
	public boolean  esisteAllegato(String owner, OracleConnection con,
			String codiceUdc, 
			String nomeFile,
			String piattaforma,
			String tipoAllegato)  throws Exception {
		logger.debug(owner + " | AllegatiManager.esisteAllegato - inizio");
		boolean result = false;
		String query  = " SELECT COUNT(*) C " +
					" 	FROM CHCI_OWN.T_CHCI_ALLEGATI " + " " +
					"   WHERE FILE_NAME ='" + nomeFile + "' AND " + 
					"         COD_UDC ='" + codiceUdc + "' AND " +
					"         COD_PIATTAFORMA ='" + piattaforma + "' AND " +
					"         TIPO_ALLEGATO='" + tipoAllegato + "'";
		
		result = con.executeQuery(query);
		
		logger.debug(owner + " | AllegatiManager.esisteAllegato - fine");
		return result;
	}
	

	/**
	 * CERCA L'ALLEGATO PER IL SOLO CODICE UDC
	 * CHE E' UN TIME STAMP PER GLI ALLEGATI UDC ANCORA TEMPORANEI
	 */
	public boolean  esisteAllegato(String owner, OracleConnection con,
			String codiceUdc, String sistemaInformativo)  throws Exception {
		logger.debug(owner + " | AllegatiManager.esisteAllegato - inizio");
		boolean result = false;
		String query = " SELECT ALLEGATO " +
					"    FROM CHCI_OWN.T_CHCI_ALLEGATI " +
					"    WHERE COD_UDC = '" + codiceUdc + "'" +
					"      AND SISTEMA_INFORMATIVO='"+sistemaInformativo+"' ";
		
		result = con.executeQuery(query);
		
		logger.debug(owner + " | AllegatiManager.esisteAllegato - fine");
		return result;
	}

	public boolean  esisteAllegatoPianoAttivazione(String owner, OracleConnection con,
			String codiceUdc, String tipoAllegato, String sistemaInformativo)  throws Exception {
		logger.debug(owner + " | AllegatiManager.esisteAllegatoPianoAttivazione - inizio");
		boolean result = false;
		int numOcc = 0;
		String query = " SELECT COUNT(*) C " +
					"    FROM CHCI_OWN.T_CHCI_ALLEGATI " +
					"    WHERE COD_UDC = '" + codiceUdc + "'" +
					"      	AND TIPO_ALLEGATO='"+tipoAllegato+"' " +
					"		AND SISTEMA_INFORMATIVO='"+sistemaInformativo+"' ";
		
		ResultSet rs = con.GetQuery(query);

		if (rs != null) {
			if (rs.next()) {
				numOcc = rs.getInt(1);
			}
		}
		if(numOcc > 0) {
			result = true;
		}
		
		logger.debug(owner + " | AllegatiManager.esisteAllegatoPianoAttivazione - fine " + query + " " + result);
		return result;
	}
	
	
	public boolean updateAllegato (String owner, OracleConnection con,
							String sistemaInformativo,
							String codiceUdcOld, 
							String codiceUdcNew,
							String piattaforma,
							String stato)  throws Exception {
		logger.debug(owner + " | AllegatiManager.updateAllegato - inizio");
		boolean result = false;
		
		if(esisteAllegato(owner, con, codiceUdcOld,sistemaInformativo)) {
			String query = " UPDATE CHCI_OWN.T_CHCI_ALLEGATI " +
			"        SET COD_UDC = '" + codiceUdcNew + "', " +
			"			 SISTEMA_INFORMATIVO='"+sistemaInformativo+"' ," +
			"            COD_PIATTAFORMA = '" + piattaforma + "', " +
			"            STATO = '" + stato + "' " +
			"        WHERE COD_UDC ='" + codiceUdcOld + "' AND SISTEMA_INFORMATIVO='"+sistemaInformativo+"' ";
	
			result = con.executeQuery(query);
		}else {
			throw new Exception("Allegato non presente nel data base.");
		}		
		
		logger.debug(owner + " | AllegatiManager.updateAllegato - fine");	
		return result;
	}
	
	public boolean deleteAllegato(String owner, 
			OracleConnection con,
			String nomeFile, 			
			String codiceUdc,
			String sistemaInformativo,
			String piattaforma,
			String tipoAllegato
			)  throws Exception {
		logger.debug(owner + " | AllegatiManager.deleteAllegato - inizio");
		boolean result = false;
		
		String query = "DELETE FROM CHCI_OWN.T_CHCI_ALLEGATI" +
		"   WHERE FILE_NAME=\'"		+ nomeFile	+ "\' AND " +
		"         COD_PIATTAFORMA=\'" + piattaforma	+ "\' AND " +
		"         COD_UDC=\'" + codiceUdc + "\' AND " +
		"         SISTEMA_INFORMATIVO=\'" + sistemaInformativo + "\' AND " +
		"         TIPO_ALLEGATO=\'" + tipoAllegato + "\'" ;
		logger.debug(owner + " | AllegatiManager.deleteAllegato - query: "+query);
		result = con.executeQuery(query);
		
		logger.debug(owner + " | AllegatiManager.deleteAllegato - fine");
		return result;
		
	}
	public boolean deleteAllegato(String owner, 
			OracleConnection con,
			String idAllegato )  throws Exception {
		boolean result = false;
		logger.debug(owner + " | AllegatiManager.deleteAllegato - inizio");
		
		String query = "DELETE FROM CHCI_OWN.T_CHCI_ALLEGATI" +
        		" WHERE ID_ALLEGATI =" + idAllegato ;
		logger.debug(owner + " | AllegatiManager.deleteAllegato - query: "+query);
		result = con.executeQuery(query);
		
		return result;
		
	}
	
	public String getNomeFile(String owner, 
							OracleConnection con,
							String codiceUdc, 
							String sistemaInformativo,
							String piattaforma, 
							String tipoAllegato) throws Exception {
		logger.debug(owner + " | AllegatiManager.getNomeFile - inizio");
		
		String nomeFile = "";
		String query = "SELECT FILE_NAME FROM CHCI_OWN.T_CHCI_ALLEGATI " +
					"	WHERE COD_UDC ='" + codiceUdc + "' " +
					"         AND " +
					"         SISTEMA_INFORMATIVO ='" + sistemaInformativo	+ "' " +
					"         AND " +
					"         COD_PIATTAFORMA ='" + piattaforma	+ "' " +
					"         AND " +
					"         TIPO_ALLEGATO ='" + tipoAllegato+  "'";
		
		logger.debug(owner + " | AllegatiManager.getNomeFile - query: "+query);
		ResultSet rs = con.GetQuery(query);
		if (rs != null) {
			if(rs.next()) {
				nomeFile =Utility.checkNull(rs.getString("FILE_NAME")).trim(); 
			}		
		}

		logger.debug(owner + " | AllegatiManager.getNomeFile - fine");

		return nomeFile;
	}
	
	public String getNomeFileDaId(String idAllegato, OracleConnection con) throws Exception {
		logger.debug(idAllegato + " | AllegatiManager.getNomeFileDaId - inizio");
		
		String nomeFile = "";
		String query = "SELECT FILE_NAME FROM CHCI_OWN.T_CHCI_ALLEGATI " +
			"	WHERE ID_ALLEGATI ='" + idAllegato + "'";
		
		logger.debug(idAllegato + " | AllegatiManager.getNomeFile - query: "+query);
		ResultSet rs = con.GetQuery(query);
		if (rs != null) {
			if(rs.next()) {
				nomeFile =Utility.checkNull(rs.getString("FILE_NAME")).trim(); 
			}		
		}
		
		logger.debug(idAllegato + " | AllegatiManager.getNomeFile - fine");
		
		return nomeFile;
	}
	
	public Vector getAll(String owner, OracleConnection con) throws Exception {
		logger.debug(owner + " | AllegatiManager.getAll - inizio");
		
		Vector vec = new Vector();
		String query = "SELECT * FROM CHCI_OWN.T_CHCI_ALLEGATI ORDER BY  ID_ALLEGATI";
		logger.debug(owner + " | AllegatiManager.getAll - query: "+query);
		ResultSet rs = con.GetQuery(query);
		if (rs != null) {
			while (rs.next()) {
				vec.add(new Allegati(Utility.checkNull(rs.getString("ID_ALLEGATI")).trim(), 
						Utility.checkNull(rs.getString("COD_PIATTAFORMA")).trim(),
						Utility.checkNull(rs.getString("COD_UDC")).trim(),
						Utility.checkNull(rs.getString("FILE_NAME")).trim(),
						Utility.checkNull(rs.getString("DESCRIZIONE")).trim(),
						Utility.checkNull(rs.getString("CONTENT_TYPE")).trim(),
						Utility.checkNull(rs.getString("TIPO_ALLEGATO")).trim(),
						Utility.checkNull(rs.getString("ALLEGATO ")).trim(),
						Utility.checkNull(rs.getString("DATA_INSERIMENTO")).trim(),
						Utility.checkNull(rs.getString("STATO")).trim(),
						Utility.checkNull(rs.getString("NUM_RICHIESTA ")).trim(),
						Utility.checkNull(rs.getString("NUM_DETTAGLIO")).trim()));
			}
		}

		logger.debug(owner + " | AllegatiManager.getAll - fine");

		return vec;
	}
	
	/**
	 * 
	 * @param oracleConnection
	 * @param owner
	 * @param codiceUdc
	 * @param piattaforma
	 * @param nomeFile
	 * @param tipoAllegato
	 * @return
	 * @throws Exception
	 */
	public Integer getIdAllegato(
			OracleConnection oracleConnection, 
			String owner, 
			String codiceUdc, 
			String piattaforma, 
			String nomeFile, 
			String tipoAllegato) 
	throws Exception{
		logger.debug(owner + " | AllegatiManager.getIdAllegato - inizio");
	    Integer idAllegato = new Integer(0);
    	String sql = "SELECT ID_ALLEGATI FROM CHCI_OWN.T_CHCI_ALLEGATI " +
    	      "WHERE FILE_NAME = '"+nomeFile
    	      +"' AND COD_PIATTAFORMA = '"+piattaforma+
    	      "' AND TIPO_ALLEGATO = '"+tipoAllegato+"'";
    	    	sql = sql +" AND COD_UDC = '"+codiceUdc+"'";
    	logger.debug(owner + " | downloadDoc.getFileDownload sql: " + sql);
    	ResultSet rst = oracleConnection.GetQuery(sql);

        if (rst.next()) {
        	idAllegato = new Integer(rst.getInt("ID_ALLEGATI"));
        }
        logger.debug(owner + " | AllegatiManager.getIdAllegato - fine");
	    return idAllegato;
	}
	
	/**
	 * 
	 * @param owner
	 * @param codiceUdc
	 * @param piattaforma
	 * @param tipoAllegato
	 * @return Allegati
	 * @throws Exception 
	 * @throws  
	 */
    public Allegati ricercaDatiAllegato(
    		OracleConnection oracleConnection,
    		String owner, 
    		String codiceUdc, 
    		String piattaforma, 
    		String tipoAllegato) 
    throws Exception {             
        
    	logger.debug(owner + " | AllegatiManager.ricercaDatiAllegato - inizio");
    	Allegati allegato = new Allegati();
        String SqlSelect = "SELECT ID_ALLEGATI, COD_PIATTAFORMA, COD_UDC, " +
        		"FILE_NAME, DESCRIZIONE, CONTENT_TYPE, TIPO_ALLEGATO, DATA_INSERIMENTO, " +
        		"STATO, NUM_RICHIESTA, NUM_DETTAGLIO FROM CHCI_OWN.T_CHCI_ALLEGATI " +
        		"WHERE COD_UDC='" + codiceUdc + "' AND COD_PIATTAFORMA='" + piattaforma + "'" 
        		+ " AND TIPO_ALLEGATO='" + tipoAllegato + "'";
        logger.debug("AllegatiManager.ricercaDatiAllegati " + SqlSelect);
        
        for(ResultSet rs = oracleConnection.GetQuery(SqlSelect); rs.next();) {
        	allegato = new Allegati(
        			Utility.checkNull(rs.getString("ID_ALLEGATI")),
					Utility.checkNull(rs.getString("COD_PIATTAFORMA")),
					Utility.checkNull(rs.getString("COD_UDC")),
					Utility.checkNull(rs.getString("FILE_NAME")),
					Utility.checkNull(rs.getString("DESCRIZIONE")),
					Utility.checkNull(rs.getString("CONTENT_TYPE")),
					Utility.checkNull(rs.getString("TIPO_ALLEGATO")),
					null,//il campo contiene un blob non necessario
					Utility.checkNull(rs.getString("DATA_INSERIMENTO")),
					Utility.checkNull(rs.getString("STATO")),
					Utility.checkNull(rs.getString("NUM_RICHIESTA")),
					Utility.checkNull(rs.getString("NUM_DETTAGLIO"))
        	);
        }
        logger.debug(owner + " | AllegatiManager.ricercaDatiAllegato - inizio");
        return allegato;
    }
}
