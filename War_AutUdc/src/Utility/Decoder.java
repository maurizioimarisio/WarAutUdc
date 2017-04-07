package Utility;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;

import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import db.connection.OracleConnection;
import db.entities.Allegati;
import db.entities.MotivoEmergenzaHost;
import db.entities.SistemaInformativo;
import db.services.AllegatiService;
import db.services.CalendarioService;
import db.services.MotivoEmergenzaHostService;
import db.services.SistemaInformativoService;

public class Decoder {
	
	private static Logger logger = null;
	
	public Decoder(){
		super();
		try {
			if (logger == null) {
				String pkgName = this.getClass().getPackage().getName();
				logger = LoggerFactory.getLogger(pkgName);
			}
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
    	logger.debug("Costruttore Decoder");
	}

	
//	public static Vector getPiattaforme(String matrProf, String codSistemaInformativo){
//		return PiattaformaSistemaInformativoService.vettPiattaformeUdcPerSI(matrProf, codSistemaInformativo);
//	}
	
//	public static String getPiattaformaDescr(String matrProf, String codSistemaInformativo, String codPiattaforma){
//		
//		String piattaformaDescr = codPiattaforma;
//		
//		try {
//			Vector vettPiattaforme = PiattaformaSistemaInformativoService.vettPiattaformeUdcPerSI(matrProf, codSistemaInformativo);
//			elencoPiattaforme elencoPiattaforme=new elencoPiattaforme();
//			for(int i=0; i<vettPiattaforme.size();i++){
//				elencoPiattaforme = (elencoPiattaforme)vettPiattaforme.get(i);
//				if (elencoPiattaforme.cod_piattaforma.equals(codPiattaforma)){
//					piattaformaDescr = elencoPiattaforme.descr_piattaforma;
//				}
//			}
//		}catch(Exception e) {	
//			logger.debug("Non è stata trovata la descrizione per il codice piattaforma >>>"+codPiattaforma+"<<<");
//		}
//		return piattaformaDescr;
//	}
	
	
	public static Vector getSistemiInformativi(String utente){
		Vector elencoSistemiInformativi = new Vector();
		OracleConnection oracleConnection = new OracleConnection(utente);
		oracleConnection.initConn();
		try {
			elencoSistemiInformativi = SistemaInformativoService.elencoSistemi(utente, oracleConnection);
		}catch(Exception e) {	
			logger.debug("Non è stato possibile recuperare i sistemi innformativi.");
		}finally {
			oracleConnection.close();
		}
		return elencoSistemiInformativi;
	}
	
	public static String getSistemaInformativoDescr(String utente, String codSistemaInformativo){
		String sistemiInformativiDescr = codSistemaInformativo;
		Hashtable hshSysInfo = new Hashtable();
		OracleConnection oracleConnection = new OracleConnection(utente);
		oracleConnection.initConn();
		try {
			hshSysInfo = SistemaInformativoService.hshSistemiInformativi(utente,oracleConnection);
			if(hshSysInfo.containsKey(codSistemaInformativo)){
				SistemaInformativo sistemaInformativo = (SistemaInformativo)hshSysInfo.get(codSistemaInformativo);
				sistemiInformativiDescr=sistemaInformativo.getDescrizione();
			}
		}catch(Exception e) {	
			logger.debug("Non è stato possibile recuperare il sistema innformativo per il codice: >>>"+codSistemaInformativo+"<<<");
		}finally {
			oracleConnection.close();
		}
		return sistemiInformativiDescr;
	}
	
	public static String getNomeAllegato(String utente, String idAllegato) {
		
		String nomeAllegato = "";
		OracleConnection oracleConnection = new OracleConnection(utente);
		oracleConnection.initConn();
		try {
			nomeAllegato = AllegatiService.leggiNomeFileDaId(idAllegato, oracleConnection);
			
		}catch(Exception e) {
			logger.debug("Non è stata trovata la descrizione per l'id allegato >>>"+idAllegato+"<<<");
		}finally {
			oracleConnection.close();
		}
		return nomeAllegato;
	}
	
	public static LinkedList getMotiviEmergenzaHost(String utente, String sistemaInformativo){
		
		LinkedList motiviEmergenzaList = new LinkedList();
		
		OracleConnection oracleConnection = new OracleConnection(utente);
		oracleConnection.initConn();
		try {
			motiviEmergenzaList = MotivoEmergenzaHostService.getMotiviEmergenzaHost(oracleConnection, utente, sistemaInformativo);
			
		}catch(Exception e) {
			logger.debug("Motivi Emergenza Host non restituito per sistemaInformativo: "+sistemaInformativo);
		}finally {
			oracleConnection.close();
		}
		return motiviEmergenzaList;
	}
	
	public static MotivoEmergenzaHost getMotivoEmergenzaHost(String utente, String sistemaInformativo, String codiceEmergenza){
		
		MotivoEmergenzaHost motivoEmergenzaHost = new MotivoEmergenzaHost();
		
		OracleConnection oracleConnection = new OracleConnection(utente);
		oracleConnection.initConn();
		try {
			motivoEmergenzaHost = MotivoEmergenzaHostService.getMotivoEmergenzaHost(oracleConnection, utente, sistemaInformativo, codiceEmergenza);
			
		}catch(Exception e) {
			logger.debug("Motivo Emergenza Host non restituito per sistemaInformativo: "+sistemaInformativo+" codiceEmergenza: "+codiceEmergenza);
		}finally {
			oracleConnection.close();
		}
		return motivoEmergenzaHost;
	}
	
	public static String getDescrizioneMotivoEmergenzaHost(String utente, String sistemaInformativo, String codiceEmergenza){
		MotivoEmergenzaHost motivoEmergenzaHost = getMotivoEmergenzaHost(utente, sistemaInformativo, codiceEmergenza);
		return motivoEmergenzaHost.getDescrMotivoEmergenza();
	}
	
	public static String getDescrCodiceCalendario(
			String utente, 
			String codiceSistemaInformativo, 
			String codicePiattaforma, 
			String codCalendario
		){
		return CalendarioService.getDescrCodiceCalendario(utente, codiceSistemaInformativo, codicePiattaforma, codCalendario);
	}
	
	public static String getCriterio(
			String utente, 
			String codiceSistemaInformativo, 
			String codicePiattaforma){
		return CalendarioService.getCriterio(utente, codiceSistemaInformativo, codicePiattaforma);
	}
	
	/**
	 * 
	 * @param utente
	 * @param codiceUdc
	 * @param piattaforma
	 * @param file
	 * @param tipoAllegato 01 piano di attivazione, 02 piano di rollback
	 * @return
	 */
	public static Integer getIdAllegato(String utente, String codiceUdc, String piattaforma, String nomeFile, String tipoAllegato) {

		Integer idAllegato = new Integer(0);
		OracleConnection oracleConnection = new OracleConnection(utente);
		oracleConnection.initConn();
		try {
			idAllegato = AllegatiService.getIdAllegato(oracleConnection, utente, codiceUdc, piattaforma, nomeFile, tipoAllegato);
			
		}catch(Exception e) {
			logger.error("Non è stato possibile recuperare l'idallegato per udc:"+codiceUdc+" piattaforma:"+piattaforma+" nomeFile:"+nomeFile+" tipoAllegato:"+tipoAllegato + "\n" +e.getMessage());  
	    	
		}finally {
			oracleConnection.close();
		}
	    return idAllegato;
	}
	
	/**
	 * 
	 * @param utente
	 * @param codiceUdc
	 * @param piattaforma
	 * @param tipoAllegato
	 * @return Allegati
	 */
    public static Allegati ricercaDatiAllegato(String utente, String codiceUdc, String piattaforma, String tipoAllegato) {             
        Allegati allegato = new Allegati(piattaforma,codiceUdc,tipoAllegato);
        OracleConnection oracleConnection = new OracleConnection(utente);
		oracleConnection.initConn();
		try {
			allegato = AllegatiService.ricercaDatiAllegato(oracleConnection, utente, codiceUdc, piattaforma, tipoAllegato);
			if ("".equals(allegato.getIdAllegati())){
				allegato = new Allegati(piattaforma,codiceUdc,tipoAllegato);
			}
		}catch(Exception e) {
			logger.error("Non è stato possibile recuperare l'idallegato per udc:"+codiceUdc+" piattaforma:"+piattaforma+" tipoAllegato:"+tipoAllegato + "\n" +e.getMessage());  
	    	
		}finally {
			oracleConnection.close();
		}  
        return allegato;
    }
		
}