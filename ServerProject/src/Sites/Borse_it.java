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

			//Cedole and name are in another div
			String pattern1 = "//h2[@class='titolo_sx_int']";
			NodeList nodes1 = (NodeList)xPath.evaluate(pattern1, response, XPathConstants.NODESET);

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;

			for (int i = 0; i < nodes.getLength();i++) 
			{
					System.out.println(i + "\t" + (String) nodes.item(i).getChildNodes().item(0).getNodeValue());
			}
			
			
			Quotation_Bond qb = new Quotation_Bond();

			qb.setName(UtilFuncs.getString(nodes1, 0));		//Nome			
//			qb.setISIN(UtilFuncs.getString(nodes, 1));		//ISIN
//			qb.setValuta(UtilFuncs.getString(nodes, 5));		//Valuta
//			qb.setMercato(UtilFuncs.getString(nodes, 7));	//Mercato
			qb.setFaseMercato(UtilFuncs.getString(nodes, 21));//Fase Mercato
			qb.setPrezzoUltimoContratto(UtilFuncs.getString(nodes, 1));	//Ultimo Prezzo
			qb.setVariazionePercentuale(UtilFuncs.getString(nodes, 3));	//Var %
			qb.setVariazioneAssoluta(UtilFuncs.getString(nodes, 29));	//Var Ass
			qb.setDataUltimoContratto(UtilFuncs.getString(nodes, 19));
			//			qb.setVolumeUltimo(nodes.item(21).getFirstChild().getNodeValue());
//			qb.setVolumeAcquisto(UtilFuncs.getString(nodes, 25));
			qb.setPrezzoAcquisto(UtilFuncs.getString(nodes, 5));
			qb.setPrezzoVendita(UtilFuncs.getString(nodes, 7));
//			qb.setVolumeVendita(UtilFuncs.getString(nodes, 27));
			qb.setVolumeTotale(UtilFuncs.getString(nodes, 27));
			//qb.setMaxAnno(nodes.item(39).getFirstChild().getNodeValue());
			qb.setMaxOggi(UtilFuncs.getString(nodes, 13));
			qb.setMinOggi(UtilFuncs.getString(nodes, 11));
			//qb.setMinAnno(nodes.item(45).getFirstChild().getNodeValue());
			//qb.setDataMinAnno(nodes.item(47).getFirstChild().getNodeValue());
			//qb.setDataMaxAnno(nodes.item(41).getFirstChild().getNodeValue());
			//qb.setCedola(nodes.item(61).getFirstChild().getNodeValue());
			//qb.setLottoMinimo(nodes.item(59).getFirstChild().getNodeValue());
			//qb.setDataStaccoCedola(nodes.item(63).getFirstChild().getNodeValue());
			qb.setAperturaChiusuraPrecedente(UtilFuncs.getString(nodes, 9));
			//qb.setScadenza(nodes.item(57).getFirstChild().getNodeValue());




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

			//Cedole and name are in another div
			String pattern1 = "//h2[@class='titolo_sx_int']";
			NodeList nodes1 = (NodeList)xPath.evaluate(pattern1, response, XPathConstants.NODESET);

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;

	
			Quotation_Share qs = new Quotation_Share();

			qs.setName(UtilFuncs.getString(nodes1,0));		//Nome			
//			qs.setISIN(UtilFuncs.getString(nodes));		//ISIN
//			qs.setLottoMinimo(UtilFuncs.getString(nodes));
			qs.setFaseMercato(UtilFuncs.getString(nodes, 23));	
			qs.setPrezzoUltimoContratto(UtilFuncs.getString(nodes, 1));
			qs.setVariazionePercentuale(UtilFuncs.getString(nodes, 3));
//			qs.setVariazioneAssoluta(UtilFuncs.getString(nodes, 21));
			qs.setDataOraUltimoAcquisto(UtilFuncs.getString(nodes, 25));
			qs.setPrezzoAcquisto(UtilFuncs.getString(nodes, 5));
			qs.setPrezzoVendita(UtilFuncs.getString(nodes, 7));
//			qs.setQuantitaUltimo(UtilFuncs.getString(nodes));
			qs.setQuantitaAcquisto(UtilFuncs.getString(nodes, 25));
			qs.setQuantitaVendita(UtilFuncs.getString(nodes,27));
//			qs.setQuantitaTotale(UtilFuncs.getString(nodes, 37));
			qs.setMaxOggi(UtilFuncs.getString(nodes, 15));
			qs.setMinOggi(UtilFuncs.getString(nodes, 13));
//			qs.setMaxAnno(maxAnno);
//			qs.setMinAnno(minAnno);
//			qs.setDataMaxAnno(dataMaxAnno);
//			qs.setDataMinAnno(dataMinAnno);
			qs.setChiusuraPrecedente(UtilFuncs.getString(nodes, 9));



			return qs;
		}
		catch (IOException e) {
			System.out.println("ISIN NON TROVATO");	
		} 
		catch (XPathExpressionException e) {
		}
		return null;		
	}
	public Quotation_Fund parseFUND(URL url)
	{
		return null;
	}
}

