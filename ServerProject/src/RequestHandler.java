
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import sun.rmi.transport.proxy.CGIHandler;

import com.github.neilprosser.cjson.CJSON;
import com.google.gson.*;

import Handlers.SiteInterface;
import Quotes.Quotation;
import Requests.Request;
import Requests.RequestContainer;
import Requests.RequestQuotation;
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
		tmpList.add(new Request("IT0004572910"));
		tmpList.add(new Request("IT0004220627"));
		tmpList.add(new Request("IT0004719297"));
		tmpList.add(new Request("IT0004224041"));
		tmpList.add(new Request("IT0003926547"));
		tmpList.add(new Request("IT0001233417"));
		tmpList.add(new Request("LU0336083497"));
		
		RequestContainer cont = new RequestContainer();
		cont.setReqList(tmpList);
		ArrayList<Quotation> resList = processRequests(cont.getReqList());
		
		if(null != resList){
			for (int j = 0; j < resList.size(); j++) {	
				//System.out.println(resList.get(j).toString());
			}
		
		Gson giasone = new Gson();
		String jason = giasone.toJson(resList);
		System.out.println(jason);
		System.out.println("---------------COMPRIMIIII>>>>>>>>>");
		String cjason = CJSON.pack(jason);
		System.out.println(cjason);
		System.out.println("<<<<<<<<<<<<<<DECOMPRIMIIIII---------------");
		String jason2 = CJSON.unpack(cjason);
		System.out.println(jason2);
		}
		
	}		
		
	
	public static ArrayList<Quotation> processRequests(ArrayList<Request> arrReq)throws InstantiationException, IllegalAccessException, ClassNotFoundException, MalformedURLException {

		MyDatabase db = new MyDatabase("pinella", "pinella", "pinella87");
		// connection with database
		if ( !db.connect() ) { 
			System.out.println("Database connection error.");
			System.out.println( db.getError() );
			System.exit(0);
		}

		RequestPolicies rp = new RequestPolicies();
		
		//SQL query to extract the list of providers ordered by "their hit rate"
		//Vector rating = db.execQuery("SELECT Name,SearchPath FROM tbl_name_search_rate ORDER BY HitRate DESC;");
		rp.setSiteSearch(db.execQuery("SELECT Name,SearchPath FROM tbl_name_search_rate ORDER BY HitRate DESC;"));
		
		/*
		if(0 == rating.size()){
			System.out.println("TABELLA NAME_SEARCH VUOTA");
		}
		*/
		if(0 == rp.getSiteSearch().size()){
			System.out.println("TABELLA NAME_SEARCH VUOTA");
		}

		
		ArrayList<Quotation> quotList = new ArrayList<Quotation>();
		Search idFinder;
		
		//iterate over the requests
		for (int countRequests = 0; countRequests < arrReq.size(); countRequests++){	

			String requestedSite = null; //name of the current provider
			String searchLink = null; //search url for the current provider
			String completeLink = null; //url of the web page containing all the details for the requested quotation 
			boolean found;
			Quotation quot = null; //object representing the response to the current request
			String[] nameSearchPath;

			Request req = arrReq.get(countRequests);
			
			//VALUTA IL TIPO DI RICHIESTA!
			//SE requestQUOTATION base: ricerca random;
			//se requestUpdate senza preferred: cerca migliore per tipo;
			//se requestUpdate con preferred: cerca su preferred, e se c'è qualche problema cerca su migliori per tipo;
			
			if(req instanceof RequestQuotation){
				int idx = (int)(Math.random() * rp.getSiteSearch().size());
				for (int count = 0; count < rp.getSiteSearch().size(); count++) {


					idx = (idx++) % rp.getSiteSearch().size();
				}


			}
						
			
			found = false;
			
			//iterate over the available providers (sites) 
			for (int countSites = 0; countSites < rp.getSiteSearch().size() && found == false; countSites++) {
				nameSearchPath = (String[]) rp.getSiteSearch().elementAt(countSites);
				requestedSite = nameSearchPath[0];
				searchLink = nameSearchPath[1];
				System.out.println("istanzio una classe "+requestedSite+"_Search ..."+" e lancio il parser per il sito "+searchLink);				
				idFinder = (Search) Class.forName("Search."+requestedSite+"_Search").newInstance();

				if(!idFinder.search(req.getIdCode(), searchLink)){
					System.out.println("ISIN non trovato, provo con il provider successivo");
				}else{					

					System.out.println("ISIN "+req.getIdCode()+" trovato (type = "+idFinder.getType()+")");
					found = true;
					completeLink = idFinder.getCompleteLink();

					SiteInterface detailsParser = (SiteInterface)Class.forName("Sites."+requestedSite).newInstance();

					//launch the right parser based on the quotation type
					switch (idFinder.getType()) {
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
						break;					
					}


					if(null != quot){//positive outcome of parse function: set the remaining fields of the quotation and add to quotList
						quot.setSite(requestedSite);
						quot.setISIN(req.getIdCode());
						quotList.add(quot);
						System.out.println("BELLA Lì per"+quot.getISIN());
					}else {//negative outcome: reset found to false in order to force the parsification of the next provider
						System.out.println("Errore nel parsing! Quotazione nulla!");
						found = false; 
					}

				}

				
			}//end for (iteration over providers)
			if(!found){
				//TODO 
				//settare la Quotation in maniera da definirla "invalida" e aggiungerla alla lista
			}
			
		}//end for (iteration over requests)
			
			
		db.disconnect();
		
		return quotList;


	}//end processRequest

}
