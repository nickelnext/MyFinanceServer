package Quotes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Quotation {

	private String name;
	private String ISIN;
	private String type;
	private String site;

	public Quotation(String isin) {
		super();
		this.ISIN = isin;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getISIN() {
		return this.ISIN;
	}
	public void setISIN(String isin) {
		this.ISIN = isin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}



	protected float repFloat(String string) {
		if(string.matches("\\w.*"))
			return 0;
		if(string.isEmpty())
			return 0;
		string = string.replace(",", ".");
		string = string.replace("%","");

		return Float.valueOf(string);
	}
	protected int repInteger(String string) {
		if(string.isEmpty())
			return 0;
		if(string.matches("\\w.*"))
			return 0;
		string = string.replace(".", "");
		string = string.replace(",", "");
		return Integer.valueOf(string);
	}

	//	01/11/11 - 11.33.42

	protected Date formatDate(String string)
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
					date = (Date)formatter.parse(string);
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
							date = (Date)formatter.parse(string);
						} 
						catch (ParseException ex2) 
						{
							System.out.println("Date exploded");
						}
					}
				}
			}
		}

		return date;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
