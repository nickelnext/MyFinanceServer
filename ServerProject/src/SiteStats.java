
public class SiteStats {

	private Site site;
	private int hits;
	private int total;
	public SiteStats(Site site, int hits, int total) {
		super();
		this.site = site;
		this.hits = hits;
		this.total = total;
	}
	public SiteStats(Site site) {
		this.site = site;
		this.hits = 0;
		this.total = 0;
	}
	
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public int getHits() {
		return hits;
	}
	public void incrementHits() {
		this.hits++;
	}
	public int getTotal() {
		return total;
	}
	public void incrementTotal() {
		this.total++;
	}
	//@Override
	public boolean equals(SiteStats stst)
	{
		if(stst.getSite().getName().equals(site.getName()))
			return true;
		return false;
	}
}
