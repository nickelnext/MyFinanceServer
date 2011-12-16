import Search.EuroTLX_com_Search;


public class Post {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EuroTLX_com_Search s = new EuroTLX_com_Search();
		s.search(null, null);
		System.out.println(s.getCompleteLink());
		System.out.println(s.getType());
	}

}
