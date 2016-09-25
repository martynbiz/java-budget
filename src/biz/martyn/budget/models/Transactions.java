package biz.martyn.budget.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import biz.martyn.budget.Observer;
import biz.martyn.budget.storage.StorageAdapter;

public class Transactions implements Iterable<Transaction> {

    List<Transaction> transactions;
    StorageAdapter storageAdapter;
    Fund fund;

    public Transactions(StorageAdapter storageAdapter) {
    	this.storageAdapter = storageAdapter;
    	transactions = new ArrayList<>();
    }

	public Transaction get(int i) {
		return (!transactions.isEmpty()) ? transactions.get(0) : null;
	}

    public void insert(Transaction transaction) {
        transactions.add(transaction);
        writeToFile();
    	notifyObservers();
    }

    /**
     * This just provides an external means to write to file if updated 
     * transactions or a single transaction (e.g. table)
     * @return void
     */
    public void writeToFile() {
    	storageAdapter.writeTransactions(transactions, fund);
    }

	public void setFund(Fund fund) {
    	this.fund = fund;
    	transactions = storageAdapter.loadTransactions(fund);
    	notifyObservers();
    }

    public Iterator<Transaction> iterator() {
        return (Iterator<Transaction> ) new TransactionsIterator();
    }
    
    
    
    public String [] getCategoriesArray() {
		
		List<String> categories = new ArrayList<> ();
		
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
    
    
    
    private List<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer observer){
        observers.add(observer);		
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    class TransactionsIterator implements Iterator<Transaction> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (currentIndex >= transactions.size()) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Transaction next() {
            return transactions.get(currentIndex++);
        }

        @Override
        public void remove() {
            transactions.remove(--currentIndex);
        }
    }

	public Fund getFund() {
		return fund;
	}

	public Transaction getById(String id) {
		Transaction match = null;
		for (Transaction transaction : transactions) {
			if (transaction.id.equals(id)) {
				match = transaction;
			}
		}
		return match;
	}
}

//import java.util.ArrayList;
//import java.util.Collections;
//
//import biz.martyn.budget.storage.StorageAdapter;
//
//public class Transactions extends AbstractModel {
//
//    private ArrayList<Transaction> transactions;
//    private StorageAdapter storageAdapter;
//    
//    public Transactions(StorageAdapter storageAdapter) {
//    	this.storageAdapter = storageAdapter;
//    	transactions = storageAdapter.loadTransactions();
//    }
//
//    public ArrayList<Transaction> get() {
//    	return transactions;
//    }
//
//    public boolean insert(Transaction transaction) {
//    	
//    	transactions.add(transaction);
//    	boolean result = storageAdapter.writeTransactions(transactions);
//    	
//    	if (result) {
//            notifyObservers();
//    	}
//    	
//    	return result;
//    }
//
//	public String [] getCategoriesArray() {
//		
//		ArrayList<String> categories = new ArrayList<> ();
//		
//		// add categories if not contained (unique only)
//		for (Transaction t : transactions) {
//			if(!categories.contains(t.category)) {
//				categories.add(t.category);
//			}
//		}
//		
//		// sort categories
//		Collections.sort(categories);
//        
//		return categories.toArray(new String[0]);
//	}
//	
//	public Transaction createObject(String desc, String date, int amount, String category) {
//		Transaction transaction = new Transaction(desc, date, amount, category);
//		return transaction;
//	}
//}
//
//
