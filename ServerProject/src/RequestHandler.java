
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import Handlers.SiteInterface;
import Quotes.Quotation;
import Quotes.Type;
import Requests.Request;
import Requests.RequestContainer;
import Search.Search;

public class RequestHandler {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, MalformedURLException {

		//TEST:
		//creo una lista di richieste  e la faccio processare dall'handler
		//poi stampo il contenuto delle quotation ritornate
		ArrayList<Request> tmpList = new ArrayList<Request>();
		tmpList.add(new Request("IT0004572910", Type.BTP));
		//tmpList.add(new Request("IT0004356843", Type.BTP));
		RequestContainer cont = new RequestContainer();
		cont.setReqList(tmpList);
		ArrayList<Quotation> resList = processRequests(cont.getReqList());
		if(null != resList){
			for (int j = 0; j < resList.size(); j++) {	
				System.out.println(resList.get(j).toString());
			}
		}
	}		
		
	
	public static ArrayList<Quotation> processRequests(ArrayList<Request> arrReq)throws InstantiationException, IllegalAccessException, ClassNotFoundException, MalformedURLException {

		MyDatabase db = new MyDatabase("pinella", "pinella", "pinella87");
		// connessione al db
		if ( !db.connect() ) { 
			System.out.println("Errore durante la connessione.");
			System.out.println( db.getError() );
			System.exit(0);
		}

		Vector rating = db.execQuery("SELECT Name,SearchPath FROM tbl_name_search_rate ORDER BY HitRate DESC;");
		if(0 == rating.size()){
			System.out.println("TABELLA NAME_SEARCH VUOTA");
		}
		
		ArrayList<Quotation> quotList = new ArrayList<Quotation>();
		Search isinFinder;
		
		for (int countRequests = 0; countRequests < arrReq.size(); countRequests++){	

			String requestedSite = null; //provider estratto dalla prima query
			String searchLink = null; //url di ricerca estratta dalla prima query
			String completeLink = null; //url contenente i dettagli del titolo trovata dalla search
			boolean found;
			Quotation quot = null;
			String[] nameSearchPath;
			String baseLink = null;

			Request req = arrReq.get(countRequests);
			found = false;
			for (int countSites = 0; countSites < rating.size() && found == false; countSites++) {
				nameSearchPath = (String[]) rating.elementAt(countSites);
				requestedSite = nameSearchPath[0];
				searchLink = nameSearchPath[1];
				System.out.println("istanzio una classe "+requestedSite+"Search ..."+" e lancio il parser per il sito "+searchLink);				
				isinFinder = (Search) Class.forName("Search."+requestedSite+"_Search").newInstance();

				if(isinFinder.search(req.getISIN(), searchLink)){
					System.out.println("ISIN trovato, vado a prendermi le info");
					found = true;
					baseLink = isinFinder.getBaseLink();
					completeLink = isinFinder.getCompleteLink();
				}else{
					System.out.println("ISIN non trovato, provo con il provider successivo");
				}				
			}//end for
			if(found){

		//		System.out.println("SELECT Type FROM tbl_name_type_url WHERE Name="+requestedSite+" AND Url="+baseLink);
				Vector typeQuery = db.execQuery("SELECT Type FROM tbl_name_type_url WHERE Name=\""+requestedSite+"\" AND Url='"+baseLink+"'");
				System.out.println("SELECT Type FROM tbl_name_type_url WHERE `Url` = '"+baseLink+"'");
//				Vector typeQuery = db.execQuery("SELECT Type FROM tbl_name_type_url WHERE `Url` = '"+baseLink+"'");

				if(0 == typeQuery.size()){
					System.out.println("URL NON TROVATA!!");
					return null;
				}
				String[] reqType = (String[])typeQuery.elementAt(0);
				SiteInterface detailsParser = (SiteInterface)Class.forName("Sites."+requestedSite).newInstance();
				Type t = Type.valueOf(reqType[0]);
				System.out.println("type è:"+t);

				switch (t) {
				case BTP:
					quot = detailsParser.parseBTP(new URL (completeLink));
					break;
				case BOT:
					quot = detailsParser.parseBOT(new URL (completeLink));
					break;
				case CCT:
					quot = detailsParser.parseCCT(new URL (completeLink));
					break;
				case CTZ:
					quot = detailsParser.parseCTZ(new URL (completeLink));
					break;
				case BOND:
					quot = detailsParser.parseBOND(new URL (completeLink));
					break;
				case SHARE:
					quot = detailsParser.parseSHARE(new URL (completeLink));
					break;
				case FUND:
					quot = detailsParser.parseFUND(new URL (completeLink));
					break;
				default:
					quot = detailsParser.parseBTP(new URL (completeLink));					
				}
				//da aggiustare magari con un'eccezione
				//può essere che un giorno un link che funzionava ed era 
				//in memoria smetta di funzionare e il parsing restituisca null
			}

			if(null != quot){
				quot.setSite(requestedSite);
				quot.setISIN(req.getISIN());
				quotList.add(quot);
			}else {
				System.out.println("Errore nel parsing! Quotazione nulla!");				
			}
		}
			
			
		db.disconnect();
		
		return quotList;


	}//end processRequest

}
