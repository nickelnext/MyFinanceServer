package Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.w3c.dom.NodeList;

public class UtilFuncs {

	public static final String ISIN_REPLACE = "__ISIN__HERE__";
//	public static final String ISIN_NOT_FOUND ="ISIN_NOT_FOUND";
	
	public static String getString(NodeList nodes, int n)
	{
		if(!nodes.item(n).hasChildNodes() || nodes.item(n).getFirstChild().getNodeValue() == null)
			return "";
		return nodes.item(n).getFirstChild().getNodeValue();
	}
	public static boolean isISIN(String s)
	{
		if(s.length()==12 && s.matches("[A-Z]{2}\\d{10}"))
			return true;
		return false;
	}
	public static float repFloat(String string) {
		if(string == "")
			return 0;
		if(string.matches("\\D+"))
			return 0;
			
		string = string.replace(",", ".");
		string = string.replace("%","");
		return Float.valueOf(string);
	}
	public static int repInteger(String string) {
		
		if(string == "")
			return 0;
		if(string.matches("\\D+"))
		{
			System.out.println("ho matchato NA");
			return 0;
		}
		string = string.replace(".", "");
		string = string.replace(",", "");
		return Integer.valueOf(string);
	}

	//	01/11/11 - 11.33.42

	public static Date formatDate(String string)
	{
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH.mm");
		Date date = null;
		try 
		{	
			date = (Date)formatter.parse(string);
		} 
		catch (ParseException e) 
		{
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			try 
			{
				date = (Date)formatter.parse(string);
			}
			catch (ParseException ex) 
			{
				formatter = new SimpleDateFormat("HH.mm");
				try 
				{
					formatter = new SimpleDateFormat("HH:mm:ss");
					date = new Date();
					date = formatter.parse(string);
					
					GregorianCalendar today = new GregorianCalendar();
					@SuppressWarnings("deprecation")
					GregorianCalendar cal = new GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 
					today.get(Calendar.DAY_OF_MONTH), date.getHours(), date.getMinutes(), date.getSeconds());
					date = cal.getTime();
				} 
				catch (ParseException e1) 
				{
					formatter = new SimpleDateFormat("dd/MM/yyyy - HH.mm.ss");
					try 
					{
						date = (Date)formatter.parse(string);
					} 
					catch (ParseException ex1) 
					{
						formatter = new SimpleDateFormat("HH:mm:ss");
						
						try 
						{
							formatter = new SimpleDateFormat("HH:mm:ss");
							date = new Date();
							date = formatter.parse(string);
							
							GregorianCalendar today = new GregorianCalendar();
							@SuppressWarnings("deprecation")
							GregorianCalendar cal = new GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 
							today.get(Calendar.DAY_OF_MONTH), date.getHours(), date.getMinutes(), date.getSeconds());
							date = cal.getTime();
						} 
						catch (ParseException ex2) 
						{
							System.out.println("Date not recognized: is it null or in an unknown format?");
						}
					}
				}
			}
		}
		return date;
	}	
	
	
	
	
	
}
