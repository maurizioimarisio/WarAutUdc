package autudc.chiamaBS.ude;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Utility.BSUtility;
import Utility.Decoder;
import Utility.Utility;
import acronimi.Applicazioni;
import autudc.chiamaBS.ErroreOutputBS;
import autudc.chiamaBS.ude.dto.Abilitazioni;
import autudc.chiamaBS.ude.dto.DateDiDistribuzione;
import autudc.chiamaBS.ude.dto.DatiContenutoUdc;
import autudc.chiamaBS.ude.dto.DatiDeroga;
import autudc.chiamaBS.ude.dto.DatiEmergenza;
import autudc.chiamaBS.ude.dto.DatiImpatti;
import autudc.chiamaBS.ude.dto.DatiManifestoDiCollaudo;
import autudc.chiamaBS.ude.dto.DatiPesatura;
import autudc.chiamaBS.ude.dto.DatiPianoAllegato;
import autudc.chiamaBS.ude.dto.DatiUdc;
import autudc.chiamaBS.ude.dto.DatiUtente;
import autudc.chiamaBS.ude.dto.PrerequisitiUdc;
import autudc.chiamaBS.ude.dto.Udc;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import autudc.swa.ProfiloSWA;

import com.dsi.business.SSA_YU.integration.jdo.P_YUZ0S17.C_YUZ0S17;
import com.dsi.business.SSA_YU.integration.jdo.P_YUZ0S17.INHEADER;
import com.dsi.business.SSA_YU.integration.jdo.P_YUZ0S17.INPBST;
import com.dsi.business.SSA_YU.integration.jdo.P_YUZ0S17.OUTBST;

import db.connection.OracleConnection;
import db.entities.Allegati;
import db.services.AllegatiService;
import db.services.ApplicazioniService;
import db.services.FasciaDistrService;

/**
 *
 *@author U0G8898
 */
public class ChiamaYUZ0 {

    private Logger logger = null;
    private static final String ID_SERVICE_YUZ0 = "YUZ0INQUDC";
    private ErroreOutputBS erroreOutputBS = new ErroreOutputBS();
    private BSUtility bsUtility = new BSUtility();
    
    public ChiamaYUZ0() {
        try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("Costruttore ChiamaYUZ0....");
    }
    
    public void chiamaBS(
    		HttpSession sessione,
    		ProfiloSWA userswa,
    		String sistemaInformativo,
    		String id
    	){
    	
    	initYUZ0(sessione, userswa, sistemaInformativo, id);
        logger.info("ChiamaYUZ0() - erroreOutputBS: " + erroreOutputBS.isEsito() + " " + erroreOutputBS.getMessaggioErrore());
        sessione.setAttribute("erroreOutputBS",erroreOutputBS);
    }
    
    
    public void chiamaBSPerUAT(
    		HttpSession sessione,
    		HttpServletRequest request,
    		ProfiloSWA userswa,
    		String sistemaInformativo,
    		String id,
    		String inizioFine
    	){
    	
    	initYUZ0DaUAT(sessione, request,userswa, sistemaInformativo, id, inizioFine);
        sessione.setAttribute("erroreOutputBS",erroreOutputBS);
    }

    
private void initYUZ0DaUAT(HttpSession sessione, HttpServletRequest request ,ProfiloSWA userswa, String sistemaInformativo, String id, String inizioFine) {
		
		boolean isSuperataInvoke = true;
        
		C_YUZ0S17 yuz0 = new C_YUZ0S17();
		String userAg = (String) sessione.getAttribute("userAg");
        

	
			yuz0.INHEADER = new INHEADER[1];
			yuz0.INHEADER[0] = new INHEADER();
			yuz0.INHEADER[0].RETCODE = 0;
			yuz0.INHEADER[0].CODICE_USERID = userswa.getUserID().trim();
			yuz0.INHEADER[0].ID_SERVIZIO = ID_SERVICE_YUZ0;
			yuz0.INHEADER[0].CODICE_TIPO_CANALE = "52";
			yuz0.INHEADER[0].CODICE_SOCIETA = "01";
			yuz0.INHEADER[0].CODICE_SPORTELLO = "00700";
			yuz0.INHEADER[0].CODICE_UO_RICH = "00700";
			yuz0.INHEADER[0].COD_RICH_CANALE = "000000000000000000000000000";
			yuz0.INHEADER[0].DATA_CONT = "24052006";
			yuz0.INHEADER[0].LUNGHEZZA_MSG = 44;
			yuz0.INHEADER[0].IND_MQ_SINCRONO = "S";
			yuz0.INPBST = new INPBST[1];
			yuz0.INPBST[0] = new INPBST();
			yuz0.INPBST[0].SIST_INFORMATIVO=sistemaInformativo;
		     yuz0.INPBST[0].PROFILO_UTENTE =  Utility.isBlackBerry(request) ? "XXXXXB" : "XXXXXC";
		    
			yuz0.INPBST[0].IND_UDC_STORICA = " ";
			yuz0.INPBST[0].IND_USA_ID_EME = inizioFine;
			yuz0.INPBST[0].ID_EMERGENZA_I = Utility.FillaCaratteri(id, "0", "S", 11);
			bsUtility.writeLogBS(yuz0.INHEADER, ID_SERVICE_YUZ0, false);
			bsUtility.writeLogBS(yuz0.INPBST, ID_SERVICE_YUZ0, true);
		
        try {
			yuz0.invoke();
		} catch (Exception e) {
        	e.printStackTrace();
			isSuperataInvoke = false;
            logger.error("ChiamaYUZ0().initYUZ0DaUAT - Errore chiamata connettore " + e.toString());
		}
		
		gestioneRispostaHost(sessione, yuz0, userswa.getUserID(), isSuperataInvoke);
	}

    
	private void initYUZ0(HttpSession sessione, ProfiloSWA userswa, String sistemaInformativo, String id) {
		
		boolean isSuperataInvoke = true;
        
		C_YUZ0S17 yuz0 = new C_YUZ0S17();

		popolaTagInHeader(yuz0, userswa.getUserID());

		popolaTagInpbst(yuz0, sistemaInformativo, id);

        try {
			yuz0.invoke();
		} catch (Exception e) {
        	e.printStackTrace();
			isSuperataInvoke = false;
            logger.error("ChiamaYUZ0() - Errore chiamata connettore " + e.toString());
		}
		
		gestioneRispostaHost(sessione, yuz0, userswa.getUserID(), isSuperataInvoke);
	}

	private void gestioneRispostaHost(
			HttpSession sessione, 
			C_YUZ0S17 yuz0,
			String utente, 
			boolean isSuperataInvoke) {
		
		String msgErrore = "";
		if (isSuperataInvoke) {
			
			boolean isOperazioneEseguita = false;
			boolean ciSonoDatiDiOutput = false;
			int OUTHEADER_RETCODE = yuz0.OUTHEADER[0].RETCODE;
            logger.debug("gestioneRispostaHost() - RetCode BS: " + OUTHEADER_RETCODE);
			switch (OUTHEADER_RETCODE) {
			case 0:
				isOperazioneEseguita = true;
				if (yuz0.OUTBST !=null && yuz0.OUTBST[0] != null){
					ciSonoDatiDiOutput = true;
				}
                 break;
			case 12:
				msgErrore = yuz0.OUTSEG[0].COD_SEGNALAZIONE+" "+yuz0.OUTSEG[0].TXT_SEGNALAZIONE;
				break;
			case 16:
			case 20:
				msgErrore = yuz0.OUTESI[0].ESITO;
				break;
			default:
				msgErrore = " gestioneRispostaHost (" + ID_SERVICE_YUZ0 + ").OUTHEADER_RETCODE  non gestito:" + OUTHEADER_RETCODE;
			}

			if (isOperazioneEseguita) {
				erroreOutputBS = new ErroreOutputBS(true, "Operazione Eseguita");
				//setto l'oggetto per l'output della pagina
				if (yuz0.OUTBST !=null && yuz0.OUTBST[0] != null){
					if (ciSonoDatiDiOutput) {
						logger.debug("YUZ0 "+yuz0.toString());
						bsUtility.writeLogBS(yuz0.OUTBST, ID_SERVICE_YUZ0, true);
						caricaDaOutbst(sessione, yuz0.OUTBST[0], utente);
					}
				}
			}else{
				erroreOutputBS = new ErroreOutputBS(false, msgErrore);
				logger.error("YUZ0 "+yuz0.toString());
			}

		}else{
			// non superata invocke
			erroreOutputBS = new ErroreOutputBS(false, "Errore invoke servizio. "+msgErrore);
		}
	    logger.debug("gestioneRispostaHost() - Messaggio da BS: " + erroreOutputBS.getMessaggioErrore());
	}
	
	private void popolaTagInHeader(
			C_YUZ0S17 yuz0, 
			String utente) {
		yuz0.INHEADER = new INHEADER[1];
		yuz0.INHEADER[0] = new INHEADER();
		yuz0.INHEADER[0].RETCODE = 0;
		yuz0.INHEADER[0].CODICE_USERID = utente.trim();
		yuz0.INHEADER[0].ID_SERVIZIO = ID_SERVICE_YUZ0;
		yuz0.INHEADER[0].CODICE_TIPO_CANALE = "52";
		yuz0.INHEADER[0].CODICE_SOCIETA = "01";
		yuz0.INHEADER[0].CODICE_SPORTELLO = "00700";
		yuz0.INHEADER[0].CODICE_UO_RICH = "00700";
		yuz0.INHEADER[0].COD_RICH_CANALE = "000000000000000000000000000";
		yuz0.INHEADER[0].DATA_CONT = "24052006";
		yuz0.INHEADER[0].LUNGHEZZA_MSG = 44;
		yuz0.INHEADER[0].IND_MQ_SINCRONO = "S";
		bsUtility.writeLogBS(yuz0.INHEADER, ID_SERVICE_YUZ0, false);
	}

	private void popolaTagInpbst(
			C_YUZ0S17 yuz0, 
			String sistemaInformativo, 
			String id
	) {

		yuz0.INPBST = new INPBST[1];
		yuz0.INPBST[0] = new INPBST();
		yuz0.INPBST[0].SIST_INFORMATIVO=sistemaInformativo;
		yuz0.INPBST[0].PROFILO_UTENTE = "XXXXXX";
		yuz0.INPBST[0].IND_UDC_STORICA = " ";
		yuz0.INPBST[0].IND_USA_ID_EME = "S";
		yuz0.INPBST[0].ID_EMERGENZA_I = Utility.FillaCaratteri(id, "0", "S", 11);
		bsUtility.writeLogBS(yuz0.INPBST, ID_SERVICE_YUZ0, true);
	}

	private void caricaDaOutbst(
			HttpSession sessione, 
			OUTBST outbst,
			String utente
		) {
		
		sessione.removeAttribute("udc");
		
		OracleConnection con = null;
		String ssaALtri = "";
		String nomeFile = "";
		StringBuilder applicazioniDafare = new StringBuilder();
		String distribuzioneDifferita = "";
		String dataDifferita = "";
		
		try {
			con = new OracleConnection(utente);
			con.initConn();
			
			ssaALtri = getSsaAltri(outbst);
			
			String elenco_acronimiHost=outbst.ELENCO_ACRONIMI;
			for (int i=0; i<elenco_acronimiHost.length(); i=i+12){
				String acronimo=elenco_acronimiHost.substring(i,i+12);
				acronimo=acronimo.trim();
				if(!acronimo.equals("")){
					applicazioniDafare.append(" ");
					applicazioniDafare.append(acronimo);			
					Applicazioni datiAcronimo= ApplicazioniService.datiAcronimo(utente, acronimo, outbst.SIST_INFORMATIVO);
					distribuzioneDifferita=Utility.checkNull(datiAcronimo.getDistribuzione_differita());
					dataDifferita=datiAcronimo.getData_differita();
				}
			}
			
			nomeFile = AllegatiService.leggiNomeFile(
					utente, con, 
					outbst.COD_UDC,
					outbst.SIST_INFORMATIVO,
					outbst.COD_TIPO_PIATT, "P"
				
					);
			
		} catch (Exception e) {
			logger.error(utente+" caricaDaOutbst Exception = "+e.getMessage());
			e.printStackTrace();
			sessione.setAttribute("err", "Errore in caricaDaOutbst: "+e.getMessage());
		}
		finally {
			if(con != null) con.close();
		}
		
		DatiUdc datiUDc = new DatiUdc(
				Utility.checkNull(outbst.SIST_INFORMATIVO),
			
				Utility.checkNull(outbst.COD_UDC),
				Utility.checkNull(outbst.COD_PROGETTO),
				Utility.checkNull(outbst.COD_ATTIVITA),
				Utility.checkNull(outbst.COD_INCIDENTE),
				Utility.checkNull(outbst.COD_TIPO_UDC),
				Utility.checkNull(outbst.DESCR_TIPO_UDC),
				Utility.checkNull(outbst.COD_STATO_UDC),
				Utility.checkNull(outbst.DESCR_STATO_UDC),
				Utility.checkNull(outbst.COD_STATO_TECN_UDC), 
				Utility.checkNull(outbst.DESCR_UDC).replaceAll("'", " "),
				Utility.checkNull(outbst.COD_TIPO_PIATT),
				Utility.checkNull(outbst.COD_ZONA_UDC),
				Utility.checkNull(outbst.COD_FAMIGLIA.trim()),
				Utility.checkNull(outbst.DESCR_FAMIGLIA),
				Utility.checkNull(outbst.NOME_RELEASE),
				Utility.checkNull(outbst.OBBIETTIVI_RELEASE),
				Utility.checkNull(outbst.TIMEST_UPDATE),
				getTimeStampStorico(Utility.checkNull(outbst.TIMEST_STORICO)),
				nomeFile,
				Utility.checkNull(outbst.MODIFICA_COLL),
				Utility.checkNull(outbst.MODIFICA_PROD),
				Utility.checkNull(outbst.MODIFICA_IT),
				Utility.checkNull(outbst.MODIFICA_ALTRI),
				Utility.checkNull(outbst.MODIFICA_ADDESTR),
				"N".equals(Utility.checkNull(outbst.IND_UDC_BLOCCATA)) ? "S" : "N",
				Utility.checkNull(outbst.IND_PROC_RIP),
				Utility.checkNull(outbst.IND_RECUP_DATI),
				Utility.checkNull(outbst.CODICE_CALENDARIO),
				Decoder.getCriterio(utente, outbst.SIST_INFORMATIVO, outbst.COD_TIPO_PIATT)
		);
		datiUDc.setMinTimeSTCutOff(outbst.MAX_TIMEST_CUTOFF);
		String timeStamp =outbst.MAX_TIMEST_CUTOFF;
		String flagCutOff = "N";

		if (timeStamp != null && timeStamp.length() >=16 && timeStamp.indexOf("-")> 0)
	    {	
			long now = System.currentTimeMillis();
			timeStamp = timeStamp.substring(0, 16);
			logger.debug("Deroghe.processRequest.. valore di timeStamp:"+timeStamp);
			try
			{
 	   			String[] st = timeStamp.split("-");
 	   			String anno = st[0];
 	   			String mese = st[1];
 	   			String giorno = st[2];
 	   			String oraMinuto = st[3];	            	    	            	    
 	   			String ora = oraMinuto.substring(0,2);
 	   			String minuti = oraMinuto.substring(3);
 	   			logger.debug("Deroghe.processRequest.. valore parsificato: anno: "+anno+" mese: " +mese+ " giorno: "+giorno+ " ora: "+ ora+ " minuti: "+ minuti);
 	   			GregorianCalendar gcMem = new GregorianCalendar(Integer.parseInt(anno),Integer.parseInt(mese) - 1, Integer.parseInt(giorno), Integer.parseInt(ora), Integer.parseInt(minuti));
 	   			if (gcMem.getTimeInMillis() < now)
 	   				flagCutOff = "S";
			}	
 	   		catch(Exception e)
 	   		{
 	   			flagCutOff= "N";
 	   		}
	    }
		
 	    sessione.setAttribute("flagCutOff", flagCutOff);
		
		
		DatiPianoAllegato pianoDiAttivazione = getPianoAllegato(utente,Utility.checkNull(outbst.COD_UDC),Utility.checkNull(outbst.COD_TIPO_PIATT),"01");
		DatiPianoAllegato pianoDiRollBack = getPianoAllegato(utente,Utility.checkNull(outbst.COD_UDC),Utility.checkNull(outbst.COD_TIPO_PIATT),"02");
		
		DateDiDistribuzione dateDiDistribuzione = new DateDiDistribuzione(
				Utility.decodificaDataDaHost(outbst.DATA_CREAZIONE),
				Utility.decodificaDataDaHost(outbst.DATA_ST),
				Utility.decodificaDataDaHost(outbst.DATA_IT),
				Utility.decodificaDataDaHost(outbst.DATA_DISTR_ADDESTR),
				Utility.decodificaDataDaHost(outbst.DATA_PP),
				Utility.decodificaDataDaHost(outbst.DATA_PP_PILA1),
				distribuzioneDifferita,
				dataDifferita,
				Utility.checkNull(outbst.COD_DATA_VAR_PP),
				Utility.checkNull(outbst.DESCR_VAR_DATA_PP), 
				Utility.decodificaDataDaHost(outbst.GG_INIZIO_TEST),
				Utility.decodificaDataDaHost(outbst.DATA_ESITO_DISTR),
				Utility.decodificaOraDaHost(outbst.ORA_LIMITE_ESITO),
				Utility.decodificaDataDaHost(outbst.DATA_LIM_PP),
				Utility.decodificaDataDaHost(outbst.DATA_PRIMO_COLL),
				Utility.decodificaDataDaHost(outbst.DATA_PRIMO_IT)
		);
		
		DatiContenutoUdc datiContenutoUdc = new DatiContenutoUdc(
				Utility.checkNull(outbst.SSA_PRINC),
				ssaALtri, 
				Utility.checkNull(outbst.ELENCO_ACRONIMI),
				applicazioniDafare.toString()
		);
		
		DatiImpatti datiImpatti = new DatiImpatti(
				Utility.checkNull(outbst.COD_RISCHIO),
				Utility.checkNull(outbst.MOD_FUNZIONALI).replaceAll("'", " "),
				Utility.checkNull(outbst.DESCR_VINCOLI).replaceAll("'", " "),
				"0".equals(Utility.checkNull(outbst.NUM_OGG_HOST)) ? "" : Utility.checkNull(outbst.NUM_OGG_HOST),
				"0".equals(Utility.checkNull(outbst.NUM_OGG_OPEN)) ? "" : Utility.checkNull(outbst.NUM_OGG_OPEN),
				"0".equals(Utility.checkNull(outbst.DIM_OGG_OPEN)) ? "" : Utility.checkNull(outbst.DIM_OGG_OPEN),
				Utility.checkNull(outbst.IND_UTENZA_FIL),
				Utility.checkNull(outbst.IND_UTENZA_CANALI), 
				Utility.checkNull(outbst.IND_UTENZA_ENTI_C),
				Utility.checkNull(outbst.IND_UTENZA_ALTRO), 
				Utility.checkNull(outbst.IND_SW_TP),
				Utility.checkNull(outbst.IND_SW_BATCH), 
				Utility.checkNull(outbst.IND_SW_ALTRO),
				Utility.checkNull(outbst.IND_IMP_CONSUMI), 
				Utility.checkNull(outbst.DESCR_IMP_CONSUMI),
				Utility.checkNull(outbst.IND_APPL_NUOVA),
				Utility.checkNull(outbst.IND_IMPATTO_DR)
		);
		
		Abilitazioni abilitazioni = new Abilitazioni(
				Utility.checkNull(outbst.IND_RICH_IT), 
				Utility.checkNull(outbst.IND_ESITO_IT),
				Utility.checkNull(outbst.IND_AUT_SW), 
				Utility.checkNull(outbst.IND_IMPATTO_RIL),
				Utility.checkNull(outbst.IND_IMPROCRAST), 
				Utility.checkNull(outbst.IND_VINCOLI),
				Utility.checkNull(outbst.IND_AUT_GECO), 
				Utility.checkNull(outbst.IND_RICH_DEROGA),
				Utility.checkNull(outbst.IND_AUT_DEROGA), 
				Utility.checkNull(outbst.IND_CTRL_PERFORMANCE), 
				//flagOverrideDataLimite,
				//flgUdcEmergenzaSpeciale,
				Utility.checkNull(outbst.FLAG_BLOCCO_PROMO),
				Utility.checkNull(outbst.UDC_GIA_PROMOSSO),
				Utility.checkNull(outbst.IND_ERRORE_BLOCCANTE)
		);

		DatiUtente datiUtenteCreatore = new DatiUtente(
				Utility.checkNull(outbst.COD_USER_CREA),
				Utility.checkNull(outbst.COGNOME_NOME_CREA),
				Utility.checkNull(outbst.TELEFONO_PREF_CREA),
				Utility.checkNull(outbst.TELEFONO_NUM_CREA)
				);
		
		DatiUtente datiUtenteResponsabile = new DatiUtente(
				Utility.checkNull(outbst.COD_USER_RESP),
				Utility.checkNull(outbst.COGNOME_NOME_RESP),
				Utility.checkNull(outbst.TELEFONO_PREF_RESP),
				Utility.checkNull(outbst.TELEFONO_NUM_RESP)
				);
		
		DatiManifestoDiCollaudo datiManifestoDiCollaudo = 
			new DatiManifestoDiCollaudo(
					Utility.checkNull(outbst.NOME_MDC),
					Utility.checkNull(outbst.USER_MDC),
					Utility.checkNull(outbst.NOME_COGNOME_USER_MDC)
				);
		
		DatiUtente datiUtenteGeco = new DatiUtente(
				Utility.checkNull(outbst.COD_USER_GECO),
				Utility.checkNull(outbst.COGNOME_NOME_GECO),
				Utility.checkNull(outbst.TELEFONO_PREF_GECO),
				Utility.checkNull(outbst.TELEFONO_NUM_GECO)
		);
		
		DatiUtente datiUtenteDeroga = new DatiUtente(
				Utility.checkNull(outbst.COD_USER_DEROGA),
				Utility.checkNull(outbst.COGNOME_NOME_DEROGA),
				Utility.checkNull(outbst.TELEFONO_PREF_DEROGA),
				Utility.checkNull(outbst.TELEFONO_NUM_DEROGA)
		);
		
		String vincoloDeroga = FasciaDistrService.getDescrizioneFasciaDistr(
				utente, 
				Utility.checkNull(outbst.SIST_INFORMATIVO), 
				Utility.checkNull(outbst.COD_VINCOLO_DEROGA)
			);
		
		DatiDeroga datiDeroga = new DatiDeroga(
				Utility.checkNull(outbst.COD_DEROGA),
				Utility.checkNull(outbst.DESCR_DEROGA),
				Utility.checkNull(outbst.MOTIVO_DEROGA),
				vincoloDeroga,
				Utility.checkNull(outbst.DESCR_VINCOLI),
				datiUtenteDeroga
		);

		DatiPesatura datiPesatura = new DatiPesatura(
				Utility.checkNull(outbst.COD_STATO_PESATURA),
				Utility.checkNull(outbst.DESCR_STATO_PESATURA),
				Utility.checkNull(outbst.COD_ESITO_PESATURA),
				Utility.checkNull(outbst.TEMPO_STIMATO_PESATURA),
				Utility.checkNull(outbst.TEMPO_DISP_PESATURA),
				"0".equals(Utility.checkNull(outbst.NUM_OGG_PESATI)) ? "" : Utility.checkNull(outbst.NUM_OGG_PESATI),
				"0".equals(Utility.checkNull(outbst.NUM_PCK_PESATI)) ? "" : Utility.checkNull(outbst.NUM_PCK_PESATI),
				Utility.checkNull(outbst.NUM_RICICLI),		
				Utility.checkNull(outbst.DATA_ULT_PESATURA),
				Utility.checkNull(outbst.ORA_ULT_PESATURA)
		);

		LinkedList prerequisitiUdc = new LinkedList();
		PrerequisitiUdc prerequisitoUdc = new PrerequisitiUdc();
		if ((outbst.TABPRE != null)) {					
			logger.debug(utente + "Occorrene TABPRE:" + outbst.TABPRE.length);
			for (int u = 0; u < outbst.TABPRE.length; u++) {
				prerequisitoUdc = new PrerequisitiUdc(
						Utility.checkNull(outbst.TABPRE[u].PRE_TIPO_PIATT),
						Utility.checkNull(outbst.TABPRE[u].PRE_UDC),
						Utility.checkNull(outbst.COD_PROGETTO),
						Utility.checkNull(outbst.TABPRE[u].PRE_USER_RESP),
						Utility.checkNull(outbst.TABPRE[u].PRE_DESCR_USER),
						Utility.checkNull(outbst.TABPRE[u].PRE_DATA_PP)
				);
				prerequisitiUdc.add(prerequisitoUdc);
			}
		}	
		
		String descrizioneMotivoEmergenza = Decoder.getDescrizioneMotivoEmergenzaHost(utente, outbst.SIST_INFORMATIVO,outbst.MOTIVO_EME);
		DatiEmergenza datiEmergenza = new DatiEmergenza(
			Utility.checkNull(outbst.IND_SBLOCCO_UDE),
			Utility.checkNull(outbst.ORARIO_UDE),
			Utility.checkNull(outbst.ID_EMERGENZA),
			Utility.checkNull(outbst.PROF_UTENTE),
			Utility.checkNull(outbst.ID_ALLEGATO_AUTORIZZ),
			Utility.checkNull(""),//TODO [VS] è presente un campo note?
			Utility.checkNull(outbst.TIPIZZAZIONE_UDC),
			Utility.checkNull(outbst.IND_ERRORE_BLOCCANTE),
			Utility.checkNull(outbst.MOTIVO_EME),
			descrizioneMotivoEmergenza
		);
		
		Udc udc = new Udc(
				abilitazioni,
				datiUDc, 
				datiContenutoUdc,
				dateDiDistribuzione,
				datiImpatti,
				datiUtenteCreatore,
				datiUtenteResponsabile, 
				datiUtenteGeco, 
				datiDeroga, 
				datiPesatura, 
				datiManifestoDiCollaudo,
				prerequisitiUdc,
				pianoDiAttivazione,
				pianoDiRollBack,
				datiEmergenza
		);
		sessione.setAttribute("udc", udc);
	}

	private String getSsaAltri(OUTBST outbst) {
		StringBuilder ssaALtri = new StringBuilder();
		String altriSsa = Utility.checkNull(outbst.SSA_ALTRI);
		String newStringa = "";

		int lung = Utility.checkNull(outbst.SSA_ALTRI).length();
		for (int i = 0; i < lung; i = i + 2) {
			newStringa = altriSsa.substring(i, i + 2);
			ssaALtri.append(newStringa);
			ssaALtri.append(" ");
		}
		return ssaALtri.toString();
	}
	
	private String getTimeStampStorico(String timestStorico){
		String retValue = "";
		if (!"".equals(timestStorico)) {
			retValue = timestStorico.substring(8,10) + "/" + timestStorico.substring(5,7) + "/"; 
			retValue = retValue + timestStorico.substring(0,4) + "/" + timestStorico.substring(11,19);
		}
		return retValue;
	}
	
	private DatiPianoAllegato getPianoAllegato(String utente, String codiceUdc, String piattaforma, String tipoAllegato){
		Allegati allegato = Decoder.ricercaDatiAllegato(utente, codiceUdc, piattaforma, tipoAllegato);
		DatiPianoAllegato datiPianoAllegato = new DatiPianoAllegato(
				(allegato.getIdAllegati()!=null), 
				allegato.getFileName(), 
				allegato.getDescrizione(), 
				allegato.getDataInerimento(),
				allegato.getIdAllegati()
		);
		return datiPianoAllegato;
	}

}

