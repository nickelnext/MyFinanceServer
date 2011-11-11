package Search;


public abstract class Search {

	private String baseLink;
	private String ISIN;
	private String code;
	
	public abstract String search(String ISIN, String searchUrl);

	public String getBaseLink() {
		return baseLink;
	}

	public void setBaseLink(String baseLink) {
		this.baseLink = baseLink;
	}

	public String getISIN() {
		return ISIN;
	}

	public void setISIN(String iSIN) {
		ISIN = iSIN;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
