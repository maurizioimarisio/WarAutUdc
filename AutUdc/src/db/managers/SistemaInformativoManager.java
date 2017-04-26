package db.managers;

import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Vector;

import Utility.Utility;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import db.connection.OracleConnection;
import db.entities.SistemaInformativo;

public class SistemaInformativoManager {
	private Logger logger = null;
	
	public SistemaInformativoManager(){
    	super();
		try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("Costruttore MotivoEmergenzaHostManager()");
	}
	
	public SistemaInformativo sistemaDefault(String owner, OracleConnection con) throws Exception {
		logger.debug(owner + " | SistemaInformativoManager.sistemaDefault - inizio");
		
		SistemaInformativo sys_info = new SistemaInformativo("01","DEFAULT","S");
		String query = "SELECT CODICE_SI, DESCRIZIONE_SI, DEFAULT_SI " +
				"       FROM CHCI_OWN.T_CHCI_SISTEMA_INFORMATIVO " +
				"       WHERE DEFAULT_SI='S' ";
		logger.debug(owner + " | SistemaInformativoManager.sistemaDefault - query: "+query);
		ResultSet rs = con.GetQuery(query);
		if (rs != null) {
			if (rs.next()) {
				sys_info = new SistemaInformativo(Utility.checkNull(rs.getString("CODICE_SI")).trim(),
						Utility.checkNull(rs.getString("DESCRIZIONE_SI")).trim(),
						Utility.checkNull(rs.getString("DEFAULT_SI")).trim());
			}else{
				logger.error(owner + " | SistemaInformativoManager.sistemaDefault - RESULT SET VUOTO ERRORE  VALORI DI DEFAULT");
			}	
		}else{
			logger.error(owner + " | SistemaInformativoManager.sistemaDefault - RESULT SET NULL ERRORE  VALORI DI DEFAULT");
		}

		logger.debug(owner + " | SistemaInformativoManager.sistemaDefault - fine");

		return sys_info;
	}
		
	public Vector getElencoSistemi(String owner, OracleConnection con) throws Exception {
		logger.debug(owner + " | SistemaInformativoManager.getElencoSistemi - inizio");
		Vector vec = new Vector();
		SistemaInformativo sys_info = new SistemaInformativo("01","DEFAULT","S");
		String query = "SELECT CODICE_SI, DESCRIZIONE_SI, DEFAULT_SI FROM CHCI_OWN.T_CHCI_SISTEMA_INFORMATIVO ORDER BY CODICE_SI ";
		logger.debug(owner + " | SistemaInformativoManager.getElencoSistemi - query: "+query);
		ResultSet rs = con.GetQuery(query);
		if (rs != null) {			
			while (rs.next()) {
				vec.add(new SistemaInformativo(Utility.checkNull(rs.getString("CODICE_SI")).trim(),
						Utility.checkNull(rs.getString("DESCRIZIONE_SI")).trim(),
						Utility.checkNull(rs.getString("DEFAULT_SI")).trim()));
			}
		}else{
			logger.error(owner + " | SistemaInformativoManager.getElencoSistemi - RESULT SET NULL ERRORE  VALORI DI DEFAULT");
			vec.add(sys_info);
		}

		logger.debug(owner + " | SistemaInformativoManager.getElencoSistemi - fine");

		return vec;
	}
	
	public Hashtable getHshSistemi(String owner, OracleConnection con) throws Exception {
		logger.debug(owner + " | SistemaInformativoManager.getHshSistemi - inizio");
		Hashtable hsh = new Hashtable();
		SistemaInformativo sys_info = new SistemaInformativo("01","DEFAULT","S");
		String query = "SELECT CODICE_SI, DESCRIZIONE_SI, DEFAULT_SI FROM CHCI_OWN.T_CHCI_SISTEMA_INFORMATIVO ORDER BY CODICE_SI ";
		logger.debug(owner + " | SistemaInformativoManager.getElencoSistemi - query: "+query);
		ResultSet rs = con.GetQuery(query);
		if (rs != null) {			
			while (rs.next()) {
				hsh.put(Utility.checkNull(rs.getString("CODICE_SI")).trim(),
						new SistemaInformativo(Utility.checkNull(rs.getString("CODICE_SI")).trim(),
						Utility.checkNull(rs.getString("DESCRIZIONE_SI")).trim(),
						Utility.checkNull(rs.getString("DEFAULT_SI")).trim()));
			}
		}else{
			logger.error(owner + " | SistemaInformativoManager.getElencoSistemi - RESULT SET NULL ERRORE  VALORI DI DEFAULT");
			hsh.put("01",sys_info);
		}

		logger.debug(owner + " | SistemaInformativoManager.getElencoSistemi - fine");

		return hsh;
	}
}
