import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import Handlers.SiteInterface;

public class Program {
	
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

//		String s = "22/08/2021 18.15";
		
//		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH.mm");
//		Date date = (Date)formatter.parse(s);  
//		System.out.println((date));
//		System.out.println(formatter.format(date));
		
		SiteInterface s = (SiteInterface)Class.forName("Sites.Borsaitaliana_it").newInstance();
		s.parseBTP(new URL("http://www.borsaitaliana.it/borsa/obbligazioni/mot/btp/dati-completi.html?isin=IT0004467483&lang=it"),"IT0004467483");
//		SiteInterface s1 = (SiteInterface)Class.forName("Sites.Borse_it").newInstance();
//		s1.parseBTP(new URL("http://www.borse.it/quotes/valore/IT0004467483"),"IT0004467483");
//		
//		SiteInterface s2 = (SiteInterface)Class.forName("Sites.Finanza_Repubblica_it").newInstance();
//		s.parseBTP(new URL("http://finanza.repubblica.it/Obbligazioni_TitoliDiStato_Scheda.aspx?addCode=IT0004467483"),"IT0004467483");
//		
	}
	
}
