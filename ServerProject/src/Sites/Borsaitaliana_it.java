package Sites;

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

import Handlers.SiteInterface;


public class Borsaitaliana_it implements SiteInterface {

	public void parseBTP(URL url)
	{
		try 
		{
			BufferedInputStream buffInput = new BufferedInputStream(url.openStream());

			Tidy tidy = new Tidy();
			tidy.setQuiet(true);
			tidy.setShowWarnings(false);
			tidy.setFixBackslash(true);
			Document response = tidy.parseDOM(buffInput, null);

			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath=factory.newXPath();
			String pattern = "//table[@class='table_dati' and not(@summary) and not(@cellpadding='0')]/tbody/tr//td";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			for (int i = 0; i < nodes.getLength();i++) 
			{
				if(nodes.item(i).hasChildNodes() && nodes.item(i).getChildNodes().item(0).getNodeValue() != "")
				{
					System.out.println("" + i + (String) nodes.item(i).getChildNodes().item(0).getNodeValue());
					//					results.add((String) nodes.item(i).getChildNodes().item(0).getNodeValue());
				}
			}

		}
		catch (IOException e) {
			System.out.println("ISIN NON TROVATO, passo al prossimo");	
		} 
		catch (XPathExpressionException e) {
		}
	}
	public void parseBOT(URL url)
	{
		parseBTP(url);
	}
	public void parseCCT(URL url)
	{
		parseBTP(url);
	}
	public void parseCTZ(URL url)
	{
		parseBTP(url);
	}
	public void parseBOND(URL url)
	{
		parseBTP(url);
	}
	public void parseSHARE(URL url)
	{
		
	}
	public void parseFUND(URL url)
	{

	}



}

