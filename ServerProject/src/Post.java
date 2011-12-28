import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Post {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		EuroTLX_com_Search s = new EuroTLX_com_Search();
//		s.search(null, null);
//		System.out.println(s.getCompleteLink());
//		System.out.println(s.getType());
		String s = "January 27";
		DateFormat f = new SimpleDateFormat("MMMMM dd",Locale.ENGLISH);
		Date d = new Date();
		System.out.println(Calendar.getInstance().getDisplayName(Calendar.MONTH, 2, Locale.ENGLISH));
		
		try {
			f.parse(s);
			System.out.println("Fatto");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
