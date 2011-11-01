
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import Handlers.SiteInterface;
import Quotes.Quotation;
import Quotes.Type;

public class RequestHandler {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, MalformedURLException {
		MyDatabase db = new MyDatabase("pinella", "pinella", "aa8bbf9b");
		// connessione al db
		if ( !db.connect() ) { 
		   System.out.println("Errore durante la connessione.");
		   System.out.println( db.getError() );
		   System.exit(0);
		}
		Request req = new Request("IT0004286966", Type.BTP);
		String requestedSite;
		String requestedUrl;
		boolean found;
		
		//eseguo ricerca nella tabella isin_site
		Vector v1 = db.execQuery("SELECT site,url FROM tbl_isin_site WHERE ISIN=\""+req.getISIN()+"\";" );
		
		if(0 != v1.size()){//isin presente in isin_site 
			
			System.out.println("isin trovato nella tabella isin-sito!");
			String[] record1 = (String[]) v1.elementAt(0);
			requestedSite = record1[0];
			requestedUrl = record1[1];
			System.out.println("istanzio una classe "+requestedSite+" ..."+" e lancio il parser per il sito "+requestedUrl);
			SiteInterface s1 = (SiteInterface)Class.forName("Sites."+requestedSite).newInstance();
			s1.parse(new URL(requestedUrl), req.getISIN(), req.getType());

		}
		else{//isin non presente in isin_site
			System.out.println("Nessun risultato trovato.. devo cercare nell'altra tabella");
			//ricerca in tabella hits_site
			Vector v2 = db.execQuery("SELECT site,url FROM tbl_hits_site WHERE Type=\""+req.getType()+"\"ORDER BY Percentage DESC;" );
			int i = 0;
			//stampa risultati(scopo illustartivo)
			while ( i<v2.size() ) {
			   String[] record = (String[]) v2.elementAt(i);
			   System.out.println("Record numero " + (i+1) );
			   for (int j=0; j<record.length; j++) {
			      System.out.println( record[j] );
			   }
			i++;
			}
			//scorro i risultati (ordinati per Percentage nella query) 
			//lanciando i relativi parser finchè non trovo la quotazione
			int k = 0;
			found = false;
			while ( k < v2.size() && found == false ) {
				String[] record2 = (String[]) v2.elementAt(k);
				requestedSite = record2[0];
				requestedUrl = record2[1];
				requestedUrl = requestedUrl.replace("__ISIN__HERE__", req.getISIN());
				System.out.println("istanzio una classe "+requestedSite+" ..."+" e lancio il parser per il sito "+requestedUrl);
				SiteInterface s2 = (SiteInterface)Class.forName("Sites."+requestedSite).newInstance();
				Quotation quot = s2.parse(new URL(requestedUrl), req.getISIN(), req.getType());
				if (null != quot){
					//aggiorno hits=hits+1, total=total+1, Percentage = hits+1/total+1 NON TROPPO ELEGANTE E SOPRATTUTTO PULITO..! 
					db.updateQuery("UPDATE tbl_hits_site SET Hits = Hits + 1, Total = Total + 1 , Percentage = (Hits+1)/(Total+1) WHERE Type = \""+req.getType()+"\" AND Site = \""+requestedSite+"\";");
					//inserisco isin-sito-url nella tabella isin_sito
					db.insertQuery("INSERT INTO tbl_isin_site (`ISIN`, `Site`, `URL`) VALUES ('"+req.getISIN()+"', '"+requestedSite+"', '"+requestedUrl+"');");
					found = true;//elemento trovato
				}else{//parser ritorna null
					//total=total+1
					db.updateQuery("UPDATE tbl_hits_site SET Total = Total + 1 , Percentage = (Hits+1)/(Total+1) WHERE Type = \""+req.getType()+"\" AND Site = \""+requestedSite+"\";");
					System.out.println("non trovato.. passo al prossimo provider");
				}
			k++;
			}//end while
				
			
		}
		

		
		/*
		int i = 0;
		while ( i<v.size() ) {
		   String[] record = (String[]) v.elementAt(i);
		   System.out.println("Record numero " + (i+1) );
		   for (int j=0; j<record.length; j++) {
		      System.out.println( record[j] );
		   }
		i++;
		}
		*/
	
		db.disconnect();
	}

}
