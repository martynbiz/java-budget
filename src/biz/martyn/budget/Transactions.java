package biz.martyn.budget;

import java.util.ArrayList;
import java.util.List;


public class Transactions implements Observable {

    private List<Observer> observers = new ArrayList<Observer>();
    private ArrayList<Transaction> transactions;
    
    public Transactions() {
    	transactions = Budget.getAdapter().loadTransactions();
    }

//    public void load() {
//    	transactions = Budget.getAdapter().loadTransactions();
//        notifyAllObservers();
//    }

    public ArrayList<Transaction> get() {
//    	if (transactions == null) {
//    		transactions = Budget.getAdapter().loadTransactions();
//    	}
    	
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

//    public ArrayList<Transaction> get() {
//        return transactions;
//    }

//    public void set(ArrayList<Transaction> transactions) {
//    	Budget.getAdapter().writeTransactions(transactions);
//        notifyAllObservers();
//    }

    public void addObserver(Observer observer){
        observers.add(observer);		
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    } 	
}


