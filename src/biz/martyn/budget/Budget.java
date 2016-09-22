// TODO file read/write - http://stackoverflow.com/questions/39642854/objectoutputstream-not-writing-to-file
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

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

public class Budget {
	public static void main(String args[]) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// toolbar
		
		JToolBar toolbar = new JToolBar();
	    toolbar.setRollover(true);
	    
	    // fund button 
	    JButton newFundButton = new JButton("New fund");
	    toolbar.add(newFundButton);
	    toolbar.addSeparator();

	    // transaction button 
	    JButton newTransactionButton = new JButton("New transaction");
	    newTransactionButton.addActionListener(new TransactionDialog(frame));
	    toolbar.add(newTransactionButton);
	    toolbar.addSeparator();
	    
	    // fund drop down
	    String[] funds = new String[]{"Nationwide","Hokuriku"};
	    JComboBox<String> fundsComboBox = new JComboBox<>(funds);
	    toolbar.add(fundsComboBox);
	    
	    Container contentPane = frame.getContentPane();
	    contentPane.add(toolbar, BorderLayout.NORTH);
	    
	    
		// transactions table
		
//	    // fill with some test data 
//		ArrayList<Transaction> transactions = new ArrayList<>();
//		transactions.add(new Transaction("Internet", "2016-09-20", -28));
//		transactions.add(new Transaction("Groceries", "2016-09-20", -26));
		
	    ArrayList<Transaction> transactions = loadTransactions();
		
//		Object columnNames[] = { "Description", "Date", "Amount" };
		DefaultTableModel tableModel = new DefaultTableModel(
			new Object [] { "Description", "Date", "Amount" }, 
			0
		);
		
		for (Transaction t : transactions) {
			String [] data = {
				t.desc,
				t.date,
				Integer.toString(t.amount) 
			};
			
			tableModel.addRow(data);
		}
		
		JTable table = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		frame.setSize(600, 300);
		frame.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Transaction> loadTransactions() {
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
        ObjectInputStream in = null;
        
		try {
	    	
	    	File f = new File("transactions.ser");
	    	
	    	// create a new file 
	    	if(!f.exists()) { 
	    		writeTransactions(transactions);
	    	}
	    	
//	    	FileOutputStream fos = new FileOutputStream(f);
//	    	ObjectOutputStream out = new ObjectOutputStream(fos);
//	        out.writeObject(transactions);
//	        out.flush();
//	        out.close();

	        FileInputStream fis = new FileInputStream(f);
	        in = new ObjectInputStream(fis);
	        transactions = (ArrayList<Transaction>) in.readObject();
	        System.out.println(transactions);
	        
	    } 
	    catch(IOException e){
	    	System.out.println("IOException");
	    } 
	    catch(ClassNotFoundException e) {
	    	System.out.println("ClassNotFoundException");
	    }
		finally {
			if (in != null) {
				try {
					in.close();
				} catch(IOException e){
			    	System.out.println("IOException");
			    } 
			}
		}
		
		return transactions;
	}
	
	public static void writeTransactions(ArrayList<Transaction> transactions) {
		
		ObjectOutputStream out = null;
		try {
	    	
	    	File f = new File("transactions.ser");
	    	
	    	FileOutputStream fos = new FileOutputStream(f);
	    	out = new ObjectOutputStream(fos);
	        out.writeObject(transactions);
//	        out.flush();
//	        out.close();
	        
	    } 
	    catch(IOException e){
	    	System.out.println("IOException");
	    } 
		finally {
			if (out != null) {
				try {
					out.close();
				} catch(IOException e){
			    	System.out.println("IOException");
			    } 
			}
		}
	}
}
