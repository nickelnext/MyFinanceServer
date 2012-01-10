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


public class Borsaitaliana_it implements SiteInterface {

	private final String siteUrl = "http://www.borsaitaliana.it/";
	
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
			String pattern = "//table[@class='table_dati' and not(@summary) and not(@cellpadding='0')]/tbody/tr//td";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;
			
			Quotation_Share qs = new Quotation_Share();
			
			qs.setSite(siteUrl);
			
//			qs.setName(UtilFuncs.getString(nodes, 1));		//Nome			
			qs.setISIN(UtilFuncs.getString(nodes, 1));		//ISIN
			qs.setLottoMinimo(UtilFuncs.getString(nodes, 13));
			qs.setFaseMercato(UtilFuncs.getString(nodes, 15));	
			qs.setPrezzoUltimoContratto(UtilFuncs.getString(nodes, 17));
			qs.setVariazionePercentuale(UtilFuncs.getString(nodes, 19));
			qs.setVariazioneAssoluta(UtilFuncs.getString(nodes, 21));
			qs.setDataOraUltimoAcquisto(UtilFuncs.getString(nodes, 25));
			qs.setPrezzoAcquisto(UtilFuncs.getString(nodes, 31));
			qs.setPrezzoVendita(UtilFuncs.getString(nodes, 33));
			qs.setQuantitaUltimo(UtilFuncs.getString(nodes, 27));
			qs.setQuantitaAcquisto(UtilFuncs.getString(nodes, 29));
			qs.setQuantitaVendita(UtilFuncs.getString(nodes, 35));
			qs.setQuantitaTotale(UtilFuncs.getString(nodes, 37));
			qs.setMaxOggi(UtilFuncs.getString(nodes, 43));
			qs.setMinOggi(UtilFuncs.getString(nodes, 47));
//			qs.setMaxAnno(maxAnno);
//			qs.setMinAnno(minAnno);
//			qs.setDataMaxAnno(dataMaxAnno);
//			qs.setDataMinAnno(dataMinAnno);
			qs.setChiusuraPrecedente(UtilFuncs.getString(nodes, 51));
			
			return qs;	
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		} 
		catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
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
			String pattern = "//table[@class='table_dati' and not(@summary) and not(@cellpadding='0')]/tbody/tr//td";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);
			
			
			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;
			
			
			Quotation_Bond qb = new Quotation_Bond();
			
			qb.setSite(siteUrl);
			qb.setName(UtilFuncs.getString(nodes, 1));		//Nome			
			qb.setISIN(UtilFuncs.getString(nodes, 3));		//ISIN
			qb.setValuta(UtilFuncs.getString(nodes, 5));		//Valuta
			qb.setMercato(UtilFuncs.getString(nodes, 7));	//Mercato
			qb.setFaseMercato(UtilFuncs.getString(nodes, 11));//Fase Mercato
			qb.setPrezzoUltimoContratto(UtilFuncs.getString(nodes, 13));	//Ultimo Prezzo
			qb.setVariazionePercentuale(UtilFuncs.getString(nodes, 15));	//Var %
			qb.setVariazioneAssoluta(UtilFuncs.getString(nodes, 17));	//Var Ass
			qb.setDataUltimoContratto(UtilFuncs.getString(nodes, 19));
			qb.setVolumeUltimo(UtilFuncs.getString(nodes, 21));
			qb.setVolumeAcquisto(UtilFuncs.getString(nodes, 23));
			qb.setPrezzoAcquisto(UtilFuncs.getString(nodes, 25));
			qb.setPrezzoVendita(UtilFuncs.getString(nodes, 27));
			qb.setVolumeVendita(UtilFuncs.getString(nodes, 29));
			qb.setVolumeTotale(UtilFuncs.getString(nodes, 31));
			qb.setMaxAnno(UtilFuncs.getString(nodes, 39));
			qb.setMaxOggi(UtilFuncs.getString(nodes, 37));
			qb.setMinOggi(UtilFuncs.getString(nodes, 43));
			qb.setMinAnno(UtilFuncs.getString(nodes, 45));
			qb.setDataMinAnno(UtilFuncs.getString(nodes, 47));
			qb.setDataMaxAnno(UtilFuncs.getString(nodes, 41));
			qb.setCedola(UtilFuncs.getString(nodes, 61));
			qb.setLottoMinimo(UtilFuncs.getString(nodes, 59));
			qb.setDataStaccoCedola(UtilFuncs.getString(nodes, 63));
			qb.setAperturaChiusuraPrecedente(UtilFuncs.getString(nodes, 51));
			qb.setScadenza(UtilFuncs.getString(nodes, 57));
			
			return qb;	
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		} 
		catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public Quotation_Fund parseFUND(URL url)
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
			String pattern = "//table[@class='table_dati']//td";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);
			
			
			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;
			
			
			Quotation_Fund qf = new Quotation_Fund();
			qf.setSite(siteUrl);
			qf.setName(UtilFuncs.getString(nodes, 1));
			qf.setISIN(UtilFuncs.getString(nodes, 30));
			qf.setBenchmarkDichiarato(UtilFuncs.getString(nodes, 42));
			qf.setCategoriaAssociati(UtilFuncs.getString(nodes, 40));
			qf.setDataUltimoPrezzo(UtilFuncs.getString(nodes, 5));
			qf.setNomeGestore(UtilFuncs.getString(nodes, 46));
//			qf.setPerformance1Anno(performance1Anno)
//			qf.setPerformance1Mese(performance1Mese)
//			qf.setPerformance3Anni(performance3Anni)
//			qf.setPerformance3Mesi(performance3Mesi)
			qf.setPrezzoPrecedente(UtilFuncs.getString(nodes, 3));
//			qf.setSite(site)
			qf.setUltimoPrezzo(UtilFuncs.getString(nodes, 2));
			qf.setValuta(UtilFuncs.getString(nodes, 4));
//			qf.setVariazioneAssoluta(variazioneAssoluta)
			qf.setVariazionePercentuale(UtilFuncs.getString(nodes, 6));
			
			
			
			return qf;	
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		} 
		catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}

