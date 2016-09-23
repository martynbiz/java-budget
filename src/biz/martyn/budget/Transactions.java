package biz.martyn.budget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transactions implements Observable {

    private List<Observer> observers = new ArrayList<Observer>();
    private ArrayList<Transaction> transactions;
    
    public Transactions() {
    	System.out.println("init Transactions");
    	transactions = Budget.getAdapter().loadTransactions();
    }

    public ArrayList<Transaction> get() {
    	return transactions;
    }

    public boolean insert(Transaction transaction) {
    	
    	transactions.add(transaction);
    	boolean result = Budget.getAdapter().writeTransactions(transactions);
    	
    	if (result) {
            notifyObservers();
    	}
    	
    	return result;
    }

    public void addObserver(Observer observer){
        observers.add(observer);		
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

	public String [] getCategoriesArray() {
		
		ArrayList<String> categories = new ArrayList<> ();
		
		// add categories if not contained (unique only)
		for (Transaction t : transactions) {
			if(!categories.contains(t.category)) {
				categories.add(t.category);
			}
		}
		
		// sort categories
		Collections.sort(categories);
        
		return categories.toArray(new String[0]);
	}
	
	public Transaction createObject(String desc, String date, int amount, String category) {
		Transaction transaction = new Transaction(desc, date, amount, category);
		return transaction;
	}
	
//	class Transaction implements Serializable {
//		
//		private static final long serialVersionUID = 1L;
//		
//		String desc; 
//		String date; 
//		int amount; 
//		String category; 
//		
//		ArrayList<Object> errors = new ArrayList<>();
//		
//		public Transaction(String desc, String date, int amount, String category) {
//			this.desc = desc;
//			this.date = date;
//			this.amount = amount;
//			this.category = category;
//		}
//		
//		public Transaction(String desc, String date, int amount) {
//			this(desc, date, amount, "");
//		}
//		
//		public boolean isValid() {
//			
//			errors.clear();
//			
//			// check description 
//			if (desc.isEmpty()) {
//				errors.add("Missing description");
//		    }
//			
//			// check date 
//			if (date.isEmpty()) {
//				errors.add("Missing date");
//		    }
//			
//			// check amount
//			if (amount == 0) {
//				errors.add("Amount cannot be zero");
//		    }
//					
//			// check category 
//			if (category.isEmpty()) {
//				errors.add("Missing category");
//		    }
//			
//			return errors.isEmpty();
//		}
//		
//		public ArrayList<Object> getErrors() {
//			return errors;
//		}
//		
//		public String[] toStringArray() {
//			String[] obj = {
//				desc,
//				date,
//				Integer.toString(amount) 
//			};
//			
//			return obj;
//		}
//	}
}


