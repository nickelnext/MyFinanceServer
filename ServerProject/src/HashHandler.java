import java.util.Hashtable;


public class HashHandler {

	
	private Hashtable<String, Site> hashtable;
	
	public boolean loadHash()
	{
		return false;
	}
	public boolean saveHash()
	{
		return false;
	}
	
	public Site getSiteFromIsin(String ISIN)
	{
		return hashtable.get(ISIN);
	}
	public void addISIN(String ISIN, Site s)
	{
		hashtable.put(ISIN, s);
	}
	public void removeISIN(String ISIN)
	{
		hashtable.remove(ISIN);
	}
	public void cleanHash()
	{
		hashtable.clear();
	}
	
}
