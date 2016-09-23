package biz.martyn.budget.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String desc; 
	public String date; 
	public int amount; 
	public String category; // optional
	
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
				
//		// check category 
//		if (category.isEmpty()) {
//			errors.add("Missing category");
//	    }
		
		return errors.isEmpty();
	}
	
	public ArrayList<Object> getErrors() {
		return errors;
	}
	
	public String[] toStringArray() {
		String[] obj = {
			desc,
			date,
			Integer.toString(amount)
		};
		
		return obj;
	}
}
