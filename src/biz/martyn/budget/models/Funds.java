package biz.martyn.budget.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import biz.martyn.budget.Observer;
import biz.martyn.budget.storage.StorageAdapter;

public class Funds implements Iterable<Fund> {

    List<Fund> funds;
    StorageAdapter storageAdapter;

    public Funds(StorageAdapter storageAdapter) {
    	this.storageAdapter = storageAdapter;
    	funds = storageAdapter.loadFunds();
    }

    public void insert(Fund fund) {
        funds.add(fund);
    	storageAdapter.writeFunds(funds);
    	notifyObservers();
    }

    public Iterator<Fund> iterator() {
        return (Iterator<Fund> ) new FundsIterator();
    }
	
	public Fund createObject(String name) {
		Fund fund = new Fund(name);
		return fund;
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

    class FundsIterator implements Iterator<Fund> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (currentIndex >= funds.size()) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Fund next() {
            return funds.get(currentIndex++);
        }

        @Override
        public void remove() {
            funds.remove(--currentIndex);
        }
    }
}

//public class Funds extends AbstractModel {
//
//    private ArrayList<Fund> funds;
//    private StorageAdapter storageAdapter;
//    
//    public Funds(StorageAdapter storageAdapter) {
//    	this.storageAdapter = storageAdapter;
//    	funds = storageAdapter.loadFunds();
//    }
//
//    public ArrayList<Fund> get() {    	
//    	return funds;
//    }
//
//    public boolean insert(Fund fund) {
//    	
//    	funds.add(fund);
//    	boolean result = storageAdapter.writeFunds(funds);
//    	
//    	if (result) {
//            notifyObservers();
//    	}
//    	
//    	return result;
//    }
//	
//	public Fund createObject(String name) {
//		Fund fund = new Fund(name);
//		return fund;
//	}
//}


