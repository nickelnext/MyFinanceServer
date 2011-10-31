package Handlers;

import java.net.URL;

public interface SiteInterface {

	public abstract void parseBTP(URL url);
	public abstract void parseBOT(URL url);
	public abstract void parseCCT(URL url);
	public abstract void parseCTZ(URL url);
	public abstract void parseBOND(URL url);
	public abstract void parseSHARE(URL url);
	public abstract void parseFUND(URL url);
	
}
