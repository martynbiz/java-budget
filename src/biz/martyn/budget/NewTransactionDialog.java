package biz.martyn.budget;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import biz.martyn.budget.models.Transaction;
import biz.martyn.budget.models.Transactions;

//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;

public class NewTransactionDialog extends JDialog implements ActionListener {
	
    private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel(new GridLayout(0, 1));
	
    private JTextField desc = new JTextField();
    private JTextField date = new JTextField();
    private JTextField amount = new JTextField("0");
    private CategoryComboBox category;
    private JButton saveButton = new JButton("Add");
    private JButton cancelButton = new JButton("Cancel");
	
	/**
	 * @var Transactions
	 */
	Transactions transactions;

    public NewTransactionDialog(Transactions transactions) {
		
		this.transactions = transactions;
		category = new CategoryComboBox(transactions);
        
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // description 
        panel.add(new JLabel("Description:"));
        panel.add(desc);
        
        // date 
        panel.add(new JLabel("Date:"));
        panel.add(date);
//        UtilDateModel model = new UtilDateModel();
//        JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
//        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
//		panel.add(datePicker);
        
        // amount
        panel.add(new JLabel("Amount:"));
        panel.add(amount);
        
        // category
        panel.add(new JLabel("Category: (optional)"));        
	    panel.add(category);
	    
	    panel.add(new JSeparator());
	    
	    // save button 
//	    saveButton = new JButton("Add");
	    saveButton.addActionListener(this);
	    panel.add(saveButton); 
	      
	    // cancel button 
//	    cancelButton = new JButton("Cancel");
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
    			(category.getSelectedItem() != null) ? category.getSelectedItem().toString() : ""
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
