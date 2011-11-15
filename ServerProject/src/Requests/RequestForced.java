package Requests;

public class RequestForced extends Request{
	
	String ignoredSite;

	public String getIgnoredSite() {
		return ignoredSite;
	}

	public void setIgnoredSite(String ignoredSite) {
		this.ignoredSite = ignoredSite;
	}

	public RequestForced(String iSIN, String ignoredSite) {
		super(iSIN);
		this.ignoredSite = ignoredSite;
	}
	
	

}
