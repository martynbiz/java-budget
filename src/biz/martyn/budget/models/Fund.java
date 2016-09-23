package biz.martyn.budget.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Fund implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String name; 
	
	ArrayList<Object> errors = new ArrayList<>();
	
	public Fund(String name) {
		this.name = name;
	}
	
	public boolean isValid() {
		
		errors.clear();
		
		// check description 
		if (name.isEmpty()) {
			errors.add("Missing description");
	    }
		
		return errors.isEmpty();
	}
	
	public ArrayList<Object> getErrors() {
		return errors;
	}
	
	public String[] toStringArray() {
		String[] obj = {
			name
		};
		
		return obj;
	}
}
