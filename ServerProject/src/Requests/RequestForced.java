package Requests;

import java.util.ArrayList;

public class RequestForced extends Request{
	
	ArrayList<String> ignoredSites;

	public ArrayList<String> getIgnoredSite() {
		return ignoredSites;
	}

	public void setIgnoredSites(ArrayList<String> ignoredSites) {
		this.ignoredSites = ignoredSites;
	}

	public RequestForced(String iSIN, ArrayList<String> ignoredSites) {
		super(iSIN);
		this.ignoredSites = ignoredSites;
	}
	
	

}
