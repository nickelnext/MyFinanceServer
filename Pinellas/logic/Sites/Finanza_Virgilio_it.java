package Sites;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

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


public class Finanza_Virgilio_it implements SiteInterface {

	private final String siteUrl = "http://finanza.economia.virgilio.it/";
	
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
			String pattern = "//ul[@class='info']/li//text() | //div[@id='panelVariazioneContainer']//span/text() | " +
					"//div[@id='ctl00_ContentPlaceHolder1_od1_pnlOdometer']//img/@src | //span[@id='ctl00_ContentPlaceHolder1_updateLabel']/text() | " +
					"//div[@id='SchedaIndici_Left']/text()";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

//			for(int i=0;i<nodes.getLength();i++)
//			{
//				System.out.println(i + "\t"  + nodes.item(i).getNodeValue());
//			}

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;


			Quotation_Share qs = new Quotation_Share(UtilFuncs.countryDefault);
			qs.setSiteUrl(siteUrl);
			qs.setName(nodes.item(0).getNodeValue());		//Nome			
			qs.setISIN(nodes.item(51).getNodeValue());		//ISIN
			qs.setLottoMinimo(nodes.item(59).getNodeValue());
			qs.setFaseMercato(nodes.item(12).getNodeValue());
			qs.setPrezzoUltimoContratto(nodes.item(22).getNodeValue());
			
			qs.setVariazionePercentuale(nodes.item(2).getNodeValue());
			qs.setVariazioneAssoluta(nodes.item(1).getNodeValue());
			
			String data = nodes.item(11).getNodeValue();
			data = data.replaceAll("[^0-9/.\\s]", "");
			data = data.trim();
			try
			{
				data = data.split("  ")[1] + " - " + data.split("  ")[0];
				String[] vendita = nodes.item(26).getNodeValue().split(" x ");
				String[] acquisto = nodes.item(28).getNodeValue().split(" x ");
				qs.setPrezzoAcquisto(acquisto[1]);
				qs.setPrezzoVendita(vendita[1]);
				qs.setQuantitaAcquisto(acquisto[0]);
				qs.setQuantitaVendita(vendita[0]);
				data = nodes.item(40).getNodeValue();
				data = data.substring(data.indexOf("[")+1, data.indexOf("]"));
				Calendar.getInstance();
				data = data + "/" + Calendar.getInstance().get(Calendar.YEAR);
				qs.setDataMinAnno(data);
				data = nodes.item(38).getNodeValue();
				data = data.substring(data.indexOf("[")+1, data.indexOf("]"));
				Calendar.getInstance();
				data = data + "/" + Calendar.getInstance().get(Calendar.YEAR);
				qs.setDataMaxAnno(data);
				
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.out.println("a");
			}
			catch(StringIndexOutOfBoundsException e)
			{
				System.out.println("s");
			}
			
			
			qs.setDataOraUltimoAcquisto(data);
			
			
			
//			qs.setQuantitaUltimo(UtilFuncs.getString(nodes, 27));
			
			qs.setQuantitaTotale(nodes.item(30).getNodeValue());
			qs.setMaxOggi(nodes.item(16).getNodeValue());
			qs.setMinOggi(nodes.item(18).getNodeValue());
			
			
			
			qs.setMaxAnno(nodes.item(37).getNodeValue());
			qs.setMinAnno(nodes.item(39).getNodeValue());
			qs.setChiusuraPrecedente(nodes.item(14).getNodeValue());
		
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
			String pattern = "//ul[@class='info']/li//text() | //div[@id='panelVariazioneContainer']//span/text() | " +
					"//div[@id='ctl00_ContentPlaceHolder1_od1_pnlOdometer']//img/@src | //span[@id='ctl00_ContentPlaceHolder1_updateLabel']/text() | " +
					"//div[@id='SchedaIndici_Left']/text()";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

//			for(int i=0;i<nodes.getLength();i++)
//			{
//				System.out.println(i + "\t"  + nodes.item(i).getNodeValue());
//			}

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;


			Quotation_Bond qb = new Quotation_Bond(UtilFuncs.countryDefault);
			qb.setSiteUrl(siteUrl);

			qb.setName(nodes.item(0).getNodeValue());		//Nome			
			qb.setISIN(nodes.item(51).getNodeValue());		//ISIN
//			qb.setValuta(UtilFuncs.getString(nodes, 5));		//Valuta
//			qb.setMercato(UtilFuncs.getString(nodes, 7));	//Mercato
			qb.setFaseMercato(nodes.item(12).getNodeValue());//Fase Mercato
			
			String ultimoprezzo = "";
			String r;
			
			try
			{
			
			   for(int i=3;i<=10;i++)
			   {

			    r = nodes.item(i).getNodeValue();
			    r = r.substring(r.lastIndexOf("/")+1,r.indexOf("."));

			    if(r.matches("\\d"))
			     ultimoprezzo += r;
			    if(r.contains("dot"))
			     ultimoprezzo += ","; //that's because LOCALE is set to IT
			   }
			
			qb.setPrezzoUltimoContratto(ultimoprezzo);	//Ultimo Prezzo
			}
			catch(StringIndexOutOfBoundsException e)
			{
				System.out.println(e.getMessage());
			}		
			
			qb.setVariazionePercentuale(nodes.item(2).getNodeValue());	//Var %
			qb.setVariazioneAssoluta(nodes.item(1).getNodeValue());	//Var Ass
			
			String data = nodes.item(11).getNodeValue();
//			System.out.println(">"+data+ "<");
			data = data.replaceAll("[^0-9/.\\s]", "");
//			System.out.println(">"+data+ "<");
			data = data.trim();
			try
			{
				data = data.split("  ")[1] + " - " + data.split("  ")[0];
				String[] vendita = nodes.item(26).getNodeValue().split(" x ");
				String[] acquisto = nodes.item(28).getNodeValue().split(" x ");
				qb.setVolumeAcquisto(acquisto[0]);
				qb.setPrezzoAcquisto(acquisto[1]);
				qb.setPrezzoVendita(vendita[1]);
				qb.setVolumeVendita(vendita[0]);
				data = nodes.item(40).getNodeValue();
				data = data.substring(data.indexOf("[")+1, data.indexOf("]"));
				Calendar.getInstance();
				data = data + "/" + Calendar.getInstance().get(Calendar.YEAR);
				qb.setDataMinAnno(data);
				data = nodes.item(38).getNodeValue();
				data = data.substring(data.indexOf("[")+1, data.indexOf("]"));
				Calendar.getInstance();
				data = data + "/" + Calendar.getInstance().get(Calendar.YEAR);
				qb.setDataMaxAnno(data);
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.out.println(e.getMessage());
			}
			catch(StringIndexOutOfBoundsException e)
			{
				System.out.println(e.getMessage());
			}
			
			
			qb.setDataUltimoContratto(data);


			qb.setVolumeUltimo(nodes.item(20).getNodeValue());
			qb.setVolumeTotale(nodes.item(30).getNodeValue());
			qb.setMaxAnno(nodes.item(37).getNodeValue());
			qb.setMaxOggi(nodes.item(16).getNodeValue());
			qb.setMinOggi(nodes.item(18).getNodeValue());
			qb.setMinAnno(nodes.item(39).getNodeValue());

			
			//			qb.setCedola(UtilFuncs.getString(nodes, 61));
			qb.setLottoMinimo(nodes.item(59).getNodeValue());
			//			qb.setDataStaccoCedola(UtilFuncs.getString(nodes, 63));
			qb.setAperturaChiusuraPrecedente(nodes.item(14).getNodeValue());
			//			qb.setScadenza(UtilFuncs.getString(nodes, 57));

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
		
//		try 
//		{
//			BufferedInputStream buffInput = new BufferedInputStream(url.openStream());
//
//			Tidy tidy = new Tidy();
//			tidy.setQuiet(true);
//			tidy.setShowWarnings(false);
//			tidy.setFixBackslash(true);
//			Document response = tidy.parseDOM(buffInput, null);
//
//			XPathFactory factory = XPathFactory.newInstance();
//			XPath xPath=factory.newXPath();
//			String pattern = "//ul[@class='info']/li/*/text() | //div[@id='panelVariazioneContainer']//span/text() | " +
//					"//div[@id='ctl00_ContentPlaceHolder1_od1_pnlOdometer']//img/@src | //span[@id='ctl00_ContentPlaceHolder1_updateLabel']/text() | " +
//					"//div[@id='SchedaIndici_Left']/span/text()";
//			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);
//
//
//
//			if(nodes.getLength()==0)		//No nodes, probably a 404 error
//				return null;
//
//
//			Quotation_Fund qf = new Quotation_Fund(UtilFuncs.countryDefault);
//			qf.setSiteUrl(siteUrl);
//			qf.setName(nodes.item(0).getNodeValue());
//			qf.setISIN(nodes.item(12).getNodeValue());
//			qf.setBenchmarkDichiarato(nodes.item(17).getNodeValue());
//			qf.setCategoriaAssociati(nodes.item(16).getNodeValue());
//
//			try{
//				String data = nodes.item(11).getNodeValue();
//				data = data.split(" ")[data.split(" ").length-1];
//				qf.setDataUltimoPrezzo(data);
//				
//				String ultimoprezzo = "";
//				String r;
//
//				for(int i=3;i<=10;i++)
//				{
//
//					r = nodes.item(i).getNodeValue();
//					r = r.substring(r.lastIndexOf("/")+1,r.indexOf("."));
//
//					if(r.matches("\\d"))
//						ultimoprezzo += r;
//					if(r.contains("dot"))
//						ultimoprezzo += ","; //that's because LOCALE is set to IT
//				}
//
//				qf.setUltimoPrezzo(ultimoprezzo);
//			}
//			catch(IndexOutOfBoundsException e)
//			{
//				System.out.println(e.getMessage());
//			}
//			
//
//			qf.setNomeGestore(nodes.item(19).getNodeValue());
//			//			qf.setPerformance1Anno(performance1Anno)
//			//			qf.setPerformance1Mese(performance1Mese)
//			//			qf.setPerformance3Anni(performance3Anni)
//			//			qf.setPerformance3Mesi(performance3Mesi)
//			//			qf.setPrezzoPrecedente(UtilFuncs.getString(nodes, 3));
//			//			qf.setSiteUrl(site)
//			
//			qf.setValuta(nodes.item(14).getNodeValue());
//			qf.setVariazioneAssoluta(nodes.item(1).getNodeValue());
//			qf.setVariazionePercentuale(nodes.item(2).getNodeValue());
//
//			return qf;	
//		}
//		catch (IOException e) {
//			System.out.println(e.getMessage());
//		} 
//		catch (XPathExpressionException e) {
//			System.out.println(e.getMessage());
//		}
		
		return null;
	}
}

