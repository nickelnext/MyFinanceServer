package mainpackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.gson.Gson;

import HistoryData.Yahoo_History_Parser;
import Quotes.HistoricalData;

public class HistoryHandler {

	public static String getHistory(String yahoo_code){
		
		ArrayList<HistoricalData> historyList = new ArrayList<HistoricalData>();
		Gson converter = new Gson();
		//TODO invocazione del parse che piglia tutti i dati giornalieri dell'ultimo mese 
		// ritorna l'arraylist da convertire
		
		//calcola la data odierna e calcola la data di un mese  fa.
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String todayString = formatter.format(cal.getTime());
		//il mese scorso
		cal.add(Calendar.MONTH, -1);
		String lastMonthString = formatter.format(cal.getTime());
		System.out.println(todayString);
		System.out.println(lastMonthString);
		
		String [] arr = todayString.split("/");
		String todayDays = arr[0];
		//i mesi vanno da 0 a 11
		String todayMonths = String.valueOf(Integer.parseInt(arr[1])-1);
		String todayYear = arr[2];
		arr = lastMonthString.split("/");
		String oldDays = arr[0];
		//i mesi vanno da 0 a 11
		String oldMonths = String.valueOf(Integer.parseInt(arr[1])-1);;
		String oldYear = arr[2];
		
		String historyUrl = "http://it.finance.yahoo.com/q/hp?s=" + yahoo_code + "&b="+ oldDays + "&a="+ oldMonths + "&c="+ oldYear +
				"&e="+ todayDays + "&d="+ todayMonths + "&f="+ todayYear + "&g=d";
		
		//invoco il parser della pagina
		historyList = Yahoo_History_Parser.getHistoricalDataList(historyUrl);
		//Se il parser ritorna null, faccio qualcosa
		//TODO
		if(historyList==null)
			System.out.println("ESPLOSIONE!!!");
		
		String result = converter.toJson(historyList);
		System.out.println(result);
		return result;
		
	}
	
}
