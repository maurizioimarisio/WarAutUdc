/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autudc.swa;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utility.Utility;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;


/**
 *
 * @author GF001126
 */
public class ProfiloSWA extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	private String SWAuserId = null;
    private String SWAuserIdentity = null;
    private String SWAsvcUrl = null;
    private String SWAprofileId = null;
    private StringBuffer diagn = null;
    private Logger myLogger = null;
    private static final String DBG_SEND_MSG = "In ProfiloSWA";

    public ProfiloSWA() {


        try {
            if (myLogger == null) {
                String pkgName = this.getClass().getPackage().getName();
                myLogger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        myLogger.debug(DBG_SEND_MSG + "Costruttore ProfiloSWA");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	
        diagn = new StringBuffer();
        diagn.append("\n***************************************************************************************************");
        diagn.append("\n************************************ PROFILASWA ***************************************************");
        diagn.append("\n***************************************************************************************************");
        diagn.append("\nDati di sessione: ");

        String userIdentity = "userIdentity";
        String userHeader = "userId";
        String sprofileId = "profileId";
        String urlHeader = "svcUrl";
        
        SWAprofileId = request.getHeader(sprofileId);
        if (SWAprofileId == null) {
            SWAuserId = "";
        }

        try {
            SWAuserIdentity = request.getHeader(userIdentity);
            if (SWAuserIdentity == null) {
                SWAuserIdentity = "";
            }
        } catch (Exception e) {
            diagn.append("\nEccezione: " + e.getMessage());
        }
        Utility.checkNull(SWAuserIdentity);
        
        SWAuserId = request.getHeader(userHeader);
        String AlluserId = SWAuserId;
        if (SWAuserId != null && SWAuserId.length() >= 7) {
            SWAuserId = SWAuserId.substring(SWAuserId.length() - 7);
            SWAuserId = SWAuserId.toUpperCase();
        }
        if (SWAuserId == null) {
            SWAuserId = "";
            AlluserId = SWAuserId;
        }

        SWAsvcUrl = request.getHeader(urlHeader);
        if (SWAsvcUrl == null) {
            SWAsvcUrl = "";
        }
        
        if ( SWAprofileId==null && SWAuserIdentity.equals("") && SWAsvcUrl.equals("") && SWAuserId.equals("")){
			
        	SWAprofileId=System.getProperty("DD_CHCI0_POSV_ProfileId");
        	SWAuserIdentity=System.getProperty("DD_CHCI0_POSV_UserIdentity");
        	SWAsvcUrl=System.getProperty("DD_CHCI0_POSV_SvcUrl");
        	SWAuserId=System.getProperty("DD_CHCI0_POSV_UserId");
			AlluserId=SWAuserId;
			diagn.append("\nProfiliSwa ATTENZIONE PARAMETRI LETTI PER LOCALE DA VARIABILI D'AMBIENTE");	
		}

        diagn.append("\n userId: " + SWAuserId);
        diagn.append("\n userIdentity: " + SWAuserIdentity);
        diagn.append("\n svcUrl: " + SWAsvcUrl);
        diagn.append("\n profileId: " + SWAprofileId);
        myLogger.debug(DBG_SEND_MSG + "processRequest() " + diagn.toString());

        // Inizio prove per recupero profili
        /*
        WebConversation wc = null;
        WebRequest req = null;
        WebResponse resp = null;

        try {
            wc = new WebConversation();
            SWAsvcUrl = SWAsvcUrl + "/GetProfile2";
            myLogger.debug(SWAuserId + "| ProfiliSwa Login Indirizzo completo : " + SWAsvcUrl);

            req = new GetMethodWebRequest(SWAsvcUrl);
            req.setParameter("Token", AlluserId);
            req.setParameter("SiteName", "");
            req.setParameter("TimeStamp", "");
            req.setParameter("EncodedPwd", "");
            req.setParameter("EncodedType", "");
            req.setParameter("Filter", "");
            resp = wc.getResponse(req);
            String rispostaXml = resp.getText();

            myLogger.debug(SWAuserId + "| ProfiliSwa rispostaXml= " + rispostaXml);
        } catch (Exception e) {
            myLogger.error(SWAuserId + "| ProfiliSwa Login Errore effettuando il login!");
            e.printStackTrace();
        }
        */


    }

    public String getUserID() {

        return SWAuserId;
    }

    public String getUserIdentity() {

        return SWAuserIdentity;
    }

    public String getDiagn() {

        return diagn.toString();
    }

    public String getUrl() {

        return SWAsvcUrl;
    }

    public String getProfileID() {

        return SWAprofileId;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
