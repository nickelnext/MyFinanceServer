package Quotes;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Quotation {

	protected String name;
	protected String ISIN;
	protected String type;
	protected String site;

	public Quotation() {
		super();
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
		if(string == "")
			return 0;
		if(string.matches("\\^d.*"))
			return 0;
		string = string.replace(",", ".");
		string = string.replace("%","");
		return Float.valueOf(string);
	}
	protected int repInteger(String string) {
		string = string.replace(".", "");
		string = string.replace(",", "");
		if(string == "")
			return 0;
		if(string.matches("\\^d.*"))
			return 0;
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
							System.out.println("Date not recognized: is it null or in an unknown format?");
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
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
          result.append("  ");
          try {
            result.append( field.getName() );
            result.append(": ");
            //requires access to private field:
            result.append( field.get(this));
          } catch ( IllegalAccessException ex ) {
//            System.out.println(ex);
          }
          result.append(newLine);
        }
        result.append("}");

        return result.toString();
      }



	
}
