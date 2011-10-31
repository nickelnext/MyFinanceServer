package Handlers;


import java.net.URL;
import java.util.Hashtable;

public abstract class Site {

	private String name;
	protected final String  text = "__ISIN_HERE__"; //string to replace
	// "BTP", {"http://www.abc/bond1.asp", "http://www.abc/bond2.asp"}
	private Hashtable<String, URL> tableTypeUrl;

	public Site(String name, Hashtable<String, URL> tableTypeUrl) {
		this.name = name;
		/*costruisco la tabella hash per ogni tipo
		 * 
		 * BTP->[link]
		 * BOT->[link1, link2]
		 * ...
		 * ...
		*/
		this.tableTypeUrl = tableTypeUrl;
	}


	public abstract boolean hasTool(String toolType, String ISIN);
	public abstract boolean getToolInfo(String toolType, String ISIN);
	protected abstract void parseBOND();
	protected abstract void parseCCT();
	protected abstract void parseCTZ();
	protected abstract void parseBOT();
	protected abstract void parseSHARE();
	protected abstract void parseFOUND();
	
	
	
	
	public boolean hasToolType(String toolType)
	{
		return tableTypeUrl.containsKey(toolType);
	}
	
	

	public String getName()
	{
		return name;
	}

	public Hashtable<String, URL> getTableTypeUrl() {
		return tableTypeUrl;
	}



}
