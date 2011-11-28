import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import Handlers.SiteInterface;
import Quotes.Quotation_Share;
import Search.Search;

public class Program {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException  {


				SiteInterface s = (SiteInterface)Class.forName("Sites.Borsaitaliana_it").newInstance();
//				Quotation_Bond q = s.parseBOND(new URL(
//						"http://www.borsaitaliana.it/borsa/obbligazioni/mot/obbligazioni-in-euro/dati-completi.html?isin=DE000UB2F5S4&lang=it"));

			


		//		SiteInterface s1 = (SiteInterface)Class.forName("Sites.Borse_it").newInstance();
		//		Quotation_Share qs = s1.parseSHARE(new URL("http://www.borse.it/quotes/valore/IT0001233417"));
		////		
		//		SiteInterface s2 = (SiteInterface)Class.forName("Sites.Finanza_Repubblica_it").newInstance();
		//		Quotation_Share q = s2.parseSHARE(new URL("http://bloomberg.finanza.repubblica.it/Company/?symbol=A2A:IM"));
		//		System.out.println(q.toString());
		//		Quotation_Fund q = s2.parseFUND(new URL("http://finanza.repubblica.it/Fondi_Scheda.aspx?addCode=FC.KWCARPAT"));
		//		System.out.println(q.toString());
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
//				Search src = (Search)Class.forName("Search.Borsaitaliana_it_Search").newInstance();
//				src.search("A2A", "http://www.borsaitaliana.it/borsa/quotazioni/azioni/cerca-titolo.html?target=search&go=y&fromhp=true&param=__ISIN__HERE__");
//				Quotation_Share qs = s.parseSHARE(new URL(src.getCompleteLink()));
//						"http://www.borsaitaliana.it/borsa/azioni/dati-completi.html?isin=IT0001233417&lang=it"));
//				System.out.println(qs.toString());
				
//		Search src = (Search)Class.forName("Search.Finanza_Virgilio_it_Search").newInstance();
//		src.search("IT0004220627", "http://economia.virgilio.it/ricerca/index.html?qs=__ISIN__HERE__&m=&canale=tutto");


				Search src = (Search)Class.forName("Search.Yahoo_Finanza_it_Search").newInstance();
				src.search("IT0003990402", "http://it.finance.yahoo.com/lookup?s=__ISIN__HERE__");
//				SiteInterface yahoo = (SiteInterface)Class.forName("Sites.Yahoo_Finanza_it").newInstance();




				System.out.println(src.getType());
				System.out.println(src.getCompleteLink());


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

	public static void compress(String st) throws IOException {
		// first compress inputfile.txt into out.gz
		BufferedReader in = new BufferedReader(new StringReader(st));
		BufferedOutputStream out = new BufferedOutputStream(
				new GZIPOutputStream(new FileOutputStream("out.gz")));
		int c;
		while ((c = in.read()) != -1) 
			out.write(c);
		in.close();
		out.close();

		// now decompress our new file
		BufferedReader in2 = new BufferedReader( new InputStreamReader(new GZIPInputStream(new FileInputStream("out.gz"))));
		String s;
		while ((s = in2.readLine()) != null)
			System.out.println(s);
	}

}
