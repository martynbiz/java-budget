package biz.martyn.budget.models;

import java.util.ArrayList;
import java.util.List;

import biz.martyn.budget.Observable;
import biz.martyn.budget.Observer;

abstract class AbstractModel implements Observable {

	private List<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer observer){
        observers.add(observer);		
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
