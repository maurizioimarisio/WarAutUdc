package db.services;

import java.util.Vector;

import autudc.logger.Logger;
import autudc.logger.LoggerFactory;


import db.managers.FasciaDistrManager;

public class FasciaDistrService {
	
	private Logger logger = null;
	private static FasciaDistrManager fdMgr = new FasciaDistrManager();
	
	public FasciaDistrService() {
		super();
		try {
			if (logger == null) {
				String pkgName = this.getClass().getPackage().getName();
				logger = LoggerFactory.getLogger(pkgName);
			}
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
    	logger.debug("Costruttore FasciaDistrService");
	}
	
	public static String getDescrizioneFasciaDistr(String owner, String sistemaInformativo, String codiceFascia){
		return fdMgr.getDescrizioneFasciaDistr(owner, sistemaInformativo, codiceFascia);
	}
	
	public static Vector fascieDistrXSI(String owner, String sistemaInformativo) {
		return fdMgr.getFascieDistrXSI(owner, sistemaInformativo);
	}
	
	public static Vector allFascie(String owner) {
		return fdMgr.getAllFascie(owner);
	}

}
