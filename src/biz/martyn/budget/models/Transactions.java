package biz.martyn.budget.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import biz.martyn.budget.storage.StorageAdapter;

/**
 * Custom collection for Transactions 
 * @author martyn
 *
 */
public class Transactions extends AbstractModel implements Iterable<Transaction> {

    public Transactions(StorageAdapter storageAdapter) {
    	this.storageAdapter = storageAdapter;
    	transactions = new ArrayList<>();
    	
    	// default filter
    	filter = new HashMap<>();
//    	filter.put("date_gt", "");
//    	filter.put("date_lt", "");
//    	filter.put("category", "Groceries");
//    	filter.put("amount_gte", 20);
//    	filter.put("amount_lte", 100);
    }
    
    /**	
     * The adapter to read/write funds and transactions (e.g. to file)
     */
    StorageAdapter storageAdapter;

    /**
     * Our Iterator list
     */
	List<Transaction> transactions;
    
    /**
     * The fund from which to display transactions 
     */
    Fund fund;
    
    /**
     * Used by iterator hasNext and next
     */
	private HashMap<String, Object> filter;

	/**
	 * Get a transaction by it's id string 
	 * @param id
	 * @return
	 */
	public Transaction getById(String id) {
		Transaction match = null;
		for (Transaction transaction : transactions) {
			if (transaction.id.equals(id)) {
				match = transaction;
			}
		}
		return match;
	}

    /**
     * Insert a new Transaction to the collection, will notify observers
     * @param transaction
     */
    public void insert(Transaction transaction) {
        transactions.add(transaction);
        writeToFile();
    	notifyObservers();
    }

    /**
     * This just provides an external means to write to file if updated 
     * transactions or a single transaction (e.g. table)
     */
    public void writeToFile() {
    	storageAdapter.writeTransactions(transactions, fund);
    }

	public Fund getFund() {
		return fund;
	}

    /**
     * Use on load, and when we want to switch funds, will notify observers
     * @param fund
     */
	public void setFund(Fund fund) {
    	this.fund = fund;
    	transactions = storageAdapter.loadTransactions(fund);
    	notifyObservers();
    }
	
	/**
	 * Filter for the table when displaying, used by getRow, will notify observers
	 * @param filter
	 */
	public void setFilter(HashMap<String, Object> filter) {
		this.filter = filter;
    	notifyObservers();
	}
	
	/**
	 * Will return the Iterator object 
	 */
    public Iterator<Transaction> iterator() {
        return (Iterator<Transaction> ) new TransactionsIterator();
    }
    
    /**
     * Get an array of categories from the transactions list
     * @return
     */
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
    
    /**
     * 
     * @return
     */
    public String[][] toStringArray() {
		
    	List<String[]> arr = new ArrayList<> ();
    	
    	for (Transaction t : this) {
    		arr.add( t.toStringArray() );
		}
    	
    	return arr.toArray(new String[0][0]);
	}
	
    /**
     * Factory method to create object (rather than `new Class(...`)
     * @param desc
     * @param date
     * @param amount
     * @param category
     * @return 
     */
	public Transaction createObject(String desc, String date, int amount, String category) {
		Transaction transaction = new Transaction(desc, date, amount, category);
		return transaction;
	}

    class TransactionsIterator implements Iterator<Transaction> {
        
    	/**
    	 * The next potential index to retrive subject to filter checking (validate)
    	 */
    	int currentIndex = 0;      
        
        @Override
        public boolean hasNext() {
        	
        	// this will set currentIndex to the next VALID index 
        	// or, if none found, will run until currentIndex is out of bounds
        	while(currentIndex < transactions.size() && !validate(transactions.get(currentIndex))) {
        		currentIndex++;
        	}
        	
        	// return true if currentIndex is not out of bounds 
        	if (currentIndex >= transactions.size()) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Transaction next() {
        	Transaction transaction = transactions.get(currentIndex);
        	currentIndex++;
        	return transaction; //transactions.get(currentIndex++);
        }
        
        @Override
        public void remove() {
            transactions.remove(--currentIndex);
        }
        
        private boolean validate(Transaction transaction) {
        	
        	// match category
        	if (filter.containsKey("category")) {
        		if (!transaction.category.equals(filter.get("category"))) {
        			return false;
        		}
        	}
        	
        	// check amount is greater than or equal to filter
        	if (filter.containsKey("amount_gte")) {
        		if (transaction.amount < Integer.parseInt((String) filter.get("amount_gte"))) {
        			return false;
        		}
        	}
        	
        	// check amount is less than or equal to filter
        	if (filter.containsKey("amount_lte")) {
        		if (transaction.amount > Integer.parseInt((String) filter.get("amount_lte"))) {
        			return false;
        		}
        	}
        	
        	return true;
        }
    }
}
