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


public class Borsaitaliana_it implements SiteInterface {

	public Quotation parse(URL url, String ISIN, Type type){
		switch (type) {
		case BTP:
			return (Quotation)parseBTP(url, ISIN);
		case BOT:
			return (Quotation)parseBOT(url, ISIN);
		case CCT:
			return (Quotation)parseCCT(url, ISIN);
		case CTZ:
			return (Quotation)parseCTZ(url, ISIN);
		case BOND:
			return (Quotation)parseBOND(url, ISIN);
		case SHARE:
			return (Quotation)parseSHARE(url, ISIN);
		case FUND:
			return (Quotation)parseSHARE(url, ISIN);		
		default:
			return null;
		}
	}
	
	public Quotation_Share parseSHARE(URL url, String ISIN)
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

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;
			
			Quotation_Share qs = new Quotation_Share(ISIN);
			
//			qs.setName(nodes.item(1).getFirstChild().getNodeValue());		//Nome			
			qs.setISIN(nodes.item(1).getFirstChild().getNodeValue());		//ISIN
			qs.setLottoMinimo(nodes.item(13).getFirstChild().getNodeValue());
			qs.setFaseMercato(nodes.item(15).getFirstChild().getNodeValue());	
			qs.setPrezzoUltimoContratto(nodes.item(17).getFirstChild().getNodeValue());
			qs.setVariazionePercentuale(nodes.item(19).getFirstChild().getNodeValue());
			qs.setVariazioneAssoluta(nodes.item(21).getFirstChild().getNodeValue());
			qs.setDataOraUltimoAcquisto(nodes.item(25).getFirstChild().getNodeValue());
			qs.setPrezzoAcquisto(nodes.item(31).getFirstChild().getNodeValue());
			qs.setPrezzoVendita(nodes.item(33).getFirstChild().getNodeValue());
			qs.setQuantitaUltimo(nodes.item(27).getFirstChild().getNodeValue());
			qs.setQuantitaAcquisto(nodes.item(29).getFirstChild().getNodeValue());
			qs.setQuantitaVendita(nodes.item(35).getFirstChild().getNodeValue());
			qs.setQuantitaTotale(nodes.item(37).getFirstChild().getNodeValue());
			qs.setMaxOggi(nodes.item(43).getFirstChild().getNodeValue());
			qs.setMinOggi(nodes.item(47).getFirstChild().getNodeValue());
//			qs.setMaxAnno(maxAnno);
//			qs.setMinAnno(minAnno);
//			qs.setDataMaxAnno(dataMaxAnno);
//			qs.setDataMinAnno(dataMinAnno);
			qs.setChiusuraPrecedente(nodes.item(51).getFirstChild().getNodeValue());
			
			return qs;	
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
			String pattern = "//table[@class='table_dati' and not(@summary) and not(@cellpadding='0')]/tbody/tr//td";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;
			
			Quotation_Bond qb = new Quotation_Bond(ISIN);
			
			qb.setName(nodes.item(1).getFirstChild().getNodeValue());		//Nome			
			qb.setISIN(nodes.item(3).getFirstChild().getNodeValue());		//ISIN
			qb.setValuta(nodes.item(5).getFirstChild().getNodeValue());		//Valuta
			qb.setMercato(nodes.item(7).getFirstChild().getNodeValue());	//Mercato
			qb.setFaseMercato(nodes.item(11).getFirstChild().getNodeValue());//Fase Mercato
			qb.setPrezzoUltimoContratto(nodes.item(13).getFirstChild().getNodeValue());	//Ultimo Prezzo
			qb.setVariazionePercentuale(nodes.item(15).getFirstChild().getNodeValue());	//Var %
			qb.setVariazioneAssoluta(nodes.item(17).getFirstChild().getNodeValue());	//Var Ass
			qb.setDataUltimoContratto(nodes.item(19).getFirstChild().getNodeValue());
			qb.setVolumeUltimo(nodes.item(21).getFirstChild().getNodeValue());
//			qb.setVolumeAcquisto(nodes.item(23).getFirstChild().getNodeValue());
			qb.setPrezzoAcquisto(nodes.item(25).getFirstChild().getNodeValue());
//			qb.setPrezzoVendita(nodes.item(27).getFirstChild().getNodeValue());
//			qb.setVolumeVendita(nodes.item(29).getFirstChild().getNodeValue());
			qb.setVolumeTotale(nodes.item(31).getFirstChild().getNodeValue());
			qb.setMaxAnno(nodes.item(39).getFirstChild().getNodeValue());
			qb.setMaxOggi(nodes.item(37).getFirstChild().getNodeValue());
			qb.setMinOggi(nodes.item(43).getFirstChild().getNodeValue());
			qb.setMinAnno(nodes.item(45).getFirstChild().getNodeValue());
			qb.setDataMinAnno(nodes.item(47).getFirstChild().getNodeValue());
			qb.setDataMaxAnno(nodes.item(41).getFirstChild().getNodeValue());
			qb.setCedola(nodes.item(61).getFirstChild().getNodeValue());
			qb.setLottoMinimo(nodes.item(59).getFirstChild().getNodeValue());
			qb.setDataStaccoCedola(nodes.item(63).getFirstChild().getNodeValue());
			qb.setAperturaChiusuraPrecedente(nodes.item(51).getFirstChild().getNodeValue());
			qb.setScadenza(nodes.item(57).getFirstChild().getNodeValue());
			
			return qb;	
		}
		catch (IOException e) {
			System.out.println("ISIN NON TROVATO");	
		} 
		catch (XPathExpressionException e) {
		}
		return null;
	}
	public void parseFUND(URL url, String ISIN)
	{
		
	}



}

