package mainpackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import HistoryData.Yahoo_History_Parser;
import Quotes.HistoricalData;
import Quotes.HistoryContainer;

import com.google.gson.Gson;

public class HistoryHandler {

	public static String getHistory(String yahoo_code){
		
		HistoryContainer cont = new HistoryContainer();
		ArrayList<HistoricalData> historyListWeek = new ArrayList<HistoricalData>();
		ArrayList<HistoricalData> historyListMonth = new ArrayList<HistoricalData>();
		ArrayList<HistoricalData> historyListYear = new ArrayList<HistoricalData>();
		Gson converter = new Gson();
		//TODO invocazione del parse che piglia tutti i dati giornalieri dell'ultimo mese 
		// ritorna l'arraylist da convertire
		

		if(yahoo_code != null){

			//calcola la data odierna e calcola la data di un mese  fa.
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			String todayString = formatter.format(cal.getTime());
			
			///////////////////////////
			//oggi
			String [] arr = todayString.split("/");
			String todayDays = arr[0];
			String todayMonths = String.valueOf(Integer.parseInt(arr[1])-1);
			String todayYear = arr[2];
			
			System.out.println("today "+ todayString);
			
			////////////////////////////////////////////////////////////////
			//settimana scorsa
			cal.add(Calendar.DATE, -7);
			String lastWeekString = formatter.format(cal.getTime());
			//i mesi vanno da 0 a 11
			arr = lastWeekString.split("/");
			String weekDays = arr[0];
			//i mesi vanno da 0 a 11
			String weekMonth = String.valueOf(Integer.parseInt(arr[1])-1);;
			String weekYear = arr[2];
			System.out.println("last week "+lastWeekString);
			////////////////////////////////////////////////////////////////
			//il mese scorso
			cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			String lastMonthString = formatter.format(cal.getTime());
			//i mesi vanno da 0 a 11
			arr = lastMonthString.split("/");
			String monthDays = arr[0];
			//i mesi vanno da 0 a 11
			String monthMonth = String.valueOf(Integer.parseInt(arr[1])-1);;
			String monthYear = arr[2];
			System.out.println("last month "+lastMonthString);
			////////////////////////////////////////////////////////////////
			//anno scorso
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -1);
			String lastYearString = formatter.format(cal.getTime());
			//i mesi vanno da 0 a 11
			arr = lastYearString.split("/");
			String yearDays = arr[0];
			//i mesi vanno da 0 a 11
			String yearMonth = String.valueOf(Integer.parseInt(arr[1])-1);;
			String yearYear = arr[2];
			System.out.println("last year "+lastYearString);
			
			System.out.println("Comincio a parsareeee");
			
			String historyUrlWeek = "http://it.finance.yahoo.com/q/hp?s=" + yahoo_code + "&b="+ weekDays + "&a="+ weekMonth + "&c="+ weekYear +
					"&e="+ todayDays + "&d="+ todayMonths + "&f="+ todayYear + "&g=d";
			String historyUrlMonth = "http://it.finance.yahoo.com/q/hp?s=" + yahoo_code + "&b="+ monthDays + "&a="+ monthMonth + "&c="+ monthYear +
					"&e="+ todayDays + "&d="+ todayMonths + "&f="+ todayYear + "&g=d";
			String historyUrlYear = "http://it.finance.yahoo.com/q/hp?s=" + yahoo_code + "&b="+ yearDays + "&a="+ yearMonth + "&c="+ yearYear +
					"&e="+ todayDays + "&d="+ todayMonths + "&f="+ todayYear + "&g=m";

			//invoco il parser della pagina
			historyListWeek = Yahoo_History_Parser.getHistoricalDataList(historyUrlWeek);
			historyListMonth = Yahoo_History_Parser.getHistoricalDataList(historyUrlMonth);
			historyListYear = Yahoo_History_Parser.getHistoricalDataList(historyUrlYear);
			
			
			//Se il parser ritorna null, setto errore e assegno cmq la history list al container
			if(historyListMonth==null || historyListWeek==null || historyListYear==null)
				ErrorHandler.setError(Errors.ERROR_NULL_HISTORY_LIST, yahoo_code);			
			
			cont.setHistoryListWeek(historyListWeek);
			cont.setHistoryListMonth(historyListMonth);
			cont.setHistoryListYear(historyListYear);	

		}
		else{// yahoo_code == null
			ErrorHandler.setError(Errors.ERROR_NULL_YAHOO_CODE);
			cont.setHistoryListWeek(null);
			cont.setHistoryListMonth(null);
			cont.setHistoryListYear(null);	
		}			
		
		//salva errori in comments
		cont.setComments(ErrorHandler.getAllErrors());
		
		String result = converter.toJson(cont);
		System.out.println(result);
		ErrorHandler.clearError();
		return result;
		
	}
	
}
