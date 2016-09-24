package biz.martyn.budget.models;

import java.util.ArrayList;
import java.util.Collections;

import biz.martyn.budget.storage.StorageAdapter;

public class Transactions extends AbstractModel {

    private ArrayList<Transaction> transactions;
    private StorageAdapter storageAdapter;
    
    public Transactions(StorageAdapter storageAdapter) {
    	this.storageAdapter = storageAdapter;
    	transactions = storageAdapter.loadTransactions();
    }

    public ArrayList<Transaction> get() {
    	return transactions;
    }

    public boolean insert(Transaction transaction) {
    	
    	transactions.add(transaction);
    	boolean result = storageAdapter.writeTransactions(transactions);
    	
    	if (result) {
            notifyObservers();
    	}
    	
    	return result;
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
}


