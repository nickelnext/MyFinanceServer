package mainpackage;

import java.util.ArrayList;

public class ErrorHandler {

	static private ArrayList<String> errors = new ArrayList<String>();
	static private String spacer = "; ";

	static void clearError()
	{
		errors.clear();
	}
	
	public static void setError(Errors e)
	{
		errors.add(e.toString());
	}
	public static void setError(Errors e, String ISIN)
	{
		errors.add(e.toString()+ " " + ISIN);
	}

	public static void removeError(Errors e)
	{
		errors.remove(e.toString());
	}
	public static String getAllErrors()
	{
		String ret = "";
		if(errors.size()==0)
		{
			ret = Errors.ERROR_OK.toString();
			return ret;
		}
		else
		{
			for(int i=0;i<errors.size();i++)
				ret += errors.get(i) + spacer;
			return ret;
		}
	}
	public static ArrayList<String> getErrors() {
		return errors;
	}
}
