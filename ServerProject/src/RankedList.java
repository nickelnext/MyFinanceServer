import java.util.ArrayList;


public class RankedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private ProductType type ;
	private ArrayList<SiteStats> siteStatsArray;
	
	
	public RankedList(ProductType t, ArrayList<SiteStats> sss)
	{
		type = t;
		siteStatsArray = sss;
	}
	public RankedList()
	{
		type = new ProductType();
		siteStatsArray = new ArrayList<SiteStats>();
	}
	
	public boolean addSite(Site s, int h, int t)
	{
		SiteStats st = new SiteStats(s, h, t);
		if(siteStatsArray.contains(st))
			return false;
		siteStatsArray.add(st);
		return true;
	}
	
	public boolean addHit(Site s)
	{
		SiteStats st = new SiteStats(s);
		if( siteStatsArray.contains(st))
		{
//			int n= siteList.lastIndexOf(s);
//			hits.add(n);
			return true;
		}
		return false;
	}
	
	private void updateRankedList()
	{
		
	}
	private Site getFirstRankedSite()
	{
		return null;
	}
	private Site getNextRankedSite()
	{
		return null;		
	}

}
