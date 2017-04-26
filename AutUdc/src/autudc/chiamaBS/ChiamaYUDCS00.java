package autudc.chiamaBS;

import javax.servlet.http.HttpSession;

import Utility.BSUtility;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import autudc.swa.ProfiloSWA;

import com.dsi.business.SSA_YU.integration.jdo.P_YUDCS00.C_YUDCS00;
import com.dsi.business.SSA_YU.integration.jdo.P_YUDCS00.INHEADER;
import com.dsi.business.SSA_YU.integration.jdo.P_YUDCS00.INPBST;

/**
 *
 * @author GF001126
 */
public class ChiamaYUDCS00 {

    private Logger logger = null;
    private BSUtility bsUtility = new BSUtility();
    private static final String DBG_SEND_MSG = "In ChiamaYUDCS00";
    private static final String ID_SERVIZIO_YUDCS00 = "YUDCAUTRES";
    private ErroreOutputBS erroreOutputBS = new ErroreOutputBS();
    private InputYUDCS00 inputBSYudc = new InputYUDCS00();
    private OutputYUDCS00 outputBSYudc = new OutputYUDCS00();

    public ChiamaYUDCS00() {
        try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug(DBG_SEND_MSG + "Costruttore ChiamaYUDCS00");
    }
    
    public void ChiamaYUDC(
    		HttpSession sessione,
    		ProfiloSWA userswa,
    		String sistemaInformativo,
    		String tipoAutorizzazione,
    		String idRichiesta,
    		String tipoRichiesta
    ){
    	
    	logger.info("chiamaYUDA idRichiesta: "+idRichiesta+" sistemaInformativo "+sistemaInformativo+" tipoAutorizzazione "+tipoAutorizzazione+" tipoRichiesta "+tipoRichiesta);
    	inputBSYudc = new InputYUDCS00(userswa.getUserID(), sistemaInformativo, tipoAutorizzazione, idRichiesta, tipoRichiesta);
    	outputBSYudc = Chiama_YUDCS00(inputBSYudc);
    	logger.info("processRequest() - Output YUDC: " + erroreOutputBS.isEsito() + " " + erroreOutputBS.getMessaggioErrore());
		// Salvataggio in sessione dati chiamata BS YUDC
		sessione.setAttribute("OutputBS", outputBSYudc);
		sessione.setAttribute("InputBS", inputBSYudc);
		sessione.setAttribute("erroreOutputBS",erroreOutputBS);
    }
    
    public InputYUDCS00 getInputBSYudc() {
		return inputBSYudc;
	}

	public OutputYUDCS00 getOutputBSYudc() {
		return outputBSYudc;
	}

	private OutputYUDCS00 Chiama_YUDCS00(InputYUDCS00 input) {
		
		boolean isSuperataInvoke = true;
       
		C_YUDCS00 yudc = new C_YUDCS00();

		popolaInHeader(yudc, input);

		popolaInpbst(yudc, input);
		
        try {
			yudc.invoke();
		} catch (Exception e) {
        	e.printStackTrace();
			isSuperataInvoke = false;
            logger.error(DBG_SEND_MSG + "Chiama_YUDCS00() - Errore chiamata connettore " + e.toString());
		}
		
		 String msgErrore = "";
        OutputYUDCS00 output = new OutputYUDCS00();
		if (isSuperataInvoke) {
			
			boolean isOperazioneEseguita = false;
			int OUTHEADER_RETCODE = yudc.OUTHEADER[0].RETCODE;
            logger.debug(DBG_SEND_MSG + "Chiama_YUDCS00() - RetCode BS: " + OUTHEADER_RETCODE);
			switch (OUTHEADER_RETCODE) {
			case 0:
				isOperazioneEseguita = true;
                 break;
			case 12:
				msgErrore = yudc.OUTSEG[0].COD_SEGNALAZIONE+" "+yudc.OUTSEG[0].TXT_SEGNALAZIONE;
				break;
			case 16:
			case 20:
				msgErrore = yudc.OUTESI[0].ESITO;
				break;
			default:
				msgErrore = " ChiamaYUDCS00 (" + ID_SERVIZIO_YUDCS00 + ").OUTHEADER_RETCODE  non gestito:" + OUTHEADER_RETCODE;
			}

			
			if (isOperazioneEseguita) {
				bsUtility.writeLogBS(yudc.INHEADER, ID_SERVIZIO_YUDCS00, true);
				erroreOutputBS.setMessaggioErrore(" Operazione eseguita");
			}else{
				erroreOutputBS = new ErroreOutputBS(false, msgErrore);
			}

		}else{
			// non superata invocke
			erroreOutputBS = new ErroreOutputBS(false, "Errore invoke servizio. "+msgErrore);
		}
	    logger.debug(DBG_SEND_MSG + "Chiama_YUDCS00() - Messaggio da BS: " + erroreOutputBS.getMessaggioErrore());
	    return output;
	
	}

	private void popolaInHeader(C_YUDCS00 yudc, InputYUDCS00 input) {
		yudc.INHEADER = new INHEADER[1];
		yudc.INHEADER[0] = new INHEADER();
		yudc.INHEADER[0].RETCODE = 0;
		yudc.INHEADER[0].CODICE_USERID = input.getUser().toUpperCase();
		yudc.INHEADER[0].ID_SERVIZIO = ID_SERVIZIO_YUDCS00;
		yudc.INHEADER[0].CODICE_TIPO_CANALE = "52";
		yudc.INHEADER[0].CODICE_SOCIETA = "01";
		yudc.INHEADER[0].CODICE_SPORTELLO = "00700";
		yudc.INHEADER[0].CODICE_UO_RICH = "00700";
		yudc.INHEADER[0].COD_RICH_CANALE = "000000000000000000000000000";
		yudc.INHEADER[0].DATA_CONT = "24052006";
		yudc.INHEADER[0].LUNGHEZZA_MSG = 44;
		yudc.INHEADER[0].IND_MQ_SINCRONO = "S";
		bsUtility.writeLogBS(yudc.INHEADER, ID_SERVIZIO_YUDCS00, false);
	}

	private void popolaInpbst(C_YUDCS00 yudc, InputYUDCS00 input) {
		yudc.INPBST = new INPBST[1];
		yudc.INPBST[0] = new INPBST();
		// CAMPI TABELLA
		yudc.INPBST[0].COD_TIPO_SI = input.getCodiceSistemaInformativo().toUpperCase();
		yudc.INPBST[0].TIPO_AUTORIZZAZIONE = input.getTipoAutorizzazione();
		yudc.INPBST[0].ID_DA_AUTORIZZARE = input.getIdDaAutorizzare();
        yudc.INPBST[0].ESITO_AUTORIZZAZ = input.getEsitoAutorizzaz().toUpperCase();
        bsUtility.writeLogBS(yudc.INPBST, ID_SERVIZIO_YUDCS00, true);
	}


}

