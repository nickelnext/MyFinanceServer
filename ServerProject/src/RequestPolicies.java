import java.util.Vector;

import Requests.Request;
import Requests.RequestForced;
import Requests.RequestQuotation;
import Requests.RequestUpdate;


public class RequestPolicies {

	private Vector siteSearch;
	private Vector shareList;
	private Vector  bondList;
	private Vector  fundList;

	
	public RequestPolicies(	) {
		
	}
	
	public void handleRequest(Request req){
		if (req instanceof RequestUpdate) {
			
		}else if (req instanceof RequestForced){
			
		}else if(req instanceof RequestQuotation){
			
		}
		
	}
	
	public Vector getSiteSearch() {
		return siteSearch;
	}
	public void setSiteSearch(Vector siteSearch) {
		this.siteSearch = siteSearch;
	}
	public Vector getShareList() {
		return shareList;
	}
	public void setShareList(Vector shareList) {
		this.shareList = shareList;
	}
	public Vector getBondList() {
		return bondList;
	}
	public void setBondList(Vector bondList) {
		this.bondList = bondList;
	}
	public Vector getFundList() {
		return fundList;
	}
	public void setFundList(Vector fundList) {
		this.fundList = fundList;
	}
	
	
	
}
