
public class RequestUpdate extends Request{

	private float oldValue;  //boh, poi ci pensiamo

	public RequestUpdate(String iSIN, String type, float oldValue) {
		super(iSIN, type);
		this.oldValue = oldValue;
	}

	public float getOldValue() {
		return oldValue;
	}

	public void setOldValue(float oldValue) {
		this.oldValue = oldValue;
	}
	
	
}
