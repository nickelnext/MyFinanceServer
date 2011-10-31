import java.net.MalformedURLException;
import java.net.URL;

import Handlers.SiteInterface;

public class Program {
	
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {


		SiteInterface s = (SiteInterface)Class.forName("Sites.Borsaitaliana_it").newInstance();
		s.parseBTP(new URL("http://www.borsaitaliana.it/borsa/obbligazioni/mot/btp/dati-completi.html?isin=IT0004467483&lang=it"));
		
		
//		s.getToolInfo("BTP", "IT0000366655");
		
//		s.getToolInfo("AZIONE", "IT0003990402");
		
		
//		s.getToolInfo("BOND", "DE000UB2F5S4");
//		s1.getToolInfo("CCT", "IT0004652175");
		
		/*
		WritableWorkbook workbook = Workbook.createWorkbook(new File("output/myfile.xls"));
		WritableSheet sheet = (WritableSheet) workbook.createSheet("Prova", 0);
		System.out.println(s.getResults().size());
		for(int i=0; i<s.getResults().size(); i++)
		{
			
			 Label label = new Label(i%2, i/2 , s.getResults().get(i));
			 sheet.addCell(label);
			
		}
		workbook.write();
		workbook.close();
*/
	}
	
}
