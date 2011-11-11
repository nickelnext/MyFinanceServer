import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import Handlers.SiteInterface;
import Quotes.Quotation_Bond;
import Quotes.Quotation_Share;
import Search.Search;

public class Program {
	
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

	
//		SiteInterface s = (SiteInterface)Class.forName("Sites.Borsaitaliana_it").newInstance();
//		Quotation_Bond q = s.parseBOND(new URL(
//				"http://www.borsaitaliana.it/borsa/obbligazioni/mot/euro-obbligazioni/dati-completi.html?isin=IT0001233417&lang=it"));
		
//		Quotation_Share qs = s.parseBTP(new URL(
//				"http://www.borsaitaliana.it/borsa/azioni/dati-completi.html?isin=IT0000123064354&lang=it"));
		
		
//		SiteInterface s1 = (SiteInterface)Class.forName("Sites.Borse_it").newInstance();
//		Quotation_Share qs = s1.parseSHARE(new URL("http://www.borse.it/quotes/valore/IT0001233417"));
////		
//		SiteInterface s2 = (SiteInterface)Class.forName("Sites.Finanza_Repubblica_it").newInstance();
//		Quotation_Bond q = s2.parseBTP(new URL("http://finanza.repubblica.it/Obbligazioni_TitoliDiStato_Scheda.aspx?addCode=IT00047123297"));
//		
//		
//		if (q==null)
//			System.out.println("non trovato");
//		else
//			System.out.println("ok");
//		
//		System.out.println(q.toString());
//		System.out.println(qs.toString());
//		System.out.println(qs.getISIN());
//		System.out.println(qs.getPrezzoUltimoContratto());
//		System.out.println(q.getName());
		
//		System.out.println(q.toString());
		
		//YAHOO
//		SiteInterface sy = (SiteInterface)Class.forName("Sites.Yahoo_Finanza_it_").newInstance();
////		Quotation_Share qy = sy.parseSHARE(new URL("http://it.finance.yahoo.com/lookup?s=IT0004572910"));
//		Quotation_Bond qy = sy.parseBTP(new URL("http://it.finance.yahoo.com/lookup?s=IT0004572910"));
//		if (qy==null)
//		System.out.println("non trovato");
//	else
//		System.out.println("ok");
		
//		search
//		Search src = (Search)Class.forName("Search.Borsaitaliana_it_Search").newInstance();
//		String ppt = src.search("IT0004572910", "http://www.borsaitaliana.it/borsa/quotazioni/azioni/cerca-titolo.html?target=search&go=y&fromhp=true&param=__ISIN__HERE__");
//		System.out.println(ppt);
		Search src = (Search)Class.forName("Search.Yahoo_Finanza_it_Search").newInstance();
		String ppt = src.search("IT0004572910", "http://it.finance.yahoo.com/lookup?s=__ISIN__HERE__");
		System.out.println(ppt);
		
//		azione 
//		SiteInterface s = (SiteInterface)Class.forName("Sites.Borsaitaliana_it").newInstance();
//		s.parseSHARE(new URL("http://www.borsaitaliana.it/borsa/azioni/dati-completi.html?isin=IT0003990402&lang=it"),"IT0003990402");		
		
	}
	
}
