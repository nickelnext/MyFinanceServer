package Quotes;

import java.lang.reflect.Field;
import Quotes.Type;

public abstract class Quotation {

	protected String name;
	protected String ISIN;
	protected Type type;
	protected String site;

	public Quotation() {
		super();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getISIN() {
		return this.ISIN;
	}
	public void setISIN(String isin) {
		this.ISIN = isin;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();
        
        //print field names paired with their values
        for ( Field field : fields  ) {
          result.append("  ");
          try {
            result.append( field.getName() );
            result.append(": ");
            //requires access to private field:
            result.append( field.get(this));
          } catch ( IllegalAccessException ex ) {
          }
          result.append(newLine);
        }
        result.append("}");

        return result.toString();
      }



	
}
