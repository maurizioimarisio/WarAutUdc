package db.services;

import java.util.Hashtable;
import java.util.Vector;

import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import db.connection.OracleConnection;
import db.entities.SistemaInformativo;
import db.managers.SistemaInformativoManager;

public class SistemaInformativoService {
	
	private Logger logger = null;
	private static SistemaInformativoManager siMgr = new SistemaInformativoManager();
	
	public SistemaInformativoService() {
		super();
		try {
			if (logger == null) {
				String pkgName = this.getClass().getPackage().getName();
				logger = LoggerFactory.getLogger(pkgName);
			}
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
    	logger.debug("Costruttore SistemaInformativoService");
	}

	public static SistemaInformativo getSistemaDiDefault(String owner, OracleConnection con) throws Exception {
		return siMgr.sistemaDefault(owner, con);
	}
	public static Vector elencoSistemi(String owner, OracleConnection con) throws Exception {
		return siMgr.getElencoSistemi(owner, con);
	}
	public static Hashtable hshSistemiInformativi(String owner, OracleConnection con) throws Exception {
		return siMgr.getHshSistemi(owner, con);
	}
}
