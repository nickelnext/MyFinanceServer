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
			
			for(int i=0; i<nodes.getLength();i++)
				System.out.println(i + "\t" + nodes.item(i).getNodeValue());
			Quotation_Bond qb = new Quotation_Bond();
			
			qb.setCountry(UtilFuncs.countryUsDateIt);
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
			System.out.println("ISIN NON TROVATO");	
		} 
		catch (XPathExpressionException e) {
			e.printStackTrace();
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
		return null;
	}
	@Override
	public Quotation_Share parseSHARE(URL url) {
		// TODO Auto-generated method stub
		return null;
	}
}

