package Utility;


import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;




public class Utility {

	private static String ALPHACHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String DIGITCHARS = "0123456789";
	private static String ALPHADIGITCHARS = ALPHACHARS+DIGITCHARS;
	
	public static boolean isBlackBerry(HttpServletRequest request){
		boolean retBool = false;
		String userAgent = (String) request.getHeader("User-Agent");
		retBool = userAgent.toUpperCase().indexOf("BLACKBERRY")==-1 ? false : true;
		return retBool;
	}

	public static String space(int numSpaces){
		String strSpaces = "";
		for(int i=0; i< numSpaces; i++){
			strSpaces = strSpaces + " ";
		}
		return strSpaces;
	}
	
	public static String checkNull(String in) {
		String risultato = "";
		try {
			if (in == null || in.trim().equals("null"))
				risultato = " ";
			else
				risultato = in.trim();
		} catch (Exception e) {
			risultato = " ";
		}
		return risultato;
	}
	
	 public static String replaceAllStr(String oldStr, String newStr, String inString) {
		 int start = inString.indexOf(oldStr);
		 String tmp="";
		 String newTmp="";
		 if(start == -1) 
			 return inString;
		 else{
			 tmp=inString;
		 }
		 while (start != -1) {			 
			 newTmp+=tmp.substring(0, start);
			 newTmp+=newStr;
			 tmp=tmp.substring(start+oldStr.length());
			 start = tmp.indexOf(oldStr);
		 }  
		 return newTmp+tmp;
	}    
	 
	 public static String replaceBackSlashWidthCodice(String oldStr) {
		String newStr = "";	
		
		int posSlasch1 = oldStr.indexOf("\\");
		int posSlasch2 = -1;
		while (posSlasch1 != -1) {
			newStr = newStr + oldStr.substring(0,posSlasch1);
			newStr = newStr +  "\\u005c";
			
			oldStr = oldStr.substring(posSlasch1 +1, oldStr.length());
			posSlasch2 = oldStr.indexOf("\\");
			if(posSlasch2 != -1){
				newStr = newStr + oldStr.substring(0, posSlasch2); 
				newStr = newStr +  "\\u005c";
				// chiedersi se \ ultimo char
				oldStr = oldStr.substring(posSlasch2 +1, oldStr.length());
			}	
			posSlasch1 = oldStr.indexOf("\\");
		}
		newStr = newStr + oldStr;
		return newStr;
	}
	 
	 /*******************************************************/       
	 /** Funzione per effettuare il replace di una stringa **/
	 /*******************************************************/
	 public static String replace(String oldStr, String newStr, String inString) {
		 int start = inString.indexOf(oldStr);
		 if (start == -1) {
			 return inString;
		 }
		 StringBuffer sb = new StringBuffer();
		 sb.append(inString.substring(0, start));
		 sb.append(newStr);
		 sb.append(inString.substring(start+oldStr.length()));
		 return sb.toString();
	}
	 
	 /**
	  * prende in input una stringa e un carattere e toglie tutti i caratteri di quel tipo
	  * che si trovano alla sua sinistra
	  * ex 0000000000034560, 0
	  * diventa 
	  * 34560
	  * @return
	  */
	 public static String ClearChar(String oldStr,char chr){
		 String newStr = oldStr;
		 if(newStr.length()>0){
			 while(newStr.charAt(0)== chr){
				 newStr = newStr.substring(1,newStr.length());
			 }
		 }
		 return newStr;
	 }
	 
	public static String addSpace(int numSpaces){
		String strSpaces = "";
		for(int i=0;i<numSpaces;i++){
			strSpaces += " ";
		}
		return strSpaces;
	}

	public static int count(String str, String tok) {
		int counter = 0;
		int start = 0;
		int ind = str.indexOf(tok);
		while (ind != -1) {
			counter++;
			start = ind+1;
			ind = str.indexOf(tok, start);
		}
		return counter;
	}

	public static String[] split(String str, String separator) {
		String[] elLst = null;
		if (str!=null&&!str.equals("")) {
			int el = count(str, separator);
			elLst = new String[++el];
			if (el!=1) {
				el = 0;
				int start = 0;
				int ind = str.indexOf(separator);
				while (ind!=-1) {
					elLst[el++] = str.substring(start, ind);
					start = ind+separator.length();
					ind = str.indexOf(separator, start);
				}
				elLst[el++] = str.substring(start);
			}
			else {
				elLst[0] = str;
			}
		}
		return elLst;
	}
	
	/**
	 * 
	 * @param originale
	 * @param carattere 
	 * @param dexSin 'D' o 'S'
	 * @param len
	 * @return
	 */
	public static String FillaCaratteri(String originale, String carattere, String dexSin, int len) {
		String strFillata = originale;
		int lenOriginale = originale.length();
		if (dexSin.equals("S")) {
			for (int i = 0; i < (len - lenOriginale); i++) {
				if (carattere.equals("b")) {
					strFillata = " " + strFillata;
				} else if (carattere.equals("0")) {
					strFillata = "0" + strFillata;
				}
			}
		} else if (dexSin.equals("D")) {
			for (int i = 0; i < (len - lenOriginale); i++) {
				if (carattere.equals("b")) {
					strFillata = strFillata + " ";
				} else if (carattere.equals("0")) {
					strFillata = strFillata + "0";
				}
			}
		}

		return strFillata;

	}
	
	public static String insertAsterischi(int numSpaces) {
		String strSpaces = "";
		for (int i = 0; i < numSpaces; i++) {
			strSpaces = strSpaces + "*";
		}
		return strSpaces;
	}

	// Restituisce la posizione del primo campo numerico che trova nella stringa
	public static int posNum(String str) {
		int posNum = 0;
		if (str!=null&&!str.equals("")) {
			int lenStr = str.length();
			int i=0;
			int tmpVal=0;
			
			while (i<lenStr&&tmpVal==0) {
				try {
					tmpVal = Integer.valueOf(str.substring(i, i+1)).intValue();
					posNum = i;
				}
				catch (Exception e) {
				}
				i++;
			}
		}
		return posNum;
	}

	// Rimuove caratteri speciali lasciando SOLO lettere dell'alfabeto e numeri
	public static String removeSpecialChars(String str) {
		String newStr = "";
		char c;
		int i = 0;
		while (i<str.length()) {
			c = str.charAt(i);
			if (ALPHADIGITCHARS.indexOf(c)!=-1) {
				newStr += c; 
			}
			i++;
		}
		return newStr;
	 }

	/**
	 * controlla che la stringa passata sia un numero
	 * non deve avere il segno +/-
	 * @param strvalue
	 * @return   
	 */
	public static boolean isNumeric(String strValue){
		String 	 numeri=".0123456789";
		boolean result = true;
		String value = "";
		if (strValue != null){
			value = strValue.trim();
		}
		if(value.length() > 1) {
			int j = 0;			
			while( j < value.length() && result) {
				String carattere = value.substring(j,j+1);
				if(numeri.indexOf(carattere)== -1){
					result = false;
				}else{
					j++;
				}
			}			
		}else{  
			if(numeri.indexOf(value) == -1){
				result = false;
			}
		}
		return result;
	}

	public static int contaToken(String strInput, String delim){
		int result = 0;
		StringTokenizer strToke = new StringTokenizer(strInput,delim);
		/*while(strToke.hasMoreElements()){
			System.out.println(strToke.nextToken());
		}*/
		
		result = strToke.countTokens();
		return result;
	} 
	
	public static String getGiornoSettimanaInItaliano(String giornoSettimanaInInglese)
	{
		if(giornoSettimanaInInglese == null)
			return null;
		
		String tmp = giornoSettimanaInInglese.toUpperCase();
		String meseItaliano = tmp;
		
		if(tmp.equalsIgnoreCase("SUNDAY"))
			meseItaliano = "Domenica";
		else if(tmp.equalsIgnoreCase("MONDAY"))
			meseItaliano = "Lunedï¿½";
		else if(tmp.equalsIgnoreCase("TUESDAY"))
			meseItaliano = "Martedï¿½";
		else if(tmp.equalsIgnoreCase("WEDNESDAY"))
			meseItaliano = "Mercoledï¿½";
		else if(tmp.equalsIgnoreCase("THURSDAY"))
			meseItaliano = "Giovedï¿½";
		else if(tmp.equalsIgnoreCase("FRIDAY"))
			meseItaliano = "Venerdï¿½";
		else if(tmp.equalsIgnoreCase("SATURDAY"))
			meseItaliano = "Sabato";
		
		
		return meseItaliano;
	}
	
	public static String getMeseInItaliano(String meseInInglese)
	{
		if(meseInInglese == null)
			return null;
		
		String tmp = meseInInglese.toUpperCase();
		String meseItaliano = tmp;
		
		if(tmp.equalsIgnoreCase("JANUARY"))
			meseItaliano = "Gennaio";
		else if(tmp.equalsIgnoreCase("FEBRUARY"))
			meseItaliano = "Febbraio";
		else if(tmp.equalsIgnoreCase("MARCH"))
			meseItaliano = "Marzo";
		else if(tmp.equalsIgnoreCase("APRIL"))
			meseItaliano = "Aprile";
		else if(tmp.equalsIgnoreCase("MAY"))
			meseItaliano = "Maggio";
		else if(tmp.equalsIgnoreCase("JUNE"))
			meseItaliano = "Giugno";
		else if(tmp.equalsIgnoreCase("JULY"))
			meseItaliano = "Luglio";
		else if(tmp.equalsIgnoreCase("AUGUST"))
			meseItaliano = "Agosto";
		else if(tmp.equalsIgnoreCase("SEPTEMBER"))
			meseItaliano = "Settembre";
		else if(tmp.equalsIgnoreCase("OCTOBER"))
			meseItaliano = "Ottobre";
		else if(tmp.equalsIgnoreCase("NOVEMBER"))
			meseItaliano = "Novembre";
		else if(tmp.equalsIgnoreCase("DECEMBER"))
			meseItaliano = "Dicembre";
		
		
		return meseItaliano;
	}
	/**
	 * SE IL CODICE UDC NON E' VUOTO E COMINCIA PER O, opp H
	 * COMPLETO DI ZERI FINO A 11 CARATTERI TRA IL PRIMO CARATTERE E QUELLI SUCCESSIVI
	 * 
	 * OXXX DIVENTA O0000000XXX
	 * H5   DIVENTA H0000000005
	 * ECC
	 * @param codUdc
	 * @return
	 */
	public static String completaCodiceUdc(String codUdc) {
		String codUdcCompletato = "";
		if(codUdc.length() >= 2){
			String primoCarattere = codUdc.substring(0,1);
			String restantiCaratteri = codUdc.substring(1,codUdc.length());
			int lenRestantiCaratteri = restantiCaratteri.length();
			int numZeri = 10-lenRestantiCaratteri; // 11 -1 - caratteri inseriti da loro
			String strZeri = "";
			for(int i=0; i < numZeri; i++){
				strZeri += "0";
			}
			if(primoCarattere.equals("O")){				
				codUdcCompletato = "O" +strZeri + restantiCaratteri;
			}else if(primoCarattere.equals("H")){
				codUdcCompletato = "H" +strZeri + restantiCaratteri;
			}else if(primoCarattere.equals("S")){
				codUdcCompletato = "S" +strZeri + restantiCaratteri;
			}else if(primoCarattere.equals("I")){
				codUdcCompletato = "I" +strZeri + restantiCaratteri;
			}else{
				codUdcCompletato = codUdc;
			}
		}else
			codUdcCompletato = codUdc;
		
		return codUdcCompletato;
	}
	/**
	 * PASSATA LA DATA IN FORMATO GG / MM / AAAA
	 * 							  01 2 34 5 67890	
	 * la ritorna come GGMMAA
	 * @param data
	 * @return
	 */
	public static String ritornaDataSuSeiCh(String data) {
		String risultato = "";
		if(data.length()==10){
			String gg=data.substring(0,2);
			String mm=data.substring(3,5);;
			String aa=data.substring(8,10);
			risultato=gg+mm+aa;
		}
		return risultato;
	}
	/**
	 * AAAA-MM-DD=GG/MM/AAAA
	 * @param data
	 * @return
	 */
	public static String daFormatoInglAItaliano(String data) {
		String risultato = "";
		if(data.length()==10){
			String aaaa=data.substring(0,4);
			String mm=data.substring(5,7);;
			String gg=data.substring(8,10);
			risultato=gg+"/"+mm+"/"+aaaa;
		}
		return risultato;
	}
	/**
	 * GGMMAA=GG/MM/AAAA
	 * @param data
	 * @return
	 */
	public static String ritornaDataHostSu10Ch(String data) {
		String risultato = "";
		if(data.length()==6){
			String gg=data.substring(0,2);
			String mm=data.substring(2,4);;
			String aa=data.substring(4,6);
			risultato=gg+"/"+mm+"/20"+aa;
		}
		return risultato;
	}
//	/**
//	 * GG.MM.AAAA=GG/MM/AAAA
//	 * @param data
//	 * @return
//	 */
//	public static String decodificaDataDaHost(String data) {
//		StringTokenizer stringData = new StringTokenizer(data, ".");
//		return stringData.nextToken() + "/" + stringData.nextToken() + "/" + stringData.nextToken();
//	}
	/**
	 * PASSATA L'ORA COME HH:MM
	 * si ritorna HHMM
	 * @param ora
	 * @return
	 */
	public static String ritornaOraSuQuattroCh(String ora) {
		String risultato = "";
		if(ora.length()==5){
			String hh=ora.substring(0,2);
			String mm=ora.substring(3,5);			
			risultato=hh+mm;
		}
		return risultato;
	}
	/**
	 * HHMM=HH:MM
	 * @param ora
	 * @return
	 */
	public static String ritornaOraHostSu5Ch(String ora) {
		String risultato = "";
		if(ora.length()==4){
			String hh=ora.substring(0,2);
			String mm=ora.substring(2,4);			
			risultato=hh+":"+mm;
		}
		return risultato;
	}
	/**
	 * UTILIZZATA PER RIMPIAZZARE LE VARIABILI NEI COMANDI
	 * @param COMANDO PRECEDUTO DA @, ex @PROGETTO
	 * @param VALORE	ex "chci0"
	 * @param STRINGA CON I COMANDI DA SOSTITUIRE ex APP="@PROGETTO"#VOB="@VOB"
	 * @return
	 */
	public static String replaceVariabili(String oldStr, String newStr, String inString){
		int start = inString.indexOf(oldStr);
		if (start == -1) {
			return inString;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(inString.substring(0, start));
		sb.append(newStr);
		sb.append(inString.substring(start+oldStr.length()));
		return sb.toString();
	}
	
	//ordina il vettore dei dettagli con un ordine fisso (NEWAPP-MOADPP-DELMOD-DELAPP-MODSPR-MODVTB)

//	public static Vector sortVettTipiNote(Vector vettTipiNote){
//		
//		Vector vettTipiNoteSort;
//		
//		vettTipiNoteSort = new Vector(6);
//		TipoDettaglio tipoDettaglioCommon = new TipoDettaglio("NEWAPP","NEWAPP");
//		vettTipiNoteSort.add(0, tipoDettaglioCommon);
//		tipoDettaglioCommon = new TipoDettaglio("MODAPP","MODAPP");
//		vettTipiNoteSort.add(1, tipoDettaglioCommon);
//		tipoDettaglioCommon = new TipoDettaglio("DELMOD","DELMOD");
//		vettTipiNoteSort.add(2, tipoDettaglioCommon);
//		tipoDettaglioCommon = new TipoDettaglio("DELAPP","DELAPP");
//		vettTipiNoteSort.add(3, tipoDettaglioCommon);
//		tipoDettaglioCommon = new TipoDettaglio("MODSPR","MODSPR");
//		vettTipiNoteSort.add(4, tipoDettaglioCommon);
//		tipoDettaglioCommon = new TipoDettaglio("MODVTB","MODVTB");
//		vettTipiNoteSort.add(5, tipoDettaglioCommon);
//		
//		Vector result = new Vector();	
//			
//		for (int n=0;n<vettTipiNoteSort.size();n++){
//			TipoDettaglio dett = (TipoDettaglio)vettTipiNoteSort.get(n);
//			for(int i=0;i<vettTipiNote.size();i++){
//				TipoDettaglio dett2 = (TipoDettaglio)vettTipiNote.get(i);
//				if(dett.getTipo_dettaglio().equals(dett2.getTipo_dettaglio().trim())){
//					result.add(dett2);
//					break;
//				}
//			}
//		}
//			
//		return result;
//	}
	


	public static Vector getMesi(){
		Vector mesi = new Vector();
		mesi.add(new String[]{"1","Gennaio"});
		mesi.add(new String[]{"2","Febbraio"});
		mesi.add(new String[]{"3","Marzo"});
		mesi.add(new String[]{"4","Aprile"});
		mesi.add(new String[]{"5","Maggio"});
		mesi.add(new String[]{"6","Giugno"});
		mesi.add(new String[]{"7","Luglio"});
		mesi.add(new String[]{"8","Agosto"});
		mesi.add(new String[]{"9","Settembre"});
		mesi.add(new String[]{"10","Ottobre"});
		mesi.add(new String[]{"11","Novembre"});
		mesi.add(new String[]{"12","Dicembre"});
		return mesi;
	}
	

	public static Vector getGiorniDellaSettimana(){
		Vector giorniDellaSettimana = new Vector();
		giorniDellaSettimana.add(new String[]{"1","LUNEDI'"});
		giorniDellaSettimana.add(new String[]{"2","MARTEDI'"});
		giorniDellaSettimana.add(new String[]{"3","MERCOLEDI'"});
		giorniDellaSettimana.add(new String[]{"4","GIOVEDI'"});
		giorniDellaSettimana.add(new String[]{"5","VENERDI'"});
		giorniDellaSettimana.add(new String[]{"6","SABATO"});
		giorniDellaSettimana.add(new String[]{"7","DOMENICA"});

		return giorniDellaSettimana;
	}
	
	/**
	 * Restituisce i livelli autorizzativi in base al sistema informativo
	 * 
	 * 	SI COD DESCR 
	 *  01 01 Capoattività
	 *  01 02 Capo ufficio
	 *  01 03 Capo servizio
	 *  01 04 Capo direzione 
	 *  03 01 Utenza specifica
	 *  04 01 Utenza specifica
	 *  05 01 Capoattività
	 *  
	 * @param sistemaInformativo
	 * @return
	 */
	public static SortedMap getMapLivelloAutorizzativo(String sistemaInformativo){
		SortedMap livelloAutorizzativo = new TreeMap();
		if ("01".equals(sistemaInformativo)){//INTESA SAN PAOLO
			livelloAutorizzativo.put("01", "Capo Attività");
			livelloAutorizzativo.put("02", "Capo Ufficio");
			livelloAutorizzativo.put("03", "Capo Servizio");
			livelloAutorizzativo.put("04", "Capo Direzione");
			
		} else if ("02".equals(sistemaInformativo)){//INTESATRADE
			
		} else if ("03".equals(sistemaInformativo)){//MEDIOFACTORING
			livelloAutorizzativo.put("01", "Utenza specifica");
			
		} else if ("04".equals(sistemaInformativo)){//LEASINT
			livelloAutorizzativo.put("01", "Utenza specifica");
			
		} else if ("05".equals(sistemaInformativo)){//NEOS
			livelloAutorizzativo.put("01", "Capo Attività");
			
		}
		return livelloAutorizzativo;
	}
	
	/**
	 * Restituisce la desrizione del livello autorizzativo
	 * @param sistemaInformativo
	 * @param livelloAutorizzativo
	 * @return
	 */
	public static String getDescrizioneLivelloAutorizzativo(String sistemaInformativo,String livelloAutorizzativo) {
		String descrizioneLivelloAutorizzativo = (String) getMapLivelloAutorizzativo(sistemaInformativo).get(livelloAutorizzativo);
		return  descrizioneLivelloAutorizzativo== null ? "" : descrizioneLivelloAutorizzativo;
	}
	
	/**
	 * GG.MM.AAAA=GG/MM/AAAA
	 * @param data
	 * @return
	 */
	public static String decodificaDataDaHost(String data) {
		String retValue = "";
		if (data != null && !"".equals(data.trim())){
			StringTokenizer stringData = new StringTokenizer(data, ".");
			int numTokens = stringData.countTokens();
			if (numTokens==3){
				String giorno = stringData.nextToken().trim();
				String mese = stringData.nextToken().trim();
				String anno = stringData.nextToken().trim();
				if ((giorno+mese+anno).length()==8){
					retValue = giorno + "/" + mese + "/" + anno;
				}
			}
		}
		return retValue;
	}
	/**
	 * hh.mm.ss=hh:mm:ss
	 * @param data
	 * @return
	 */
	public static String decodificaOraDaHost(String orario) {
		String retValue = "";
		if (orario != null && !"".equals(orario.trim())){
			StringTokenizer stringOrario = new StringTokenizer(orario, ".");
			int numTokens = stringOrario.countTokens();
			if (numTokens==3){
				String ore = stringOrario.nextToken().trim();
				String minuti = stringOrario.nextToken().trim();
				String secondi = stringOrario.nextToken().trim();
				if ((ore+minuti+secondi).length()==8){
					retValue = 
						FillaCaratteri(ore,"0","S",2)  
						+ ":" + FillaCaratteri(minuti,"0","S",2)  
						+ ":" + FillaCaratteri(secondi,"0","S",2);
				}
			}
		}
		return retValue;
	}
	
}


