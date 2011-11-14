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
		catch (IOException | XPathExpressionException e) {
			System.out.println("ISIN NON TROVATO");	
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
		tidy.setShowErrors(0);
		Document response = tidy.parseDOM(buffInput, null);

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath=factory.newXPath();
		String pattern = "//div[@class='body']//table//td//*/text() | //div[@class='body']//h1/text() | //div[@class='Text_Small_LightGray timeStamp']/*/text()";
		
		NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

		
		if(nodes.getLength()==0)		//No nodes, probably a 404 error
			return null;
		
		Quotation_Share qs = new Quotation_Share();
		
		qs.setName(nodes.item(0).getNodeValue());		//Nome		
//		qs.setISIN(UtilFuncs.getString(nodes, 1));		//ISIN
//		qs.setLottoMinimo(UtilFuncs.getString(nodes, 13));
//		qs.setFaseMercato(UtilFuncs.getString(nodes, 15));	
		qs.setPrezzoUltimoContratto(nodes.item(2).getNodeValue());
		qs.setVariazionePercentuale(nodes.item(5).getNodeValue());
		qs.setVariazioneAssoluta(nodes.item(3).getNodeValue());
		qs.setDataOraUltimoAcquisto(nodes.item(12).getNodeValue());
//		qs.setPrezzoAcquisto(UtilFuncs.getString(nodes, 31));
//		qs.setPrezzoVendita(UtilFuncs.getString(nodes, 33));
//		qs.setQuantitaUltimo(UtilFuncs.getString(nodes, 27));
//		qs.setQuantitaAcquisto(UtilFuncs.getString(nodes, 29));
//		qs.setQuantitaVendita(UtilFuncs.getString(nodes, 35));
//		qs.setQuantitaTotale(UtilFuncs.getString(nodes, 37));
//		qs.setMaxOggi(UtilFuncs.getString(nodes, 43));
//		qs.setMinOggi(UtilFuncs.getString(nodes, 47));
		qs.setMaxAnno(nodes.item(10).getNodeValue());
		qs.setMinAnno(nodes.item(7).getNodeValue());
		qs.setDataMaxAnno(nodes.item(11).getNodeValue());
		qs.setDataMinAnno(nodes.item(8).getNodeValue());
//		qs.setChiusuraPrecedente(UtilFuncs.getString(nodes, 51));
		
		return qs;	
	}
	catch (IOException e) {
		System.out.println("ISIN NON TROVATO");	
	} 
	catch (XPathExpressionException e) {
		System.out.println("ISIN NON TROVATO");	
	}
	return null;
		
	}
	public Quotation_Fund parseFUND(URL url)
	{
		return null;
	}



}

