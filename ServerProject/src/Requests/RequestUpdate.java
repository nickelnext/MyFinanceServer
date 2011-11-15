package Requests;

import Quotes.Type;



public class RequestUpdate extends Request{

	private String preferredSite;
	private Type type;

	public RequestUpdate(String iSIN, Type type, String preferredSite) {
		super(iSIN);
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
