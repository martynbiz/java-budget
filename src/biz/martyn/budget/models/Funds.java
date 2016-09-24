package biz.martyn.budget.models;

import java.util.ArrayList;

import biz.martyn.budget.storage.StorageAdapter;

public class Funds extends AbstractModel {

    private ArrayList<Fund> funds;
    private StorageAdapter storageAdapter;
    
    public Funds(StorageAdapter storageAdapter) {
    	this.storageAdapter = storageAdapter;
    	funds = storageAdapter.loadFunds();
    }

    public ArrayList<Fund> get() {    	
    	return funds;
    }

    public boolean insert(Fund fund) {
    	
    	funds.add(fund);
    	boolean result = storageAdapter.writeFunds(funds);
    	
    	if (result) {
            notifyObservers();
    	}
    	
    	return result;
    }
	
	public Fund createObject(String name) {
		Fund fund = new Fund(name);
		return fund;
	}
}


