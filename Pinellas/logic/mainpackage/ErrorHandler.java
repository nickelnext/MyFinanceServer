package mainpackage;

import java.util.ArrayList;

public class ErrorHandler {

	private ArrayList<String> errors;
	private String spacer;

	public ErrorHandler() {
		super();
		this.errors = new ArrayList<String>();
	}
	public ErrorHandler(String spacer) {
		new ErrorHandler();
		this.spacer = spacer;
	}

	public void setError(Errors e)
	{
		errors.add(e.toString());
	}
	public void removeError(Errors e)
	{
		errors.remove(e.toString());
	}
	public String getAllErrors()
	{
		String ret = "";
		for(int i=0;i<errors.size();i++)
			ret += errors.get(i) + spacer;
		return ret;	
	}
	public ArrayList<String> getErrors() {
		return errors;
	}
	
	

}
