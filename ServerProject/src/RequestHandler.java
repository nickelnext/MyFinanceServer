
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import sun.rmi.transport.proxy.CGIHandler;

import com.github.neilprosser.cjson.CJSON;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import Handlers.SiteInterface;
import Quotes.Quotation;
import Quotes.QuotationType;
import Requests.Request;
import Requests.RequestContainer;
import Requests.RequestForced;
import Requests.RequestQuotation;
import Requests.RequestUpdate;
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
		ArrayList<Request> tmpList2 = new ArrayList<Request>();

		tmpList.add(new Request("IT0004572910"));
		tmpList.add(new Request("IT0004719297", QuotationType.BOND, "__NONE__" ));
		tmpList.add(new Request("IT0004220627", QuotationType.BOND, "Borsaitaliana_it"));
		tmpList.add(new Request("IT0003926547", QuotationType.BOND, "Yahoo_Finanza_it"));
		tmpList.add(new Request("IT0001233417"));
		tmpList.add(new Request("LU0336083497"));
		tmpList.add(new Request("US38259P5089"));
		
		/*
		Gson giasone0 = new Gson();
		String jason0 = giasone0.toJson(tmpList);
		System.out.println(jason0);
		System.out.println("---------------COMPRIMIIII>>>>>>>>>");
		String cjason0 = CJSON.pack(jason0);
		System.out.println(cjason0);
		System.out.println("<<<<<<<<<<<<<<DECOMPRIMIIIII---------------");
		String jason02 = CJSON.unpack(cjason0);
		System.out.println(jason02);
		Type typeOfT = new TypeToken<ArrayList<Request>>(){}.getType();
		tmpList2 = giasone0.fromJson(jason02, typeOfT);
	
		*/
		
		
		RequestContainer cont = new RequestContainer();
		cont.setReqList(tmpList);
		ArrayList<Quotation> resList = processRequests(cont.getReqList());
		
		if(null != resList){
			for (int j = 0; j < resList.size(); j++) {	
				//System.out.println(resList.get(j).toString());
			}
/*		
		Gson giasone = new Gson();
		String jason = giasone.toJson(resList);
		System.out.println(jason);
		System.out.println("---------------COMPRIMIIII>>>>>>>>>");
		String cjason = CJSON.pack(jason);
		System.out.println(cjason);
		System.out.println("<<<<<<<<<<<<<<DECOMPRIMIIIII---------------");
		String jason2 = CJSON.unpack(cjason);
		System.out.println(jason2);
		*/
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
		//rp.setSiteSearch(db.execQuery("SELECT Name,SearchPath FROM tbl_name_search_rate;"));
		rp.setSiteSearch(db.execQuery("SELECT DISTINCT Name,SearchPath FROM tbl_name_search_rate;"));
		rp.setSiteNameTable(rp.getSiteSearch());
		
		ArrayList<String[]> pino = db.execQuery2("SELECT DISTINCT Name,SearchPath FROM tbl_name_search_rate;");
		for (int culo = 0; culo < pino.size(); culo++) {
			System.out.println("PINELLLLLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.out.println(pino.get(culo)[0]+"\t"+pino.get(culo)[1]);
		}
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
			boolean noMoreSites;
			boolean valid;
			Quotation quot = null; //object representing the response to the current request
			String[] nameSearchPath = new String[2];

			Request req = arrReq.get(countRequests);
			
			//dichiara indici (e inizializza)
			int randomIdx = (int)(Math.random() * rp.getSiteSearch().size());
			int quotIdx = 0;
			int updateIdx = 0;
			int forcedIdx = 0;
			int threshold;
			found = false;
			noMoreSites = false;
			
			while(!found && !noMoreSites){
			
			//VALUTA IL TIPO DI RICHIESTA!
			//SE requestQUOTATION base: ricerca random;
			//se requestUpdate senza preferred: cerca migliore per tipo;
			//se requestUpdate con preferred: cerca su preferred, e se c'è qualche problema cerca su migliori per tipo;
			
			//switch per assegnare valori a nameSearchPath[0-1]
				switch (req.getReqType()) { 
				case QUOTATION:
					//ricerca random
					System.out.println("------------->>QUOTATION");
					//let's pick a random element from Sitesearch vector
					nameSearchPath = (String[]) rp.getSiteSearch().elementAt(randomIdx);	
					randomIdx = (randomIdx+1) % rp.getSiteSearch().size();
					if(quotIdx+1 >= rp.getSiteSearch().size()){
						noMoreSites = true;
					}
					quotIdx++;
					break;//end case QUOTATION


				case UPDATE:
					//ricerca per tipo con possibile preferenza
					System.out.println("------------->>UPDATE");
					//int updateIdx = 0;
					if(updateIdx == 0 && req.getPreferredSite() != "__NONE__"){
						//get search url from HT siteNameTable
						nameSearchPath[0] = req.getPreferredSite();
						nameSearchPath[1] = rp.getSiteNameTable().get(req.getPreferredSite());
					}
					else{//no preferred site, or preferred site already parsed with negative result
						//search of providers by type
						valid = false;
						while(!valid && !noMoreSites){
							switch (req.getQuotType()) {
							case FUND:
								nameSearchPath = (String[]) rp.getFundList(db).elementAt(updateIdx);
								threshold = rp.getFundList(db).size();
								break;
							case BOND:
								nameSearchPath = (String[]) rp.getBondList(db).elementAt(updateIdx);
								threshold = rp.getBondList(db).size();
								break;
							case SHARE:
								nameSearchPath = (String[]) rp.getShareList(db).elementAt(updateIdx);
								threshold = rp.getShareList(db).size();
								break;
							default:
								threshold = 0;
								break;
							}
							if((updateIdx + 1) >= threshold){
								noMoreSites = true;
							}
							valid = !nameSearchPath[0].equals(req.getPreferredSite());
							updateIdx++;
						}
						
					}	
					break;//end case UPDATE


				case FORCED:
					System.out.println("------------->>FORCED");
					//int forcedIdx = 0;
					valid = false;
					//to be valid, the provider must be not included in ignoredSites list
					while(!valid && !noMoreSites){
						switch (req.getQuotType()) {
						case FUND:
							nameSearchPath = (String[]) rp.getFundList(db).elementAt(forcedIdx);
							threshold = rp.getFundList(db).size();
							break;
						case BOND:
							nameSearchPath = (String[]) rp.getBondList(db).elementAt(forcedIdx);
							threshold = rp.getBondList(db).size();
							break;
						case SHARE:
							nameSearchPath = (String[]) rp.getShareList(db).elementAt(forcedIdx);
							threshold = rp.getShareList(db).size();
							break;
						default:
							threshold = 0;
							break;
						}
						if(!req.getIgnoredSites().contains(nameSearchPath[0])){
							valid = true;
						}
						if(forcedIdx + 1 >= threshold){
							noMoreSites = true;
						}
						forcedIdx++;
					}

					break;//end case FORCED

				default:
					break;
				}//end switch req.getReqType() 

			requestedSite = nameSearchPath[0];
			searchLink = nameSearchPath[1];
			//instantiate the requestedSite_Search parser to search for idCode in searchLink url
			System.out.println("istanzio una classe "+requestedSite+"_Search ..."+" e lancio il parser per il sito "+searchLink);				
			idFinder = (Search) Class.forName("Search."+requestedSite+"_Search").newInstance();

			//launch idFinder and immediately verify the boolean return value
			if(!idFinder.search(req.getIdCode(), searchLink)){
				System.out.println("IdCode non trovato, provo con il provider successivo");
			}else{

				System.out.println("IdCode "+req.getIdCode()+" trovato (type = "+idFinder.getType()+")");
				found = true;
				
				completeLink = idFinder.getCompleteLink();

				//Instantiate the second parser to find details of the quotation 
				SiteInterface detailsParser = (SiteInterface)Class.forName("Sites."+requestedSite).newInstance();

				//launch the right parser based on the quotation type
				if(null != idFinder.getType()){					
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
				}else {
					quot = null;
				}

				if(null != quot){//positive outcome of parse function: set the remaining fields of the quotation and add to quotList
					quot.setSite(requestedSite);
					quot.setISIN(req.getIdCode());
					quotList.add(quot);
					System.out.println("BELLA Lì per"+quot.getISIN());
				}else {//negative outcome: reset found to false in order to force the parsing of the next provider
					System.out.println("Errore nel parsing! Quotazione nulla!");
					found = false; 
				}

			}

				
			}//end while(!found && !noMoreSites)
			if(!found){
				//do something DEFINIRE QUOTATION INVALIDA
			}
			
		}			
		
			
			
			
		

		/*
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

			*/
			
		db.disconnect();
		
		return quotList;


	}//end processRequest

}
