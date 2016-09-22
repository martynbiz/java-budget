// TODO File... etc menu "New transaction" with shortcuts
// TODO testing
// TODO docblocks, javadoc
// TODO how to git a project (e.g. ignore files?)
// TODO multiple languages

// TODO android app 

package biz.martyn.budget;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO Q: Is it better to define each individually?
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JComboBox;

import java.util.Date;

public class Budget {
	public static void main(String args[]) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// TODO filter by date 
		
		// Create the toolbar
		
		JToolBar toolbar = new JToolBar();
	    toolbar.setRollover(true);
	    
	    // TODO add popup dialogue to this
	    // TODO button icon
	    JButton button = new JButton("New fund");
	    toolbar.add(button);
	    toolbar.addSeparator();

	    // TODO add popup dialogue to this
	    // TODO button icon
	    JButton newTransactionButton = new JButton("New transaction");
	    newTransactionButton.addActionListener(new NewTransaction());
	    toolbar.add(newTransactionButton);
	    toolbar.addSeparator();
	    
	    // TODO store this in file/db
	    toolbar.add(new JComboBox(new String[]{"Nationwide","Hokuriku"}));
	    
	    Container contentPane = frame.getContentPane();
	    contentPane.add(toolbar, BorderLayout.NORTH);
	    
		// Create transactions table
	    
//	    try {
//			FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
//			ObjectInputStream in = new ObjectInputStream(fileIn);
//			e = (Employee) in.readObject();
//			in.close();
//			fileIn.close();
//		}catch(IOException i) {
//			i.printStackTrace();
//			return;
//		}catch(ClassNotFoundException c) {
//			System.out.println("Employee class not found");
//			c.printStackTrace();
//			return;
//		}

	    // TODO store this in file/db
	    // TODO store as HashMap?		
		Object rowData[][] = { 
			{ "Internet", new Date(2016, 9, 20), -28 },
			{ "JapanTravel", new Date(2016, 9, 20), 1500 } 
		};
		Object columnNames[] = { "Description", "Date", "Amount" };
		JTable table = new JTable(rowData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		// TODO Q: why does this need to go at the end?
		frame.setSize(600, 300);
		frame.setVisible(true);
	}
}
