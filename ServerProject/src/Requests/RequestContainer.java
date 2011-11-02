package Requests;

import java.util.ArrayList;
import java.util.Set;

import Quotes.Type;

public class RequestContainer {
	
	private int id; //da definire: qualcosa che lo identifichi
	private Set<Type> preferredTypes; //per definire eventuali preferenze
	private ArrayList<Request> reqList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<Type> getPreferredTypes() {
		return preferredTypes;
	}
	public void setPreferredTypes(Set<Type> preferredTypes) {
		this.preferredTypes = preferredTypes;
	}
	public ArrayList<Request> getReqList() {
		return reqList;
	}
	public void setReqList(ArrayList<Request> reqList) {
		this.reqList = reqList;
	}
	
	

}
