package biz.martyn.budget;

import java.util.ArrayList;

import biz.martyn.budget.models.Fund;
import biz.martyn.budget.models.Transaction;

public interface StorageAdapter {
	public ArrayList<Transaction> loadTransactions();
	public boolean writeTransactions(ArrayList<Transaction> transactions);
	public ArrayList<Fund> loadFunds();
	public boolean writeFunds(ArrayList<Fund> funds);
}
