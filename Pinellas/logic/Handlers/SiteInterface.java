package Handlers;

import java.net.URL;

import Quotes.Quotation_Bond;
import Quotes.Quotation_Fund;
import Quotes.Quotation_Share;

public interface SiteInterface {


	public abstract Quotation_Bond parseBTP(URL url);
	public abstract Quotation_Bond parseBOT(URL url);
	public abstract Quotation_Bond parseCCT(URL url);
	public abstract Quotation_Bond parseCTZ(URL url);
	public abstract Quotation_Bond parseBOND(URL url);
	public abstract Quotation_Share parseSHARE(URL url);
	public abstract Quotation_Fund parseFUND(URL url);
	
}
