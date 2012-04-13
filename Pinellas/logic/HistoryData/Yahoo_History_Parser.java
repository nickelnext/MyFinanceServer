package HistoryData;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

import Quotes.HistoricalData;

public class Yahoo_History_Parser {

	public static ArrayList<HistoricalData> getHistoricalDataList(String url)
	{
		try 
		{
			BufferedInputStream buffInput = new BufferedInputStream(new URL(url).openStream());

			Tidy tidy = new Tidy();
			tidy.setQuiet(true);
			tidy.setShowWarnings(false);
			tidy.setFixBackslash(true);
			tidy.setShowErrors(0);
			Document response = tidy.parseDOM(buffInput, null);

			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath=factory.newXPath();
			String pattern = "//td[@class='yfnc_tabledata1' and not(@align='center') and not(@colspan='7')]/text()";

			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);



			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;


			ArrayList<HistoricalData> arrHistory = new ArrayList<HistoricalData>();

			String date = "";
			float val;

			for(int i=0;i<nodes.getLength();i++)
			{

				if(i%7==0)
				{
					date = parseDate(nodes.item(i).getNodeValue());
//					System.out.println(i+"\t"+ parseDate(nodes.item(i).getNodeValue()));
				}
				if(i%7==6)
				{
					val = Float.parseFloat(nodes.item(i).getNodeValue().replace(",", "."));
//					System.out.println(i+"\t"+nodes.item(i).getNodeValue());
					arrHistory.add(new HistoricalData(date, val));
				}
			}
			System.out.println(arrHistory.size());
			return arrHistory;
			//				System.out.println(i+"\t"+nodes.item(i).getNodeValue());

		}		
		catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
		} catch(StringIndexOutOfBoundsException e)	{
			System.out.println(e.getMessage());
		} 

		return null;
	}

	private static String parseDate(String dateInLetters)
	{
		dateInLetters = dateInLetters.replace(" ", "/");
		String month = "";
		try{

			month = dateInLetters.split("/")[1];
			switch (month) {
			case "gen":
				dateInLetters = dateInLetters.replace(month, "01");
				break;
			case "feb":
				dateInLetters = dateInLetters.replace(month, "02");
				break;
			case "mar":
				dateInLetters = dateInLetters.replace(month, "03");
				break;
			case "apr":
				dateInLetters = dateInLetters.replace(month, "04");
				break;
			case "mag":
				dateInLetters = dateInLetters.replace(month, "05");
				break;
			case "giu":
				dateInLetters = dateInLetters.replace(month, "06");
				break;
			case "lug":
				dateInLetters = dateInLetters.replace(month, "07");
				break;
			case "ago":
				dateInLetters = dateInLetters.replace(month, "08");
				break;
			case "set":
				dateInLetters = dateInLetters.replace(month, "09");
				break;
			case "ott":
				dateInLetters = dateInLetters.replace(month, "10");
				break;
			case "nov":
				dateInLetters = dateInLetters.replace(month, "11");
				break;
			case "dic":
				dateInLetters = dateInLetters.replace(month, "12");
				break;
			default:
				break;
			}
			String days;
			String months;
			
			String [] arr = dateInLetters.split("/");
			days = arr[0];
			months = arr[1];
			dateInLetters = days + "/" + months + "/" + arr[2];
			
		}
		catch (StringIndexOutOfBoundsException e) {
		}
		return dateInLetters;
	}

}
