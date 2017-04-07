package db.services;

import java.util.LinkedList;

import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import db.connection.OracleConnection;
import db.entities.MotivoEmergenzaHost;
import db.managers.MotivoEmergenzaHostManager;

public class MotivoEmergenzaHostService {
	
	private Logger logger = null;
	private static MotivoEmergenzaHostManager mehMgr = new MotivoEmergenzaHostManager();
	
	
	public MotivoEmergenzaHostService() {
		super();
		try {
			if (logger == null) {
				String pkgName = this.getClass().getPackage().getName();
				logger = LoggerFactory.getLogger(pkgName);
			}
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
    	logger.debug("Costruttore MotivoEmergenzaHostService");
	}

	public static LinkedList getMotiviEmergenzaHost(
				OracleConnection oracleConnection,
				String owner,
	    		String sistemaInformativo
	    	){
		 return mehMgr.getMotiviEmergenzaHost(oracleConnection, owner, sistemaInformativo);
	 }
	
	 public static MotivoEmergenzaHost getMotivoEmergenzaHost(
	    		OracleConnection oracleConnection,
	    		String owner,
	    		String sistemaInformativo,
	    		String codiceEmergenza
	    	){
		 return mehMgr.getMotivoEmergenzaHost(oracleConnection, owner, sistemaInformativo, codiceEmergenza);
	 }
	
	
}
