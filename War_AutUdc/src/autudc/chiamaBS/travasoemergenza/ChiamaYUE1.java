package autudc.chiamaBS.travasoemergenza;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import Utility.BSUtility;
import Utility.Constants;
import Utility.Utility;
import autudc.chiamaBS.ErroreOutputBS;
import autudc.chiamaBS.travasoemergenza.dto.Abilitazioni;
import autudc.chiamaBS.travasoemergenza.dto.ControlliTestata;
import autudc.chiamaBS.travasoemergenza.dto.DatiAutorizzazione;
import autudc.chiamaBS.travasoemergenza.dto.DatiIncidente;
import autudc.chiamaBS.travasoemergenza.dto.DatiRichiesta;
import autudc.chiamaBS.travasoemergenza.dto.DatiUtente;
import autudc.chiamaBS.travasoemergenza.dto.ElementoListaDettagli;
import autudc.chiamaBS.travasoemergenza.dto.MotivoEmergenza;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import autudc.swa.ProfiloSWA;

import com.dsi.business.SSA_YU.integration.jdo.P_YUE1S02.C_YUE1S02;
import com.dsi.business.SSA_YU.integration.jdo.P_YUE1S02.DET;
import com.dsi.business.SSA_YU.integration.jdo.P_YUE1S02.INHEADER;
import com.dsi.business.SSA_YU.integration.jdo.P_YUE1S02.INPBST;
import com.dsi.business.SSA_YU.integration.jdo.P_YUE1S02.OUTBST;

/**
 *
 *@author U0G8898
 */
public class ChiamaYUE1 {

    private Logger logger = null;
    private static final String ID_SERVIZIO_YUE1 = "YUE1ELEDET";
    private ErroreOutputBS erroreOutputBS = new ErroreOutputBS();
    private BSUtility bsUtility = new BSUtility();
    
    public ChiamaYUE1() {
        try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("Costruttore ChiamaYUE1");
    }
    
    public void chiamaBS(
    		HttpSession sessione,
    		ProfiloSWA userswa,
    		String sistemaInformativo,
    		String idRichiesta,
    		String tipoAutorizzazione
    	){
    	
    	initYUE1(sessione, userswa.getUserID(), sistemaInformativo, idRichiesta, tipoAutorizzazione);
        logger.info("ChiamaYUE1() - erroreOutputBS: " + erroreOutputBS.isEsito() + " " + erroreOutputBS.getMessaggioErrore());
        sessione.setAttribute("erroreOutputBS",erroreOutputBS);
    }
    
	private void initYUE1(HttpSession sessione, String utente, String sistemaInformativo, String idRichiesta, String tipoAutorizzazione) {
		
		boolean isSuperataInvoke = true;
        
		C_YUE1S02 yue1 = new C_YUE1S02();

		popolaTagInHeader(yue1, utente);

		popolaTagInpbst(yue1, utente, sistemaInformativo, idRichiesta);

        try {
			yue1.invoke();
		} catch (Exception e) {
        	e.printStackTrace();
			isSuperataInvoke = false;
            logger.error("ChiamaYUE1() - Errore chiamata connettore " + e.toString());
		}
		
		gestioneRispostaHost(sessione, yue1, sistemaInformativo,tipoAutorizzazione, isSuperataInvoke);
	}

	private void gestioneRispostaHost(
			HttpSession sessione, 
			C_YUE1S02 yue1,
			String sistemaInformativo, 
			String tipoAutorizzazione,
			boolean isSuperataInvoke) {
		
		String msgErrore = "";
		if (isSuperataInvoke) {
			
			boolean isOperazioneEseguita = false;
			boolean ciSonoDatiDiOutput = false;
			int OUTHEADER_RETCODE = yue1.OUTHEADER[0].RETCODE;
            logger.debug("gestioneRispostaHost() - RetCode BS: " + OUTHEADER_RETCODE);
			switch (OUTHEADER_RETCODE) {
			case 0:
				isOperazioneEseguita = true;
				if (yue1.OUTBST !=null && yue1.OUTBST[0] != null){
					ciSonoDatiDiOutput = true;
				}
                 break;
			case 12:
				msgErrore = yue1.OUTSEG[0].COD_SEGNALAZIONE+" "+yue1.OUTSEG[0].TXT_SEGNALAZIONE;
				break;
			case 16:
			case 20:
				msgErrore = yue1.OUTESI[0].ESITO;
				break;
			default:
				msgErrore = " gestioneRispostaHost (" + ID_SERVIZIO_YUE1 + ").OUTHEADER_RETCODE  non gestito:" + OUTHEADER_RETCODE;
			}

			if (isOperazioneEseguita) {
				erroreOutputBS = new ErroreOutputBS(true, "Operazione Eseguita");
				//setto l'oggetto per l'output della pagina
				if (yue1.OUTBST !=null && yue1.OUTBST[0] != null){
					if (ciSonoDatiDiOutput) {
						logger.debug("YUE1 "+yue1.toString());
						bsUtility.writeLogBS(yue1.OUTBST, ID_SERVIZIO_YUE1, true);
						caricaDaOutbst(sessione, yue1.OUTBST[0],sistemaInformativo,tipoAutorizzazione);
					}
				}
			}else{
				erroreOutputBS = new ErroreOutputBS(false, msgErrore);
				logger.error("YUE1 "+yue1.toString());
			}

		}else{
			// non superata invocke
			erroreOutputBS = new ErroreOutputBS(false, "Errore invoke servizio. "+msgErrore);
		}
	    logger.debug("gestioneRispostaHost() - Messaggio da BS: " + erroreOutputBS.getMessaggioErrore());
	}
	
	private void popolaTagInHeader(
			C_YUE1S02 yue1, 
			String utente) {
		yue1.INHEADER = new INHEADER[1];
		yue1.INHEADER[0] = new INHEADER();
		yue1.INHEADER[0].RETCODE = 0;
		yue1.INHEADER[0].CODICE_USERID = utente.trim();
		yue1.INHEADER[0].ID_SERVIZIO = ID_SERVIZIO_YUE1;
		yue1.INHEADER[0].CODICE_TIPO_CANALE = "52";
		yue1.INHEADER[0].CODICE_SOCIETA = "01";
		yue1.INHEADER[0].CODICE_SPORTELLO = "00700";
		yue1.INHEADER[0].CODICE_UO_RICH = "00700";
		yue1.INHEADER[0].COD_RICH_CANALE = "000000000000000000000000000";
		yue1.INHEADER[0].DATA_CONT = "24052006";
		yue1.INHEADER[0].LUNGHEZZA_MSG = 44;
		yue1.INHEADER[0].IND_MQ_SINCRONO = "S";
		bsUtility.writeLogBS(yue1.INHEADER, ID_SERVIZIO_YUE1, false);
	}

	private void popolaTagInpbst(
			C_YUE1S02 yue1, 
			String utente,
			String sistemaInformativo, 
			String idRichiesta) {
		
		yue1.INPBST = new INPBST[1];
		yue1.INPBST[0] = new INPBST();
		
		yue1.INPBST[0].SIST_INFORMATIVO = Utility.checkNull(sistemaInformativo);
//		yue1.INPBST[0].PROFILO_UTENTE = Utility.FillaCaratteri(utente.trim(), "0", "S", 6);
		yue1.INPBST[0].ID_EMERGENZA = Utility.FillaCaratteri(idRichiesta.trim(), "0", "S", 11);
		bsUtility.writeLogBS(yue1.INPBST, ID_SERVIZIO_YUE1, true);
	}

	private void caricaDaOutbst(
			HttpSession sessione, 
			OUTBST outbst, 
			String sistemaInformativo,
			String tipoAutorizzazione) {
		
		sessione.removeAttribute(Constants.PARAM_DATI_LISTA_DETTAGLI);
		sessione.removeAttribute(Constants.PARAM_DATI_CONTROLLO_TESTATA);
		
		String descSistemaInformativo= "";
		//abilitazioni
		//TODO [VS] quando sarà cambiato il bs yue1 completare la creazione dell'oggetto abilitazioni
		Abilitazioni abilitazioni = new Abilitazioni(
				Utility.checkNull(outbst.ABIL_CONFERMA).trim(),
				Utility.checkNull(outbst.ABIL_PUBBLICA).trim(),
				Utility.checkNull(outbst.ABIL_RESET).trim(),
				Utility.checkNull(outbst.ABIL_VARIA).trim(),
				Utility.checkNull("").trim(),//nuovo
				Utility.checkNull("").trim(),//duplica
				Utility.checkNull(outbst.ABIL_CANCELLA).trim(),
				Utility.checkNull(outbst.ABIL_RIEPILOGO).trim(), 
				Utility.checkNull(outbst.ABIL_AUTORIZZ_ATT).trim(),
				Utility.checkNull(outbst.ABIL_AUTORIZZ_RESP).trim()
		);
		
		//dati richiesta
		String codiceMotivoEmergenza = Utility.checkNull(outbst.COD_MOTIVO_EME).trim();
		String descrMotivoEmergenza = "";
		
		MotivoEmergenza motivoEmergenza = new MotivoEmergenza(
				codiceMotivoEmergenza, 
				descrMotivoEmergenza);
		
		DatiRichiesta datiRichiesta = new DatiRichiesta(
				Utility.checkNull(outbst.SSA_RICHIESTA).trim(), 
				Utility.checkNull(outbst.TIPO_RICHIESTA).trim(),
				Utility.checkNull(outbst.NUMERO_RICHIESTA_OUT).trim(),
				Utility.checkNull(outbst.STATO_RICHIESTA).trim(),
				Utility.checkNull(outbst.STATO_RICH_BREVE).trim(),
				Utility.checkNull(outbst.STATO_RICH_ESTESA).trim(), 
				Utility.decodificaDataDaHost(Utility.checkNull(outbst.DATA_ESECUZIONE).trim()),
				Utility.checkNull(outbst.DESCR_RICHIESTA).trim(),
				Utility.checkNull(outbst.TIMEST_ULT_AGG).trim(),
				motivoEmergenza
		);
		//dati incidente
		String codiceIncidente = Utility.checkNull(outbst.O_INCIDENTE).trim();
		String descrIncidente = Utility.checkNull(outbst.DESC_INCIDENTE).trim();
		String codiceProgetto =Utility.checkNull(outbst.PROGETTO).trim();
		String riferimentoIncidente =  Utility.checkNull(outbst.RIFERIMENTO_GAE).trim();
		DatiIncidente datiIncidente = new DatiIncidente(
				codiceProgetto, 
				codiceIncidente, 
				descrIncidente, 
				riferimentoIncidente
		);
		//dati richiedente
		DatiUtente richiedente = new DatiUtente(
				Utility.checkNull(Utility.checkNull(outbst.USER_RICHIEDENTE)).trim(), 
				Utility.checkNull(Utility.checkNull(outbst.NOME_RICHIEDENTE)).trim(), 
				Utility.checkNull(Utility.checkNull(outbst.PREFISSO_RICHIEDENTE)).trim()
					+" "+Utility.checkNull(Utility.checkNull(outbst.TEL_RICHIEDENTE)).trim(), 
				Utility.checkNull(Utility.checkNull(outbst.EMAIL_RICHIEDENTE)).trim()
		);
		//datiPubblicatore
		DatiUtente pubblicatore =  new DatiUtente(
				Utility.checkNull(Utility.checkNull(outbst.USERID_PUBBL)).trim(), 
				Utility.checkNull(Utility.checkNull(outbst.NOME_PUBBL)).trim(), 
				Utility.checkNull(Utility.checkNull(outbst.PREFISSO_PUBBL)).trim()
					+" "+Utility.checkNull(Utility.checkNull(outbst.TEL_PUBBL)).trim(), 
				Utility.checkNull(Utility.checkNull(outbst.EMAIL_PUBBL)).trim()
		);
		//dati autorizzazione
		String idAllegato = Integer.valueOf(outbst.ID_ALLEGATO_ATT).intValue()==0?"":String.valueOf(Integer.valueOf(outbst.ID_ALLEGATO_ATT).intValue());
		String nomeAllegato = "";
		String noteAutorizzazioneAttuatore = Utility.checkNull(Utility.checkNull(outbst.NOTE_AUTORIZ_ATTUATORE)).trim();
		String impattoDiBusiness =  Utility.checkNull(outbst.IMPATTO_BUSINESS).trim();
		String erroreBloccante =  Utility.checkNull(outbst.ERRORE_BLOCCANTE).trim();
		String idAtorizzEmergenza =  Utility.checkNull(outbst.ID_AUTORIZZ_EMERGENZA).trim();
		idAtorizzEmergenza = "".equals(idAtorizzEmergenza) ? "0" : idAtorizzEmergenza;

		DatiAutorizzazione datiAutorizzazione = new DatiAutorizzazione(
				idAllegato,
				nomeAllegato,
				noteAutorizzazioneAttuatore,
				impattoDiBusiness,
				erroreBloccante,
				idAtorizzEmergenza,
				tipoAutorizzazione);
		
		ControlliTestata controlliTestata = new ControlliTestata(
				sistemaInformativo,
				descSistemaInformativo,
				datiRichiesta, 
				datiIncidente,
				richiedente,
				pubblicatore,
				abilitazioni,
				datiAutorizzazione
				);
		sessione.setAttribute(Constants.PARAM_DATI_CONTROLLO_TESTATA, controlliTestata);
		
		LinkedList listaDettagli = new LinkedList();
		if (outbst.DET != null && outbst.DET[0] != null){
			DET[] listaDettagliBS = outbst.DET;
			for (int i=0; i<listaDettagliBS.length;i++){
				DET dettaglioBS = outbst.DET[i];
				ElementoListaDettagli elemento = new ElementoListaDettagli(
						Utility.checkNull(Utility.checkNull(dettaglioBS.PROGR_DETTAGLIO)).trim(),
						Utility.checkNull(Utility.checkNull(dettaglioBS.TIPO_DETTAGLIO)).trim(), 
						Utility.checkNull(Utility.checkNull(dettaglioBS.STATO_DETTAGLIO)).trim(),
						Utility.checkNull(Utility.checkNull(dettaglioBS.STATO_DETT_BREVE)).trim(), 
						Utility.checkNull(Utility.checkNull(dettaglioBS.STATO_DETT_ESTESO)).trim(),
						Utility.checkNull(Utility.checkNull(dettaglioBS.PRES_NOTE_ATTUATORE)).trim()
				);
				listaDettagli.add(elemento);
			}
		}
		sessione.setAttribute(Constants.PARAM_DATI_LISTA_DETTAGLI, listaDettagli);
	}

}

