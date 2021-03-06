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

import Quotes.QuotationType;
import Utils.UtilFuncs;

public class Finanza_Repubblica_it_Search extends Search {

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
			String pattern = "//table[@class='TLB-commontb TLB-SearchResults']//td/text() |" +
					"//a[@id='ctl00_ContentPlaceHolder1_gvSearch_ctl02_lnk_Description']/@href";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			if(nodes.getLength()!=0)		//No nodes, probably a 404 error
			{
				this.setCompleteLink("http://finanza.repubblica.it" +nodes.item(0).getNodeValue());
				String s = nodes.item(1).getNodeValue();

				if(s.contains("Tit. di Stato") || s.contains("Obbligazioni"))
					this.setType(QuotationType.BOND);
				else
					if(s.contains("Azioni"))
						this.setType(QuotationType.SHARE);
					else
					{
						System.out.println("No Bond no Share: what is it?");
						//TODO
						return false;
					}
				return true;

			}

			return false;


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
