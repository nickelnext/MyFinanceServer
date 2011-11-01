package Handlers;

import java.net.URL;

import Quotes.Quotation_Bond;

public interface SiteInterface {

	public abstract Quotation_Bond parseBTP(URL url, String ISIN);
	public abstract void parseBOT(URL url, String ISIN);
	public abstract void parseCCT(URL url, String ISIN);
	public abstract void parseCTZ(URL url, String ISIN);
	public abstract void parseBOND(URL url, String ISIN);
	public abstract void parseSHARE(URL url, String ISIN);
	public abstract void parseFUND(URL url, String ISIN);
	
}
