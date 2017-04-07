<%@ page language="java" autoFlush="true" pageEncoding="UTF-8" contentType="text/html;charset=ISO-8859-1" %>

<%@page import="java.util.StringTokenizer"%>
<%@page import="autudc.chiamaBS.travasoemergenza.dto.Abilitazioni"%>
<%@page import="autudc.chiamaBS.travasoemergenza.dto.ControlliTestata"%>
<%@page import="autudc.chiamaBS.travasoemergenza.dto.DatiAutorizzazione"%>
<%@page import="autudc.chiamaBS.travasoemergenza.dto.DatiIncidente"%>
<%@page import="autudc.chiamaBS.travasoemergenza.dto.DatiRichiesta"%>
<%@page import="autudc.chiamaBS.travasoemergenza.dto.DatiUtente"%>
<%@page import="autudc.chiamaBS.travasoemergenza.dto.ElementoListaDettagli"%>
<%@page import="autudc.chiamaBS.travasoemergenza.dto.MotivoEmergenza"%>

<jsp:useBean id="userSWA" scope="session" class="autudc.swa.ProfiloSWA" />
<jsp:useBean id="inpServlet" scope="session" class="java.lang.String" />
<jsp:useBean id="erroreOutputBS" scope="session" class="autudc.chiamaBS.ErroreOutputBS" />
<jsp:useBean id="controlliTestata" scope="session" class="autudc.chiamaBS.travasoemergenza.dto.ControlliTestata" />
<jsp:useBean id="messErr" scope="session" class="java.lang.String"/>

<%

StringTokenizer str = new StringTokenizer(inpServlet==null?"":inpServlet);
String inputId = (str.hasMoreTokens()) ? str.nextToken(" ") : "";
String inputSistemaInformativo = (str.hasMoreTokens()) ? str.nextToken(" ") : "";
String inputTipoAutorizzazione = (str.hasMoreTokens()) ? str.nextToken(" ") : "";

String codiceSistemaInformativo = inputSistemaInformativo;
//dati Richiesta
String codiceSsa = "";
String tipoRichiesta = "";
String descrTipoRichiesta = "";
String numeroRichiesta = "";
String codiceStatoRichiesta = "";
String descrStatoRichiestaBreve = "";
String descrStatoRichiestaEsteso = "";
String dataRichiesta = "";
String descrRichiesta = "";
String timeStampUltimoAggiornamento = "";
String codiceMotivoEmergenza = "";
String descrMotivoEmergenza = "";
//dati incidente
String codiceProgetto = "";
String codiceIncidente = "";
String descrIncidente = "";
String riferimentoIncidente = "";
//dati Richiedente
String utenteRichiedente = "";
String nomeRichiedente = "";
String telefonoRichiedente = "";
String mailRichiedente = "";
//dati Pubblicatore
String utentePubblicatore = "";
String nomePubblicatore = "";
String telefonoPubblicatore = "";
String mailPubblicatore = "";
// dati Autorizzazione
String idAllegato = "";
String nomeAllegato = "";
String noteAutorizzazioneAttuatore = "";
String impattoDiBusiness =  "";
String erroreBloccante =  "";
String idAutorizzEmergenza =  "";
//abilitazioni
String abilitaConferma = "";
String abilitaPubblica = "";
String abilitaReset = "";
String abilitaVaria = "";
String abilitaNuovaAttivita = "";
String abilitaDuplica = "";
String abilitaCancella = "";
String abilitaRiepilogoTravasoGae = "";
String abilitaAutorizzAttuatore = "";
String abilitaAutorizzResponsabile = "";

if (controlliTestata!= null){
	codiceSistemaInformativo = controlliTestata.getCodiceSistemaInformativo().trim().length()>0 ? controlliTestata.getCodiceSistemaInformativo() : inputSistemaInformativo;
	//dati Richiesta
	codiceSsa = controlliTestata.getDatiRichiesta().getCodiceSsa();
	tipoRichiesta = controlliTestata.getDatiRichiesta().getTipoRichiesta();
	numeroRichiesta = controlliTestata.getDatiRichiesta().getNumeroRichiesta();
	codiceStatoRichiesta = controlliTestata.getDatiRichiesta().getCodiceStatoRichiesta();
	descrStatoRichiestaBreve = controlliTestata.getDatiRichiesta().getDescrStatoRichiestaBreve();
	descrStatoRichiestaEsteso = controlliTestata.getDatiRichiesta().getDescrStatoRichiestaEsteso();
	dataRichiesta = controlliTestata.getDatiRichiesta().getDataRichiesta();
	descrRichiesta = controlliTestata.getDatiRichiesta().getDescrRichiesta();
	timeStampUltimoAggiornamento = controlliTestata.getDatiRichiesta().getTimeStampUltimoAggiornamento();
	codiceMotivoEmergenza = controlliTestata.getDatiRichiesta().getMotivoEmergenza().getCodiceEmergenza();
	descrMotivoEmergenza = controlliTestata.getDatiRichiesta().getMotivoEmergenza().getDescrEmergenza();
	//dati incidente
	codiceProgetto = controlliTestata.getDatiIncidente().getCodiceProgetto();
	codiceIncidente = controlliTestata.getDatiIncidente().getCodiceIncidente();
	descrIncidente = controlliTestata.getDatiIncidente().getDescrIncidente();
	riferimentoIncidente = controlliTestata.getDatiIncidente().getRiferimentoIncidente();
	//dati Richiedente
	utenteRichiedente = controlliTestata.getDatiRichiedente().getUtente();
	nomeRichiedente = controlliTestata.getDatiRichiedente().getNome();
	telefonoRichiedente = controlliTestata.getDatiRichiedente().getTelefono();	
	mailRichiedente = controlliTestata.getDatiRichiedente().getMail();
	//dati Pubblicatore
	utentePubblicatore = controlliTestata.getDatiPubblicatore().getUtente();
	nomePubblicatore = controlliTestata.getDatiPubblicatore().getNome();
	telefonoPubblicatore = controlliTestata.getDatiPubblicatore().getTelefono();	
	mailPubblicatore = controlliTestata.getDatiPubblicatore().getMail();
	//dati Autorizzazione
	idAllegato = controlliTestata.getDatiAutorizzazione().getIdAllegato();
	nomeAllegato = controlliTestata.getDatiAutorizzazione().getNomeAllegato();
	noteAutorizzazioneAttuatore = controlliTestata.getDatiAutorizzazione().getNoteAutorizzazioneAttuatore();
	impattoDiBusiness =  controlliTestata.getDatiAutorizzazione().getImpattoDiBusiness();
	erroreBloccante =  controlliTestata.getDatiAutorizzazione().getErroreBloccante();
	idAutorizzEmergenza =  controlliTestata.getDatiAutorizzazione().getIdIAutorizzEmergenza();
	//abilitazioni
	abilitaConferma = controlliTestata.getAbilitazioni().getAbilitaConferma();
	abilitaPubblica = controlliTestata.getAbilitazioni().getAbilitaPubblica();
	abilitaReset = controlliTestata.getAbilitazioni().getAbilitaReset();
	abilitaVaria = controlliTestata.getAbilitazioni().getAbilitaVaria();
	abilitaNuovaAttivita = controlliTestata.getAbilitazioni().getAbilitaNuovo();
	abilitaDuplica = controlliTestata.getAbilitazioni().getAbilitaDuplica();
	abilitaCancella = controlliTestata.getAbilitazioni().getAbilitaCancella();
	abilitaRiepilogoTravasoGae = controlliTestata.getAbilitazioni().getAbilitaRiepilogoTravasoGae();
	abilitaAutorizzAttuatore = controlliTestata.getAbilitazioni().getAbilitaAutorizzAttuatore();
	abilitaAutorizzResponsabile = controlliTestata.getAbilitazioni().getAbilitaAutorizzResponsabile();
	
} else {
	controlliTestata = new ControlliTestata(
			codiceSistemaInformativo, 
			"", 
			new DatiRichiesta(),
			new DatiIncidente(), 
			new DatiUtente(), 
			new DatiUtente(),
			new Abilitazioni(),
			new DatiAutorizzazione()
			);
}

Boolean isReadOnly = Boolean.FALSE;
String readonly = "";
String disabled = "";
if (isReadOnly.booleanValue()) {
    readonly = "readonly=\"readonly\"";
    disabled = "disabled=\"disabled\"";
}

String esitoBs = "";
if (erroreOutputBS.isEsito()) {
	esitoBs = " Operazione eseguita";
} else {
	esitoBs = "Errore in evasione richiesta: "+ erroreOutputBS.getMessaggioErrore();
}
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Esito Travaso Emergenza</title>
          <link rel=stylesheet href="/portalChci0/css/changeConsole.css" type="text/css" media="screen">
        <link rel=stylesheet href="/portalChci0/css/Testi.css" type="text/css" media="screen">


        <style type="text/css">

            body
            {
                background-color:#EFEFEF;
                margin-left: 0px;
                margin-top: 0px;
                margin-right: 0px;
                margin-bottom: 0px;
                position:absolute;
                color:#000000;

            }

            .TitlePage
            {

                filter:progid:DXImageTransform.Microsoft.Gradient(
                    GradientType=0,StartColorStr='#47A1A8',EndColorStr='#EFEFEF');


            }
        </style>
        
        <script type="text/javascript">
        
        function initPage() {
        	var messErr = "<%=messErr%>";
			    if (messErr!='') {
			      alert("<%=messErr.replace('"',' ')%>");
			<%    session.removeAttribute("messErr");  %>
			    }
		}
        
        </script>


    </head>
    <body class="bground_Grigio_MIO" onload="javascript:initPage();">
        <!--Tabelle con il titolo -->
           <table class="TitlePage" name="TITOLO_PAGE" id="TITOLO_PAGE" width="100%"  cellpadding=0 cellspacing=0 >
            <tr height=25>
                <td width="16%" align=left>
                    <table width=100% valign=center cellpadding=0 cellspacing=0 >
                        <tr>
                            <td>
                                <IMG src="/portalChci0/img/imageSanPaoloimi.jpg">
                            </td>
                        </tr>
                    </table>
                </td>

                <td valign=top align=center width="12%" nowrap>
                    <font size=2 style="FONT-SIZE:8pt;font-family:Georgia"><I><B>Change Console</B></I></font>
                </td>
                <td valign=top align=center width="12%">
                    <img height="25px" src="/portalChci0/img/logoIntegrata.gif">
                </td>

                <td align=left class="text_header" width="55%" valign=top>

                    <table width="100%" >
                        <tr>
                            <td align=center class="text_header" valign=top>&nbsp;

                            </td>
                        </tr>
                        <tr>
                            <td align=left class="text_header" valign=top>
                                &nbsp;

                            </td>
                        </tr>
                    </table>

                </td>

                <td align=center width=5% nowrap valign=top align=center>
                    &nbsp;

                </td>
            </tr>
            <tr>
	<td colspan=5 align=center>

	<font style="FONT-SIZE:11pt;FONT-FAMILY: Georgia,Times New Roman,Verdana;"><B><I>Esito Travaso Emergenza</I></B></font>

	</td>
        </tr>
        </table>

        <table width="100%" cellspacing="0" cellpadding="0">
            <br>
            <tr valign=center>
                <td  class="text_label">&nbsp;</td>
                <td  class="text_label">Numero Richiesta</td>
                <td  class="text_label"><b><%=codiceIncidente+"/"+numeroRichiesta%> </b>
                </td>
            </tr>

            <tr valign=center>
                <td  class="text_label">&nbsp;</td>
                <td class="text_label">Esito</td>
                <td class="text_label"><b><%=esitoBs%></b>
                </td>
        </table>

        <br>
        <table align="center">
                        <tr>

                            <td align="center">
                                &nbsp;
                            </td>
                        </tr>

                    </table>
        

    </body>
</html>
