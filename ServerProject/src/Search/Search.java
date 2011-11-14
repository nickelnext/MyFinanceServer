package Search;

import Quotes.Type;


public abstract class Search {

	private String completeLink;
	private Type type;
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public abstract boolean search(String ISIN, String searchUrl);

	public String getCompleteLink() {
		return completeLink;
	}

	public void setCompleteLink(String completeLink) {
		this.completeLink = completeLink;
	}
	
	
}
