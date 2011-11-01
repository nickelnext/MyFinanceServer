package Quotes;

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
	protected Date formatDate(String string)
	{
		String[] arr = string.split(" ");
		return null;
	}
	
}
