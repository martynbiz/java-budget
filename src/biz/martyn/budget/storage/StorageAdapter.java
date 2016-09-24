package biz.martyn.budget.storage;

import java.util.List;
import biz.martyn.budget.models.Fund;
import biz.martyn.budget.models.Transaction;

public interface StorageAdapter {
	public List<Transaction> loadTransactions(Fund fund);
	public boolean writeTransactions(List<Transaction> transactions, Fund fund);
	public List<Fund> loadFunds();
	public boolean writeFunds(List<Fund> funds);
}
