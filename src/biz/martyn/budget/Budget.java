// TODO multiple languages
// TODO menu bar with shortcuts 
// TODO configuration
// TODO testing
// TODO docblocks, javadoc
// TODO date selector
// TODO filter by date 
// TODO graphs
// TODO category autocomplete box 
// TODO android app 

package biz.martyn.budget;

import java.awt.Container;

import javax.swing.JFrame;

public class Budget {
	public static void main(String args[]) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = frame.getContentPane();
		
		TransactionsPanel transactionPanel = new TransactionsPanel();
		contentPane.add(transactionPanel);
		
		frame.setSize(600, 300);
		frame.setVisible(true);
	}
	
	public static FileAdapter getAdapter() {
		FileAdapter adapter = new FileAdapter();
		return adapter;
	}
}
