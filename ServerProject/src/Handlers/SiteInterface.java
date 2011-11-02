package Handlers;

import java.net.URL;

import Quotes.Quotation;
import Quotes.Quotation_Share;
import Quotes.Quotation_Bond;
import Quotes.Type;

public interface SiteInterface {


	public abstract Quotation_Bond parseBTP(URL url, String ISIN);
	public abstract Quotation_Bond parseBOT(URL url, String ISIN);
	public abstract Quotation_Bond parseCCT(URL url, String ISIN);
	public abstract Quotation_Bond parseCTZ(URL url, String ISIN);
	public abstract Quotation_Bond parseBOND(URL url, String ISIN);
	public abstract Quotation_Share parseSHARE(URL url, String ISIN);
	public abstract void parseFUND(URL url, String ISIN);
	
}
