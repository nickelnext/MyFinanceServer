import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import Handlers.SiteInterface;
import Quotes.Quotation_Bond;

public class Program {
	
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

	
		SiteInterface s = (SiteInterface)Class.forName("Sites.Borsaitaliana_it").newInstance();
		Quotation_Bond q = s.parseBOT(new URL(
				"http://www.borsaitaliana.it/borsa/obbligazioni/mot/bot/dati-completi.html?isin=" +
				"IT0004719297" +
				"&lang=it"));
		
		
		
		
		
//		SiteInterface s1 = (SiteInterface)Class.forName("Sites.Borse_it").newInstance();
//		Quotation_Bond q = s1.parseBTP(new URL("http://www.borse.it/quotes/valore/IT0004467483"),"IT0004467483");
//		
		if (q==null)
			System.out.println("non trovato");
		else
			System.out.println("ok");
		
		System.out.println(q.toString());
//		System.out.println(q.getName());
		
//		System.out.println(q.toString());
//		SiteInterface s2 = (SiteInterface)Class.forName("Sites.Finanza_Repubblica_it").newInstance();
//		s2.parseBTP(new URL("http://finanza.repubblica.it/Obbligazioni_TitoliDiStato_Scheda.aspx?addCode=IT0004467483"),"IT0004467483");

//		azione 
//		SiteInterface s = (SiteInterface)Class.forName("Sites.Borsaitaliana_it").newInstance();
//		s.parseSHARE(new URL("http://www.borsaitaliana.it/borsa/azioni/dati-completi.html?isin=IT0003990402&lang=it"),"IT0003990402");		
		
	}
	
}
