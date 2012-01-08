package Utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class UtilFuncs {

	public static final String ISIN_REPLACE = "__ISIN__HERE__";
	
	
	//APP
	public static final String SERVER_VERSION = "0.8";
	public static final String CLIENT_SUPPORTED = "0.8";
	
	
	//isin
	public static final String ERROR_ISIN_LOCALLY_NOT_FOUND = "ISIN_LOCALLY_NOT_FOUND";
	public static final String ERROR_ISIN_GLOBALLY_NOT_FOUND = "ISIN_GLOBALLY_NOT_FOUND";
	public static final String ERROR_ISIN_INVALID_LENGTH = "ERROR_ISIN_INVALID_LENGTH";
	
	
	//quotation
	public static final String ERROR_QUOTATION_NULL = "ERROR_QUOTATION_NULL";
	
	//exceptions
	public static final String ERROR_PARSE_EXCEPTION = "ERROR_PARSE_EXCEPTION";
	public static final String ERROR_PARSING_XML_EXCEPTION = "ERROR_PARSING_XML_EXCEPTION";
	public static final String ERROR_PARSING_HTML_EXCEPTION = "ERROR_PARSING_HTML_EXCEPTION";
	public static final String ERROR_ARRAY_INDEXOUTOFBOUND_EXCEPTION = "ERROR_ARRAY_INDEXOUTOFBOUND_EXCEPTION";
	public static final String ERROR_STRING_INDEXOUTOFBOUND_EXCEPTION = "ERROR_STRING_INDEXOUTOFBOUND_EXCEPTION";
	public static final String ERROR_IO_EXCEPTION = "ERROR_IO_EXCEPTION";
	public static final String ERROR_SAX_EXCEPTION = "ERROR_SAX_EXCEPTION";
	public static final String ERROR_PARSING_DATE_EXCEPTION = "ERROR_PARSING_DATE_EXCEPTION";
	
	//numbers
	public static final String ERROR_NOT_A_NUMBER = "ERROR_NOT_A_NUMBER";

	
	//responses
	public static final String ERROR_NONE = "ERROR_NONE";
	public static final String ERROR_OK = "ERROR_OK";
	
	//database
	public static final String ERROR_DATABASE_CONNECTION = "ERROR_DATABASE_CONNECTION";
	public static final String ERROR_INVALID_SQL = "ERROR_INVALID_SQL";
	public static final String ERROR_EMPTY_TABLE = "ERROR_EMPTY_TABLE";
	public static final String ERROR_INVALID_TABLE = "ERROR_INVALID_TABLE";
	//json
	public static final String ERROR_JSON_PACKING = "ERROR_JSON_PACKING";
	
	
	
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
			try
			{
				return doc.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();
			}
			catch(Exception e)
			{
				//TODO
				return null;
			}
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
