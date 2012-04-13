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
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String todayString = formatter.format(cal.getTime());
		cal.add(Calendar.MONTH, -3);
		String lastMonthString = formatter.format(cal.getTime());
		System.out.println(todayString);
		System.out.println(lastMonthString);
		
		String [] arr = todayString.split("/");
		String todayDays = arr[0];
		String todayMonths = String.valueOf(Integer.parseInt(arr[1])-1);
		String todayYear = arr[2];
		arr = lastMonthString.split("/");
		String oldDays = arr[0];
		String oldMonths = String.valueOf(Integer.parseInt(arr[1])-1);;
		String oldYear = arr[2];
		
		String historyUrl = "http://it.finance.yahoo.com/q/hp?s=" + yahoo_code + "&b="+ oldDays + "&a="+ oldMonths + "&c="+ oldYear +
				"&e="+ todayDays + "&d="+ todayMonths + "&f="+ todayYear + "&g=d";
		
		
		historyList = Yahoo_History_Parser.getHistoricalDataList(historyUrl);
		if(historyList==null)
			System.out.println("ESPLOSIONE!!!");
		
		String result = converter.toJson(historyList);
		System.out.println(result);
		return result;
		
	}
	
}
