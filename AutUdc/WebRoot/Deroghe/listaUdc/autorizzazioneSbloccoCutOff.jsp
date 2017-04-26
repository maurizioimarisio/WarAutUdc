<%@ page language="java" autoFlush="true" pageEncoding="UTF-8" contentType="text/html;charset=ISO-8859-1" %>

<%@page import="autudc.chiamaBS.RichiestaDeroga"%>
<%@page import="autudc.chiamaBS.SingoloUdc"%>
<%@page import="autudc.chiamaBS.OutputYUDAS00"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.LinkedList"%>

<jsp:useBean id="userSWA" scope="session" class="autudc.swa.ProfiloSWA" />
<jsp:useBean id="urlRichiestaDeroga" scope="session" class="java.lang.String" />
<jsp:useBean id="flagCutOff" scope="session" class="java.lang.String" />
<jsp:useBean id="inpServlet" scope="session" class="java.lang.String" />
<jsp:useBean id="udc" scope="session" class="autudc.chiamaBS.ude.dto.Udc" />
<jsp:useBean id="erroreOutputBS" scope="session" class="autudc.chiamaBS.ErroreOutputBS" />


<%
String userIdDestinatoario = "";
String destinatario = "";
String livelloAutorizzativo = "";

String codSistemaInformativo = "";
String codPiattaforma = "";
String codUdc = "";
String codProgetto = "";
String dataProduzione = "";

StringTokenizer str = new StringTokenizer(inpServlet==null?"":inpServlet);
String inputId = "";
String inputSistemaInformativo = "";
String inputTipoAutorizzazione = "";
if (inpServlet.length()>0){
	inputId = (str.hasMoreTokens()) ? str.nextToken(" ") : "";
	inputSistemaInformativo = (str.hasMoreTokens()) ? str.nextToken(" ") : "";
	inputTipoAutorizzazione = (str.hasMoreTokens()) ? str.nextToken(" ") : "";
}

//DATI UDC
String codiceUdc = "";
//DATI RESPONSABILE
String utenteResponsabile = "";
String nomeResponsabile = "";
String prefissoResponsabile = "";
String telefonoResponsabile = "";
String telefonoCompletoResponsabile = "";
//DATE DI DISTRIBUZIONE
String dataCreazione = "";
String dataSt = "";
String dataIt = "";
String dataDistrAddestr = "";
String dataPp = "";
String dataPpPila1 = "";
String dataPpPila2 = "";
String distribuzioneDifferita = "";
String dataDifferita = "";
String codiceVarDataPp = "";
String descrVarDataPp = "";
String ggInizioTest = "";
String dataEsitoDistr = "";
String oraLimiteEsito = "";
String dataLimitePassaggioProduzione = "";
String dataPrimoColl = "";
String dataPrimoIt = "";
//DATI EMERGENZA
String flagSbloccoUde = "";
String orario = "00.00";
String idEmergenza = "";
String profiloUtente = "";
String idAllegatoAutorizzazione = "";
String flagImpattoDiBusiness = "";
String flagErroreBloccante = "";
String codiceMotivoEmergenza = "";
String descrizioneMotivoEmergenza = "";
if (udc.getDatiUDc().getCodiceUdc().trim().length()>0){
	//DATI UDC
	codiceUdc = udc.getDatiUDc().getCodiceUdc();
	//DATI RESPONSABILE
	utenteResponsabile = udc.getDatiUtenteResponsabile().getUtente();
	nomeResponsabile = udc.getDatiUtenteResponsabile().getNome();
	prefissoResponsabile = udc.getDatiUtenteResponsabile().getPrefisso();
	telefonoResponsabile = udc.getDatiUtenteResponsabile().getTelefono();
	telefonoCompletoResponsabile = udc.getDatiUtenteResponsabile().getTelefonoCompleto();
	//DATE DI DISTRIBUZIONE
	dataCreazione = udc.getDateDiDistribuzione().getDataCreazione();
	dataSt = udc.getDateDiDistribuzione().getDataSt();
	dataIt = udc.getDateDiDistribuzione().getDataIt();
	dataDistrAddestr = udc.getDateDiDistribuzione().getDataDistrAddestr();
	dataPp = udc.getDateDiDistribuzione().getDataPp();
	dataPpPila1 = udc.getDateDiDistribuzione().getDataPpPila1();
	distribuzioneDifferita = udc.getDateDiDistribuzione().getDistribuzioneDifferita();
	dataDifferita = udc.getDateDiDistribuzione().getDataDifferita();
	codiceVarDataPp = udc.getDateDiDistribuzione().getCodiceVarDataPp();
	descrVarDataPp = udc.getDateDiDistribuzione().getDescrVarDataPp();
	ggInizioTest = udc.getDateDiDistribuzione().getGgInizioTest();
	dataEsitoDistr = udc.getDateDiDistribuzione().getDataEsitoDistr();
	oraLimiteEsito = udc.getDateDiDistribuzione().getOraLimiteEsito();
	dataLimitePassaggioProduzione = udc.getDateDiDistribuzione().getDataLimitePassaggioProduzione();
	dataPrimoColl = udc.getDateDiDistribuzione().getDataPrimoColl();
	dataPrimoIt = udc.getDateDiDistribuzione().getDataPrimoIt();
	//DATI EMERGENZA
	flagSbloccoUde = udc.getDatiEmergenza().getFlagSbloccoUde().trim();
	orario = udc.getDatiEmergenza().getOrario().trim();
	idEmergenza = udc.getDatiEmergenza().getIdEmergenza().trim();
	profiloUtente = udc.getDatiEmergenza().getProfiloUtente().trim();
	livelloAutorizzativo = udc.getDatiEmergenza().getIdAllegatoAutorizzazione().trim();
	flagImpattoDiBusiness = udc.getDatiEmergenza().getFlagImpattoDiBusiness().trim();
	flagErroreBloccante = udc.getDatiEmergenza().getFlagErroreBloccante().trim();
	codiceMotivoEmergenza = udc.getDatiEmergenza().getCodiceMotivoEmergenza().trim();
	descrizioneMotivoEmergenza = udc.getDatiEmergenza().getDescrizioneMotivoEmergenza().trim();
}
String orarioFormattato = "";
boolean isPresenteOrario = false;
if (orario != null && orario.trim().length()>2){
	isPresenteOrario = true;
	orarioFormattato = orario.replace('.', ':');
}

boolean isPresentePile = false;
if(dataPp.trim().equals(dataPpPila1.trim())){
	//dataPp= dataPp;
} else {
	isPresentePile = true;
	dataPpPila2 = dataPp;
}


Boolean isReadOnly = Boolean.FALSE;
String readonly = "";
String disabled = "";
if (isReadOnly.booleanValue()) {
    readonly = "readonly=\"readonly\"";
    disabled = "disabled=\"disabled\"";
}
// personalizzazioni Ude / Udu ;


%>

<html>
    <head>
    	<title>Autorizzazione Sblocco Cut-Off</title>
        <link rel=stylesheet href="css/testi.css" type="text/css" media="screen">
		<link rel=stylesheet href="css/style.css" type="text/css" media="screen">
        
        <script language="javascript">

            function effettuaApprova(){
                var id =  document.getElementById("id").value;
                var tipo = document.getElementById("tipo").value;
                var url = '/scriptChci0/AutUdc/Deroghe';
                var params = "?id="+id;
                params = params + "&tipo="+tipo;
                params = params + "&sistemaInformativo" +'<%=inputSistemaInformativo%>';
                document.approvaRichiesta.action = url+params;
                document.approvaRichiesta.submit();
			    
            }
            function effettuaRifiuta(){
                var id =  document.getElementById("id").value;
                var tipo = document.getElementById("tipo").value;
                var url = '/scriptChci0/AutUdc/Deroghe';
                var params = "?id="+id;
                params = params + "&tipo="+tipo;
				params = params + "&sistemaInformativo" +'<%=inputSistemaInformativo%>';
                document.rifiutaRichiesta.action = url+params;
                document.rifiutaRichiesta.submit();
            }
            
          function abiDisApprova()
          {
           <%if("S".equals(flagCutOff) ){%>
             if (document.getElementById("contCap").checked)
             {
             	document.getElementById("approvaAbl").style.display = "inline";
             	document.getElementById("approvaDis").style.display = "none";
             }
             else
             {
             	document.getElementById("approvaAbl").style.display = "none";
             	document.getElementById("approvaDis").style.display = "inline";
             }
             
             <%}%>
          }
        </script>
    </head>

    <body>

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
                            <td align=center class="text_header" valign=top>&nbsp;</td>
                        </tr>
                        <tr>
                            <td align=left class="text_header" valign=top>&nbsp;</td>
                        </tr>
                    </table>
                </td>
                <td align=center width=5% nowrap valign=top align=center>&nbsp; </td>
            </tr>
            <tr>
               <td> &nbsp; </td>
				<td colspan=4 align=left>
					<font style="FONT-SIZE:11pt;FONT-FAMILY: Georgia,Times New Roman,Verdana;"><B><I>Richiesta Sblocco Cut-Off</I></B></font>
				</td>
			</tr>
        </table>

        <!--Tabella con i dati della Richiesta -->
        
        <!--Tabella con la lista delle UDC -->
        <table border="0" bordercolor="#111111" style="BORDER-COLLAPSE:collapse" cellpadding="0" cellspacing="0" width="50%">
             <tr>
                <td>&nbsp;</td>
            </tr>

            <!-- inizio elenco udc -->
            <tr>
                <td align="center">
                   
                    <table border="1" bordercolor="#111111" style="BORDER-COLLAPSE:collapse;" cellpadding="0" cellspacing="0" width="50%">
                        <colgroup>
                            <col align="left" width="25%"/>
                            <col align="left" width="75%"/>
                        </colgroup>
                         
                        <tr  class="header">
                            <td> Udc</td>
                            <td>Responsabile</td>
                        </tr>
                        
                        <tr class="text_label">
                            <td ><%=codiceUdc%></td>
                            <td ><%=utenteResponsabile%> - <%=nomeResponsabile%></td>
                        </tr>
               
                    </table>
                    <br />
                    <br />
                    <!-- Tabella con i tasti .    -->
                    
                    <table>
                        <tr>
                        	<td width="25%">
                            <td bgcolor="green">

                                <form id="approvaRichiesta" name="approvaRichiesta" method="get">
                                    <input type="hidden" name="id" value="<%=idEmergenza%>"/>
                                    <input type="hidden" name="tipo" value="OK"/>
									<% if (isReadOnly.booleanValue()) {%>
                                    	<A class="header_green" href="">&nbsp;&nbsp;APPROVA&nbsp;&nbsp;</A>
                                    <%}else{ if ("S".equals(flagCutOff)){%>
                                    	<A class="header_green"  id="approvaDis" style= "display: inline">&nbsp;&nbsp;APPROVA&nbsp;&nbsp;</A>
                                    	<A class="header_green"  id="approvaAbl" href="<%=urlRichiestaDeroga%>?id=<%=inputId%>OK<%=inputTipoAutorizzazione%>&flC=S" style="display:none;">&nbsp;&nbsp;APPROVA&nbsp;&nbsp;</A>
                                    <%}else{ %>    
                                    	<A class="header_green" id="approvaAbl" href="<%=urlRichiestaDeroga%>?id=<%=inputId%>OK<%=inputTipoAutorizzazione%>&flC=N">&nbsp;&nbsp;APPROVA&nbsp;&nbsp;</A>
                                    <%}} %>
                                </form>
                            </td>
                            <td width="10%"> </td>
                            <td bgcolor="red">

                                <form id="rifiutaRichiesta" name="rifiutaRichiesta" method="get">
                                    <input type="hidden" name="id" value="<%=idEmergenza%>"/>
                                    <input type="hidden" name="tipo" value="KO"/>
									
									<% if (isReadOnly.booleanValue()) {%>
                                    	<A class="header_red" >&nbsp;&nbsp;RIFIUTA&nbsp;&nbsp;</A>
                                    <%}else{ if ("S".equals(flagCutOff)){ %>
                                    	<A class="header_red" href="<%=urlRichiestaDeroga%>?id=<%=inputId%>KO<%=inputTipoAutorizzazione%>&flC=S">&nbsp;&nbsp;RIFIUTA&nbsp;&nbsp;</A>
                                      <%}else{%>
                                      <A class="header_red" href="<%=urlRichiestaDeroga%>?id=<%=inputId%>KO<%=inputTipoAutorizzazione%>&flC=N">&nbsp;&nbsp;RIFIUTA&nbsp;&nbsp;</A>
                                      <%}%>
                                    <%}%>
                                </form>
                            </td>
                            <td width="25%" id ="tdDich" >
                            <% if ("S".equals(flagCutOff)) {%>
                           <input type = "checkbox" onclick="abiDisApprova();" name = "contCap" id="contCap">Dichiaro di aver contattato tutte le strutture preposte allo sblocco
                            <%} %>
                            </td>
                         </tr>
                    </table>
                   
                </td>
            </tr>
            <!-- fine elenco udc -->
        </table>
    </body>
</html>