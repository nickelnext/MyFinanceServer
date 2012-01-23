package mainpackage;

import java.util.ArrayList;

import com.google.gson.Gson;

import Quotes.HistoricalData;

public class HistoryHandler {

	public static String getHistory(String ISIN){
		
		ArrayList<HistoricalData> historyList = new ArrayList<HistoricalData>();
		Gson converter = new Gson();
		//TODO invocazione del parse che piglia tutti i dati giornalieri dell'ultimo mese 
		// ritorna l'arraylist da convertire
		//historyList = RobiGaio.parseHistory(ISIN);
		
		String result = converter.toJson(historyList);
		return result;
		
	}
	
}
