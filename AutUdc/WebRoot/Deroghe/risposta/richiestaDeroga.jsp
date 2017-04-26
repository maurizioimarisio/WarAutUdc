<%@ page language="java" autoFlush="true" pageEncoding="UTF-8" contentType="text/html;charset=ISO-8859-1" %>

<jsp:useBean id="userSWA" scope="session" class="autudc.swa.ProfiloSWA" />
<jsp:useBean id="inpServlet" scope="session" class="java.lang.String" />
<jsp:useBean id="OutputBS" scope="session" class="autudc.chiamaBS.OutputYUDCS00" />
<jsp:useBean id="InputBS" scope="session" class="autudc.chiamaBS.InputYUDCS00" />
<jsp:useBean id="erroreOutputBS" scope="session" class="autudc.chiamaBS.ErroreOutputBS" />
<jsp:useBean id="messErr" scope="session" class="java.lang.String"/>

<%
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
        <title>Esito Deroga</title>
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
			      alert("<%=messErr.replace('"', ' ')%>");
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
                            <td align=center class="text_header" valign=top>

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

	<font style="FONT-SIZE:11pt;FONT-FAMILY: Georgia,Times New Roman,Verdana;"><B><I>Richiesta Deroga</I></B></font>

	</td>
        </tr>
        </table>

        <!--Tabella con i dati della Richiesta -->
        <table width="100%" cellspacing="0" cellpadding="0">
            <br>
            <tr valign=center>
                <td  class="text_label">&nbsp;</td>
                <td  class="text_label">ID Deroga</td>
                <td  class="text_label"><b><%=InputBS.getIdDaAutorizzare()%> </b>
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
