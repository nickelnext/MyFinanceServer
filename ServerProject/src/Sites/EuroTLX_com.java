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


public class EuroTLX_com implements SiteInterface {

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
			String pattern = "//div[@class='popup-tables-holder']//td/text() | //div[@class='popup-tables-holder']//th/text() " +
					"| //div[@class='popup-nav']//text() | //ul[@class='product-description']//text()";
			
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;
			
			for(int i=0;i<nodes.getLength();i++)
				System.out.println(i + "\t" + nodes.item(i).getNodeValue());
			
			
			Quotation_Bond qb = new Quotation_Bond();
			
			qb.setCountry(UtilFuncs.countryUs);
			qb.setName(nodes.item(14).getNodeValue());		//Nome			
			qb.setISIN(nodes.item(1).getNodeValue());		//ISIN
			qb.setValuta(nodes.item(104).getNodeValue());		//Valuta
			qb.setMercato("EuroTLX");	//Mercato
//			qb.setFaseMercato(UtilFuncs.getString(nodes, 52));//Fase Mercato
			qb.setPrezzoUltimoContratto(nodes.item(44).getNodeValue());	//Ultimo Prezzo
			qb.setVariazionePercentuale(nodes.item(16).getNodeValue());	//Var %
//			qb.setVariazioneAssoluta(nodes.item(17).getFirstChild().getNodeValue());	//Var Ass
			qb.setDataUltimoContratto(nodes.item(48).getNodeValue());
//			qb.setVolumeUltimo(UtilFuncs.getString(nodes, 46));
//			qb.setVolumeAcquisto(UtilFuncs.getString(nodes, 91));
//			qb.setPrezzoAcquisto(UtilFuncs.getString(nodes, 84));
//			qb.setPrezzoVendita(UtilFuncs.getString(nodes, 86));
//			qb.setVolumeVendita(UtilFuncs.getString(nodes, 93));
//			qb.setVolumeTotale(UtilFuncs.getString(nodes, 89));
			qb.setMaxAnno(nodes.item(59).getNodeValue());
//			qb.setMaxOggi(nodes.item(59).getNodeValue());
//			qb.setMinOggi(UtilFuncs.getString(nodes, 66));
			qb.setMinAnno(nodes.item(57).getNodeValue());
//			qb.setDataMinAnno(UtilFuncs.getString(nodes, 35));
//			qb.setDataMaxAnno(UtilFuncs.getString(nodes, 41));
			qb.setCedola(nodes.item(106).getNodeValue());
			qb.setLottoMinimo(nodes.item(64).getNodeValue());
			//scadenza non stacco
			qb.setDataStaccoCedola(nodes.item(100).getNodeValue());
//			qb.setAperturaChiusuraPrecedente(UtilFuncs.getString(nodes, 64));
			
			return qb;
		}
		catch (IOException e) {
			System.out.println("ISIN NON TROVATO");	
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
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
	public Quotation_Bond parseBOND(URL url)
	{
		return parseBTP(url);
	}
	public Quotation_Share parseSHARE(URL url)
	{
		return null;
	}
	public Quotation_Fund parseFUND(URL url) {
		return null;
	}



}

