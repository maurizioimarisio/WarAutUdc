package db.services;

import java.util.Vector;

import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import db.connection.OracleConnection;
import db.entities.Allegati;
import db.managers.AllegatiManager;


public class AllegatiService {
	private Logger logger = null;
	private static AllegatiManager aMgr = new AllegatiManager();
	
	
	
	public AllegatiService() {
		super();
		try {
			if (logger == null) {
				String pkgName = this.getClass().getPackage().getName();
				logger = LoggerFactory.getLogger(pkgName);
			}
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
    	logger.debug("Costruttore AllegatiService");
	}

	public static Vector getAll(String owner, OracleConnection con) throws Exception {
		return aMgr.getAll(owner, con);
	}

	public static String leggiNomeFile(String owner, OracleConnection con,
					String codiceUdc, 
					String sistemaInformativo,
					String piattaforma, 
					String tipoAllegato)  throws Exception {
		return aMgr.getNomeFile(owner, con, codiceUdc,sistemaInformativo, piattaforma, tipoAllegato);
	}
	
	public static String leggiNomeFileDaId(String idAllegato, OracleConnection con)  throws Exception {
		return aMgr.getNomeFileDaId(idAllegato, con);
	}
	
	public static boolean updateDB(String owner, OracleConnection con,
			String sistemaInformativo,
			String codiceUdcOld, 
			String codiceUdcNew,
			String piattaforma,
			String stato)  throws Exception {		
		return aMgr.updateAllegato(owner, con, sistemaInformativo, codiceUdcOld, codiceUdcNew, piattaforma, stato);
	}
	public static boolean deleteAllegato(String owner, OracleConnection con,
			String nomeDocumento, 
			String codiceUdc,
			String sistemaInformativo,
			String piattaforma,
			String tipoAllegato)  throws Exception {		
		return aMgr.deleteAllegato(owner, con, nomeDocumento, codiceUdc, sistemaInformativo, piattaforma, tipoAllegato);
	}
	
	public static boolean deleteAllegato(String owner, OracleConnection con,
			String idAllegato)  throws Exception {		
		return aMgr.deleteAllegato(owner, con, idAllegato);
	}
	
	public static boolean esisteAllegato(String owner, OracleConnection con,
			String codiceUdc,String sistemaInformativo)  throws Exception {		
		return aMgr.esisteAllegato(owner, con, codiceUdc,sistemaInformativo);
	}
	public static boolean esisteAllegato(String owner, OracleConnection con,
			String codiceUdc, 
			String nomeFile,
			String piattaforma,
			String tipoAllegato)  throws Exception {		
		return aMgr.esisteAllegato(owner, con, codiceUdc, nomeFile, piattaforma, tipoAllegato);
	}
	
	public static boolean esisteAllegatoPianoAttivazione(String owner, OracleConnection con,
			String codiceUdc, String tipoAllegato, String sistemaInformativo)  throws Exception {		
		return aMgr.esisteAllegatoPianoAttivazione(owner, con, codiceUdc, tipoAllegato,sistemaInformativo);
	}
	
	public static Integer getIdAllegato(
			OracleConnection oracleConnection, 
			String owner, 
			String codiceUdc, 
			String piattaforma, 
			String nomeFile, 
			String tipoAllegato) 
	throws Exception{
		return aMgr.getIdAllegato(oracleConnection, owner, codiceUdc, piattaforma, nomeFile, tipoAllegato);
	}
	
	public static Allegati ricercaDatiAllegato(
    		OracleConnection oracleConnection,
    		String owner, 
    		String codiceUdc, 
    		String piattaforma, 
    		String tipoAllegato) 
    throws Exception {
		return aMgr.ricercaDatiAllegato(oracleConnection, owner, codiceUdc, piattaforma, tipoAllegato);
	}
	
}
