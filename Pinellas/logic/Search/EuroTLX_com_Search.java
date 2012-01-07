package Search;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

import Quotes.QuotationType;


public class EuroTLX_com_Search extends Search {

	public boolean search(String ISIN, String searchUrl) {

		try {
			// Construct data
			String data = URLEncoder.encode("isin", "UTF-8") + "=" + URLEncoder.encode(ISIN, "UTF-8");
			data += "&" + URLEncoder.encode("nisi", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
			data += "&" + URLEncoder.encode("cercaisin", "UTF-8") + "=" + URLEncoder.encode("TROVA", "UTF-8");

			// Send data
			URL url = new URL("http://www.eurotlx.com/strumenti/ricerca-avanzata.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;

			String webPage = "";
			while ((line = rd.readLine()) != null) {
				webPage += line;
			}
			wr.close();
			rd.close();
			InputStream is = null;

			try {
				is = new ByteArrayInputStream(webPage.getBytes("UTF-8"));

			} catch (UnsupportedEncodingException e) {
				//TODO
				e.printStackTrace();
			}


			Tidy tidy = new Tidy();
			tidy.setQuiet(true);
			tidy.setShowWarnings(false);
			tidy.setFixBackslash(true);
			tidy.setShowErrors(0);
			Document response = tidy.parseDOM(is, null);
			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath=factory.newXPath();
			String pattern = "//div[@class='bottom-instrument-data']//td/a/@onclick | //div[@class='bottom-instrument-data']//td[@class='col-2']/text()";
			NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);

			this.setCompleteLink("http://www.eurotlx.com" +nodes.item(0).getNodeValue().split("'")[1]);

			switch (nodes.item(1).getNodeValue()) { //type
			case "CERT-X":
				this.setType(null);
				break;
			case "Altri Titoli di Debito":
				this.setType(null);
				break;
			case "ABS":
				this.setType(null);
				break;
			case "Azioni / DR":
				this.setType(QuotationType.SHARE);
				return true;
			default:
				this.setType(QuotationType.BOND);
				return true;
			}


		} catch (Exception e) {
			//TODO
			e.printStackTrace();
		}
		return false;
	}
}
