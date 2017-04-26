package autudc.chiamaBS.ude;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Utility.BSUtility;
import Utility.Constants;
import Utility.Utility;
import autudc.chiamaBS.ErroreOutputBS;
import autudc.chiamaBS.ude.dto.Udc;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import autudc.swa.ProfiloSWA;

import com.dsi.business.SSA_YU.integration.jdo.P_YUZNS02.C_YUZNS02;
import com.dsi.business.SSA_YU.integration.jdo.P_YUZNS02.INHEADER;
import com.dsi.business.SSA_YU.integration.jdo.P_YUZNS02.INPBST;
import com.dsi.business.SSA_YU.integration.jdo.P_YUZNS02.OUTBST;

/**
 *
 *@author U0G8898
 */
public class ChiamaYUZN {

    private Logger logger = null;
    private BSUtility bsUtility = new BSUtility();
    private static final String ID_SERVIZIO_YUZN = "YUZNAGGSTA";
    private ErroreOutputBS erroreOutputBS = new ErroreOutputBS();
    
    public ChiamaYUZN() {
        try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("Costruttore ChiamaYUZN");
    }
    
    public void chiamaBS(
    		HttpSession sessione,
    		HttpServletRequest request,
    		ProfiloSWA userswa,
    		String sistemaInformativo,
    		String tipoRichiesta,
    		String tipoEme
    	){
    	
    	initYUZN(sessione, request, userswa.getUserID(), sistemaInformativo, tipoRichiesta, tipoEme);
        logger.info("ChiamaYUZN() - erroreOutputBS: " + erroreOutputBS.isEsito() + " " + erroreOutputBS.getMessaggioErrore());
        sessione.setAttribute("erroreOutputBS",erroreOutputBS);
    }
    
	private void initYUZN(
			HttpSession sessione, 
			HttpServletRequest request,
			String utente, 
			String sistemaInformativo,
			String tipoRichiesta,
			String tipoEme
		) {
		
		boolean isSuperataInvoke = true;
        C_YUZNS02 yuzn = new C_YUZNS02();

		popolaTagInHeader(yuzn, utente);
		
		popolaTagInpbst(sessione, request, yuzn, sistemaInformativo, tipoRichiesta, tipoEme);

        try {
			yuzn.invoke();
		} catch (Exception e) {
        	e.printStackTrace();
			isSuperataInvoke = false;
            logger.error("ChiamaYUZN() - Errore chiamata connettore " + e.toString());
		}
		
		gestioneRirpostaHost(sessione, yuzn, isSuperataInvoke);
	}

	private void gestioneRirpostaHost(
			HttpSession sessione, 
			C_YUZNS02 yuzn,
			boolean isSuperataInvoke) {
		
		String msgErrore = "";
		if (isSuperataInvoke) {
			
			boolean isOperazioneEseguita = false;
			boolean ciSonoDatiDiOutput = false;
			int OUTHEADER_RETCODE = yuzn.OUTHEADER[0].RETCODE;
            logger.debug("gestioneRirpostaHost() - RetCode BS: " + OUTHEADER_RETCODE);
			switch (OUTHEADER_RETCODE) {
			case 0:
				isOperazioneEseguita = true;
				if (yuzn.OUTBST !=null && yuzn.OUTBST[0] != null){
					ciSonoDatiDiOutput = true;
				}
                 break;
			case 12:
				msgErrore = yuzn.OUTSEG[0].COD_SEGNALAZIONE+" "+yuzn.OUTSEG[0].TXT_SEGNALAZIONE;
				break;
			case 16:
			case 20:
				msgErrore = yuzn.OUTESI[0].ESITO;
				break;
			default:
				msgErrore = " ChiamaYUZN (" + ID_SERVIZIO_YUZN + ").OUTHEADER_RETCODE  non gestito:" + OUTHEADER_RETCODE;
			}

			if (isOperazioneEseguita) {
				erroreOutputBS = new ErroreOutputBS(true, "Operazione Eseguita");
				//setto l'oggetto per l'output della pagina
				if (yuzn.OUTBST !=null && yuzn.OUTBST[0] != null){
					
					if (ciSonoDatiDiOutput) {
						bsUtility.writeLogBS(yuzn.INPBST, ID_SERVIZIO_YUZN, true);
						caricaDaOutbst(sessione, yuzn.OUTBST[0]);
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
			C_YUZNS02 yuzn, 
			String utente) {
		
		yuzn.INHEADER = new INHEADER[1];
		yuzn.INHEADER[0] = new INHEADER();
		yuzn.INHEADER[0].RETCODE = 0;
		yuzn.INHEADER[0].ID_SERVIZIO = "YUZNAGGSTA";
		yuzn.INHEADER[0].CODICE_TIPO_CANALE = "52";
		yuzn.INHEADER[0].CODICE_SOCIETA = "01";
		yuzn.INHEADER[0].CODICE_SPORTELLO = "00700";
		yuzn.INHEADER[0].CODICE_UO_RICH = "00700";
		yuzn.INHEADER[0].CODICE_USERID = utente;
		yuzn.INHEADER[0].COD_RICH_CANALE = "000000000000000000000000000";
		yuzn.INHEADER[0].DATA_CONT = "24052006";
		yuzn.INHEADER[0].LUNGHEZZA_MSG = 44;
		yuzn.INHEADER[0].IND_MQ_SINCRONO = "S";
		bsUtility.writeLogBS(yuzn.INHEADER, ID_SERVIZIO_YUZN, false);
	}
	
	private void popolaTagInpbst(
			HttpSession sessione,
			HttpServletRequest request,
			C_YUZNS02 yuzn,
			String sistemaInformativo,
			String tipoRichiesta, 
			String tipoEme
	){
		
		Udc udc = sessione.getAttribute("udc") == null ? new Udc() : (Udc)sessione.getAttribute("udc");
		String flC   = (String) sessione.getAttribute("flC");
		String note  = Constants.TIPO_RICHIESTA_APPROVA.equals(tipoRichiesta) ? "" : "RIFIUTO RESPONSABILE";
		String aliaAzioni = Constants.TIPO_RICHIESTA_APPROVA.equals(tipoRichiesta) ? "AUTUDEREOK" : "AUTUDEREKO";
		if ("K".equals(tipoEme))
			aliaAzioni = Constants.TIPO_RICHIESTA_APPROVA.equals(tipoRichiesta) ? "AUTUDTREOK" : "AUTUDTREKO";
		if ("C".equals(tipoEme))
		{
			if ("S".equals(flC))
				aliaAzioni = Constants.TIPO_RICHIESTA_APPROVA.equals(tipoRichiesta) ? "AUTCUTRUOK" : "AUTCUTRUKO";
			else
				aliaAzioni = Constants.TIPO_RICHIESTA_APPROVA.equals(tipoRichiesta) ? "AUTCUTREOK" : "AUTCUTREKO";
		}
		if ("I".equals(tipoEme))
		{
			aliaAzioni = Constants.TIPO_RICHIESTA_APPROVA.equals(tipoRichiesta) ? "AUTINIATOK" : "AUTINIATKO";
		}
		if ("F".equals(tipoEme))
		{
			aliaAzioni = Constants.TIPO_RICHIESTA_APPROVA.equals(tipoRichiesta) ? "AUTFINATOK" : "AUTFINATKO";
		}	
		String codiceUdc = udc.getDatiUDc().getCodiceUdc();
		String profiloUtente = Utility.isBlackBerry(request) ? "XXXXXB" : "XXXXXC";
		
		yuzn.INPBST = new INPBST[1];
		yuzn.INPBST[0] = new INPBST();
		yuzn.INPBST[0].COD_PROFILO_UTENTE = profiloUtente;
		yuzn.INPBST[0].ALIAS_AZIONE = aliaAzioni;
		yuzn.INPBST[0].COD_UDC = codiceUdc;
		yuzn.INPBST[0].SIST_INFORMATIVO = sistemaInformativo;
		yuzn.INPBST[0].ID_ALLEGATO = Utility.FillaCaratteri("", "0", "S", 15);
		yuzn.INPBST[0].NOTE = note;
		bsUtility.writeLogBS(yuzn.INPBST, ID_SERVIZIO_YUZN, true);
	}
	
	private void caricaDaOutbst(
			HttpSession sessione, 
			OUTBST outbst) {

		//l'outbst non esiste per questo bs
	}

}

