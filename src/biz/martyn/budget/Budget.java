// TODO http://www.algosome.com/articles/java-jcombobox-autocomplete.html
// TODO store categories 
// TODO date selector
// TODO maven
// TODO java table filter
// TODO graphs
// TODO testing
// TODO docblocks, javadoc

// TODO configuration
// TODO Nicer GUI
// TODO multiple languages
// TODO write to sqlite db with SQLiteAdapter class

// TODO android app 
// TODO .deb file 

package biz.martyn.budget;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Budget {
	public static void main(String args[]) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = frame.getContentPane();

		Transactions transactions = new Transactions();
		Funds funds = new Funds();
		
		// toolbar 
		TransactionsToolbar toolbar = new TransactionsToolbar(transactions, funds);
	    contentPane.add(toolbar, BorderLayout.NORTH);
	    
		// transactions table
		TransactionsTable table = new TransactionsTable(transactions);
		table.setAutoCreateRowSorter(true);
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
