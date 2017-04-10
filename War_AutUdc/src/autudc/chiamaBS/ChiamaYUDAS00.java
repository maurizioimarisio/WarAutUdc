package autudc.chiamaBS;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import com.dsi.business.SSA_YU.integration.jdo.P_YUDAS01.*;
import Utility.*;

import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import autudc.swa.ProfiloSWA;

/**
 *
 * @author GF001126
 */
public class ChiamaYUDAS00 {

    private Logger logger = null;
    private BSUtility bsUtility = new BSUtility();
    private static final String DBG_SEND_MSG = "In ChiamaYUDAS00";
    private static final String ID_SERVIZIO_YUDAS00 = "YUDAELEDER";
    private ErroreOutputBS erroreOutputBS = new ErroreOutputBS();
    private InputYUDAS00 inputBSYuda = new InputYUDAS00();
    private OutputYUDAS00 outputBSYuda = new OutputYUDAS00();

    public ChiamaYUDAS00() {
        try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
        	logger.error(DBG_SEND_MSG + "errore in Costruttore ChiamaYUDAS00");
        	ex.printStackTrace();
            
        }
        logger.debug(DBG_SEND_MSG + "Costruttore - ChiamaYUDAS00");
        
        
    }
    
    public void chiamaYUDA(
    		HttpSession sessione,
    		ProfiloSWA userswa,
    		String idRichiesta,
    		String sistemaInformativo){
    	
    	logger.info("chiamaYUDA idRichiesta: "+idRichiesta+" sistemaInformativo "+sistemaInformativo);
    	inputBSYuda = new InputYUDAS00(userswa.getUserID(), idRichiesta, sistemaInformativo);
        outputBSYuda = Chiama_YUDAS00(inputBSYuda);
        logger.info("chiamaYUDA() - erroreOutputBS: " + erroreOutputBS.isEsito() + " " + erroreOutputBS.getMessaggioErrore());
        sessione.setAttribute("InputBS",inputBSYuda);
        sessione.setAttribute("OutputBS",outputBSYuda);
        sessione.setAttribute("erroreOutputBS",erroreOutputBS);
    }
    
    public InputYUDAS00 getInputBSYuda() {
		return inputBSYuda;
	}

	public OutputYUDAS00 getOutputBSYuda() {
		return outputBSYuda;
	}

	private OutputYUDAS00 Chiama_YUDAS00(InputYUDAS00 input) {

        boolean isSuperataInvoke = true;

        C_YUDAS01 yuda = new C_YUDAS01();

        popolaInHeader(input, yuda);

        popolaInpbst(input, yuda);
        
        try {
            yuda.invoke();
        } catch (Exception e) {
            e.printStackTrace();
            isSuperataInvoke = false;
            logger.error(DBG_SEND_MSG + "Chiama_YUDAS00() - Errore chiamata connettore " + e.toString());
        }

        
        OutputYUDAS00 output = gestioneRispostaHost(input, yuda, isSuperataInvoke);
        
        return output;

    }

	private OutputYUDAS00 gestioneRispostaHost(
			InputYUDAS00 input,
			C_YUDAS01 yuda, 
			boolean isSuperataInvoke) {
		
		String msgErrore = "";
		OutputYUDAS00 output = new OutputYUDAS00();
        
        if (isSuperataInvoke) {

            boolean isOperazioneEseguita = false;
            int OUTHEADER_RETCODE = yuda.OUTHEADER[0].RETCODE;
            logger.debug(DBG_SEND_MSG + "Chiama_YUDAS00() - RetCode BS: " + OUTHEADER_RETCODE);
            switch (OUTHEADER_RETCODE) {
                case 0:
                    isOperazioneEseguita = true;
                    break;
                case 12:
               		msgErrore = yuda.OUTSEG[0].COD_SEGNALAZIONE + " " + yuda.OUTSEG[0].TXT_SEGNALAZIONE;	
                    break;
                case 16:
                case 20:
                    msgErrore = yuda.OUTESI[0].ESITO;

                    break;
                default:
                    msgErrore = " ChiamaYUDAS00 (" + ID_SERVIZIO_YUDAS00 + ").OUTHEADER_RETCODE  non gestito:" + OUTHEADER_RETCODE;
            }

            if (isOperazioneEseguita) {

            	erroreOutputBS.setMessaggioErrore("Operazione Eseguita");
                if (yuda.OUTBST != null && yuda.OUTBST[0] != null) {
                	bsUtility.writeLogBS(yuda.OUTBST, ID_SERVIZIO_YUDAS00, true);
                	
                	RichiestaDeroga richiestaDeroga = new RichiestaDeroga();
                    LinkedList elencoUdc = new LinkedList();
                    OUTBST outbst = yuda.OUTBST[0];
                    richiestaDeroga.setUserIdDesctinatario(outbst.USERID_AUTORIZ);
                    richiestaDeroga.setDestinatario(outbst.NOME_AUTORIZ);
                    richiestaDeroga.setLivelloAutorizzativo(Utility.getDescrizioneLivelloAutorizzativo(input.getCodSistemaInformativo(), outbst.COD_LIV_AUTORIZ));
                    richiestaDeroga.setUserIdCapoAttivita(outbst.USERID_CAPO_ATTIV);
                    richiestaDeroga.setCapoAttivita(outbst.NOME_CAPO_ATTIV);
                    richiestaDeroga.setCodPiattaforma(outbst.COD_TIPO_PIATT);

                    richiestaDeroga.setEmailCapoAttivita(outbst.EMAIL_CAPO_ATTIV);
                    richiestaDeroga.setEmailRichiedente(outbst.EMAIL_RICHIED);
                    richiestaDeroga.setCodProgetto(outbst.COD_PROGETTO);
                    richiestaDeroga.setDescrizioneProgetto(outbst.DESC_PROGETTO);
                    richiestaDeroga.setDataDeroga(Utility.decodificaDataDaHost(outbst.DATA_DEROGA));
                    output.setRichiestaDeroga(richiestaDeroga);

                    //setto l'oggetto per l'output della pagina
                    if (outbst.TABUDC != null && outbst.TABUDC[0] != null) {

                        for (int i = 0; i < outbst.TABUDC.length; i++) {
                            TABUDC tabUdc = outbst.TABUDC[i];
                            SingoloUdc udc =
                                    new SingoloUdc(
                                    tabUdc.COD_UDC_O,
                                    tabUdc.COD_USERID_RESP,
                                    tabUdc.NOMINATIVO_USER_RESP,
                                    tabUdc.DESCR_UDC);
                            udc.setZona(tabUdc.COD_ZONA_UDC);
                            udc.setMotivoDeroga(tabUdc.MOTIVO_DEROGA);
                            elencoUdc.add(udc);
                        }
                        output.setElencoUdc(elencoUdc);
                    }
                }

            }else {
            	// non superata invocke
            	erroreOutputBS = new ErroreOutputBS(false, msgErrore);
        }
        } else {
			// non superata invocke
        	erroreOutputBS = new ErroreOutputBS(false, "Errore invoke servizio. "+msgErrore);
        }
        logger.debug(DBG_SEND_MSG + "Chiama_YUDAS00() - Messaggio da BS: " + erroreOutputBS.getMessaggioErrore());
		return output;
	}

	private void popolaInHeader(InputYUDAS00 input, C_YUDAS01 yuda) {
		yuda.INHEADER = new INHEADER[1];
        yuda.INHEADER[0] = new INHEADER();
        yuda.INHEADER[0].RETCODE = 0;
        yuda.INHEADER[0].CODICE_USERID = input.getUser().toUpperCase() + " ";
        yuda.INHEADER[0].ID_SERVIZIO = ID_SERVIZIO_YUDAS00;
        yuda.INHEADER[0].CODICE_TIPO_CANALE = "52";
        yuda.INHEADER[0].CODICE_SOCIETA = "01";
        yuda.INHEADER[0].CODICE_SPORTELLO = "00700";
        yuda.INHEADER[0].CODICE_UO_RICH = "00700";
        yuda.INHEADER[0].COD_RICH_CANALE = "000000000000000000000000000";    
        yuda.INHEADER[0].DATA_CONT = "24052006";
        yuda.INHEADER[0].LUNGHEZZA_MSG = 44;
        yuda.INHEADER[0].IND_MQ_SINCRONO = "S";
        bsUtility.writeLogBS(yuda.INHEADER, ID_SERVIZIO_YUDAS00, false);
	}
	
	private void popolaInpbst(InputYUDAS00 input, C_YUDAS01 yuda) {
		yuda.INPBST = new INPBST[1];
        yuda.INPBST[0] = new INPBST();
        // CAMPI TABELLA
        yuda.INPBST[0].COD_TIPO_SI = input.getCodSistemaInformativo();
        yuda.INPBST[0].COD_TIPO_PIATT = input.getCodTipoPiattaforma();
        yuda.INPBST[0].COD_PROGETTO = input.getCodProgetto();
        yuda.INPBST[0].COD_UDC_I = input.getCodUdc();
        yuda.INPBST[0].DATA_PRODUZIONE = input.getDataProduzione();
        yuda.INPBST[0].ID_DEROGA = input.getIdDeroga();
        bsUtility.writeLogBS(yuda.INPBST, ID_SERVIZIO_YUDAS00, true);
	}
    
}
