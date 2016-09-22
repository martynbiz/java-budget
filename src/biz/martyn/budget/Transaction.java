package biz.martyn.budget;

import java.io.Serializable;
import java.util.ArrayList;

class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String desc; 
	String date; 
	int amount; 
	String category; 
	
	ArrayList<Object> errors = new ArrayList<>();
	
	public Transaction(String desc, String date, int amount, String category) {
		this.desc = desc;
		this.date = date;
		this.amount = amount;
		this.category = category;
	}
	
	public Transaction(String desc, String date, int amount) {
		this(desc, date, amount, "");
	}
	
	public boolean save() {
		
		// validation
		if (!isValid()) {
			return false;
	    }
		
		// get translations for this fund 
		
		
		// write transactions to storage
		
		
		return true;
	}
	
	public boolean isValid() {
		
		errors.clear();
		
		// check description 
		if (desc.isEmpty()) {
			errors.add("Missing description");
	    }
		
		// check date 
		if (date.isEmpty()) {
			errors.add("Missing date");
	    }
		
		// check amount
		if (amount == 0) {
			errors.add("Amount cannot be zero");
	    }
				
		// check category 
		if (category.isEmpty()) {
			errors.add("Missing category");
	    }
		
		return false;
	}
	
	public ArrayList<Object> getErrors() {
		return errors;
	}
	
	
}
