package mainpackage;
import java.io.IOException;
import java.net.URL;

import Handlers.SiteInterface;
import Quotes.Quotation_Fund;
import Quotes.Quotation_Share;

public class Program {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException  {


//				SiteInterface s = (SiteInterface)Class.forName("Sites.Borsaitaliana_it").newInstance();
//				Quotation_Bond q = s.parseBOND(new URL(
//						"http://www.borsaitaliana.it/borsa/obbligazioni/mot/obbligazioni-in-euro/dati-completi.html?isin=DE000UB2F5S4&lang=it"));
				
//		SiteInterface s = (SiteInterface)Class.forName("Sites.Finanza_Virgilio_it").newInstance();
//		SiteInterface s = (SiteInterface)Class.forName("Sites.EuroTLX_com").newInstance();
//		Quotation_Bond q = s.parseBTP(new URL("http://www.eurotlx.com/home3/popup.php?dd=T1MZ14"));
//		System.out.println(q.toString());
		SiteInterface s = (SiteInterface)Class.forName("Sites.Boerse_Frankfurt_de").newInstance();
//		Quotation_Bond q = s.parseBOND(new URL("http://www.boerse-frankfurt.de/EN/index.aspx?pageID=108&ISIN=XS0650262875"));
//		Quotation_Bond q = s.parseBOND(new URL("http://finanza.economia.virgilio.it/borsa-italiana/titoli-stato/cct-1mz14-ind_IT0004224041.html"));
//		System.out.println(q.toString());
		
//		Quotation_Share qs = s.parseSHARE(new URL(
//				"http://www.eurotlx.com/home3/popup.php?dd=GOOG-U"));

//		Quotation_Fund qs = s.parseFUND(new URL(
//				"http://www.boerse-frankfurt.de/EN/index.aspx?pageID=104&ISIN=FR0010135103"));
		Quotation_Share qs = s.parseSHARE(new URL(
				"http://www.boerse-frankfurt.de/EN/index.aspx?pageID=35&ISIN=DE0007100000"));
		
		
		
//		Quotation_Share qs = s.parseSHARE(new URL(
//				"http://finanza.economia.virgilio.it/borsa-italiana/azioni/a2a_IT0001233417.html"));
		System.out.println(qs.toString());
		
		
//		System.out.println(Calendar.getInstance().get(Calendar.getInstance().YEAR));
		
		//		SiteInterface s1 = (SiteInterface)Class.forName("Sites.Borse_it").newInstance();
		//		Quotation_Share qs = s1.parseSHARE(new URL("http://www.borse.it/quotes/valore/IT0001233417"));
		////		
		//		SiteInterface s2 = (SiteInterface)Class.forName("Sites.Finanza_Repubblica_it").newInstance();
		//		Quotation_Share q = s2.parseSHARE(new URL("http://bloomberg.finanza.repubblica.it/Company/?symbol=A2A:IM"));
		//		System.out.println(q.toString());
		//		Quotation_Fund q = s2.parseFUND(new URL("http://finanza.repubblica.it/Fondi_Scheda.aspx?addCode=FC.KWCARPAT"));
//				System.out.println(q.toString());
		//		System.out.println();
		//		
		//		
		//		if (q==null)
		//			System.out.println("non trovato");
		//		else
		//			System.out.println("ok");
		//		
//				System.out.println(q.toString());
		//		System.out.println(qs.toString());
		//		System.out.println(qs.getISIN());
		//		System.out.println(qs.getPrezzoUltimoContratto());
		//		System.out.println(q.getName());

		//		System.out.println(q.toString());

		//		Gson g = new Gson();
		//		String jsonString = g.toJson(q);
		//		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		//		Program.compress(jsonString);







		//		System.out.println(jsonString);
		//		System.out.println("Bytes: \t" + jsonString.getBytes().length);
		//		System.out.println("KBytes: \t" + jsonString.getBytes().length/1024);
		//		GZIPOutputStream gz = new GZIPOutputStream(bs);
		//		bs.write(jsonString.getBytes());
		//		
		//		System.out.println("bs.size()\t" + bs.size());
		//		System.out.println("bs.tostring()\t" + bs.toString());
		//		System.out.println("bs.tostring()\t" + bs.);
		//		gz.close();


		//YAHOO
//				SiteInterface sy = (SiteInterface)Class.forName("Sites.Yahoo_Finanza_it").newInstance();
//				Quotation_Share qy = sy.parseSHARE(new URL("http://it.finance.yahoo.com/lookup?s=IT0004572910"));
//		//		Quotation_Bond qy = sy.parseBTP(new URL("http://it.finance.yahoo.com/lookup?s=IT0004572910"));
//				if (qy==null)
//				System.out.println("non trovato");
//			else
//				System.out.println("ok");
//				System.out.println(qy.toString());
		//		search
//				Search src = (Search)Class.forName("Search.Boerse_Frankfurt_de_Search").newInstance();
//				boolean k = src.search("XS0209139244", "http://www.boerse-frankfurt.de/EN/index.aspx?pageID=108&ISIN=__ISIN__HERE__");
//				System.out.println(k);
//				System.out.println(src.getCompleteLink());
//				Quotation_Share qs = s.parseSHARE(new URL(src.getCompleteLink()));
//						"http://www.borsaitaliana.it/borsa/azioni/dati-completi.html?isin=IT0001233417&lang=it"));
//				System.out.println(qs.toString());
				
//		Search src = (Search)Class.forName("Search.Finanza_Virgilio_it_Search").newInstance();
//		boolean k;
		//BOND
//		k = src.search("IT0004572910", "http://economia.virgilio.it/ricerca/index.html?qs=__ISIN__HERE__&m=&canale=tutto");
//		System.out.println(k);
//		System.out.println(src.getCompleteLink());
		
		//azione
//		k = src.search("US38259P5089", "http://economia.virgilio.it/ricerca/index.html?qs=__ISIN__HERE__&m=&canale=tutto");
//		System.out.println(k);
//		System.out.println(src.getCompleteLink());

//				Search src = (Search)Class.forName("Search.Yahoo_Finanza_it_Search").newInstance();
//				src.search("IT0003990402", "http://it.finance.yahoo.com/lookup?s=__ISIN__HERE__");
//				SiteInterface yahoo = (SiteInterface)Class.forName("Sites.Yahoo_Finanza_it").newInstance();




//				System.out.println(src.getType());
//				System.out.println(src.getCompleteLink());


		//		Search src = (Search)Class.forName("Search.Finanza_Repubblica_it_Search").newInstance();
		//		src.search("IT0004572910", "http://finanza.repubblica.it/ricercaTitolo.aspx?searchText=__ISIN__HERE__");
		//		
		//		
		//		System.out.println("tuamadre");
		//		System.out.println(src.getCompleteLink());
		//		System.out.println(src.getType());



		//		System.out.println(src.getCode());
		//		System.out.println(src.getISIN());

		//		Quotation_Bond q = yahoo.parseCCT(new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20%28%22PAP12.MI%22%29&env=store://datatables.org/alltableswithkeys"));
		//		System.out.println(q.toString());
//				Quotes.Quotation_Share qs = yahoo.parseSHARE(new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20%28%22A2A.MI%22%29&env=store://datatables.org/alltableswithkeys"));
//				System.out.println(qs.toString());

		//		azione 
		//		SiteInterface s = (SiteInterface)Class.forName("Sites.Borsaitaliana_it").newInstance();
		//		s.parseSHARE(new URL("http://www.borsaitaliana.it/borsa/azioni/dati-completi.html?isin=IT0003990402&lang=it"),"IT0003990402");		

		//		SiteInterface s = (SiteInterface)Class.forName("Sites.Borsaitaliana_it").newInstance();
		//		
		//		Quotation_Bond qy = s.parseCTZ(new URL("http://www.borsaitaliana.it/borsa/obbligazioni/mot/ctz/dati-completi.html?isin=IT0004572910&lang=it"));
		//		System.out.println(qy.toString());
	}

	

}
