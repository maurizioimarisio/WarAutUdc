<%@ page language="java" autoFlush="true" pageEncoding="UTF-8" contentType="text/html;charset=ISO-8859-1" %>

<%@page import="java.util.StringTokenizer;"%>

<jsp:useBean id="inpServlet" scope="session" class="java.lang.String" />

<%
StringTokenizer str = new StringTokenizer(inpServlet==null?"":inpServlet);
String inputIdRichiesta = "";
String inputEsito = "";
String inputTipoAutorizzazione= "";
if (inpServlet.length()>0){
	inputIdRichiesta = (str.hasMoreTokens()) ? str.nextToken(" ") : "";
	inputEsito = (str.hasMoreTokens()) ? str.nextToken(" ") : "";
	inputTipoAutorizzazione = (str.hasMoreTokens()) ? str.nextToken(" ") : "";
}


String pageToForward = "/Deroghe/risposta/";
if ("T".equals(inputTipoAutorizzazione)){
	pageToForward = pageToForward + "travasoEmergenza.jsp";
} else if ("E".equals(inputTipoAutorizzazione)){ 
	pageToForward = pageToForward + "autorizzazioneUde.jsp";
} else if ("U".equals(inputTipoAutorizzazione)){ 
	pageToForward = pageToForward + "autorizzazioneUde.jsp";
}else if ("K".equals(inputTipoAutorizzazione)){ 
	pageToForward = pageToForward + "autorizzazioneUde.jsp";
} else if ("F".equals(inputTipoAutorizzazione)){
	pageToForward = pageToForward + "autorizzazioneFine.jsp";
} else if ("I".equals(inputTipoAutorizzazione)){
    pageToForward = pageToForward + "autorizzazioneInizio.jsp";
}else if ("D".equals(inputTipoAutorizzazione) || "".equals(inputTipoAutorizzazione)){
	pageToForward = pageToForward + "richiestaDeroga.jsp";
} else if ("C".equals(inputTipoAutorizzazione)){
	pageToForward  = pageToForward + "autorizzazioneSbloccoCutOff.jsp";
}else {
	pageToForward = pageToForward + "errore/erroreFunzione.jsp";
}

%>

<jsp:forward page="<%=pageToForward%>"></jsp:forward>

