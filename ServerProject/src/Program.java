import java.net.MalformedURLException;
import java.net.URL;

import Handlers.SiteInterface;

public class Program {
	
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {


		SiteInterface s = (SiteInterface)Class.forName("Sites.Borsaitaliana_it").newInstance();
		s.parseBTP(new URL("http://www.borsaitaliana.it/borsa/obbligazioni/mot/btp/dati-completi.html?isin=IT0004467483&lang=it"),"IT0004467483");
		
		
		
		
	}
	
}
