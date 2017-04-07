package Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

public class DateTime {
	
	
	public DateTime() {	}

	public static String getSimpleDateFormat(String format, Date dataDaFormattare){
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat(format, java.util.Locale.ITALIAN); 	
        return simpleDateFormat.format(dataDaFormattare);
	}
	
	/**
	 * la data di oggi in formato DD/MM/AAAA
	 * 
	 * @return
	 */
	public String getToday() {
		String today = "";
		Calendar cal = Calendar.getInstance();

		int gg = cal.get(Calendar.DAY_OF_MONTH);
		int mm = (cal.get(Calendar.MONTH) + 1);
		int aaaa = cal.get(Calendar.YEAR);

		String strGG = "" + gg;
		if (gg < 10)
			strGG = "0" + gg;

		String strMM = "" + mm;
		if (mm < 10)
			strMM = "0" + mm;

		today = strGG + "/" + strMM + "/" + aaaa;
		return today;
	}

	public boolean isMinoreUguale(String dataUno, String dataDue) {
		boolean isMinore = false;
		
		if(dataUno.length()==10 && dataDue.length() == 10) {
			if(dataUno.equals(dataDue)) {
				isMinore = true;
			}else {
				int i_gg_1 = new Integer(dataUno.substring(0,2)).intValue();
				int i_mm_1 = new Integer(dataUno.substring(3,5)).intValue();
				int i_aaaa_1 = new Integer(dataUno.substring(6,10)).intValue();
				Calendar cal_1 = Calendar.getInstance();
				cal_1.set(i_aaaa_1,i_mm_1 - 1 ,i_gg_1);
				
				int i_gg_2 = new Integer(dataDue.substring(0,2)).intValue();
				int i_mm_2 = new Integer(dataDue.substring(3,5)).intValue();
				int i_aaaa_2 = new Integer(dataDue.substring(6,10)).intValue();
				
				Calendar cal_2 = Calendar.getInstance();
				cal_2.set(i_aaaa_2,i_mm_2 - 1 ,i_gg_2);
				isMinore = cal_1.before(cal_2);
			}
		}
		
		return isMinore;
	}
	public boolean isMinore(String dataUno, String dataDue) {
		boolean isMinore = false;
		if(dataUno.length()==10 && dataDue.length() == 10) {
			if(dataUno.equals(dataDue)) {
				isMinore = false;
			}else {
				int i_gg_1 = new Integer(dataUno.substring(0,2)).intValue();
				int i_mm_1 = new Integer(dataUno.substring(3,5)).intValue();
				int i_aaaa_1 = new Integer(dataUno.substring(6,10)).intValue();
				Calendar cal_1 = Calendar.getInstance();
				cal_1.set(i_aaaa_1,i_mm_1 - 1 ,i_gg_1);
				
				int i_gg_2 = new Integer(dataDue.substring(0,2)).intValue();
				int i_mm_2 = new Integer(dataDue.substring(3,5)).intValue();
				int i_aaaa_2 = new Integer(dataDue.substring(6,10)).intValue();
				
				Calendar cal_2 = Calendar.getInstance();
				cal_2.set(i_aaaa_2,i_mm_2 - 1 ,i_gg_2);
				isMinore = cal_1.before(cal_2);
			}
		}
		return isMinore;
	}
	
	
	String days[] = { "DO", "LU", "MA", "ME", "GI", "VE", "SA" };

	String months[] = { "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio",
			"Giugno", "Luglio", "Agosto", "Settembre", "0ttobre", "Novembre",
			"Dicembre" };

	int DaysInMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	static final int YTOP = 90; /* y-size of margin above calendar box */

	static final int YHEADER = 30; /* y-size of horz strip with day names */

	static final int NCELLX = 7; /* number of cells across */

	static final int CELLSIZE = 60; /* size of each square cell */

	static final int MARGIN = 8; /* margin from number to cell top, right */

	static final int FEBBRAIO = 1; /* special month during leap years */

	/*
	 * USE: Calculates day of the week the first day of the month falls on. IN:
	 * year = given year after 1582 (start of the Gregorian calendar). month = 0
	 * for January, 1 for FEBBRAIO, etc. OUT: First day of month: 0 = Sunday, 1 =
	 * Monday, etc.
	 */
	/**
	 * CALCOLA IL PRIMO GIORNO DEL MESE DATO L'ANNO E IL MESE
	 */
	public int CalcFirstOfMonth(int year, int month) {
		int firstDay; /* day of week for Jan 1, then first day of month */
		int i; /* to traverse months before given month */
		/* Start at 1582, when modern calendar starts. */
		if (year < 1582)
			return (-1);
		/* Catch month out of range. */
		if ((month < 0) || (month > 11))
			return (-1);
		/* Get day of week for Jan 1 of given year. */
		firstDay = CalcJanuaryFirst(year);

		/*
		 * Increase firstDay by days in year before given month to get first day
		 * of month.
		 */
		for (i = 0; i < month; i++)
			firstDay += DaysInMonth[i];

		/* Increase by one if month after FEBBRAIO and leap year. */
		if ((month > FEBBRAIO) && IsLeapYear(year))
			firstDay++;

		/* Convert to day of the week and return. */
		return (firstDay % 7);
	} // CalcFirstOfMonth

	/*
	 * USE: Calculate day of the week on which January 1 falls for given year.
	 * IN: year = given year after 1582 (start of the Gregorian calendar). OUT:
	 * Day of week for January 1: 0 = Sunday, 1 = Monday, etc. NOTE: Formula
	 * starts with a 5, since January 1, 1582 was a Friday; then advances the
	 * day of the week by one for every year, adding the number of leap years
	 * intervening, because those years Jan 1 advanced by two days. Calculate
	 * mod 7 to get the day of the week.
	 */
	private int CalcJanuaryFirst(int year) {
		/* Start at 1582, when modern calendar starts. */
		if (year < 1582)
			return (-1);

		/* Start Fri 01-01-1582; advance a day for each year, 2 for leap yrs. */
		return ((5 + (year - 1582) + CalcLeapYears(year)) % 7);
	} // CalcJanuaryFirst

	/*
	 * USE: Calculate number of leap years since 1582. IN: year = given year
	 * after 1582 (start of the Gregorian calendar). OUT: number of leap years
	 * since the given year, -1 if year < 1582 NOTE: Count doesn't include the
	 * given year if it is a leap year. In the Gregorian calendar, used since
	 * 1582, every fourth year is a leap year, except for years that are a
	 * multiple of a hundred, but not a multiple of 400, which are no longer
	 * leap years. Years that are a multiple of 400 are still leap years: 1700,
	 * 1800, 1990 were not leap years, but 2000 will be.
	 */
	private int CalcLeapYears(int year) {
		int leapYears; /* number of leap years to return */
		int hundreds; /* number of years multiple of a hundred */
		int fourHundreds; /* number of years multiple of four hundred */

		/* Start at 1582, when modern calendar starts. */
		if (year < 1582)
			return (-1);

		/* Calculate number of years in interval that are a multiple of 4. */
		leapYears = (year - 1581) / 4;

		/*
		 * Calculate number of years in interval that are a multiple of 100;
		 * subtract, since they are not leap years.
		 */
		hundreds = (year - 1501) / 100;
		leapYears -= hundreds;

		/*
		 * Calculate number of years in interval that are a multiple of 400; add
		 * back in, since they are still leap years.
		 */
		fourHundreds = (year - 1201) / 400;
		leapYears += fourHundreds;

		return (leapYears);
	} // CalcLeapYears

	/*
	 * USE: Determines if given year is a leap year. IN: year = given year after
	 * 1582 (start of the Gregorian calendar). OUT: TRUE if given year is leap
	 * year, FALSE if not. NOTE: Formulas capture definition of leap years; cf
	 * CalcLeapYears().
	 */
	private boolean IsLeapYear(int year) {
		/* If multiple of 100, leap year iff multiple of 400. */
		if ((year % 100) == 0)
			return ((year % 400) == 0);
		/* Otherwise leap year iff multiple of 4. */
		return ((year % 4) == 0);
	} // IsLeapYear

	public static Vector getMesi(){
		Vector mesi = new Vector();
		mesi.add(Calendar.JANUARY,"Gennaio");
		mesi.add(Calendar.FEBRUARY,"Febbraio");
		mesi.add(Calendar.MARCH,"Marzo");
		mesi.add(Calendar.APRIL,"Aprile");
		mesi.add(Calendar.MAY,"Maggio");
		mesi.add(Calendar.JUNE,"Giugno");
		mesi.add(Calendar.JULY,"Luglio");
		mesi.add(Calendar.AUGUST,"Agosto");
		mesi.add(Calendar.SEPTEMBER,"Settembre");
		mesi.add(Calendar.OCTOBER,"Ottobre");
		mesi.add(Calendar.NOVEMBER,"Novembre");
		mesi.add(Calendar.DECEMBER,"Dicembre");
		return mesi;
	}
	
	//per utilizzo con GregorianCalendar
	public static Vector getGiorniDellaSettimana(){
		Vector giorniDellaSettimana = new Vector();
		giorniDellaSettimana.add(0,"");
		giorniDellaSettimana.add(Calendar.SUNDAY,"Domenica");
		giorniDellaSettimana.add(Calendar.MONDAY,"Lunedì");
		giorniDellaSettimana.add(Calendar.TUESDAY,"Martedì");
		giorniDellaSettimana.add(Calendar.WEDNESDAY,"Mercoledì");
		giorniDellaSettimana.add(Calendar.THURSDAY,"Giovedì");
		giorniDellaSettimana.add(Calendar.FRIDAY,"Venerdì");
		giorniDellaSettimana.add(Calendar.SATURDAY,"Sabato");
		return giorniDellaSettimana;
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
	 * hh.mm.ss=hh:ms:ss
	 * hh.mm=hh:mm
	 * @param ora
	 * @return
	 */
	public static String decodificaOraDaHost(String ore) {
		String retValue = "";
		if (ore != null && !"".equals(ore.trim())){
			StringTokenizer stringData = new StringTokenizer(ore, ".");
			int numTokens = stringData.countTokens();
			String ora = stringData.nextToken().trim();;
			String minuti = stringData.nextToken().trim();;
			if (numTokens==3){
				String secondi = stringData.nextToken().trim();
				if ((ora+minuti+secondi).length()==6){
					retValue = ora + ":" + minuti + ":" + secondi;
				}
			} else if (numTokens==2){
				if ((ora+minuti).length()==4){
					retValue = ora + ":" + minuti;
				}
			}
		}
		return retValue;
	}
	
	/**
	 * Passando una data in formato dd/mm/yyyy
	 * restituisce un date
	 * @return
	 */
	public static Date getDateFromString(String data){
		GregorianCalendar gregorianCalendar = new GregorianCalendar(Locale.ITALIAN);
		StringTokenizer stringTokenizer = new StringTokenizer(data, "/");	
		int giorno = Integer.parseInt(stringTokenizer.nextToken());
		int mese = Integer.parseInt(stringTokenizer.nextToken())-1;
		int anno = Integer.parseInt(stringTokenizer.nextToken());
		gregorianCalendar.set(giorno, mese, anno);
		return gregorianCalendar.getGregorianChange();
	}
	
	/**
	 * passando una data nel formato dd sep mm sep yyyy (es. dd/mm/yyyy)
	 * viene restituita la data nel formato yyyymmdd
	 * @param data stringa contenente la data
	 * @param sep separatore usato 
	 * @return
	 */
	public static String getDateStringWithoutSep(String data, String sep){
		StringTokenizer stringTokenizer = new StringTokenizer(data, sep);
		String giorno = (String)stringTokenizer.nextElement();
		String mese = (String)stringTokenizer.nextElement();
		String anno = (String)stringTokenizer.nextElement();
		return anno+mese+giorno;
		
	}
}
