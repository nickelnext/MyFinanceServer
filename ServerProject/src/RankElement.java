import java.util.ArrayList;


public class RankElement {

	private ProductType type;
	private ArrayList<SiteStats> siteStatsList;
	
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	public ArrayList<SiteStats> getSiteStatsList() {
		return siteStatsList;	
	}
	public void setSiteStatsList(ArrayList<SiteStats> siteStatsList) {
		this.siteStatsList = siteStatsList;
	}
	
	public void addSiteStats(SiteStats s)
	{
		if(siteStatsList == null)
			siteStatsList = new ArrayList<SiteStats>();
		siteStatsList.add(s);
	}
	
	public void updateRank()
	{
		
	}
}
