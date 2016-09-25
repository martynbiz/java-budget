package biz.martyn.budget.models;

import java.util.Iterator;
import java.util.List;

import biz.martyn.budget.storage.StorageAdapter;

public class Funds extends AbstractModel implements Iterable<Fund> {

    List<Fund> funds;
    StorageAdapter storageAdapter;

    public Funds(StorageAdapter storageAdapter) {
    	this.storageAdapter = storageAdapter;
    	funds = storageAdapter.loadFunds();
    }

	/**
	 * Get a transaction by it's id string 
	 * @param id
	 * @return
	 */
	public Fund getById(String id) {
		Fund match = null;
		for (Fund fund : funds) {
			if (fund.id.equals(id)) {
				match = fund;
			}
		}
		return match;
	}
	
	/**
	 * Get a transaction by it's id string 
	 * @param id
	 * @return
	 */
	public Fund getByName(String fundName) {
		Fund match = null;
		for (Fund fund : funds) {
			if (fund.name.equals(fundName)) {
				match = fund;
			}
		}
		return match;
	}

	/**
	 * Get the default fund to use for when the app starts
	 * @return
	 */
	public Fund getDefaultFund() {
		
		// first fund is default 
		return (!funds.isEmpty()) ? funds.get(0) : null;
	}

	/**
	 * Insert a new fund to the collection 
	 * @param fund
	 */
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
    
    
    
//    private List<Observer> observers = new ArrayList<Observer>();
//
//    public void addObserver(Observer observer){
//        observers.add(observer);		
//    }
//
//    public void notifyObservers(){
//        for (Observer observer : observers) {
//            observer.update();
//        }
//    }

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
