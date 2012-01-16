package mainpackage;


import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import Handlers.SiteInterface;
import Quotes.Quotation;
import Quotes.QuotationContainer;
import Quotes.QuotationType;
import Quotes.Quotation_Bond;
import Quotes.Quotation_Fund;
import Quotes.Quotation_Invalid;
import Quotes.Quotation_Share;
import Requests.Request;
import Search.Search;

import com.github.neilprosser.cjson.CJSON;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

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

		//		tmpList.add(new Request("FNC"));
//		tmpList.add(new Request("IT0004572910",  QuotationType.BOND, null, null));
//		tmpList.add(new Request("IT0004719297", QuotationType.BOND, null, null));
		//		tmpList.add(new Request("IT0004220627"));
		//		tmpList.add(new Request("IT0003926547"));
		//		tmpList.add(new Request("IT0001233417"));
		//		tmpList.add(new Request("LU0336083497"));
		//		tmpList.add(new Request("US38259P5089", QuotationType.SHARE, "Yahoo_Finanza_it"));
		//		tmpList.add(new Request("IT0003406334", QuotationType.FUND, "Yahoo_Finanza_it"));
		//		tmpList.add(new Request("IT0004168826"));
		//		tmpList.add(new Request("IT0000382983"));






		Gson giasone0 = new Gson();
		String jason0 = giasone0.toJson(tmpList);
		System.out.println(jason0);



		String cJsonResp = doStuff(jason0);
		System.out.println(cJsonResp);
		String jsonResp = CJSON.unpack(cJsonResp);
		System.out.println(jsonResp);
		QuotationContainer pannula = decodeQuotations(CJSON.unpack(jsonResp));




		for (Quotation_Bond qb : pannula.getBondList()) {
			System.out.println("-->BOND LIST:");
			System.out.println(qb.toString());
		}
		for (Quotation_Fund qf : pannula.getFundList()) {
			System.out.println("-->FUND LIST:");
			System.out.println(qf.toString());
		}
		for (Quotation_Share qs : pannula.getShareList()) {
			System.out.println("-->SHARE LIST:");
			System.out.println(qs.toString());
		}

		if(pannula.getBondList().size()==0){
			System.out.println("BOND NULLO");
		}
		if(pannula.getShareList().size()==0){
			System.out.println("SHARE NULLO");
		}
		if(pannula.getFundList().size()==0){
			System.out.println("FUND NULLO");
		}


	}		


	// decode the compressed json string, convert it to Arraylist<Request> and return it	
	/*	public static ArrayList<Request> decodeRequests(String cjson){
		//TODO modificare a seconda di come decideremo di comprimere/convertire le  richieste

		ArrayList<Request> res;
		Gson converter = new Gson();

		String json = CJSON.unpack(cjson);
//		System.out.println(jason);
		Type typeOfT = new TypeToken<ArrayList<Request>>(){}.getType();
		res = converter.fromJson(json, typeOfT);

		return res;

	}
	 */


	public static ArrayList<Request> decodeRequests(String json) throws JsonSyntaxException{
		//TODO modificare a seconda di come decideremo di comprimere/convertire le  richieste	
		ArrayList<Request> res;
		Gson converter = new Gson();	
		Type typeOfT = new TypeToken<ArrayList<Request>>(){}.getType();
		res = converter.fromJson(json, typeOfT);
		return res;
	}

	public static QuotationContainer decodeQuotations(String json) throws JsonSyntaxException{
		//TODO modificare a seconda di come decideremo di comprimere/convertire le  richieste
		QuotationContainer res;
		Gson converter = new Gson();	
		Type typeOfT = new TypeToken<QuotationContainer>(){}.getType();
		res = converter.fromJson(json, typeOfT);
		return res;
	}


	public static ArrayList<Quotation> decodeQuotations2(String json) throws JsonSyntaxException{
		//TODO modificare a seconda di come decideremo di comprimere/convertire le  richieste
		ArrayList<Quotation> res;
		Gson converter = new Gson();	
		Type typeOfT = new TypeToken<ArrayList<Quotation>>(){}.getType();
		
		res = converter.fromJson(json, typeOfT);
		
		return res;
	}




	public static String doStuff(String jasonReq) throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException, JsonSyntaxException{
		String jasonResp; 
//		String cJasonResp;
		QuotationContainer quot;
		quot = processRequests(decodeRequests(jasonReq));
		Gson converter = new Gson();
		//convert to json string
		jasonResp = converter.toJson(quot);
		//compress with cjson
		//		cJasonResp = CJSON.pack(jasonResp);

		return jasonResp;
	}


	public static QuotationContainer processRequests(ArrayList<Request> arrReq) throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException{

		//		System.out.println("PROCESS REQUESTS!");
		QuotationContainer result = new QuotationContainer();

		MyDatabase db = new MyDatabase("pinella", "root", "myfinance");
		// connection with database

		long startTimeDB = System.currentTimeMillis();
		if ( !db.connect() ) { 
			ErrorHandler.setError(Errors.ERROR_DATABASE_CONNECTION);
			System.exit(0);
		}
		long endTimeDB = System.currentTimeMillis();
		long secondsDB = (endTimeDB - startTimeDB);
		System.out.println("Connessione DB: " + secondsDB + " millisecondi");
		RequestPolicies rp = new RequestPolicies();

		//extract 
		//		rp.setSiteSearch(db.execQuery("SELECT DISTINCT Name,SearchPath FROM tbl_name_search_rate;"));
		//		rp.setSiteNameTable(rp.getSiteSearch(db));

		if(0 == rp.getSiteSearch(db).size()){
			System.out.println("EMPTY NAME_SEARCH TABLE");
		}

		Search idFinder = null;

		//FOR ALL THE REQUESTS IN THE LIST
		for (int countRequests = 0; countRequests < arrReq.size(); countRequests++){							

			String requestedSite = null; //name of the current provider
			String searchLink = null; //search url for the current provider
			String completeLink = null; //url of the web page containing all the details for the requested quotation 
			boolean found;
			boolean noMoreSites;
			boolean valid;
			boolean firstAttempt = true;
			boolean isPreferred;
			boolean isIgnored;
			Quotation quot = null; //object representing the response to the current request
			String[] nameSearchPath = new String[2];

			Request req = arrReq.get(countRequests);

			//declaration of indexes for the various type of requests
			int randomIdx = (int)(Math.random() * rp.getSiteSearch(db).size()); //for random extraction of the provider 
			int quotIdx = 0;
			int updateIdx = 0;
			//			int forcedIdx = 0;
			int threshold;
			found = false;
			noMoreSites = false;


			//iteration to get quotation details based on the type of request [continue until the information is found or there are no more providers(failure)]
			while(!found && !noMoreSites){

				//VALUTA IL TIPO DI RICHIESTA!
				//SE requestQUOTATION base: ricerca random;
				//se requestUpdate senza preferred: cerca migliore per tipo;
				//se requestUpdate con preferred: cerca su preferred, e se c'è qualche problema cerca su migliori per tipo;

				//switchCase to choose the provider based on the RequestType 
				switch (req.getReqType()) { 

				case QUOTATION:		//NO INFO ABOUT QUOTATION TYPE: search quotation in a random selected provider  
					System.out.println("------------->>QUOTATION");
					//let's pick a random element from Sitesearch vector
					nameSearchPath = (String[]) rp.getSiteSearch(db).elementAt(randomIdx);	
					randomIdx = (randomIdx+1) % rp.getSiteSearch(db).size();
					if(quotIdx+1 >= rp.getSiteSearch(db).size()){
						noMoreSites = true;
					}
					quotIdx++;
					break;//end case QUOTATION


				case UPDATE:	//QUOTATION TYPE KNOWN, POSSIBLY PREFERRED SITE AND IGNORED SITES
					//select the provider based on the quotation type [precedence precedence to the preferred site]
					System.out.println("------------->>UPDATE");
					//verify whether or not preferredSite is specified 
					if(firstAttempt && req.getPreferredSite() != null){
						//get search url from HT siteNameTable
						nameSearchPath[0] = req.getPreferredSite();
						nameSearchPath[1] = rp.getSiteNameTable().get(req.getPreferredSite());
						firstAttempt = false;
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
							if((updateIdx + 1) >= threshold){//verify whether all the providers have been "inspected"
								noMoreSites = true;
							}
						
							if(req.getPreferredSite() != null){
								isPreferred = nameSearchPath[0].equals(req.getPreferredSite());
							}else{
								isPreferred = false;
							}
							
							if(req.getIgnoredSites() != null){
								isIgnored = req.getIgnoredSites().contains(nameSearchPath[0]);
							}else{
								isIgnored = false;
							}

							// if the selected provider is neither in the ignoredSites list, nor the preferredSite (already considered), it is valid.
							if(!isIgnored && !isPreferred){ 
								valid = true;
							}
							updateIdx++;
						}//end while(!valid && !noMoreSites)

					}	
					break;//end case UPDATE

					//
					//				case FORCED:	//TYPE IS KNOWN.. PROVIDERS IN ignoredSites MUST BE IGNORED
					//					System.out.println("------------->>FORCED");
					//					//int forcedIdx = 0;
					//					valid = false;
					//					//to be valid, the provider must be not included in ignoredSites list
					//					while(!valid && !noMoreSites){
					//						switch (req.getQuotType()) {
					//						case FUND:
					//							nameSearchPath = (String[]) rp.getFundList(db).elementAt(forcedIdx);
					//							threshold = rp.getFundList(db).size();
					//							break;
					//						case BOND:
					//							nameSearchPath = (String[]) rp.getBondList(db).elementAt(forcedIdx);
					//							threshold = rp.getBondList(db).size();
					//							break;
					//						case SHARE:
					//							nameSearchPath = (String[]) rp.getShareList(db).elementAt(forcedIdx);
					//							threshold = rp.getShareList(db).size();
					//							break;
					//						default:
					//							threshold = 0;
					//							break;
					//						}
					//						if(!req.getIgnoredSites().contains(nameSearchPath[0])){// if the selected provider is not in the ignoredSite list, it is valid. 
					//							valid = true;
					//						}
					//						if(forcedIdx + 1 >= threshold){
					//							noMoreSites = true;
					//						}
					//						forcedIdx++;
					//					}
					//
					//					break;//end case FORCED

				default:
					noMoreSites = true;
					System.out.println("Invalid Request type");
					ErrorHandler.setError(Errors.ERROR_INVALID_REQUEST_TYPE);
					break;
				}//end switch req.getReqType() 
				System.out.println(req.getIdCode());

				requestedSite = nameSearchPath[0];
				searchLink = nameSearchPath[1];

				if(null == requestedSite || null == searchLink){
									System.out.println("ERROR!! NULL VARIABLES!! \n requestedSite="+requestedSite+"\t searchLink="+searchLink);
				}else{



					//instantiate the requestedSite_Search parser to search for idCode in searchLink url
					System.out.println("instantiating "+requestedSite+"_Search class..."+" parsing "+searchLink);				
					long startTime = System.currentTimeMillis();
					idFinder = (Search) Class.forName("Search."+requestedSite+"_Search").newInstance();			


					//launch idFinder and immediately verify the boolean return value
					if(!idFinder.search(req.getIdCode(), searchLink)){

						//						System.out.println("IdCode "+req.getIdCode()+" not found.. let's try with the next provider");
//						ErrorHandler.setError(Errors.ERROR_ISIN_LOCALLY_NOT_FOUND,requestedSite+" "+req.getIdCode());
						found = false;
						long endTime = System.currentTimeMillis();
						long seconds = (endTime - startTime);
						System.out.println("Search totale: " + seconds + " millisecondi");
					}else{

						System.out.println("IdCode "+req.getIdCode()+" found (type = "+idFinder.getType()+")");
						found = true;

						completeLink = idFinder.getCompleteLink();
						long endTime = System.currentTimeMillis();
						long seconds = (endTime - startTime);
						System.out.println("Search totale: " + seconds + " millisecondi");

						//Instantiate the second parser to find details of the quotation
						long startTime1 = System.currentTimeMillis();

						SiteInterface detailsParser = null;
						detailsParser = (SiteInterface)Class.forName("Sites."+requestedSite).newInstance();
						

						//launch the right parser based on the quotation type
						if(null != idFinder.getType()){					
							switch (idFinder.getType()) {
							/*							case BTP:
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
							 */							
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
							switch (quot.getType()) {
							case FUND:
								result.getFundList().add((Quotation_Fund)quot);
								break;
							case BOND:
								result.getBondList().add((Quotation_Bond)quot);
								break;
							case SHARE:
								result.getShareList().add((Quotation_Share)quot);
								break;															
							}
							System.out.println("BELLA Lì for"+quot.getISIN());
							//aggiornamento ranking se è quotation Request					
						}else {//negative outcome: reset found to false in order to force the parsing of the next provider
							//							System.out.println("Parsing Error! null Quotation!");							
//							ErrorHandler.setError(Errors.ERROR_ISIN_LOCALLY_NOT_FOUND,requestedSite+" "+req.getIdCode());
							found = false; 						
						}
						long endTime1 = System.currentTimeMillis();
						long seconds1 = (endTime1 - startTime1);
						System.out.println("Parse totale: " + seconds1 + " millisecondi");
						long startTime2 = System.currentTimeMillis();
						rp.updateRankingTables(found, req.getReqType(), idFinder.getType(), requestedSite, db);
						long endTime2 = System.currentTimeMillis();
						long seconds2 = (endTime2 - startTime2);
						System.out.println("Update Ranking: " + seconds2 + " millisecondi");
					}

				}
			}//end while(!found && !noMoreSites)
			if(!found){
				//do something DEFINIRE QUOTATION INVALIDA
				ErrorHandler.setError(Errors.ERROR_ISIN_GLOBALLY_NOT_FOUND, req.getIdCode());
				Quotation invQuot = new Quotation_Invalid();
				invQuot.setISIN(req.getIdCode());
				result.getInvalidList().add((Quotation_Invalid)invQuot);
			}
			System.out.println("\n\n");
		}//end FOR ALL requests			

		db.disconnect();

		//		return quotList;
		result.setComments(ErrorHandler.getAllErrors());
		return result;


	}//end processRequest

}
