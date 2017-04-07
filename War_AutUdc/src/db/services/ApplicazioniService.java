package db.services;

import java.util.Hashtable;
import java.util.Vector;

import acronimi.Applicazioni;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import db.managers.ApplicazioniManager;

public class ApplicazioniService {
	private Logger logger = null;
	private static ApplicazioniManager aMgr = new ApplicazioniManager();
	
	public ApplicazioniService() {
		super();
		try {
			if (logger == null) {
				String pkgName = this.getClass().getPackage().getName();
				logger = LoggerFactory.getLogger(pkgName);
			}
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
    	logger.debug("Costruttore ApplicazioniService");
	}
	
	public static Vector loadApplicazioniPadre(String owner, String sistemaInformativo){
		return aMgr.loadApplicazioniPadre(owner,sistemaInformativo);
	}
	public static Hashtable allAcronimiHsh(String owner,String sistemaInformativo){
		return aMgr.allAcronimiHsh(owner,sistemaInformativo);
	}
	public static Vector allAcronimiVect(String owner, String sistemaInformativo){
		return aMgr.allAcronimiVect(owner,sistemaInformativo);
	}
	public static Vector acronimi4SSAPerUdc(String owner, String SSAPrincipale, String AltriSSA, String sistemaInformativo, String piattaforma, String codCalendario, String criterio){
		return aMgr.acronimi4SSA(owner, SSAPrincipale, AltriSSA,sistemaInformativo, piattaforma, codCalendario, criterio);
	}
	public static Vector acronimi4SSA(String owner, String SSAPrincipale, String sistemaInformativo){
		return aMgr.acronimi4SSA(owner, SSAPrincipale, sistemaInformativo);
	}
	public static Vector acronimifigliDi(String owner, String acronimoPadre, String sistemaInformativo){
		return aMgr.acronimifigliDi(owner, acronimoPadre, sistemaInformativo);
	}
	public static Applicazioni datiAcronimo(String owner, String acronimo,String sistemaInformativo){
		return aMgr.datiAcronimo(owner, acronimo, sistemaInformativo);
	}
	public static String zonaAcronimo(String owner, String acronimo, String sistemaInformativo){
		return aMgr.zonaAcronimo(owner, acronimo, sistemaInformativo);
	}	
	public static Vector leggiTuttiFigli(String owner, String acronimoPadre, String sistemaInformativo){
		return aMgr.leggiTuttiFigli(owner, acronimoPadre, sistemaInformativo);
	}
	public static String leggiAcronimoPadre(String owner, String acronimo, String sistemaInformativo){
		return aMgr.leggiAcronimoPadre(owner, acronimo, sistemaInformativo);
	}
	public static String ssaAcronimo(String owner, String acronimo, String sistemaInformativo){
		return aMgr.ssaAcronimo(owner, acronimo, sistemaInformativo);
	}
	public static String acronimoAPila(String owner, String acronimo,String sistemaInformativo){
		return aMgr.acronimoAPila(owner, acronimo,sistemaInformativo);
	}
	public static String acronimoAPilaDifferita(String owner, String acronimo,String sistemaInformativo){
		return aMgr.acronimoAPilaDifferita(owner, acronimo,sistemaInformativo);
	}
	public static Vector acronimoPerSIeFascia(String owner, String sistemaInformativo,String fasciaDistribuzione){
		return aMgr.acronimoPerSIeFascia(owner, sistemaInformativo,fasciaDistribuzione);
	}
	public static String  acronimoVobRTC(String owner, String acronimo, String sistemaInformativo){
		return aMgr.acronimoVobRTC(owner,acronimo, sistemaInformativo);
	}
	public static String  recuperaFlagAppplicazioneCritica(String owner, String acronimo, String sistemaInformativo){
		return aMgr.recuperaFlagAppplicazioneCritica(owner,acronimo, sistemaInformativo);
	}
	public static String  recuperaFlagEmergenzaSpeciale(String acronimo, String sistemaInformativo){
		return aMgr.recuperaFlagEmergenzaSpeciale(acronimo, sistemaInformativo);
		
	}
	
	
}
