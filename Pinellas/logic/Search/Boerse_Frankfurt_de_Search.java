package Search;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import mainpackage.ErrorHandler;
import mainpackage.Errors;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

import Quotes.QuotationType;
import Utils.UtilFuncs;

public class Boerse_Frankfurt_de_Search extends Search {

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
			String pattern = "//h4/text()";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

//			System.out.println("nodes.getlength "  +nodes.getLength());
			
			
			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return false;
			
			String s = nodes.item(0).getNodeValue().split(",")[0];
//			System.out.println(s);

			this.setCompleteLink(searchUrl);
			switch (s) {
			case "Bond":
				this.setType(QuotationType.BOND);
				break;
			case "Funds":
				this.setType(QuotationType.FUND);
				break;
			case "Equity":
				this.setType(QuotationType.SHARE);
				break;
			default:
				this.setType(null);
				return false;
			}
			return true;



		}
		catch (IOException e) {
			System.out.println("ISIN NON TROVATO");
			//TODO
		} 
		catch (XPathExpressionException e) {
			ErrorHandler.setError(Errors.ERROR_DATABASE_CONNECTION);
			e.printStackTrace();
			//TODO
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			//TODO
		}
		return false;
	}
}
