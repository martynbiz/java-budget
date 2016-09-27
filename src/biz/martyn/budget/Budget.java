// TODO graphs
// TODO set ints as doubles? for uk currency etc
// TODO total
// TODO language switcher 
// TODO java table filter
// TODO testing
// TODO docblocks, javadoc

// TODO maven
// TODO configuration
// TODO Nicer GUI
// TODO write to sqlite db with SQLiteAdapter class

// TODO android app 
// TODO .deb file 

package biz.martyn.budget;

import javax.swing.UIManager;

import biz.martyn.budget.models.Funds;
import biz.martyn.budget.models.Transactions;
import biz.martyn.budget.storage.FileAdapter;
import biz.martyn.budget.storage.StorageAdapter;
import biz.martyn.budget.ui.BudgetFrame;

public class Budget {
	
	public static void main(String args[]) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Funds funds = new Funds(Budget.getAdapter());
		Transactions transactions = new Transactions(Budget.getAdapter());
		
		BudgetFrame budgetFrame = new BudgetFrame(funds, transactions);
		budgetFrame.setVisible(true);
	}
	
	public static StorageAdapter getAdapter() {
		FileAdapter adapter = new FileAdapter();
		return adapter;
	}
}
