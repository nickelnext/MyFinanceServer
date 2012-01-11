package mainpackage;

import java.util.ArrayList;

import com.google.gson.Gson;

public class VersionHandler {


	public static String getVersion()
	{
		MyDatabase db = new MyDatabase("pinella", "root", "myfinance");
		String result = "";
		if ( !db.connect() ) { 
			ErrorHandler.setError(Errors.ERROR_DATABASE_CONNECTION);
			result = ErrorHandler.getAllErrors();
		}
		else
		{
			RequestPolicies rp = new RequestPolicies();
			result = "" +rp.getSiteType(db).hashCode();
		}
		return result;
	}

		public static String getVersionStuff(){
			String result = "";
			MyDatabase db = new MyDatabase("pinella", "root", "myfinance");

			if ( !db.connect() ) { 
				ErrorHandler.setError(Errors.ERROR_DATABASE_CONNECTION);
				result = ErrorHandler.getAllErrors();
				//			System.exit(0);
			}
			else
			{
				RequestPolicies rp = new RequestPolicies();
				ArrayList<TypeSiteObject> list = rp.getSiteType(db);
				Gson converter = new Gson();
				result = converter.toJson(list);
			}
			//		CODICE PER SPACCHETTARLO
			//		Type typeOfT = new TypeToken<ArrayList<TypeSiteObject>>(){}.getType();
			//		ArrayList<TypeSiteObject> lista = converter.fromJson(json, typeOfT);
			return result;
		}


	}
