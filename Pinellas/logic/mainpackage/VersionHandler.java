package mainpackage;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Requests.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class VersionHandler {

	
	public static String getVersionStuff(){
		String result = "CULO";
		String tmp;
		MyDatabase db = new MyDatabase("pinella", "root", "myfinance");
		
		if ( !db.connect() ) { 
//			ErrorHandler.setError(Errors.ERROR_DATABASE_CONNECTION);
			System.exit(0);
		}
		
		RequestPolicies rp = new RequestPolicies();
		ArrayList<TypeSiteObject> list = rp.getSiteType(db);
		
		Gson converter = new Gson();
		result = converter.toJson(list);

//		CODICE PER SPACCHETTARLO
//		Type typeOfT = new TypeToken<ArrayList<TypeSiteObject>>(){}.getType();
//		ArrayList<TypeSiteObject> lista = converter.fromJson(json, typeOfT);
		
		return result;
	}
	
	
}
