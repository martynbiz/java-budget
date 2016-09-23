package biz.martyn.budget;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class NewTransactionDialog extends JDialog implements ActionListener {
	
    private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel(new GridLayout(0, 1));
	
    private JTextField desc = new JTextField();
    private JTextField date = new JTextField();
    private JTextField amount = new JTextField("0");
    private JComboBox<String> category = new JComboBox<>();
    private JButton saveButton = null;
    private JButton cancelButton = null;
	
	/**
	 * @var Transactions
	 */
	Transactions transactions;

    public NewTransactionDialog(final Transactions transactions) {
		
		this.transactions = transactions;
        
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
        
//        String [] categoryData = {"Internet", "Clothes", "Rent", "Salary", "Groceries"};
//        for( String cat : categoryData ) {
//        	category.addItem(cat);
//        }
//        category.setEditable(true);
//	    panel.add(category);
        
        /**	
         * @see http://www.algosome.com/articles/java-jcombobox-autocomplete.html
         */
        try{
			SwingUtilities.invokeAndWait(new Runnable() {
			    @Override
			    public void run() {
//			    	ArrayList<String> categories = transactions.getCategoriesList(); //new ArrayList<> ();
			    	ArrayList<String> categories = new ArrayList<> ();
			        categories.add("bike");
			        categories.add("car");
			        categories.add("cap");
			        categories.add("cape");
			        categories.add("canadian");
			        categories.add("caprecious");
			        categories.add("catepult");
			        StringSearchable searchable = new StringSearchable(categories);
			        AutocompleteJComboBox categoryCombo = new AutocompleteJComboBox(searchable);
			        panel.add(categoryCombo);
			    }
			});
		} 
		catch (InvocationTargetException e) {
			System.out.println("InvocationTargetException");
		}
		catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
	    
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
//        setLocationRelativeTo(panel);
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
        	
            if (transaction.isValid()) {
            	transactions.insert(transaction); // TODO handle false
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
