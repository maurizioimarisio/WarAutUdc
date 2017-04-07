<%@ page language="java" autoFlush="true" pageEncoding="UTF-8" contentType="text/html;charset=ISO-8859-1" %>

<%@page import="autudc.chiamaBS.RichiestaDeroga"%>
<%@page import="autudc.chiamaBS.SingoloUdc"%>
<%@page import="autudc.chiamaBS.OutputYUDAS00"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.LinkedList"%>

<jsp:useBean id="userSWA" scope="session" class="autudc.swa.ProfiloSWA" />
<jsp:useBean id="urlRichiestaDeroga" scope="session" class="java.lang.String" />
<jsp:useBean id="inpServlet" scope="session" class="java.lang.String" />
<jsp:useBean id="OutputBS" scope="session" class="autudc.chiamaBS.OutputYUDAS00" />
<jsp:useBean id="InputBS" scope="session" class="autudc.chiamaBS.InputYUDAS00" />
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
String inputTipoAutorizzazione= "";
if (inpServlet.length()>0){
	inputId = (str.hasMoreTokens()) ? str.nextToken(" ") : "";
	inputSistemaInformativo = (str.hasMoreTokens()) ? str.nextToken(" ") : "";
	inputTipoAutorizzazione = (str.hasMoreTokens()) ? str.nextToken(" ") : "";
}

RichiestaDeroga richiestaDeroga = new RichiestaDeroga();

if (OutputBS == null) {
    OutputBS = new OutputYUDAS00();

} else {
    richiestaDeroga = OutputBS.getRichiestaDeroga();
    userIdDestinatoario = richiestaDeroga.getUserIdDesctinatario();
    destinatario = richiestaDeroga.getDestinatario();
    livelloAutorizzativo = richiestaDeroga.getLivelloAutorizzativo();
    codSistemaInformativo = richiestaDeroga.getCodSistemaInformativo();
    codPiattaforma = richiestaDeroga.getCodPiattaforma();
    codUdc = richiestaDeroga.getCodUdc();
    codProgetto = richiestaDeroga.getCodProgetto();
    dataProduzione = richiestaDeroga.getDataProduzione();

}

LinkedList elencoRichiesteDeroga = new LinkedList();
elencoRichiesteDeroga = OutputBS.getElencoUdc();

Boolean isReadOnly = Boolean.FALSE;
String readonly = "";
String disabled = "";
if (isReadOnly.booleanValue()) {
    readonly = "readonly=\"readonly\"";
    disabled = "disabled=\"disabled\"";
}


%>


<html>
    <head>
    	<title>Autorizzazione Deroga</title>
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
				<td colspan=5 align=center>
					<font style="FONT-SIZE:11pt;FONT-FAMILY: Georgia,Times New Roman,Verdana;"><B><I>Richiesta Deroga</I></B></font>
				</td>
			</tr>
        </table>

        <!--Tabella con i dati della Richiesta -->
        <table width="50%" cellspacing="0" cellpadding="0">
            <br>
            <tr valign=center>
                <td>&nbsp;</td>
                <td  class="text_label">ID Deroga</td>
                <td >
                    <input class="text_input_bold" type="text" value="<%=InputBS.getIdDeroga()%>" readonly />
                </td>
            </tr>
            <tr valign=center>
                <td>&nbsp;</td>
                <td class="text_label">Livello Autorizzativo</td>
                <td >
                    <input class="text_input_bold" type="text" value="<%=richiestaDeroga.getLivelloAutorizzativo()%>" readonly />
                </td>
            </tr>
            <tr valign=center>
                <td >&nbsp;</td>
                <td class="text_label">Data Deroga</td>
                <td >
                    <input class="text_input_bold" type="text" value="<%=richiestaDeroga.getDataDeroga()%>" readonly />
                </td>
            </tr>
        </table>

        <!--Tabella con la lista delle UDC -->
        <table border="0" bordercolor="#111111" style="BORDER-COLLAPSE:collapse" cellpadding="0" cellspacing="0" width="50%">
             <tr>
                <td>&nbsp;</td>
            </tr>

            <!-- inizio elenco udc -->
            <tr>
                <td align="center">
                    <%if (elencoRichiesteDeroga.size() > 0) {%>

                    <table border="1" bordercolor="#111111" style="BORDER-COLLAPSE:collapse;" cellpadding="0" cellspacing="0" width="50%">
                        <colgroup>
                            <col align="left" width="25%"/>
                            <col align="left" width="75%"/>
                        </colgroup>
                         
                        <tr  class="header">
                            <td> Udc</td>
                            <td>Responsabile</td>
                        </tr>
                        <%
                            for (int i = 0; i < elencoRichiesteDeroga.size(); i++) {
                                SingoloUdc elencoUdc = (SingoloUdc) elencoRichiesteDeroga.get(i);
                                String codiceUdc = elencoUdc.getCodiceUdc();
                                String userIdResponsabile = elencoUdc.getNomeIdResponsabile();
                                String descrizione = elencoUdc.getDescrizione();
                        %>
                        <tr class="text_label">
                            <td ><%=codiceUdc%></td>
                            <td ><%=userIdResponsabile%></td>
                        </tr>
                        <% }%>
                    </table>
                    <br />
                    <br />
                    <!-- Tabella con i tasti .    -->
                    
                    <table>
                        <tr>
                        	<td width="25%">
                            <td bgcolor="green">

                                <form id="approvaRichiesta" name="approvaRichiesta" method="get">
                                    <input type="hidden" name="id" value="<%=InputBS.getIdDeroga()%>"/>
                                    <input type="hidden" name="tipo" value="OK"/>
									<% if (isReadOnly.booleanValue()) {%>
                                    	<A class="header_green" href="">APPROVA</A>
                                    <%}else{ %>
                                    	<A class="header_green" href="<%=urlRichiestaDeroga%>?id=<%=inputId%>OK<%=inputTipoAutorizzazione%>">&nbsp;&nbsp;APPROVA&nbsp;&nbsp;</A>
                                    <%} %>
                                </form>
                            </td>
                            <td width="10%">
                            <td bgcolor="red">

                                <form id="rifiutaRichiesta" name="rifiutaRichiesta" method="get">
                                    <input type="hidden" name="id" value="<%=InputBS.getIdDeroga()%>"/>
                                    <input type="hidden" name="tipo" value="KO"/>
									
									<% if (isReadOnly.booleanValue()) {%>
                                    	<A class="header_red" href="">&nbsp;&nbsp;RIFIUTA&nbsp;&nbsp;</A>
                                    <%}else{ %>
                                    	<A class="header_red" href="<%=urlRichiestaDeroga%>?id=<%=inputId%>KO<%=inputTipoAutorizzazione%>">&nbsp;&nbsp;RIFIUTA&nbsp;&nbsp;</A>
                                    <%} %>
                                </form>
                            </td>
                            <td width="25%">
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
            <!-- fine elenco udc -->
        </table>
    </body>
</html>