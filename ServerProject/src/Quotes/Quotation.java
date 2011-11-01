package Quotes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Quotation {

	private String name;
	private String ISIN;
	private String type;


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



	protected float rep(String string) {
		string = string.replace(",", ".");
		string = string.replace("%","");
		if(string.isEmpty())
			return 0;
		return Float.valueOf(string);
	}
	protected int rep2(String string) {
		if(string.isEmpty())
			return 0;
		string = string.replace(".", "");
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
						System.out.println("Date exploded");
					}
				}
			}
		}
		
	return date;
	}

}
