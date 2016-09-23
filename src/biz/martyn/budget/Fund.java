package biz.martyn.budget;

import java.io.Serializable;
import java.util.ArrayList;


class Fund implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String name; 
	
	ArrayList<Object> errors = new ArrayList<>();
	
	public Fund(String name) {
		this.name = name;
	}
	
//	public boolean save() {
//		
//		// validation
//		if (!isValid()) {
//			return false;
//	    }
//		
//		// get translations for this fund 
//		StorageAdapter adapter = Budget.getAdapter();
//		ArrayList<Fund> funds = adapter.loadFunds();
//		funds.add(this);
//		
//		// write funds to storage
//		adapter.writeFunds(funds);
//		
//		return true;
//	}
	
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
