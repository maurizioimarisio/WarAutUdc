<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=ISO-8859-1" %>

<jsp:useBean id="erroreOutputBS" scope="session" class="autudc.chiamaBS.ErroreOutputBS" />

<%
	String err = "Errore generico durante la chiamata ******";
	if (erroreOutputBS != null && !"".equals(erroreOutputBS.getMessaggioErrore())){
		err = erroreOutputBS.getMessaggioErrore();
	}
%>
<html>
<head>
<script language='javascript'>

function sonoQui()
{
}
</script>
</head>
<body  >
<form id='errorPage' name="errorPage" method="post">
	<center/>
	<BR><BR><BR><BR>
	<img src="/portalChci0/img/error.gif" border=0>&nbsp;
	<B><%=err%></B>
	<br>
	 <input class="newbutton" type="button" value="Indietro" name="Indietro" onClick="history.back()">
</form>	
<!-- prova commit per git FEDE -->
</body>
</html>