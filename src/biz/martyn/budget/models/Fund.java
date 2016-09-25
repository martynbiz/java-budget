package biz.martyn.budget.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Fund implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String id;
	public String name; 
	
	ArrayList<Object> errors = new ArrayList<>();
	
	public Fund(String name) {
		this.id = getNextId();
		this.name = name;
	}

    private String getNextId() {
		String id = UUID.randomUUID().toString();
    	return id;
	}
	
	public boolean isValid(Funds funds) {
		
		errors.clear();
		
		// check name 
		if (name.isEmpty()) {
			errors.add("Missing name");
	    } else if (funds.getByName(name) != null) {
	    	errors.add("'" + name + "' fund already exists");
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
