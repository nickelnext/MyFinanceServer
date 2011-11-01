import Quotes.Type;


public class Request {
	private String ISIN;
	private Type type;
	
	public Request(String ISIN, Type type) {
		this.ISIN = ISIN;
		this.type = type;
	}
	public String getISIN() {
		return ISIN;
	}
	public void setISIN(String iSIN) {
		this.ISIN = iSIN;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
}
