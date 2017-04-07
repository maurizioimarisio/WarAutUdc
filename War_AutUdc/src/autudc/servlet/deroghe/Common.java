package autudc.servlet.deroghe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Utility.Constants;
import autudc.chiamaBS.ErroreOutputBS;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;

public class Common extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	
    private String tipoRichiesta = "";
    private String id = "";
    private String sistemaInformativo = "";
    private String tipoAutorizzazione = "";
    private boolean lista = false;
    private boolean esisteid = false;
    private boolean esisteSistemaInformativo = false;
    private boolean esisteTipoRichiesta = false;
    private boolean esisteTipoAutorizzazione = false;
    private boolean parametriOk = false;

    public Common() {
    	try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug(" Costruttore Common ");
	}

	protected void initServlet(HttpSession sessione){
		sistemaInformativo = "";
		esisteSistemaInformativo = false;
    	id = "";
    	esisteid = false;
    	tipoRichiesta = "";
    	esisteTipoRichiesta = false;
        tipoAutorizzazione = "";
        esisteTipoAutorizzazione = false;
        parametriOk = false;
        lista = false;
        sessione.removeAttribute("OutputBS");
		sessione.removeAttribute("InputBS");
		sessione.removeAttribute("erroreOutputBS");
		String urlRichiestaDeroga = System.getProperty("DD_CHCI0_URL_RICHIESTA_DEROGA");
		logger.info("urlRichiestaDerogaRifiuta-->"+urlRichiestaDeroga);
		sessione.setAttribute("urlRichiestaDeroga", urlRichiestaDeroga);
    }
    
	protected String initFromInputParameters(
			HttpSession sessione,
			HttpServletRequest request) 
	throws ServletException {
		
		initServlet(sessione);
		//acquisizione stringa composta da id, approva (OK, KO), tipo autorizzazione (D, T, E)
		String params = request.getParameter("id") == null ? "" : request.getParameter("id").toUpperCase();
		parametriOk = false;
		esisteid = false;

        // Acquisizione Id Richiesta
		if (params.length()==13 || params.length()==14) {
	    	esisteid = true;
	        id = params.substring(0,11);
	        if (params.indexOf(Constants.TIPO_RICHIESTA_APPROVA)!=-1 || params.indexOf(Constants.TIPO_RICHIESTA_RIFIUTA)!=-1){
	        	lista = false;
	        	tipoRichiesta = params.substring(11, 13);
	        	esisteTipoRichiesta = true;
	        	sistemaInformativo = request.getParameter("sistemaInformativo") == null ? "" : request.getParameter("sistemaInformativo");
	            if (sistemaInformativo.length()!=2 ) {
	                esisteSistemaInformativo = false;
	            } else {
	                esisteSistemaInformativo = true;
	            }
	        } else {
	        	lista = true;
	        	sistemaInformativo = params.substring(11, 13);
	        	esisteSistemaInformativo = true;
	        }

	        if (!esisteSistemaInformativo){
				sistemaInformativo = request.getParameter("sistemaInformativo")!=null ? (String) request.getParameter("sistemaInformativo") : "";
			}
	        
	        if (params.length() == 14){
            	esisteTipoAutorizzazione = true;
            	tipoAutorizzazione = params.substring(13, 14);
            } else {
            	esisteTipoAutorizzazione = true;
            	tipoAutorizzazione = getCodiceTipoAutorizzazione(Constants.TIPO_AUTORIZZAZIONE_DESCR_DEROGA);
            }
        } else {
        	ErroreOutputBS erroreOutputBS = new ErroreOutputBS(false, "Parametri passati in input non validi: >>>" + params + "<<< ");
            sessione.setAttribute("erroreOutputBS", erroreOutputBS);
        }
		
        if (esisteTipoAutorizzazione && esisteid 
        	&& (esisteTipoRichiesta || esisteSistemaInformativo)
        ) {
            parametriOk = true;
        }

        String input = id + " " + tipoRichiesta + " " + sistemaInformativo + " " + tipoAutorizzazione;
        if (esisteTipoRichiesta && esisteSistemaInformativo){
        	input = id + " " + tipoRichiesta + " " + tipoAutorizzazione;
        }
        logger.debug("Input: " + input);
		return input;
	}
	
	/**
	 * Ghiglieri: gestione UDU  - cfr Utility/Constants.java
	 *  
	 * @param il codice autorizzazione tra
	 * D = Deroga
	 * T = Travaso Emergenza
	 * E = Ude 
	 * U = Udu
	 * 
	 * @return la descrizione breve tra
	 * DER = Deroga
	 * TRA = TravasoEmergenza
	 * UDE = UDe
	 * UDU = UDU
	 */
	protected String getDescrBreveTipoAutorizzazione(String codiceAutorizzazione){
		
		String tipoAutorizzazione = "D";
		if (Constants.TIPO_AUTORIZZAZIONE_COD_TRAVASO.equals(codiceAutorizzazione)){
			tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_DESCR_DEROGA;
		} else if (Constants.TIPO_AUTORIZZAZIONE_COD_UDE.equals(codiceAutorizzazione)){
			tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_DESCR_UDE;
		} else if (Constants.TIPO_AUTORIZZAZIONE_COD_UDU.equals(codiceAutorizzazione)){
			tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_DESCR_UDU;
		} else if (Constants.TIPO_AUTORIZZAZIONE_COD_DEROGA.equals(codiceAutorizzazione)){
			tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_DESCR_DEROGA;
		}
		else if (Constants.TIPO_AUTORIZZAZIONE_SBLOCCO.equals(codiceAutorizzazione)){
			tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_SBLOCCO;
		}
		return tipoAutorizzazione;
	}
	
	/**
	 * Ghiglieri: gestione UDU  - cfr Utility/Constants.java
	 * 
	 * @param la descrizione breve tra
	 * DER = Deroga
	 * TRA = TravasoEmergenza
	 * UDE = UDe
	 * UDU = UDu
	 * 
	 * @return il codice autorizzazione tra
	 * D = Deroga
	 * T = Travaso Emergenza
	 * E = Ude 
	 * U = Udu
	 * 
	 */
	protected String getCodiceTipoAutorizzazione(String descrBreveAutorizzazione){
		
		String tipoAutorizzazione = "DER";
		if (Constants.TIPO_AUTORIZZAZIONE_DESCR_DEROGA.equals(descrBreveAutorizzazione)){
			tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_COD_DEROGA;
		} else if (Constants.TIPO_AUTORIZZAZIONE_DESCR_UDE.equals(descrBreveAutorizzazione)){
			tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_COD_UDE;
		} else if (Constants.TIPO_AUTORIZZAZIONE_DESCR_UDU.equals(descrBreveAutorizzazione)){
			tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_COD_UDU;
		} else if (Constants.TIPO_AUTORIZZAZIONE_DESCR_DEROGA.equals(descrBreveAutorizzazione)){
			tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_COD_DEROGA;
		}
		else if (Constants.TIPO_AUTORIZZAZIONE_SBLOCCO.equals(descrBreveAutorizzazione)){
				tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_SBLOCCO;
		}
		else if (Constants.TIPO_AUTORIZZAZIONE_COD_FINE.equals(descrBreveAutorizzazione)){
				tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_COD_FINE;
				
			}
		else if (Constants.TIPO_AUTORIZZAZIONE_COD_INIZIO.equals(descrBreveAutorizzazione)){
			   tipoAutorizzazione = Constants.TIPO_AUTORIZZAZIONE_COD_INIZIO;
		}
	
		return tipoAutorizzazione;
	}

	public String getTipoRichiesta() {
		return tipoRichiesta;
	}

	public String getId() {
		return id;
	}

	public String getSistemaInformativo() {
		return sistemaInformativo;
	}

	public String getTipoAutorizzazione() {
		return tipoAutorizzazione;
	}

	public boolean isLista() {
		return lista;
	}

	public boolean isEsisteid() {
		return esisteid;
	}

	public boolean isEsisteSistemaInformativo() {
		return esisteSistemaInformativo;
	}
	
	public boolean isEsisteTipoRichiesta() {
		return esisteTipoRichiesta;
	}

	public boolean isEsisteTipoAutorizzazione() {
		return esisteTipoAutorizzazione;
	}

	public boolean isParametriOk() {
		return parametriOk;
	}

}
