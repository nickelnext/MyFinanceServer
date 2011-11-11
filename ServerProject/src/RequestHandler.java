
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
		if(0 != rating.size()){
			System.out.println("TABELLA NAME_SEARCH VUOTA");
		}
		
		ArrayList<Quotation> quotList = new ArrayList<Quotation>();
		Search isinFinder;
		
		for (int countRequests = 0; countRequests < arrReq.size(); countRequests++){	

			String requestedSite = null; //provider estratto dalla prima query
			String requestedSearchUrl = null; //url di ricerca estratta dalla prima query
			String requestedDetailsUrl = null; //url contenente i dettagli del titolo trovata dalla search
			boolean found;
			Quotation quot = null;
			String[] nameSearchPath;
			String genericUrl = null;
			       
			Request req = arrReq.get(countRequests);
			found = false;
			for (int countSites = 0; countSites < rating.size() && found == false; countSites++) {
				nameSearchPath = (String[]) rating.elementAt(countSites);
				requestedSite = nameSearchPath[0];
				requestedSearchUrl = nameSearchPath[1];
				System.out.println("istanzio una classe "+requestedSite+"Search ..."+" e lancio il parser per il sito "+requestedSearchUrl);				
				isinFinder = (Search) Class.forName("Search."+requestedSite+"_Search").newInstance();
				
				if(isinFinder.search(req.getISIN(), requestedSearchUrl)){
					System.out.println("ISIN trovato, vado a prendermi le info");
					found = true;
					genericUrl = isinFinder.getBaseLink();
					requestedDetailsUrl = isinFinder.getCompleteLink();
				}else{
					System.out.println("ISIN non trovato, provo con il provider successivo");
				}				
			}
			if(found){
				
				System.out.println("SELECT Type FROM tbl_name_type_url WHERE Name="+requestedSite+" AND Url="+genericUrl);
//				Vector typeQuery = db.execQuery("SELECT Type FROM tbl_name_type_url WHERE Name=\""+requestedSite+"\" AND Url=\""+genericUrl+"\"");
				Vector typeQuery = db.execQuery("SELECT Type FROM tbl_name_type_url WHERE Url=\""+genericUrl+"\"");
//				Vector typeQuery = db.execQuery("SELECT Type FROM tbl_name_type_url WHERE Name=\""+requestedSite+"\"");
				if(0 == typeQuery.size()){
					System.out.println("URL NON TROVATA!!");
					return null;
				}
				String[] reqType = (String[])typeQuery.elementAt(0);
				SiteInterface detailsParser = (SiteInterface)Class.forName("Sites."+requestedSite).newInstance();
				Type t = Type.valueOf(reqType[0]);
				
				switch (t) {
				case BTP:
					quot = detailsParser.parseBTP(new URL (requestedDetailsUrl));
					break;
				case BOT:
					quot = detailsParser.parseBOT(new URL (requestedDetailsUrl));
					break;
				case CCT:
					quot = detailsParser.parseCCT(new URL (requestedDetailsUrl));
					break;
				case CTZ:
					quot = detailsParser.parseCTZ(new URL (requestedDetailsUrl));
					break;
				case BOND:
					quot = detailsParser.parseBOND(new URL (requestedDetailsUrl));
					break;
				case SHARE:
					quot = detailsParser.parseSHARE(new URL (requestedDetailsUrl));
					break;
				case FUND:
					quot = detailsParser.parseFUND(new URL (requestedDetailsUrl));
					break;
				default:
					quot = detailsParser.parseBTP(new URL (requestedDetailsUrl));					
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
			
			
/*			
			//eseguo ricerca nella tabella isin_site
			Vector v1 = db.execQuery("SELECT site,url FROM tbl_isin_site WHERE ISIN=\""+req.getISIN()+"\";" );

			if(0 != v1.size()){//isin presente in isin_site 

				System.out.println("isin trovato nella tabella isin-sito!");
				String[] record1 = (String[]) v1.elementAt(0);
				requestedSite = record1[0];
				requestedUrl = record1[1];
				System.out.println("istanzio una classe "+requestedSite+" ..."+" e lancio il parser per il sito "+requestedUrl);
				SiteInterface s1 = (SiteInterface)Class.forName("Sites."+requestedSite).newInstance();

				switch (req.getType()) {
				case BTP:
					quot = s1.parseBTP(new URL (requestedUrl));
					break;
				case BOT:
					quot = s1.parseBOT(new URL (requestedUrl));
					break;
				case CCT:
					quot = s1.parseCCT(new URL (requestedUrl));
					break;
				case CTZ:
					quot = s1.parseCTZ(new URL (requestedUrl));
					break;
				case BOND:
					quot = s1.parseBOND(new URL (requestedUrl));
					break;
				case SHARE:
					quot = s1.parseSHARE(new URL (requestedUrl));
					break;
				case FUND:
					quot = s1.parseFUND(new URL (requestedUrl));
					break;
				default:
					quot = s1.parseBTP(new URL (requestedUrl));					
				}
				//da aggiustare magari con un'eccezione
				//può essere che un giorno un link che funzionava ed era 
				//in memoria smetta di funzionare e il parsing restituisca null
				if(null != quot){
					quot.setSite(requestedSite);
					quot.setISIN(req.getISIN());
					quotList.add(quot);
				}else {
					System.out.println("Errore nel parsing! Quotazione nulla!");				
				}
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

					switch (req.getType()) {
					case BTP:
						quot = s2.parseBTP(new URL (requestedUrl));
						break;
					case BOT:
						quot = s2.parseBOT(new URL (requestedUrl));
						break;
					case CCT:
						quot = s2.parseCCT(new URL (requestedUrl));
						break;
					case CTZ:
						quot = s2.parseCTZ(new URL (requestedUrl));
						break;
					case BOND:
						quot = s2.parseBOND(new URL (requestedUrl));
						break;
					case SHARE:
						quot = s2.parseSHARE(new URL (requestedUrl));
						break;
					case FUND:
						quot = s2.parseFUND(new URL (requestedUrl));
						break;	
					default:
						quot = s2.parseBTP(new URL (requestedUrl));
					}

					if (null != quot){
						//aggiorno hits=hits+1, total=total+1, Percentage = hits+1/total+1 NON TROPPO ELEGANTE E SOPRATTUTTO PULITO..! 
						db.updateQuery("UPDATE tbl_hits_site SET Hits = Hits + 1, Total = Total + 1 , Percentage = (Hits+1)/(Total+1) WHERE Type = \""+req.getType()+"\" AND Site = \""+requestedSite+"\";");
						//inserisco isin-sito-url nella tabella isin_sito
						db.insertQuery("INSERT INTO tbl_isin_site (`ISIN`, `Site`, `URL`) VALUES ('"+req.getISIN()+"', '"+requestedSite+"', '"+requestedUrl+"');");
						found = true;//elemento trovato
						quot.setSite(requestedSite);
						quot.setISIN(req.getISIN());
						quotList.add(quot);
					}else{//parser ritorna null
						//total=total+1
						db.updateQuery("UPDATE tbl_hits_site SET Total = Total + 1 , Percentage = (Hits)/(Total+1) WHERE Type = \""+req.getType()+"\" AND Site = \""+requestedSite+"\";");
						System.out.println("non trovato.. passo al prossimo provider");
					}
					k++;
				}//end while
				//TODO se found è false avvisa ol client che non hai trovato niente...	
				if (!found){
					System.out.println("quotazione non trovata.. controllare correttezza di ISIN e tipo");
				}
			}
	
	}//end while
	*/

		db.disconnect();
		
		return quotList;


	}//end processRequest

}
