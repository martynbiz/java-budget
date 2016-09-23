package biz.martyn.budget.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import biz.martyn.budget.Budget;
import biz.martyn.budget.Observable;
import biz.martyn.budget.Observer;

public class Transactions implements Observable {

    private List<Observer> observers = new ArrayList<Observer>();
    private ArrayList<Transaction> transactions;
    
    public Transactions() {
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
	
//	public Transaction createObject(String desc, String date, int amount, String category) {
//		Transaction transaction = new Transaction(desc, date, amount, category);
//		return transaction;
//	}
}


