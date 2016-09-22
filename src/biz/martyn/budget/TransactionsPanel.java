package biz.martyn.budget;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

/**
 * Panel to contain transaction toolbar, table and dialog
 * 
 * @author Martyn Bissett
 */
public class TransactionsPanel extends JPanel {
	
	/**
	 * @var long
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @var TransactionsToolbar
	 */
	TransactionsToolbar toolbar;
	
	/**
	 * @var TransactionsTable
	 */
	TransactionsTable table;
	
	/**
	 * @var TransactionDialog
	 */
	TransactionDialog dialog;
	
	/**
	 * @var ArrayList<Transaction>
	 */
	ArrayList<Transaction> transactions;

	public TransactionsPanel() {

	    transactions = Budget.getAdapter().loadTransactions();
		
		toolbar = new TransactionsToolbar();
	    this.add(toolbar);
	    
//	    Container contentPane = frame.getContentPane();
//	    contentPane.add(toolbar, BorderLayout.NORTH);
	    
		// transactions table
	    table = new TransactionsTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Toolbar with new fund, new transaction buttons etc
	 */
	public class TransactionsToolbar extends JToolBar {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public TransactionsToolbar() {
			
//			JToolBar toolbar = new JToolBar();
		    this.setRollover(true);
		    
		    // fund button 
		    JButton newFundButton = new JButton("New fund");
		    this.add(newFundButton);
		    this.addSeparator();

		    // transaction button 
		    JButton newTransactionButton = new JButton("New transaction");
		    newTransactionButton.addActionListener(new TransactionDialog());
		    this.add(newTransactionButton);
		    this.addSeparator();
		    
		    // fund drop down
		    String[] funds = new String[]{"Nationwide","Hokuriku"};
		    JComboBox<String> fundsComboBox = new JComboBox<>(funds);
		    this.add(fundsComboBox);
		}
	}
	
	/**
	 * Table of transactions 
	 */
	public class TransactionsTable extends JTable {
		
		private static final long serialVersionUID = 1L;
		private Object [] columnNames = { "Description", "Date", "Amount" };
		
		DefaultTableModel tableModel;

		public TransactionsTable() {
			
			tableModel = new DefaultTableModel(columnNames, 0);
			setModel(tableModel);
			render();

//			this.getModel().addTableModelListener(new TableModelListener() {
//	
//				@Override		
//			    public void tableChanged(TableModelEvent e) {
//			       System.out.println(e);
//			    }
//			});
		}
		
		public void render() {
			tableModel.setRowCount(0);
			for (Transaction t : transactions) {
				tableModel.addRow( t.toStringArray() );
			}
		}
	}
	
	/**
	 * Dialog to create new table 
	 */
	public class TransactionDialog extends JDialog implements ActionListener {
		
	    private static final long serialVersionUID = 1L;

		private JPanel panel = new JPanel(new GridLayout(0, 1));
		
	    private JTextField desc = new JTextField();
	    private JTextField date = new JTextField();
	    private JTextField amount = new JTextField("0");
	    private JComboBox<String> category = new JComboBox<>();
	    private JButton saveButton = null;
	    private JButton cancelButton = null;

	    public TransactionDialog() {
	        
	        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        
	        // description 
	        panel.add(new JLabel("Description:"));
	        panel.add(desc);
	        
	        // date 
	        panel.add(new JLabel("Date:"));
	        panel.add(date);
	        
	        // amount
	        panel.add(new JLabel("Amount:"));
	        panel.add(amount);
	        
	        // category
	        panel.add(new JLabel("Category: (optional)"));
	        String [] categoryData = {"Internet", "Clothes", "Rent", "Salary", "Groceries"};
	        for( String cat : categoryData ) {
	        	category.addItem(cat);
	        }
		    panel.add(category);
		    
		    // save button 
		    saveButton = new JButton("Add");
		    saveButton.addActionListener(this);
		    panel.add(saveButton); 
		      
		    // cancel button 
		    cancelButton = new JButton("Cancel");
		    cancelButton.addActionListener(this);
		    panel.add(cancelButton);
	        
	        getContentPane().add(panel);
	        pack();
//	        setLocationRelativeTo(panel);
	    }

	    public void actionPerformed(ActionEvent e) {
	    	
	    	// show the new transaction dialog 
	        setVisible(true);
	        
	        if(saveButton == e.getSource()) {
	        	
	        	// save transaction
	        	Transaction transaction = new Transaction(
	    			desc.getText(), 
	    			date.getText(), 
	    			Integer.parseInt(amount.getText()), 
	    			category.getSelectedItem().toString()
	        	);
	        	
	            if (transaction.save()) {
	            	transactions = Budget.getAdapter().loadTransactions();
	            	table.render();
	            	setVisible(false);
	            } else {
	            	String message = "Please fix the following:\n";
	            	for( Object err : transaction.getErrors() ) {
	                	message += "- " + err + "\n";
	                }
	            	JOptionPane.showMessageDialog(panel, message, "Error saving", JOptionPane.WARNING_MESSAGE);
	            }
			}
			else if(cancelButton == e.getSource()) {
			    
				// close 
				setVisible(false);
			}
	    }
	}
}
