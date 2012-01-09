package Search;

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

import Quotes.QuotationType;
import Utils.UtilFuncs;

public class Finanza_Virgilio_it_Search extends Search {

	public boolean search(String ISIN, String searchUrl) {

		searchUrl = searchUrl.replace(UtilFuncs.ISIN_REPLACE, ISIN);

		try 
		{
			BufferedInputStream buffInput = new BufferedInputStream(new URL(searchUrl).openStream());

			Tidy tidy = new Tidy();
			tidy.setQuiet(true);
			tidy.setShowWarnings(false);
			tidy.setFixBackslash(true);
			Document response = tidy.parseDOM(buffInput, null);

			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath=factory.newXPath();
			String pattern = "//div[@class='itemtitoli']//span[@class='nometitolo']/a/@href | " +
					"//div[@class='itemtitoli']//span[@class='borsa']/text()";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);
			//
			ArrayList<String> arr = new ArrayList<String>();
			for(int i=0;i<nodes.getLength();i++)
			{
				if(nodes.item(i).getNodeValue().contains("javascript:void(0)"))
					i = i+1;
				else
					arr.add(nodes.item(i).getNodeValue());
			}

			if(arr.size()<2)		//No nodes, probably a 404 error
				return false;

			this.setCompleteLink(arr.get(0));


			//TODO
			//continuare
//			System.out.println("TYPE=" + nodes.item(2).getNodeValue());
			String type = arr.get(1);
			if(type.contains("Bond") || 
					type.contains("Tit. di Stato") ||
					type.contains("Obbligazioni"))
				this.setType(QuotationType.BOND);
			else
				if(type.contains("Milano - Azioni") ||
						type.contains("Milano - T.A.H.") ||
						type.contains("NASDAQ - National") ||
						type.contains("NASDAQ - Unit Investment Trust"))
					this.setType(QuotationType.SHARE);
				else
					if(type.contains("Fondi e SICAV"))
						this.setType(QuotationType.FUND);
					else
						this.setType(null);
			//
			return true;
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		} 
		catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
