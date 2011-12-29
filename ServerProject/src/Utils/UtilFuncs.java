package Utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class UtilFuncs {

	public static final String ISIN_REPLACE = "__ISIN__HERE__";
	//	public static final String ISIN_NOT_FOUND ="ISIN_NOT_FOUND";

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
}
