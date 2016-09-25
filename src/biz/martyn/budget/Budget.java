// TODO sort filter 
// TODO tidy up 
// TODO hide table columns (e.g. id)
// TODO maven
// TODO java table filter
// TODO graphs
// TODO testing
// TODO docblocks, javadoc
// TODO language switcher 

// TODO configuration
// TODO Nicer GUI
// TODO write to sqlite db with SQLiteAdapter class

// TODO android app 
// TODO .deb file 

package biz.martyn.budget;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import biz.martyn.budget.components.TransactionsFilterToolbar;
import biz.martyn.budget.components.TransactionsTable;
import biz.martyn.budget.components.TransactionsToolbar;
import biz.martyn.budget.models.Fund;
import biz.martyn.budget.models.Funds;
import biz.martyn.budget.models.Transactions;
import biz.martyn.budget.storage.FileAdapter;
import biz.martyn.budget.storage.StorageAdapter;

public class Budget {
	
	public static void main(String args[]) {

		// collections 
		
		Funds funds = new Funds(getAdapter());
		Transactions transactions = new Transactions(getAdapter());
		
		Fund defaultFund = funds.getDefaultFund();
		if (defaultFund != null) {
			transactions.setFund(defaultFund);
		}
		
		// components 
		
		ResourceBundle bundle = ResourceBundle.getBundle("biz.martyn.budget.i18n.Text", Locale.ENGLISH);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		// toolbar 
		TransactionsToolbar toolbar = new TransactionsToolbar(transactions, funds, bundle);
		contentPane.add(toolbar, BorderLayout.NORTH);
		
		// filter toolbar
		TransactionsFilterToolbar filterPanel = new TransactionsFilterToolbar(transactions, bundle);
		contentPane.add(filterPanel, BorderLayout.SOUTH);
		
		// transactions table
		TransactionsTable table = new TransactionsTable(transactions, bundle);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		frame.setSize(600, 300);
		frame.setVisible(true);
	}
	
	public static StorageAdapter getAdapter() {
		FileAdapter adapter = new FileAdapter();
		return adapter;
	}
}
