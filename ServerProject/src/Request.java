
public class Request {
	private String ISIN;
	private String type;
	
	public Request(String ISIN, String type) {
		this.ISIN = ISIN;
		this.type = type;
	}
	public String getISIN() {
		return ISIN;
	}
	public void setISIN(String iSIN) {
		this.ISIN = iSIN;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
