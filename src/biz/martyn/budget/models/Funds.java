package biz.martyn.budget.models;

import java.util.ArrayList;
import java.util.List;

import biz.martyn.budget.Budget;
import biz.martyn.budget.Observable;
import biz.martyn.budget.Observer;


public class Funds implements Observable {

    private List<Observer> observers = new ArrayList<Observer>();
    private ArrayList<Fund> funds;
    
    public Funds() {
    	funds = Budget.getAdapter().loadFunds();
    }

    public ArrayList<Fund> get() {    	
    	return funds;
    }

    public boolean insert(Fund fund) {
    	
    	funds.add(fund);
    	boolean result = Budget.getAdapter().writeFunds(funds);
    	
    	if (result) {
            notifyObservers();
    	}
    	
    	return result;
    }

//    public ArrayList<Fund> get() {
//        return funds;
//    }

//    public void set(ArrayList<Fund> funds) {
//    	Budget.getAdapter().writeFunds(funds);
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


