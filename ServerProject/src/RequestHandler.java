
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Vector;

import Handlers.SiteInterface;

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
		if ( !db.connect() ) {
		   System.out.println("Errore durante la connessione.");
		   System.out.println( db.getError() );
		   System.exit(0);
		}
		Request req = new Request("IT0004712748", "BTP");
		String requestedSite;
		String requestedUrl;
		
		Vector v1 = db.execQuery("SELECT site,url FROM tbl_isin_site WHERE ISIN=\""+req.getISIN()+"\";" );
		if(0 != v1.size()){
			
			System.out.println("isin trovato nella tabella isin-sito!");
			String[] record1 = (String[]) v1.elementAt(0);
			requestedSite = record1[0];
			requestedUrl = record1[1];
			System.out.println("istanzio una classe "+requestedSite+" ..."+" e lancio il parser per il sito "+requestedUrl);
			SiteInterface s1 = (SiteInterface)Class.forName("Sites."+requestedSite).newInstance();
			s1.parseBTP(new URL(requestedUrl), requestedSite);

		}
		else{
			System.out.println("Nessun risultato trovato.. devo cercare nell'altra tabella");
			Vector v2 = db.execQuery("SELECT site,url FROM tbl_hits_site WHERE Type=\""+req.getType()+"\"ORDER BY Percentage DESC;" );
			int i = 0;
			while ( i<v2.size() ) {
			   String[] record = (String[]) v2.elementAt(i);
			   System.out.println("Record numero " + (i+1) );
			   for (int j=0; j<record.length; j++) {
			      System.out.println( record[j] );
			   }
			i++;
			}
			String[] record2 = (String[]) v2.elementAt(0);
			requestedSite = record2[0];
			requestedUrl = record2[1];
			requestedUrl = requestedUrl.replace("url=\"", "");
			requestedUrl = requestedUrl.replace("__ISIN__HERE__", req.getISIN());
			System.out.println("istanzio una classe "+requestedSite+" ..."+" e lancio il parser per il sito "+requestedUrl);
			SiteInterface s2 = (SiteInterface)Class.forName("Sites."+requestedSite).newInstance();
			s2.parseBTP(new URL(requestedUrl), requestedSite);
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
