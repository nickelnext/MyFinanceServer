package Search;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

import Quotes.Type;
import Utils.UtilFuncs;

public class Yahoo_Finanza_it_Search extends Search {

	

	
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
			String pattern = "//table[@class='yui-dt' and @summary='YFT_SL_TABLE_SUMMARY']//td//a/text() | " +
					"//tr[@class='yui-dt-odd']/td/text()";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return false;
			
			String s = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20%28%22" + Utils.UtilFuncs.ISIN_REPLACE + "%22%29&env=store://datatables.org/alltableswithkeys";
			this.setCompleteLink(s.replace(UtilFuncs.ISIN_REPLACE, nodes.item(0).getNodeValue()));

			
			switch (nodes.item(4).getNodeValue()) { //type
			case "Obbligazioni":
				this.setType(Type.BOND);
				break;
			case "Azione":
				this.setType(Type.SHARE);
				break;
			default:
				this.setType(null);
				break;
			}
			
			return true;
		}
		catch (IOException e) {
			System.out.println("ISIN NON TROVATO");	
		} 
		catch (XPathExpressionException e) {
		}
		return false;
	}
}
