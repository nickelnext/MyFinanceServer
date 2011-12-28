package Utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class UtilFuncs {

	public static final String ISIN_REPLACE = "__ISIN__HERE__";
	//	public static final String ISIN_NOT_FOUND ="ISIN_NOT_FOUND";
	public static final String[] datePatterns = {
		
		"dd/MM/yyyy HH.mm",
		"dd/MM/yyyy HH.mm.ss",
		"dd/MM/yyyy HH:mm",
		"dd/MM/yyyy HH:mm:ss",
		"dd/MM/yyyy", 
		//
		"dd/MM/yyyy - HH.mm",
		"dd/MM/yyyy - HH.mm.ss",
		"dd/MM/yyyy - HH:mm",
		"dd/MM/yyyy - HH:mm:ss",
		//
//		"dd-MM-yyyy",
		"dd-MM-yyyy HH.mm",
		"dd-MM-yyyy HH.mm.ss",
		"dd-MM-yyyy HH:mm",
		"dd-MM-yyyy HH:mm:ss",
		//
		"HH.mm",
		"HH.mm.ss",
		//
		"HH:mm",
		"HH:mm:ss",
		"dd MMMMM",
		"ddddd MM",
		"MMMMM dd",
		"MM ddddd",
		
		
	};
	
	private static String[] createEnglishPatterns(String[] it)
	{
		String[] en = new String[it.length];
		for (int i=0;i<it.length;i++)
		{
			en[i] = it[i].replace("M", "k");
			en[i] = en[i].replace("d", "M");
			en[i] = en[i].replace("k", "d");
		}
		return en;
	}
	
//	public static final String[] datePatternsEng = {
//		"MM/dd/yyyy",
//		"MM/dd/yyyy HH.mm",
//		"MM/dd/yyyy HH.mm.ss",
//		"MM/dd/yyyy HH:mm",
//		"MM/dd/yyyy HH:mm:ss",
//		//
//		"MM/dd/yyyy - HH.mm",
//		"MM/dd/yyyy - HH.mm.ss",
//		"MM/dd/yyyy - HH:mm",
//		"MM/dd/yyyy - HH:mm:ss",
//		//
//		"MM-dd-yyyy",
//		"MM-dd-yyyy HH.mm",
//		"MM-dd-yyyy HH.mm.ss",
//		"MM-dd-yyyy HH:mm",
//		"MM-dd-yyyy HH:mm:ss",
//		//
//		"HH.mm",
//		"HH.mm.ss",
//		//
//		"HH:mm",
//		"HH:mm:ss",
//	};

	public static final String[] datePatternsEng = UtilFuncs.createEnglishPatterns(UtilFuncs.datePatterns);
	
	public static final String countryUs = "us";
	public static final String countryIt = "it";
	public static final String countryDefault = "it";
	public static final String countryItDateUs = "itus";
	public static final String countryUsDateIt = "usit";
	



	public static String getString(NodeList nodes, int n)
	{
		if(!nodes.item(n).hasChildNodes() || nodes.item(n).getFirstChild().getNodeValue() == null)
			return "";
		return nodes.item(n).getFirstChild().getNodeValue();
	}
	public static String getString(Document doc, String nodeName)
	{
			return doc.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();
	}


	public static boolean isISIN(String s)
	{
		if(s.length()==12 && s.matches("[A-Z]{2}\\d{10}"))
			return true;
		return false;
	}
	public static float repFloat(String string, String country) {
		Locale l;
		switch (country) {
		case UtilFuncs.countryUs:
			l = Locale.US;
			break;
		case UtilFuncs.countryIt:
			l = Locale.ITALY;
			break;
		case UtilFuncs.countryItDateUs:
			l = Locale.ITALY;
			break;
		case UtilFuncs.countryUsDateIt:
			l = Locale.US;
			break;
		default:
			l = Locale.ITALY;
			break;
		}
		
		if(string == "")
			return 0;
		if(string.matches("\\D+"))
			return 0;
		try 
		{
			Number number = NumberFormat.getNumberInstance(l).parse(string);
			return number.floatValue();
		} 
		catch (ParseException e) 
		{
			if(string.matches("\\D+"))
			{
				System.out.println("ho matchato NA");
				return 0;
			}
		string = string.replace(",", ".");
		string = string.replace("%","");
		return Float.valueOf(string);
		}
	}
	public static int repInteger(String string) {

		if(string == "")
			return 0;
		try 
		{
			Number number = NumberFormat.getNumberInstance(Locale.ITALY).parse(string);
			return number.intValue();
		} 
		catch (ParseException e) 
		{
			if(string.matches("\\D+"))
			{
				System.out.println("ho matchato NA");
				return 0;
			}
			string = string.replace(".", "");
			string = string.replace(",", "");
			return Integer.valueOf(string);
		}
	}

	//	01/11/11 - 11.33.42


	public static Date formatDate(String s, String country) {
		String[] datepattern;
		Locale l;
		switch (country) {
		case UtilFuncs.countryIt:
			datepattern = datePatterns;
			l = Locale.ITALY;
			break;
		case UtilFuncs.countryUs:
			datepattern = datePatternsEng;
			l = Locale.US;
			break;
		case UtilFuncs.countryUsDateIt:
			datepattern = datePatterns;
			l = Locale.US;
			break;
		case UtilFuncs.countryItDateUs:
			datepattern = datePatternsEng;
			l = Locale.ITALY;
			break;
		default:
			datepattern = datePatterns;
			l = Locale.ITALY;
			break;
		}
		Date date = null;
		int i=0;
		DateFormat formatter;
		boolean found=false;
		
		while(!found && i< datepattern.length)
		{
			formatter = new SimpleDateFormat(datepattern[i], l);
			try {
				//
				date = formatter.parse(s);
				if(!datepattern[i].contains("/") && !datepattern[i].contains("-"))
				{
					//only time of the day i set, no date, so we need to add it using calendar
					GregorianCalendar today = new GregorianCalendar();
					@SuppressWarnings("deprecation")
					GregorianCalendar cal = new GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 
							today.get(Calendar.DAY_OF_MONTH), date.getHours(), date.getMinutes(), date.getSeconds());
					date = cal.getTime();
				}
				System.out.println("found\t" + datepattern[i]);
				found = true;
			}
			catch (ParseException e) {
				System.out.println(datepattern[i]);
				i++;	
			}
		}
		return date;
	}
	
	
	

}
