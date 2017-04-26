<%@ page language="java" autoFlush="true" pageEncoding="UTF-8" contentType="text/html;charset=ISO-8859-1" %>

<%@page import="java.util.StringTokenizer"%>

<jsp:useBean id="userSWA" scope="session" class="autudc.swa.ProfiloSWA" />
<jsp:useBean id="urlRichiestaDeroga" scope="session" class="java.lang.String" />
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
String orario = "";
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
	idAllegatoAutorizzazione = udc.getDatiEmergenza().getIdAllegatoAutorizzazione().trim();
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

String tagHTMLTitle="";
String tagHTMLtdmotivo="";
if (inputTipoAutorizzazione.equals("E")) {
	tagHTMLTitle= "Autorizzazione Ude";
	tagHTMLtdmotivo = "Motivo Emergenza";
	}
else if (inputTipoAutorizzazione.equals("U")) {
	tagHTMLTitle= "Autorizzazione Udu";
	tagHTMLtdmotivo = "Motivo Cambiamento";
	}
%>

<html>
    <head>
    	<title><%=tagHTMLTitle%></title>
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
            
            function visApprova()
            {
               if (document.getElementById("chck").checked == true)
               {
   				   document.getElementById("approva").style.display="inline";
                   document.getElementById("approvaDis").style.display="none";
            }
               else
               {
               	   document.getElementById("approva").style.display="none";
                   document.getElementById("approvaDis").style.display="inline";
 
               }
               	
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
                <td align=center width=5% nowrap valign=top align=center>&nbsp;</td>
            </tr>
            <tr>
				<td colspan="1" align="center">
				</td>
				<td colspan="4">
					<font style="FONT-SIZE:11pt;FONT-FAMILY: Georgia,Times New Roman,Verdana;"><B><I><%=tagHTMLTitle%></I></B></font>
				</td>
			</tr>
        </table>

        <!--Tabella con i dati della Richiesta -->
        <table width="50%" cellspacing="0" cellpadding="0">
            <% if (isPresentePile){%>
            <tr valign=center>
                <td  class="text_label">&nbsp;</td>
                <td  class="text_label">Data Pila 1</td>
                <td>
            		<input class="text_input_bold" type="text" value="<%=dataPpPila1%>" readonly maxlength="10" size="12"/>
            	</td>
            </tr>
            <tr valign=center>
                <td  class="text_label">&nbsp;</td>
                <td  class="text_label">Data Pila 2</td>
                <td>
            		<input class="text_input_bold" type="text" value="<%=dataPpPila2%>" readonly maxlength="10" size="12"/>
            	</td>
            </tr>
            <% } else { %>
            <tr valign=center>
                <td  class="text_label">&nbsp;</td>
                <td  class="text_label">Data Passaggio in Produzione</td>
                <td >
                    <input class="text_input_bold" type="text" value="<%=dataPp%>" readonly maxlength="10" size="12" />
                </td>
            </tr>
            <%}%>
            <tr valign=center>
                <td  class="text_label">&nbsp;</td>
                <td class="text_label">Attivazione software dalle</td>
                <td>
                	<% if (isPresenteOrario){%>
                    	<input class="text_input_bold" type="text" value="<%=orarioFormattato%>" readonly maxlength="8" size="8"/>
					<% } else { %>
						<b>&nbsp;Immediata&nbsp;</b>
					<%}%>
                </td>
            </tr>
            <tr valign=center>
                <td  class="text_label">&nbsp;</td>
                <td class="text_label"><%=tagHTMLtdmotivo%></td>
                <td >
                    <input class="text_input_bold" type="text" value="<%=descrizioneMotivoEmergenza%>" readonly size="50"/>
                </td>
            </tr>
        </table>

        <table border="0" bordercolor="#111111" style="BORDER-COLLAPSE:collapse" cellpadding="0" cellspacing="0" width="50%">
             <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td align="center">
                    <%if (codiceUdc.trim().length() > 0) {%>
                    <table border="1" bordercolor="#111111" style="BORDER-COLLAPSE:collapse" cellpadding="0" cellspacing="0" width="50%">
                        <colgroup>
                            <col align="left" width="75%"/>
                            <col align="left" width="25%"/>
                        </colgroup>
                        <tr  class="header">
                            <td> Udc</td>
                            <td>Responsabile</td>
                        </tr>
                        <tr class="text_label">
                            <td><%=codiceUdc%></td>
                            <td><%=utenteResponsabile%></td>
                        </tr>
                        <tr class="text_label">
                            <td ><input type="checkbox" id="chck" name="chck" onclick="visApprova()" ></td>
                            <td>Presa visione presenza oggetti</td>
                        </tr>
                    </table>
                    <br />
                    <br />
                    <table>
                        <tr>
                        	<td width="25%">&nbsp;</td>
                            <td bgcolor="green">
                                <form id="approvaRichiesta" name="approvaRichiesta" method="get">
                                    <input type="hidden" name="id" value="<%=inputId%>"/>
                                    <input type="hidden" name="tipo" value="OK"/>
									<% if (isReadOnly.booleanValue()) {%>
                                    	<A class="header_green" href="">APPROVA</A>
                                    <%}else{ %>
                                    	<A class="header_green" id="approva" href="<%=urlRichiestaDeroga%>?id=<%=inputId%>OK<%=inputTipoAutorizzazione%>" style="display: none;">&nbsp;&nbsp;APPROVA&nbsp;&nbsp;</A>
                                        <B class="header_gray" id="approvaDis"  style="display: inline;">APPROVA</B>
									<%} %>
                                </form>
                            </td>
                            <td width="10%">&nbsp;</td>
                            <td bgcolor="red">
                                <form id="rifiutaRichiesta" name="rifiutaRichiesta" method="get">
                                    <input type="hidden" name="id" value="<%=inputId%>"/>
                                    <input type="hidden" name="tipo" value="KO"/>
									<% if (isReadOnly.booleanValue()) {%>
                                    	<A class="header_red" href="">&nbsp;&nbsp;RIFIUTA&nbsp;&nbsp;</A>
                                    <%}else{ %>
                                    	<A class="header_red" href="<%=urlRichiestaDeroga%>?id=<%=inputId%>KO<%=inputTipoAutorizzazione%>">&nbsp;&nbsp;RIFIUTA&nbsp;&nbsp;</A>
                                    <%} %>
                                </form>
                            </td>
                            <td width="25%">&nbsp;</td>
                        </tr>
                    </table>

                    <% } else {%>
                    <table align="center">
                        <tr>
                            <%if (erroreOutputBS.isEsito()) {%>
                            <td align="center" class="text_label">RICHIESTA NON PRESENTE IN ARCHIVIO.</td>
                            <% } else {%>

                            <td align="center" class="text_label"><%=erroreOutputBS.getMessaggioErrore()%></td>
                            <% }%>
                        </tr>
                        <tr>
                            <td align="center">
                                &nbsp;
                            </td>
                        </tr>
                    </table>
                    <% }%>
                </td>
            </tr>
        </table>
    </body>
</html>