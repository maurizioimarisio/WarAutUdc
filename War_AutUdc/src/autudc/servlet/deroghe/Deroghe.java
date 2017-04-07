package autudc.servlet.deroghe;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utility.Constants;
import Utility.Utility;
import autudc.chiamaBS.ChiamaYUDAS00;
import autudc.chiamaBS.ChiamaYUDCS00;
import autudc.chiamaBS.ErroreOutputBS;
import autudc.chiamaBS.travasoemergenza.ChiamaYUE1;
import autudc.chiamaBS.travasoemergenza.ChiamaYUE4;
import autudc.chiamaBS.ude.ChiamaYUZ0;
import autudc.chiamaBS.ude.ChiamaYUZN;
import autudc.chiamaBS.ude.dto.DatiUdc;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import autudc.swa.ProfiloSWA;



public class Deroghe extends Common {


	private static final long serialVersionUID = 1L;
	
	private Logger logger = null;
    private String paginadiRisposta = null;
    
    public Deroghe() {
    	try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug(" Costruttore Deroghe.. ");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		processRequest(request, response);
	}
	
    protected void processRequest(
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws ServletException, IOException {
    	
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessione = request.getSession();
        try {
	        String input = initFromInputParameters(sessione, request);

	        logger.debug(" User-Agent: " + request.getHeader("User-Agent"));
	        logger.debug(" Host: " + request.getHeader("Host"));
	        // Lettura utente da SWA e salvataggio dati in sessione
	        ProfiloSWA userswa = new ProfiloSWA();
	        userswa.processRequest(request, response);
	        sessione.setAttribute("userSWA", userswa);
	        String userAg = Utility.checkNull(request.getHeader("User-Agent"));
	        String host = Utility.checkNull(request.getHeader("Host"));
	        sessione.setAttribute("userAg", userAg);
	        sessione.setAttribute("host", host);
	        
	        
	        if (isParametriOk()) {
	        	String sistemaInform = getSistemaInformativo();
	        	String inpServletSession = ((String)sessione.getAttribute("inpServlet"));
	    		logger.info("inpServlet in session prima "+inpServletSession);
	        	if (!isEsisteSistemaInformativo() && inpServletSession !=null && inpServletSession.trim().length()>0 ){
	        		inpServletSession = inpServletSession.replaceAll(" ", "");
	        		logger.info("inpServlet in session dopo "+inpServletSession);
	        		sistemaInform = inpServletSession.substring(11, 13);
	        		if (!Utility.isNumeric(sistemaInform)){
	        			sistemaInform = "";
	        		}
	        		
	        	}
	        	logger.info("sistemaInform "+sistemaInform);
	
	        	// Chiamata a BS per recuperare i dati relativi alla richiesta da approvare o rifiutare
	        	if (isLista()) {
	        		
	        		sessione.removeAttribute("OutputBS");
	        		sessione.removeAttribute("InputBS");
	        		sessione.removeAttribute("erroreOutputBS");
	        		sessione.removeAttribute("inpServlet");
	
	        		sessione.setAttribute("inpServlet", input);
	
	        		//travaso emergenza
	            	if (Constants.TIPO_AUTORIZZAZIONE_COD_TRAVASO.equals(getTipoAutorizzazione())){
	            		new ChiamaYUE1().chiamaBS(sessione, userswa, getSistemaInformativo(), getId(), getTipoAutorizzazione());
	            		paginadiRisposta = "/Deroghe/ListaUdc.jsp";
	            	// autorizzazione ude
	            	} else if (Constants.TIPO_AUTORIZZAZIONE_COD_UDE.equals(getTipoAutorizzazione())){
	            		new ChiamaYUZ0().chiamaBS(sessione, userswa, getSistemaInformativo(), getId());
	            		paginadiRisposta = "/Deroghe/ListaUdc.jsp";
	            	// autorizzazione udu
	            	} else if (Constants.TIPO_AUTORIZZAZIONE_COD_UDU.equals(getTipoAutorizzazione())){
	            		new ChiamaYUZ0().chiamaBS(sessione, userswa, getSistemaInformativo(), getId());
	            		paginadiRisposta = "/Deroghe/ListaUdc.jsp";	
	            	//richiesta deroga
	            	} else if ("K".equals(getTipoAutorizzazione())){
	            		new ChiamaYUZ0().chiamaBS(sessione, userswa, getSistemaInformativo(), getId());
	            		paginadiRisposta = "/Deroghe/ListaUdc.jsp";	
	            	//richiesta ude Host	            		
	            	}else if (Constants.TIPO_AUTORIZZAZIONE_COD_DEROGA.equals(getTipoAutorizzazione()) 
	            			|| "".equals(getTipoAutorizzazione())){
	            		new ChiamaYUDAS00().chiamaYUDA(sessione, userswa, getId(), getSistemaInformativo());
	            		paginadiRisposta = "/Deroghe/ListaUdc.jsp";
	            		//richiesta autorizzazione inizio UAT
	            	} else if (Constants.TIPO_AUTORIZZAZIONE_COD_INIZIO.equals(getTipoAutorizzazione())){
	            		new ChiamaYUZ0().chiamaBSPerUAT(sessione, request,userswa,getSistemaInformativo(), getId(),"I");
	            		paginadiRisposta = "/Deroghe/ListaUdc.jsp";
	            	} else if  (Constants.TIPO_AUTORIZZAZIONE_COD_FINE.equals(getTipoAutorizzazione())){
	            		new ChiamaYUZ0().chiamaBSPerUAT(sessione, request,userswa,getSistemaInformativo(), getId(), "F");
	            		paginadiRisposta = "/Deroghe/ListaUdc.jsp";
	            	}
	            	
	            	//deroga
	            	else if ("C".equals(getTipoAutorizzazione())){	            		
	            			new ChiamaYUZ0().chiamaBS(sessione, userswa, getSistemaInformativo(), getId());
	            	  	paginadiRisposta = "/Deroghe/ListaUdc.jsp";	
	            	//richiesta blocco cut off	            		
	            	}else {
	            		ErroreOutputBS erroreOutputBS = new ErroreOutputBS(false, "Tipo Autorizzazione non gestito.");
	            		sessione.setAttribute("erroreOutputBS", erroreOutputBS);
	            		paginadiRisposta = "/Deroghe/errore/erroreFunzione.jsp";
	            	}
	                
	
	            // Chiamata a BS per aggiornamento richiesta
	            } else {
	            	
	                //travaso emergenza
	            	if (Constants.TIPO_AUTORIZZAZIONE_COD_TRAVASO.equals(getTipoAutorizzazione())){
	            		String tipoOperazione = "";
	            		if (Constants.TIPO_RICHIESTA_APPROVA.equals(getTipoRichiesta())){
	            			tipoOperazione = "T";
	            		} else if (Constants.TIPO_RICHIESTA_RIFIUTA.equals(getTipoRichiesta())){
	            			tipoOperazione = "R";
	            		}
	            		if(sessione.getAttribute("controlliTestata")==null){
	            			//se sono nella pagina di test devo recuperare il numero richiesta dallo yue1
	            			if (!"".equals(sistemaInform)){
	            				new ChiamaYUE1().chiamaBS(sessione, userswa, sistemaInform, getId(), getTipoAutorizzazione());
	            			} else {
	            				sessione.setAttribute("erroreOutputBS", new ErroreOutputBS(false, "Operazione non valida."));
	            			}
	            		}
	            		new ChiamaYUE4().chiamaBS(sessione, userswa, tipoOperazione);
	            		paginadiRisposta = "/Deroghe/risposta.jsp";
	            		
	           		// autorizzazione ude
	            	} else if (Constants.TIPO_AUTORIZZAZIONE_COD_UDE.equals(getTipoAutorizzazione())){
	            		new ChiamaYUZN().chiamaBS(sessione, request, userswa, sistemaInform, getTipoRichiesta(), getTipoAutorizzazione());
	            		paginadiRisposta = "/Deroghe/risposta.jsp";
	            		
	               // autorizzazione ude host
	            	}else if(Constants.TIPO_AUTORIZZAZIONE_COD_UDE_HOST.equals(getTipoAutorizzazione())){
	            		new ChiamaYUZN().chiamaBS(sessione, request, userswa, sistemaInform, getTipoRichiesta(), getTipoAutorizzazione());
	            		paginadiRisposta = "/Deroghe/risposta.jsp";
	            	}else if (Constants.TIPO_AUTORIZZAZIONE_COD_INIZIO.equals(getTipoAutorizzazione()) || Constants.TIPO_AUTORIZZAZIONE_COD_FINE.equals(getTipoAutorizzazione())){
	            		new ChiamaYUZN().chiamaBS(sessione, request, userswa, sistemaInform, getTipoRichiesta(), getTipoAutorizzazione());
	            		paginadiRisposta = "/Deroghe/risposta.jsp";
	            	}
		           	// autorizzazione udu
	            	else if ("C".equals(getTipoAutorizzazione())){
	            		String fsc =  request.getParameter("flC") == null ? "" : request.getParameter("flC");
	            		sessione.setAttribute("flC", fsc);
	            		new ChiamaYUZN().chiamaBS(sessione, request, userswa, sistemaInform, getTipoRichiesta(), getTipoAutorizzazione());
	            		paginadiRisposta = "/Deroghe/risposta.jsp";
		           	} else if (Constants.TIPO_AUTORIZZAZIONE_COD_UDU.equals(getTipoAutorizzazione())){
		           		new ChiamaYUZN().chiamaBS(sessione, request, userswa, sistemaInform, getTipoRichiesta(), getTipoAutorizzazione());
		           		paginadiRisposta = "/Deroghe/risposta.jsp";
	            		
	            	//richiesta deroga
	            	} else if (Constants.TIPO_AUTORIZZAZIONE_COD_DEROGA.equals(getTipoAutorizzazione()) 
	            			|| "".equals(getTipoAutorizzazione())){
	            		if (isEsisteid() && isEsisteTipoRichiesta() && isEsisteTipoAutorizzazione()) {
	            			new ChiamaYUDCS00().ChiamaYUDC(sessione, userswa, sistemaInform, getTipoAutorizzazione(), getId(), getTipoRichiesta());
	            		} else {
	            			sessione.setAttribute("messErr", "Dati passatti non corretti.");
	            		}
	            		paginadiRisposta = "/Deroghe/risposta.jsp";
	            		
	            	} else {
	            		ErroreOutputBS erroreOutputBS = new ErroreOutputBS(false, "Tipo Autorizzazione non gestito.");
	            		sessione.setAttribute("erroreOutputBS", erroreOutputBS);
	            		paginadiRisposta = "/Deroghe/errore/erroreFunzione.jsp";
	            	}
	                
	            }
	
	        } else {
	            paginadiRisposta = "/Deroghe/ListaUdc.jsp";
	        }
    	} catch (Exception e) {
    		logger.error("processRequest() - Errore " + e.getStackTrace());
    		ErroreOutputBS erroreOutputBS = (ErroreOutputBS)sessione.getAttribute("erroreOutputBS");
    		if (erroreOutputBS!=null && !erroreOutputBS.isEsito()){
    			if (erroreOutputBS.isEsito()){
    				erroreOutputBS = new ErroreOutputBS(false, e.getMessage());
	    		}  else {
	    			erroreOutputBS.setMessaggioErrore(erroreOutputBS.getMessaggioErrore()+ "\n" + e.getMessage());
	    		}
    		}
    		paginadiRisposta = "/Deroghe/errore/erroreFunzione.jsp";
		} finally{
			logger.debug("processRequest() - Output inviato a " + paginadiRisposta);
			getServletContext().getRequestDispatcher(paginadiRisposta).forward(request, response);
		}
    }

	public boolean isEsisteid() {
		return super.isEsisteid();
	}

	public boolean isEsisteSistemaInformativo() {
		return super.isEsisteSistemaInformativo();
	}
	
	public boolean isEsisteTipoRichiesta() {
		return super.isEsisteTipoRichiesta();
	}

	public boolean isEsisteTipoAutorizzazione() {
		return super.isEsisteTipoAutorizzazione();
	}

	public boolean isLista() {
		return super.isLista();
	}

	public boolean isParametriOk() {
		return super.isParametriOk();
	}

	public String getId() {
		return super.getId();
	}

	public String getSistemaInformativo() {
		return super.getSistemaInformativo();
	}

	public String getTipoAutorizzazione() {
		return super.getTipoAutorizzazione();
	}

	public String getTipoRichiesta() {
		return super.getTipoRichiesta();
	}

}
