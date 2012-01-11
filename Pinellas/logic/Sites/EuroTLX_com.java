package Sites;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	private final String siteUrl = "http://www.eurotlx.com/";
	
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

			Quotation_Bond qb = new Quotation_Bond(UtilFuncs.countryUs);
			qb.setSiteUrl(siteUrl);
			qb.setName(nodes.item(14).getNodeValue());		//Nome			
			qb.setISIN(nodes.item(1).getNodeValue());		//ISIN
			qb.setValuta(nodes.item(104).getNodeValue());		//Valuta
			qb.setMercato("EuroTLX");	//Mercato
			//			qb.setFaseMercato(UtilFuncs.getString(nodes, 52));//Fase Mercato
			String stringOpening = nodes.item(90).getNodeValue().split(" ")[0];
			String stringClosing = nodes.item(90).getNodeValue().split(" ")[1];
			SimpleDateFormat s = new SimpleDateFormat("HH:mm");
			Date dateOpening = s.parse(stringOpening);
			Date dateClosing = s.parse(stringClosing);

			//if the market's closed, then we have the normal parsing
			if(Calendar.getInstance().before(dateOpening) || Calendar.getInstance().after(dateClosing))
			{
				qb.setPrezzoUltimoContratto(nodes.item(44).getNodeValue());
				qb.setVolumeUltimo(nodes.item(46).getNodeValue());
				qb.setDataUltimoContratto(nodes.item(48).getNodeValue());
			}
			//the market's open, then we have the 5 level book for prices.
			else
			{
				qb.setDataUltimoContratto(nodes.item(12).getNodeValue());
				qb.setPrezzoAcquisto(nodes.item(24).getNodeValue());
				qb.setPrezzoVendita(nodes.item(23).getNodeValue());
				qb.setVolumeAcquisto(nodes.item(25).getNodeValue());
				qb.setVolumeVendita(nodes.item(22).getNodeValue());
			}




			//			qb.setPrezzoUltimoContratto(nodes.item(44).getNodeValue());	//Ultimo Prezzo
			qb.setVariazionePercentuale(nodes.item(16).getNodeValue());	//Var %
			//			qb.setVariazioneAssoluta(nodes.item(17).getFirstChild().getNodeValue());	//Var Ass
			//			qb.setDataUltimoContratto(nodes.item(48).getNodeValue());
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
			System.out.println(e.getMessage());
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		} catch(StringIndexOutOfBoundsException e)	{
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
			String pattern = "//div[@class='popup-tables-holder']//td//text() | //div[@class='popup-tables-holder']//th/text() " +
					"| //div[@class='popup-nav']//text() | //ul[@class='product-description']//text()";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

//			for(int i=0;i<nodes.getLength();i++)
//				System.out.println(i + "\t" + nodes.item(i).getNodeValue());

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;

			if(nodes.item(91).getNodeValue()!="Equity") //checking if it's a share.
				return null;
			
			Quotation_Share qs = new Quotation_Share(UtilFuncs.countryUs);
			qs.setSiteUrl(siteUrl);

			String stringOpening = nodes.item(85).getNodeValue().split(" ")[0];
			String stringClosing = nodes.item(85).getNodeValue().split(" ")[1];
			SimpleDateFormat s = new SimpleDateFormat("HH:mm");
			Date dateOpening = s.parse(stringOpening);
			Date dateClosing = s.parse(stringClosing);

			//if the market's closed, then we have the normal parsing
			if(Calendar.getInstance().before(dateOpening) || Calendar.getInstance().after(dateClosing))
			{
				qs.setPrezzoUltimoContratto(nodes.item(42).getNodeValue());
				qs.setQuantitaUltimo(nodes.item(44).getNodeValue());
				qs.setDataOraUltimoAcquisto(nodes.item(46).getNodeValue());
			}
			//the market's open, then we have the 5 level book for prices.
			else
			{
				qs.setDataOraUltimoAcquisto(nodes.item(10).getNodeValue());
				qs.setPrezzoAcquisto(nodes.item(22).getNodeValue());
				qs.setPrezzoVendita(nodes.item(21).getNodeValue());
				qs.setQuantitaAcquisto(nodes.item(23).getNodeValue());
				qs.setQuantitaVendita(nodes.item(20).getNodeValue());
			}

			qs.setName(nodes.item(7).getNodeValue());		//Nome			
			qs.setISIN(nodes.item(1).getNodeValue());		//ISIN
			//			qs.setLottoMinimo(UtilFuncs.getString(nodes, 13));
			//			qs.setFaseMercato(UtilFuncs.getString(nodes, 15));	
			qs.setVariazionePercentuale(nodes.item(14).getNodeValue());
			qs.setValuta(nodes.item(92).getNodeValue());
			//			qs.setVariazioneAssoluta(UtilFuncs.getString(nodes, 21));

			//			qs.setQuantitaTotale(UtilFuncs.getString(nodes, 37));
			//			qs.setMaxOggi(UtilFuncs.getString(nodes, 43));
			//			qs.setMinOggi(UtilFuncs.getString(nodes, 47));
			qs.setMaxAnno(nodes.item(58).getNodeValue());
			qs.setMinAnno(nodes.item(56).getNodeValue());
			//			qs.setDataMaxAnno(dataMaxAnno);
			//			qs.setDataMinAnno(dataMinAnno);
			qs.setChiusuraPrecedente(nodes.item(49).getNodeValue());

			return qs;	
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		} 
		catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public Quotation_Fund parseFUND(URL url) {
		return null;
	}



}

