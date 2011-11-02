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
import Quotes.Quotation_Bond;
import Quotes.Quotation_Fund;
import Quotes.Quotation_Share;
import Utils.UtilFuncs;


public class Borse_it implements SiteInterface {

	public Quotation_Bond parseBTP(URL url)
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
			
			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;
			
			Quotation_Bond qb = new Quotation_Bond();
			
//			qb.setName(UtilFuncs.getString(nodes, 1));		//Nome			
			qb.setISIN(UtilFuncs.getString(nodes, 1));		//ISIN
//			qb.setValuta(UtilFuncs.getString(nodes, 5));		//Valuta
//			qb.setMercato(UtilFuncs.getString(nodes, 7));	//Mercato
//			qb.setFaseMercato(UtilFuncs.getString(nodes, 11));//Fase Mercato
//mah?		qb.setPrezzoUltimoContratto(UtilFuncs.getString(nodes, 17));	//Ultimo Prezzo
			qb.setVariazionePercentuale(UtilFuncs.getString(nodes, 25));	//Var %
			qb.setVariazioneAssoluta(UtilFuncs.getString(nodes, 3));	//Var Ass
			qb.setDataUltimoContratto(UtilFuncs.getString(nodes, 39));
//			qb.setVolumeUltimo(nodes.item(21).getFirstChild().getNodeValue());
			qb.setVolumeAcquisto(UtilFuncs.getString(nodes, 27));
			qb.setPrezzoAcquisto(UtilFuncs.getString(nodes, 5));
			qb.setPrezzoVendita(UtilFuncs.getString(nodes, 7));
			qb.setVolumeVendita(UtilFuncs.getString(nodes, 29));
			qb.setVolumeTotale(UtilFuncs.getString(nodes, 31));
//			qb.setMaxAnno(nodes.item(39).getFirstChild().getNodeValue());
			qb.setMaxOggi(UtilFuncs.getString(nodes, 15));
			qb.setMinOggi(UtilFuncs.getString(nodes, 13));
//			qb.setMinAnno(nodes.item(45).getFirstChild().getNodeValue());
//			qb.setDataMinAnno(nodes.item(47).getFirstChild().getNodeValue());
//			qb.setDataMaxAnno(nodes.item(41).getFirstChild().getNodeValue());
//			qb.setCedola(nodes.item(61).getFirstChild().getNodeValue());
//			qb.setLottoMinimo(nodes.item(59).getFirstChild().getNodeValue());
//			qb.setDataStaccoCedola(nodes.item(63).getFirstChild().getNodeValue());
			qb.setAperturaChiusuraPrecedente(UtilFuncs.getString(nodes, 19));
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
	public Quotation_Bond parseBOT(URL url)
	{
		return parseBTP(url);
	}
	public Quotation_Bond parseCCT(URL url)
	{
		return parseBTP(url);
	}
	public Quotation_Bond parseCTZ(URL url)
	{
		return parseBTP(url);
	}
	public Quotation_Bond parseBOND(URL url)
	{
		return parseBTP(url);
	}
	public Quotation_Share parseSHARE(URL url)
	{
		return null;
	}
	public Quotation_Fund parseFUND(URL url)
	{
		return null;
	}
}

