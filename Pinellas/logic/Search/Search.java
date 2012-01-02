package Search;

import Quotes.QuotationType;


public abstract class Search {

	private String completeLink;
	private QuotationType type;
	
	public QuotationType getType() {
		return type;
	}

	public void setType(QuotationType bond) {
		this.type = bond;
	}

	public abstract boolean search(String ISIN, String searchUrl);

	public String getCompleteLink() {
		return completeLink;
	}

	public void setCompleteLink(String completeLink) {
		this.completeLink = completeLink;
	}
	
	
}
