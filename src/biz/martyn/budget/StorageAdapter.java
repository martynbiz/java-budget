package biz.martyn.budget;

import java.util.ArrayList;


public interface StorageAdapter {
	public ArrayList<Transaction> loadTransactions();
	public boolean writeTransactions(ArrayList<Transaction> transactions);
}
