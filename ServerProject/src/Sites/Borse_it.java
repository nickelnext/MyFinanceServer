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
import Quotes.Quotation;
import Quotes.Quotation_Bond;
import Quotes.Quotation_Share;
import Quotes.Type;


public class Borse_it implements SiteInterface {

	public Quotation_Bond parseBTP(URL url, String ISIN)
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
			String pattern = "//li[../../@class='schede']";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);
			
			Quotation_Bond qb = new Quotation_Bond(ISIN);
			
//			qb.setName(nodes.item(1).getFirstChild().getNodeValue());		//Nome			
			qb.setISIN(nodes.item(1).getFirstChild().getNodeValue());		//ISIN
//			qb.setValuta(nodes.item(5).getFirstChild().getNodeValue());		//Valuta
//			qb.setMercato(nodes.item(7).getFirstChild().getNodeValue());	//Mercato
//			qb.setFaseMercato(nodes.item(11).getFirstChild().getNodeValue());//Fase Mercato
//mah?		qb.setPrezzoUltimoContratto(nodes.item(17).getFirstChild().getNodeValue());	//Ultimo Prezzo
			qb.setVariazionePercentuale(nodes.item(25).getFirstChild().getNodeValue());	//Var %
			qb.setVariazioneAssoluta(nodes.item(3).getFirstChild().getNodeValue());	//Var Ass
			qb.setDataUltimoContratto(nodes.item(39).getFirstChild().getNodeValue());
//			qb.setVolumeUltimo(nodes.item(21).getFirstChild().getNodeValue());
			qb.setVolumeAcquisto(nodes.item(27).getFirstChild().getNodeValue());
			qb.setPrezzoAcquisto(nodes.item(5).getFirstChild().getNodeValue());
			qb.setPrezzoVendita(nodes.item(7).getFirstChild().getNodeValue());
			qb.setVolumeVendita(nodes.item(29).getFirstChild().getNodeValue());
			qb.setVolumeTotale(nodes.item(31).getFirstChild().getNodeValue());
//			qb.setMaxAnno(nodes.item(39).getFirstChild().getNodeValue());
			qb.setMaxOggi(nodes.item(15).getFirstChild().getNodeValue());
			qb.setMinOggi(nodes.item(13).getFirstChild().getNodeValue());
//			qb.setMinAnno(nodes.item(45).getFirstChild().getNodeValue());
//			qb.setDataMinAnno(nodes.item(47).getFirstChild().getNodeValue());
//			qb.setDataMaxAnno(nodes.item(41).getFirstChild().getNodeValue());
//			qb.setCedola(nodes.item(61).getFirstChild().getNodeValue());
//			qb.setLottoMinimo(nodes.item(59).getFirstChild().getNodeValue());
//			qb.setDataStaccoCedola(nodes.item(63).getFirstChild().getNodeValue());
			qb.setAperturaChiusuraPrecedente(nodes.item(19).getFirstChild().getNodeValue());
//			qb.setScadenza(nodes.item(57).getFirstChild().getNodeValue());
					
			return qb;
		}
		catch (IOException e) {
			System.out.println("ISIN NON TROVATO");	
			} 
		catch (XPathExpressionException e) {
			}
		return null;
	}
	public Quotation_Bond parseBOT(URL url, String ISIN)
	{
		return parseBTP(url, ISIN);
	}
	public Quotation_Bond parseCCT(URL url, String ISIN)
	{
		return parseBTP(url, ISIN);
	}
	public Quotation_Bond parseCTZ(URL url, String ISIN)
	{
		return parseBTP(url, ISIN);
	}
	public Quotation_Bond parseBOND(URL url, String ISIN)
	{
		return parseBTP(url, ISIN);
	}
	public Quotation_Share parseSHARE(URL url, String ISIN)
	{
		return null;
	}
	public void parseFUND(URL url, String ISIN)
	{
		
	}
	@Override
	public Quotation parse(URL url, String ISIN, Type type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

