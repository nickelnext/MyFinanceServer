package Sites;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Handlers.SiteInterface;
import Quotes.Quotation_Bond;
import Quotes.Quotation_Fund;
import Quotes.Quotation_Share;
import Utils.UtilFuncs;

public class Yahoo_Finanza_it implements SiteInterface {


	public Quotation_Bond parseBOT(URL url)
	{
		return parseBTP(url);
	}
	public Quotation_Bond parseCCT(URL url)
	{

		try {
			BufferedInputStream buffInput = new BufferedInputStream(url.openStream());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(buffInput);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName("quote");

			Quotation_Bond qb = new Quotation_Bond();
			
			
			qb.setName(UtilFuncs.getString(doc, "Name"));		//Nome			
			qb.setISIN(UtilFuncs.getString(doc, "Symbol"));		//ISIN
//			qb.setValuta(UtilFuncs.getString(nodes, 5));		//Valuta
//			qb.setMercato(UtilFuncs.getString(nodes, 7));	//Mercato
//			qb.setFaseMercato(UtilFuncs.getString(nodes, 11));//Fase Mercato
			qb.setPrezzoUltimoContratto(UtilFuncs.getString(doc, "LastTradePriceOnly"));	//Ultimo Prezzo
			qb.setVariazionePercentuale(UtilFuncs.getString(doc, "PercentChange"));	//Var %
			qb.setVariazioneAssoluta(UtilFuncs.getString(doc, "ChangeRealtime"));	//Var Ass
			qb.setDataUltimoContratto(UtilFuncs.getString(doc, "LastTradeDate") + " " + UtilFuncs.getString(doc, "LastTradeTime"));
			qb.setVolumeUltimo(UtilFuncs.getString(doc, "AverageDailyVolume"));
//			qb.setVolumeAcquisto(UtilFuncs.getString(nodes, 23));
			qb.setPrezzoAcquisto(UtilFuncs.getString(doc, "AskRealtime"));
			qb.setPrezzoVendita(UtilFuncs.getString(doc, "BidRealtime"));
//			qb.setVolumeVendita(UtilFuncs.getString(nodes, 29));
			qb.setVolumeTotale(UtilFuncs.getString(doc, "Volume"));
			qb.setMaxAnno(UtilFuncs.getString(doc, "YearHigh"));
			qb.setMaxOggi(UtilFuncs.getString(doc, "DaysHig"));
			qb.setMinOggi(UtilFuncs.getString(doc, "DaysLow"));
			qb.setMinAnno(UtilFuncs.getString(doc, "YearLow"));
//			qb.setDataMinAnno(UtilFuncs.getString(nodes, 47));
//			qb.setDataMaxAnno(UtilFuncs.getString(nodes, 41));
//			qb.setCedola(UtilFuncs.getString(nodes, 61));
//			qb.setLottoMinimo(UtilFuncs.getString(nodes, 59));
//			qb.setDataStaccoCedola(UtilFuncs.getString(nodes, 63));
			qb.setAperturaChiusuraPrecedente(UtilFuncs.getString(doc, "PreviousClose"));
//			qb.setScadenza(UtilFuncs.getString(nodes, 57));
			
			return qb;	
			
			
			

		} catch (IOException | ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		





		return null;
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
		return null;	
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

