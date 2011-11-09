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

import Utils.UtilFuncs;
import Handlers.SiteInterface;
import Quotes.Quotation_Bond;
import Quotes.Quotation_Fund;
import Quotes.Quotation_Share;


public class Finanza_Repubblica_it implements SiteInterface {

	public Quotation_Bond parseBTP(URL url)
	{
		try 
		{
			BufferedInputStream buffInput = new BufferedInputStream(url.openStream());

			Tidy tidy = new Tidy();
			tidy.setQuiet(true);
			tidy.setShowWarnings(false);
			tidy.setFixBackslash(true);
			tidy.setShowErrors(0);
			Document response = tidy.parseDOM(buffInput, null);

			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath=factory.newXPath();
			String pattern = "//div[@class='TLB-container']//*";
			
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;
			
			Quotation_Bond qb = new Quotation_Bond();
			
			qb.setName(UtilFuncs.getString(nodes, 7));		//Nome			
			qb.setISIN(UtilFuncs.getString(nodes, 55));		//ISIN
//			qb.setValuta(nodes.item(5).getFirstChild().getNodeValue());		//Valuta
//			qb.setMercato(nodes.item(7).getFirstChild().getNodeValue());	//Mercato
			qb.setFaseMercato(UtilFuncs.getString(nodes, 52));//Fase Mercato
			qb.setPrezzoUltimoContratto(UtilFuncs.getString(nodes, 62));	//Ultimo Prezzo
			qb.setVariazionePercentuale(UtilFuncs.getString(nodes, 73));	//Var %
//			qb.setVariazioneAssoluta(nodes.item(17).getFirstChild().getNodeValue());	//Var Ass
			qb.setDataUltimoContratto(UtilFuncs.getString(nodes, 44));
			qb.setVolumeUltimo(UtilFuncs.getString(nodes, 68));
			qb.setVolumeAcquisto(UtilFuncs.getString(nodes, 91));
			qb.setPrezzoAcquisto(UtilFuncs.getString(nodes, 84));
			qb.setPrezzoVendita(UtilFuncs.getString(nodes, 86));
			qb.setVolumeVendita(UtilFuncs.getString(nodes, 93));
			qb.setVolumeTotale(UtilFuncs.getString(nodes, 89));
			qb.setMaxAnno(UtilFuncs.getString(nodes, 40));
			qb.setMaxOggi(UtilFuncs.getString(nodes, 75));
			qb.setMinOggi(UtilFuncs.getString(nodes, 66));
			qb.setMinAnno(UtilFuncs.getString(nodes, 34));
			qb.setDataMinAnno(UtilFuncs.getString(nodes, 35));
			qb.setDataMaxAnno(UtilFuncs.getString(nodes, 41));
//			qb.setCedola(nodes.item(61).getFirstChild().getNodeValue());
//			qb.setLottoMinimo("");
//			qb.setDataStaccoCedola(nodes.item(63).getFirstChild().getNodeValue());
			qb.setAperturaChiusuraPrecedente(UtilFuncs.getString(nodes, 64));
			
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

