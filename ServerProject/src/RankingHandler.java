import java.util.ArrayList;


public class RankingHandler {

	private ArrayList<RankElement> rankedList;
	
	
	public void loadRankedList()
	{
		
	}
	public void saveRankedList()
	{
		
	}
	public RankElement getRankElementFromType(ProductType p)
	{
		for(RankElement r: rankedList)
		{
			if(r.getType() == p)
				return r;
		}
		return null;
	}
	
	public int getRankElementIndexOf(ProductType p)
	{
		for(RankElement r: rankedList)
		{
			if(r.getType() == p)
				return rankedList.indexOf(r);
		}
		return -1;
	}
	
	public boolean addRankElement(RankElement r)
	{
		if(rankedList.contains(r))
			return false;
		rankedList.add(r);
		return true;
	}
}
