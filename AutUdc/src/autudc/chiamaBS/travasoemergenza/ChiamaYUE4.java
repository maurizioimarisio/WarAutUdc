package autudc.chiamaBS.travasoemergenza;

import javax.servlet.http.HttpSession;

import Utility.BSUtility;
import Utility.Utility;
import autudc.chiamaBS.ErroreOutputBS;
import autudc.chiamaBS.travasoemergenza.dto.ControlliTestata;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import autudc.swa.ProfiloSWA;

import com.dsi.business.SSA_YU.integration.jdo.P_YUE4S02.C_YUE4S02;
import com.dsi.business.SSA_YU.integration.jdo.P_YUE4S02.INHEADER;
import com.dsi.business.SSA_YU.integration.jdo.P_YUE4S02.INPBST;
import com.dsi.business.SSA_YU.integration.jdo.P_YUE4S02.OUTBST;

/**
 *
 *@author U0G8898
 */
public class ChiamaYUE4 {

	
	protected static final String TIPO_OPERAZIONE_INSERIMENTO = "I";
	protected static final String TIPO_OPERAZIONE_VARIAZIONE = "V";
	protected static final String TIPO_OPERAZIONE_CANCELLAZIONE = "C";
	protected static final String TIPO_OPERAZIONE_DUPLICAZIONE = "D";
	protected static final String TIPO_OPERAZIONE_CONFERMA = "A";
	protected static final String TIPO_OPERAZIONE_PUBBLICA = "S";
	protected static final String TIPO_OPERAZIONE_RESET = "M";
	protected static final String TIPO_OPERAZIONE_TRAVASO_RIFIUTA = "R";
	protected static final String TIPO_OPERAZIONE_TRAVASO_APPROVA = "T";
	protected static final String TIPO_OPERAZIONE_NUOVA_ATTIVITA = "";
	protected static final String TIPO_OPERAZIONE_DATI_EMERGENZA_ATT_SALVA = "T";
	
    private Logger logger = null;
    private BSUtility bsUtility = new BSUtility();
    private static final String ID_SERVIZIO_YUE4 = "YUE4AGGRIC";
    private ErroreOutputBS erroreOutputBS = new ErroreOutputBS();
    
    private static final String PARAM_DATI_CONTROLLO_TESTATA = "controlliTestata";

    public ChiamaYUE4() {
        try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("Costruttore ChiamaYUE4");
    }
    
    public void chiamaBS(
    		HttpSession sessione,
    		ProfiloSWA userswa,
    		String tipoOperazione
    	){
    	
    	initYUE4(sessione, userswa.getUserID(), tipoOperazione);
        logger.info("ChiamaYUE4() - erroreOutputBS: " + erroreOutputBS.isEsito() + " " + erroreOutputBS.getMessaggioErrore());
        sessione.setAttribute("erroreOutputBS",erroreOutputBS);
    }
    
	private void initYUE4(HttpSession sessione, String utente, String tipoOperazione) {
		
		boolean isSuperataInvoke = true;
        C_YUE4S02 yue4 = new C_YUE4S02();

		popolaTagInHeader(yue4, utente);
		
		popolaTagInpbst(sessione, yue4, utente.trim(), tipoOperazione);

        try {
			yue4.invoke();
		} catch (Exception e) {
        	e.printStackTrace();
			isSuperataInvoke = false;
            logger.error("ChiamaYUE4() - Errore chiamata connettore " + e.toString());
		}
		
		gestioneRirpostaHost(sessione, yue4, isSuperataInvoke);
	}

	private void gestioneRirpostaHost(
			HttpSession sessione, 
			C_YUE4S02 yue4,
			boolean isSuperataInvoke) {
		
		String msgErrore = "";
		if (isSuperataInvoke) {
			
			boolean isOperazioneEseguita = false;
			boolean ciSonoDatiDiOutput = false;
			int OUTHEADER_RETCODE = yue4.OUTHEADER[0].RETCODE;
            logger.debug("gestioneRirpostaHost() - RetCode BS: " + OUTHEADER_RETCODE);
			switch (OUTHEADER_RETCODE) {
			case 0:
				isOperazioneEseguita = true;
				if (yue4.OUTBST !=null && yue4.OUTBST[0] != null){
					ciSonoDatiDiOutput = true;
				}
                 break;
			case 12:
				msgErrore = yue4.OUTSEG[0].COD_SEGNALAZIONE+" "+yue4.OUTSEG[0].TXT_SEGNALAZIONE;
				break;
			case 16:
			case 20:
				msgErrore = yue4.OUTESI[0].ESITO;
				break;
			default:
				msgErrore = " ChiamaYUE4 (" + ID_SERVIZIO_YUE4 + ").OUTHEADER_RETCODE  non gestito:" + OUTHEADER_RETCODE;
			}

			if (isOperazioneEseguita) {
				erroreOutputBS = new ErroreOutputBS(true, "Operazione Eseguita");
				//setto l'oggetto per l'output della pagina
				if (yue4.OUTBST !=null && yue4.OUTBST[0] != null){
					
					if (ciSonoDatiDiOutput) {
						bsUtility.writeLogBS(yue4.INPBST, ID_SERVIZIO_YUE4, true);
						caricaDaOutbst(sessione, yue4.OUTBST[0]);
					}
				}
				
			}else{
				erroreOutputBS = new ErroreOutputBS(false, msgErrore);
			}

		}else{
			// non superata invocke
			erroreOutputBS = new ErroreOutputBS(false, "Errore invoke servizio. "+msgErrore);
		}
	    logger.debug("gestioneRirpostaHost() - Messaggio da BS: " + erroreOutputBS.getMessaggioErrore());
	}

	private void popolaTagInHeader(
			C_YUE4S02 yue4, 
			String utente) {
		
		yue4.INHEADER = new INHEADER[1];
		yue4.INHEADER[0] = new INHEADER();
		yue4.INHEADER[0].RETCODE = 0;
		yue4.INHEADER[0].CODICE_USERID = utente.trim();
		yue4.INHEADER[0].ID_SERVIZIO = ID_SERVIZIO_YUE4;
		yue4.INHEADER[0].CODICE_TIPO_CANALE = "52";
		yue4.INHEADER[0].CODICE_SOCIETA = "01";
		yue4.INHEADER[0].CODICE_SPORTELLO = "00700";
		yue4.INHEADER[0].CODICE_UO_RICH = "00700";
		yue4.INHEADER[0].COD_RICH_CANALE = "000000000000000000000000000";
		yue4.INHEADER[0].DATA_CONT = "24052006";
		yue4.INHEADER[0].LUNGHEZZA_MSG = 44;
		yue4.INHEADER[0].IND_MQ_SINCRONO = "S";
		bsUtility.writeLogBS(yue4.INHEADER, ID_SERVIZIO_YUE4, false);
	}
	
	private void popolaTagInpbst(
			HttpSession sessione,
			C_YUE4S02 yue4,
			String utente,
			String tipoOperazione
			){
		
		yue4.INPBST = new INPBST[1];
		yue4.INPBST[0] = new INPBST();
		
		ControlliTestata controlliTestata = (ControlliTestata) sessione.getAttribute(PARAM_DATI_CONTROLLO_TESTATA);
		
		yue4.INPBST[0].TIPO_SI = Utility.checkNull(controlliTestata.getCodiceSistemaInformativo());
		yue4.INPBST[0].SSA_RICHIESTA = Utility.checkNull(controlliTestata.getDatiRichiesta().getCodiceSsa());
		yue4.INPBST[0].DESC_RICHIESTA = Utility.checkNull(controlliTestata.getDatiRichiesta().getDescrRichiesta());
		yue4.INPBST[0].TIPO_RICHIESTA = Utility.checkNull(controlliTestata.getDatiRichiesta().getTipoRichiesta());
		yue4.INPBST[0].NUM_RICHIESTA = Utility.FillaCaratteri(controlliTestata.getDatiRichiesta().getNumeroRichiesta(), "0", "S", 9);
		yue4.INPBST[0].INCIDENTE = Utility.checkNull(controlliTestata.getDatiIncidente().getCodiceIncidente());
		yue4.INPBST[0].MOTIVO_EME = Utility.checkNull(controlliTestata.getDatiRichiesta().getMotivoEmergenza().getCodiceEmergenza());
		yue4.INPBST[0].PROGETTO = Utility.checkNull(controlliTestata.getDatiIncidente().getCodiceProgetto());
		yue4.INPBST[0].RIFERIMENTO = Utility.checkNull(controlliTestata.getDatiIncidente().getRiferimentoIncidente());
		yue4.INPBST[0].TIPO_OPERAZIONE = tipoOperazione;
		if (!TIPO_OPERAZIONE_INSERIMENTO.equals(tipoOperazione)){
			yue4.INPBST[0].TIMEST_ULT_AGG_RICHIESTA = Utility.checkNull(controlliTestata.getDatiRichiesta().getTimeStampUltimoAggiornamento());
		}
//		if (TIPO_OPERAZIONE_DUPLICAZIONE.equals(tipoOperazione)){
//			yue4.INPBST[0].NUM_RICHIESTA_ORIGINE_DUPLICA = 
//				Utility.FillaCaratteri(controlliTestata.getDatiDuplicazione().getNumeroRichiesta(), "0", "S", 9);
//			yue4.INPBST[0].INCIDENTE_ORIGINE_DUPLICA = Utility.checkNull(controlliTestata.getDatiDuplicazione().getCodiceIncidente());
//		}
		String erroreBloccante = Utility.checkNull(controlliTestata.getDatiAutorizzazione().getErroreBloccante());
		yue4.INPBST[0].ERRORE_BLOCCANTE = Utility.checkNull("".equals(erroreBloccante.trim())?"N":erroreBloccante);
		String impattoDiBusiness = Utility.checkNull(controlliTestata.getDatiAutorizzazione().getImpattoDiBusiness());
		yue4.INPBST[0].IMPATTO_BUSINESS = Utility.checkNull("".equals(impattoDiBusiness.trim())?"N":impattoDiBusiness);
//		yue4.INPBST[0].PROF_UTENTE = utente;
		bsUtility.writeLogBS(yue4.INPBST, ID_SERVIZIO_YUE4, true);
	}
	
	private void caricaDaOutbst(
			HttpSession sessione, 
			OUTBST outbst) {

		ControlliTestata controlliTestata = (ControlliTestata)sessione.getAttribute(PARAM_DATI_CONTROLLO_TESTATA);
		
		String numRichiesta = Integer.valueOf(Utility.checkNull(outbst.NUM_RICH_OUT)).toString();
		String codStatoRichiesta = Utility.checkNull(outbst.STATO_RICHIESTA).trim();

		controlliTestata.getDatiRichiesta().setNumeroRichiesta(numRichiesta);
		controlliTestata.getDatiRichiesta().setCodiceStatoRichiesta(codStatoRichiesta);
	}

}

