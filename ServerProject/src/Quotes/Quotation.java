package Quotes;

public class Quotation {
	
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
	
	

}
