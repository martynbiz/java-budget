// TODO maven
// TODO Sorting/filter table
// TODO menu bar with shortcuts 
// TODO configuration
// TODO testing
// TODO docblocks, javadoc
// TODO date selector
// TODO graphs
// TODO Nicer GUI
// TODO category autocomplete box 
// TODO multiple languages

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
		
		// new transaction dialog 
		NewTransactionDialog dialog = new NewTransactionDialog(transactions);
		dialog.setLocationRelativeTo(contentPane);
		
		// toolbar 
		TransactionsToolbar toolbar = new TransactionsToolbar(dialog);
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
