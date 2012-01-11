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


public class Boerse_Frankfurt_de implements SiteInterface {

	private final String siteUrl = "http://www.http://www.boerse-frankfurt.de/EN/index.aspx";
	
	public Quotation_Bond parseBOND(URL url)
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
			String pattern = "//div[@class='info']//text() | //table[@class='halfsingle']//text()";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;

			//			for(int i=0; i<nodes.getLength();i++)
			//				System.out.println(i + "\t" + nodes.item(i).getNodeValue());
			Quotation_Bond qb = new Quotation_Bond(UtilFuncs.countryUsDateIt);
			qb.setSiteUrl(siteUrl);
			qb.setName(nodes.item(0).getNodeValue());		//Nome			
			qb.setISIN(nodes.item(86).getNodeValue());		//ISIN
			qb.setValuta(nodes.item(104).getNodeValue());		//Valuta
			qb.setMercato(nodes.item(52).getNodeValue());	//Mercato
			//			qb.setFaseMercato(UtilFuncs.getString(nodes, 52));//Fase Mercato
			qb.setPrezzoUltimoContratto(nodes.item(19).getNodeValue());	//Ultimo Prezzo
			qb.setVariazionePercentuale(nodes.item(26).getNodeValue());	//Var %
			qb.setVariazioneAssoluta(nodes.item(24).getNodeValue());	//Var Ass


			qb.setDataUltimoContratto(nodes.item(21).getNodeValue() + " " + nodes.item(22).getNodeValue());
			//			qb.setVolumeUltimo(UtilFuncs.getString(nodes, 46));
			//			qb.setVolumeAcquisto(UtilFuncs.getString(nodes, 91));
			//			qb.setPrezzoAcquisto(UtilFuncs.getString(nodes, 84));
			//			qb.setPrezzoVendita(UtilFuncs.getString(nodes, 86));
			//			qb.setVolumeVendita(UtilFuncs.getString(nodes, 93));
			//			qb.setVolumeTotale(UtilFuncs.getString(nodes, 89));
			qb.setMaxAnno(nodes.item(42).getNodeValue());
			qb.setMaxOggi(nodes.item(38).getNodeValue());
			qb.setMinOggi(nodes.item(40).getNodeValue());
			qb.setMinAnno(nodes.item(44).getNodeValue());
			//			qb.setDataMinAnno(UtilFuncs.getString(nodes, 35));
			//			qb.setDataMaxAnno(UtilFuncs.getString(nodes, 41));
			qb.setCedola(nodes.item(72).getNodeValue());
			//			qb.setLottoMinimo(nodes.item(64).getNodeValue());
			qb.setDataStaccoCedola(nodes.item(74).getNodeValue());

			qb.setAperturaChiusuraPrecedente(nodes.item(46).getNodeValue());

			qb.setScadenza(nodes.item(112).getNodeValue());


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

	public Quotation_Bond parseBTP(URL url)
	{
		return parseBOND(url);
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
			String pattern = "//div[@class='info']//text() | //table[@class='halfsingle']//text()";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;


			Quotation_Fund qf = new Quotation_Fund(UtilFuncs.countryDefault);
			qf.setSiteUrl(siteUrl);
			qf.setName(nodes.item(0).getNodeValue());
			
			String isin = nodes.item(1).getNodeValue();
			String type = isin.split(",")[0];
			
			if(type!="Funds")
				return null;
			
			isin = isin.split(",")[1];
			isin = isin.trim();
			isin = isin.split(" ")[1];
			
			
			qf.setISIN(isin);
			qf.setBenchmarkDichiarato(nodes.item(38).getNodeValue());
			qf.setCategoriaAssociati(nodes.item(111).getNodeValue());
			qf.setDataUltimoPrezzo(nodes.item(7).getNodeValue());
			qf.setNomeGestore(nodes.item(97).getNodeValue());
//			qf.setPerformance1Anno(UtilFuncs.getString(nodes, 14));
//			qf.setPerformance1Mese(performance1Mese)
//			qf.setPerformance3Anni(UtilFuncs.getString(nodes, 15));
//			qf.setPerformance3Mesi(UtilFuncs.getString(nodes, 13));
			qf.setPrezzoPrecedente(nodes.item(34).getNodeValue());
//			qf.setSiteUrl(site)
			qf.setUltimoPrezzo(nodes.item(5).getNodeValue());
			qf.setValuta(nodes.item(75).getNodeValue());
			qf.setVariazioneAssoluta(nodes.item(10).getNodeValue());
			qf.setVariazionePercentuale(nodes.item(12).getNodeValue());

			return qf;	
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		} 
		catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
		}
		catch (StringIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	@Override
	public Quotation_Share parseSHARE(URL url) {
		
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
			String pattern = "//div[@class='info']//text() | //table[@class='halfsingle']//text()";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;

			String isin = nodes.item(1).getNodeValue();
			String type = isin.split(",")[0];
			
			if(type!="Equity")
				return null;
			
			isin = isin.split(",")[1];
			isin = isin.trim();
			isin = isin.split(" ")[1];
			
			
			Quotation_Share qs = new Quotation_Share(UtilFuncs.countryDefault);
			qs.setSiteUrl(siteUrl);
			qs.setName(nodes.item(0).getNodeValue());		//Nome		
			qs.setISIN(isin);		//ISIN
//			qs.setLottoMinimo(UtilFuncs.getString(nodes, 13));
//			qs.setFaseMercato(UtilFuncs.getString(nodes, 15));	
			qs.setPrezzoUltimoContratto(nodes.item(7).getNodeValue());
			qs.setVariazionePercentuale(nodes.item(19).getNodeValue());
			qs.setVariazioneAssoluta(nodes.item(17).getNodeValue());
			qs.setDataOraUltimoAcquisto(nodes.item(11).getNodeValue() + " - " + nodes.item(12).getNodeValue());
			qs.setPrezzoAcquisto(nodes.item(31).getNodeValue().split(" : ")[1]);
			qs.setPrezzoVendita(nodes.item(31).getNodeValue().split(" : ")[0]);
			qs.setQuantitaUltimo(nodes.item(25).getNodeValue().split(" : ")[1]);
			qs.setQuantitaAcquisto(nodes.item(34).getNodeValue().split(" : ")[1]);
			qs.setQuantitaVendita(nodes.item(34).getNodeValue().split(" : ")[0]);
//			qs.setQuantitaTotale(UtilFuncs.getString(nodes, 37));
			qs.setMaxOggi(nodes.item(40).getNodeValue());
			qs.setMinOggi(nodes.item(43).getNodeValue());
			qs.setMaxAnno(nodes.item(46).getNodeValue());
			qs.setMinAnno(nodes.item(49).getNodeValue());
//			qs.setDataMaxAnno(nodes.item(11).getNodeValue());
//			qs.setDataMinAnno(nodes.item(8).getNodeValue());
			qs.setChiusuraPrecedente(nodes.item(52).getNodeValue());
			
			return qs;

			
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		} 
		catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
		}
		catch (StringIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}

