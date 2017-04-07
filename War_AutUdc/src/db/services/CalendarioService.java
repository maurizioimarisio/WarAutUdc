package db.services;

import acronimi.TipoCalendarioPiattaforma;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import db.managers.CalendarioManager;

public class CalendarioService {
	private Logger logger = null;
	private static CalendarioManager aMgr = new CalendarioManager();
	
	public CalendarioService() {
		super();
		try {
			if (logger == null) {
				String pkgName = this.getClass().getPackage().getName();
				logger = LoggerFactory.getLogger(pkgName);
			}
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
    	logger.debug("Costruttore CalendarioService");
	}
		
	public static TipoCalendarioPiattaforma getTipoCalendarioPiattaforma(
			String owner,
			String sistemaInformativo,
			String piattaforma,
			String calendario
		){
		return aMgr.getTipoCalendarioPiattaforma(owner, sistemaInformativo, piattaforma, calendario);
	}
		
	public static String getCodiceCalendario(
			String owner, 
			String codiceSistemaInformativo, 
			String codicePiattaforma, 
			String codCalendario,
			String criterio,
			String param
	){
		return aMgr.getCodiceCalendario(owner, codiceSistemaInformativo, codicePiattaforma, codCalendario, criterio, param);
	}
	
	public static String getDescrCodiceCalendario(
			String owner, 
			String codiceSistemaInformativo, 
			String codicePiattaforma, 
			String codCalendario
	){
		return aMgr.getDescrCodiceCalendario(owner, codiceSistemaInformativo, codicePiattaforma, codCalendario);
	}
	
	public static String getCriterio(
			String owner, 
			String codiceSistemaInformativo, 
			String codicePiattaforma){
		return aMgr.getCriterio(owner, codiceSistemaInformativo, codicePiattaforma);
	}
	
	public static String getGiornoStandard(
			String owner,
			String codCalendario
		){
		return aMgr.getGiornoStandard(owner, codCalendario);
	}
	
}
