package Requests;

import Quotes.QuotationType;



public class RequestUpdate extends Request{

	private String preferredSite;
	private QuotationType type;

	public RequestUpdate(String idCode, QuotationType type, String preferredSite) {
		super(idCode);
		this.preferredSite = preferredSite;
		this.type = type;
	}

	public String getPreferredSite() {
		return preferredSite;
	}

	public void setPreferredSite(String preferredSite) {
		this.preferredSite = preferredSite;
	}
		
}
