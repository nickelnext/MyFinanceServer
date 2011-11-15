package Requests;


public class Request {
	private String ISIN;
	
	public Request(String ISIN) {
		this.ISIN = ISIN;
	}
	public String getISIN() {
		return ISIN;
	}
	public void setISIN(String iSIN) {
		this.ISIN = iSIN;
	}
	
	//TODO pensare ad altri attributi/preferenze utili
	
}
