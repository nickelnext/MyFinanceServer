package Sites;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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
import de.hdtconsulting.yahoo.finance.YQuote;
import de.hdtconsulting.yahoo.finance.YSymbol;
import de.hdtconsulting.yahoo.finance.Yapi;
import de.hdtconsulting.yahoo.finance.csv.format.YFormat;
import de.hdtconsulting.yahoo.finance.csv.format.YTag;

public class Yahoo_Finanza_it implements SiteInterface {

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
			String pattern = "//table[@summary='YFT_SL_TABLE_SUMMARY']//tr[@class='yui-dt-odd']/td/a/text()";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			//in nodes.item(0).getValue() there's the Yahoo Code for the tool.
			//now it's time to make the real parsing of the proper webpage.

			
			
			
			
			System.out.println(nodes.getLength());

			if(nodes.getLength()==0)		//No nodes, probably a 404 error
				return null;

			String nome = nodes.item(0).getNodeValue();
			System.out.println(nome);

			Quotation_Share qs = new Quotation_Share();

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
			System.out.println("ISIN NON TROVATO");	
		} 
		catch (XPathExpressionException e) {
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
		Yapi yapi = new Yapi();

		YFormat format = new YFormat();
		format.setStatusOn(YTag.BID);
		format.setStatusOn(YTag.BID_REAL_TIME);
		format.setStatusOn(YTag.NAME);

		yapi.setFormat(format);

		YSymbol symbol = new YSymbol("DTE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("DTE.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("PAH3.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("DBK.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("DE0008404005.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("IT0004572910.IT");
		yapi.addQuote(symbol);
		symbol = new YSymbol("IT0004572910");
		yapi.addQuote(symbol);

		System.out.println(yapi.getCsv());
		System.out.println(yapi.getRefreshTime());

		yapi.refresh();

		System.out.println();
		System.out.println(yapi.getCsv());
		System.out.println(yapi.getRefreshTime());

		ArrayList<YQuote> quoteList = yapi.getQuotes();

		for(YQuote quote : quoteList) {

			System.out.println(quote.getSymbol().getCode());
			System.out.println(quote.getValue(YTag.SYMBOL));
			System.out.println(quote.getValue(YTag.NAME));
			System.out.println(quote.getValue(YTag.BID_REAL_TIME));
			System.out.println(quote.getValue(YTag.BID));
			System.out.println(quote.isValid());
			System.out.println();
		}
		return null;

	}
	public Quotation_Fund parseFUND(URL url)
	{
		return null;
	}
}

