package Sites;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

import Handlers.SiteInterface;
import Quotes.Quotation_Bond;


public class Borsaitaliana_it implements SiteInterface {

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

			
			Quotation_Bond qb = new Quotation_Bond(ISIN);
			
			qb.setName(nodes.item(1).getFirstChild().getNodeValue());		//Nome			
			qb.setISIN(nodes.item(3).getFirstChild().getNodeValue());		//ISIN
			qb.setValuta(nodes.item(5).getFirstChild().getNodeValue());		//Valuta
			qb.setMercato(nodes.item(7).getFirstChild().getNodeValue());	//Mercato
			qb.setFaseMercato(nodes.item(11).getFirstChild().getNodeValue());//Fase Mercato
			qb.setPrezzoUltimoContratto(Float.valueOf(nodes.item(13).getFirstChild().getNodeValue()));	//Ultimo Prezzo
			qb.setVariazionePercentuale(Float.valueOf(nodes.item(15).getFirstChild().getNodeValue()));
			qb.setVariazioneAssoluta(Float.valueOf(nodes.item(17).getFirstChild().getNodeValue()));
			qb.setDataUltimoContratto(Date.valueOf(nodes.item(19).getFirstChild().getNodeValue()));
			qb.setVolumeUltimo(Integer.valueOf(nodes.item(21).getFirstChild().getNodeValue()));
			qb.setVolumeAcquisto(Integer.valueOf(nodes.item(23).getFirstChild().getNodeValue()));
			qb.setPrezzoAcquisto(Float.valueOf(nodes.item(25).getFirstChild().getNodeValue()));
			qb.setPrezzoVendita(Float.valueOf(nodes.item(27).getFirstChild().getNodeValue()));
			qb.setVolumeVendita(Integer.valueOf(nodes.item(29).getFirstChild().getNodeValue()));
			qb.setVolumeTotale(Integer.valueOf(nodes.item(31).getFirstChild().getNodeValue()));
			qb.setMaxAnno(Float.valueOf(nodes.item(39).getFirstChild().getNodeValue()));
			qb.setMaxOggi(Float.valueOf(nodes.item(37).getFirstChild().getNodeValue()));
			qb.setMinOggi(Float.valueOf(nodes.item(43).getFirstChild().getNodeValue()));
			qb.setMinAnno(Float.valueOf(nodes.item(45).getFirstChild().getNodeValue()));
			qb.setDataMinAnno(Date.valueOf(nodes.item(47).getFirstChild().getNodeValue()));
			qb.setDataMaxAnno(Date.valueOf(nodes.item(41).getFirstChild().getNodeValue()));
			qb.setCedola(Float.valueOf(nodes.item(61).getFirstChild().getNodeValue()));
			qb.setLottoMinimo(Integer.valueOf(nodes.item(59).getFirstChild().getNodeValue()));
			qb.setDataStaccoCedola(Date.valueOf(nodes.item(63).getFirstChild().getNodeValue()));
						
			return qb;
			
			
			
			
			
			
		}
		catch (IOException e) {
			System.out.println("ISIN NON TROVATO, passo al prossimo");	
		} 
		catch (XPathExpressionException e) {
		}
		return null;
	}
	public void parseBOT(URL url, String ISIN)
	{
		parseBTP(url, ISIN);
	}
	public void parseCCT(URL url, String ISIN)
	{
		parseBTP(url, ISIN);
	}
	public void parseCTZ(URL url, String ISIN)
	{
		parseBTP(url, ISIN);
	}
	public void parseBOND(URL url, String ISIN)
	{
		parseBTP(url, ISIN);
	}
	public void parseSHARE(URL url, String ISIN)
	{
		
	}
	public void parseFUND(URL url, String ISIN)
	{

	}



}

