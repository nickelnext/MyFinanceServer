package mainpackage;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import Quotes.QuotationType;
import Requests.RequestType;

@SuppressWarnings("rawtypes")
public class RequestPolicies {

	
	
	
	private Vector siteSearch;
	private Vector shareList;
	private Vector  bondList;
	private Vector  fundList;
	private Hashtable<String, String> siteNameTable;
	  
	
	public RequestPolicies(	) {
		
	}
	

	
/*	public String getSearchPathFromSitename(Request req, MyDatabase db){
		String[] tmp;
		String res;
		if(siteNameTable.get(req.getPreferredSite()) != null){
			return siteNameTable.get(req.getPreferredSite());
		}
		Vector v = db.execQuery("SELECT SearchPath FROM tbl_name_search_rate WHERE Site='"+req.getPreferredSite()+";");
		res = (String)v.elementAt(0);
		this.siteNameTable.put(req.getPreferredSite(), res);
		return res;
	}
*/
		
	public void setSiteNameTable(Vector v){
		this.siteNameTable = new Hashtable<String, String>();
		@SuppressWarnings("unchecked")
		Iterator<Object> iter = v.iterator();
		String[] tmp;
		String name;
		String searchPath;
		while(iter.hasNext()){
			tmp = (String[])iter.next();
			name = tmp[0];
			searchPath = tmp[1];
			this.siteNameTable.put(name, searchPath);
		}

	}

	public Hashtable<String, String> getSiteNameTable() {
		return siteNameTable;
	}

	public void setSiteNameTable(Hashtable<String, String> siteNameTable) {
		this.siteNameTable = siteNameTable;
	}

	
	
	public Vector getSiteSearch(MyDatabase db) {
		//kinda sigleton pattern
		if(this.siteSearch == null){
			this.siteSearch = db.execQuery("SELECT DISTINCT Name,SearchPath FROM tbl_name_type_search_rate;");
			this.setSiteNameTable(this.siteSearch);
		}
		return siteSearch;
	}
	public void setSiteSearch(Vector siteSearch) {
		this.siteSearch = siteSearch;
	}
	public Vector getShareList(MyDatabase db) {
		//kinda sigleton pattern
		if(this.shareList == null){
			this.shareList = db.execQuery("SELECT name,searchUrl FROM tbl_name_type_search_rate WHERE Type=\""+QuotationType.SHARE+"\"ORDER BY Rating DESC;" );
		}
		return shareList;
	}
	
	
	public void setShareList(Vector shareList) {
		this.shareList = shareList;
	}
	
	public Vector getBondList(MyDatabase db) {
		//kinda sigleton pattern
		if(this.bondList == null){
			this.bondList = db.execQuery("SELECT name,searchUrl FROM tbl_name_type_search_rate WHERE Type=\""+QuotationType.BOND+"\"ORDER BY Rating DESC;" );
		}
		return bondList;
	}
	public void setBondList(Vector bondList) {
		this.bondList = bondList;
	}
	public Vector getFundList(MyDatabase db) {
		//kinda sigleton pattern
				if(this.fundList == null){
					this.fundList = db.execQuery("SELECT name,searchUrl FROM tbl_name_type_search_rate WHERE Type=\""+QuotationType.FUND+"\"ORDER BY Rating DESC;" );
				}
				return fundList;
	}
	
	public void setFundList(Vector fundList) {
		this.fundList = fundList;
	}
	
	public void updateRankingTables(boolean found, RequestType rType, QuotationType qType, String siteName, MyDatabase db){
		
		if(found){
			if(rType.equals(RequestType.QUOTATION) || rType.equals(RequestType.UPDATE)){
				db.updateQuery("UPDATE `tbl_name_type_search_rate` SET   `Hits`=`Hits`+'1' , `Total`=`Total`+'1' , `Rating`=(`Hits`+'1')/(`Total`+'1')*100 WHERE `tbl_name_type_search_rate`.`Name` = '"+siteName+"' AND `tbl_name_type_search_rate`.`Type` = '"+qType+"';");
			}
		}else{
			if(rType.equals(RequestType.QUOTATION) || rType.equals(RequestType.UPDATE)){
				db.updateQuery("UPDATE `tbl_name_type_search_rate` SET   `Total`=`Total`+'1' , `Rating`=(`Hits`)/(`Total`)*100 WHERE `tbl_name_type_search_rate`.`Name` = '"+siteName+"' AND `tbl_name_type_search_rate`.`Type` = '"+qType+"';");
			}
		}
				

	}
	
	
}

				
