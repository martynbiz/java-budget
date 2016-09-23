package biz.martyn.budget;

public interface Observable {
	public void addObserver(Observer observer);
    public void notifyObservers();
}
