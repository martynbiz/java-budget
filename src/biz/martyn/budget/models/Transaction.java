package biz.martyn.budget.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public String id;
	public String desc; 
	public String date; 
	public int amount; 
	public String category; // optional
	
	ArrayList<Object> errors = new ArrayList<>();
//UUID.randomUUID()
	
	public Transaction(String desc, String date, int amount, String category) {
		this.id = getNextId();
		this.desc = desc;
		this.date = date;
		this.amount = amount;
		this.category = category;
	}
	
	public Transaction(String desc, String date, int amount) {
		this(desc, date, amount, "");
	}

    private String getNextId() {
		String id = UUID.randomUUID().toString();
    	return id;
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
		
		return errors.isEmpty();
	}
	
	public ArrayList<Object> getErrors() {
		return errors;
	}
	
	public String[] toStringArray() {
		String[] obj = {
			id,
			desc,
			date,
			category,
			Integer.toString(amount)
		};
		
		return obj;
	}
}
