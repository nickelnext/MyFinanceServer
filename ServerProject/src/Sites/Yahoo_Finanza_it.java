package Sites;

import java.net.URL;

import Handlers.SiteInterface;
import Quotes.Quotation_Bond;
import Quotes.Quotation_Fund;
import Quotes.Quotation_Share;

public class Yahoo_Finanza_it implements SiteInterface {

	
	public Quotation_Bond parseBOT(URL url)
	{
		return parseBTP(url);
	}
	public Quotation_Bond parseCCT(URL url)
	{
		return parseBTP(url);
	}
	public Quotation_Bond parseCTZ(URL url)
	{
		return parseBTP(url);
	}
	public Quotation_Bond parseBOND(URL url)
	{
		return parseBTP(url);
	}
	public Quotation_Bond parseBTP(URL url)
	{
		return null;	
	}
	public Quotation_Fund parseFUND(URL url)
	{
		return null;
	}
	@Override
	public Quotation_Share parseSHARE(URL url) {
		// TODO Auto-generated method stub
		return null;
	}
}

