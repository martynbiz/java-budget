package biz.martyn.budget;

import java.util.ArrayList;

public interface StorageAdapter {
	public ArrayList<Transaction> loadTransactions();
	public void writeTransactions(ArrayList<Transaction> transactions);
}
