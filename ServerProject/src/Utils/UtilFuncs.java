package Utils;

import org.w3c.dom.NodeList;

public class UtilFuncs {

	public static String getString(NodeList nodes, int n)
	{
		if(!nodes.item(n).hasChildNodes() || nodes.item(n).getFirstChild().getNodeValue() == null)
		{
			System.out.println("''");
			return "";
		}
		System.out.println(nodes.item(n).getFirstChild().getNodeValue());
		return nodes.item(n).getFirstChild().getNodeValue();
	}
	public static boolean isISIN(String s)
	{
		if(s.length()==12 && s.matches("[A-Z]{2}\\d{10}"))
			return true;
		return false;
	}
	
}
