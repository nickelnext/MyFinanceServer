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

public class Borsaitaliana_it_Search extends Search {

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
			String pattern = "//table[@class='table_dati']//td//a/@href";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return false;

			
			this.setCompleteLink("http://www.borsaitaliana.it" + nodes.item(0).getNodeValue());
			this.setCompleteLink(this.getCompleteLink().replace("scheda", "dati-completi"));
			
			
			
			if(this.getCompleteLink().contains("ctz"))
				this.setType(QuotationType.BOND); //this.setType(QuotationType.CTZ);
			else
				if(this.getCompleteLink().contains("cct"))
					this.setType(QuotationType.BOND); //this.setType(QuotationType.CCT);
				else
					if(this.getCompleteLink().contains("bot"))
						this.setType(QuotationType.BOND); //this.setType(QuotationType.BOT);
					else
						if(this.getCompleteLink().contains("btp"))
							this.setType(QuotationType.BOND); //this.setType(QuotationType.BTP);
						else
							if(this.getCompleteLink().contains("extramot"))
								this.setType(QuotationType.BOND);
							else
								if(this.getCompleteLink().contains("obbligazioni"))
									this.setType(QuotationType.BOND);
								else
									if(this.getCompleteLink().contains("/azioni/"))
										this.setType(QuotationType.SHARE);
									else
										if(this.getCompleteLink().contains("fondi"))
											this.setType(QuotationType.FUND);
										else
											this.setType(null);
			
			
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
