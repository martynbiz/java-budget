package biz.martyn.budget.components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import biz.martyn.budget.DateLabelFormatter;
import biz.martyn.budget.models.Transaction;
import biz.martyn.budget.models.Transactions;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class NewTransactionDialog extends JDialog implements ActionListener {
	
    private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel(new GridLayout(0, 1));
	
    private JTextField desc;
    private JDatePickerImpl datePicker;
    private JTextField amount;
    private CategoryComboBox category;
    private JButton saveButton;
    private JButton cancelButton;
	
	/**
	 * @var Transactions
	 */
	Transactions transactions;
	
	ResourceBundle bundle;

    public NewTransactionDialog(Transactions transactions, ResourceBundle bundle) {
		
		this.transactions = transactions;
		this.bundle = bundle;
        
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // description 
        desc = new JTextField();
        panel.add(new JLabel(bundle.getString("description_label")));
        panel.add(desc);
        
        // date 
        panel.add(new JLabel(bundle.getString("date_label")));
        Properties properties = new Properties();
        properties.put("text.today", bundle.getString("today"));
        properties.put("text.month", bundle.getString("month"));
        properties.put("text.year", bundle.getString("year"));
        UtilDateModel model = new UtilDateModel();
        model.setSelected(true); // today
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		panel.add(datePicker);
        
        // amount
		amount = new JTextField();
        panel.add(new JLabel(bundle.getString("amount_label")));
        panel.add(amount);
        
        // category  
		category = new CategoryComboBox(transactions);  
        panel.add(new JLabel(bundle.getString("category_label")));    
	    panel.add(category);
	    
	    panel.add(new JSeparator());
	    
	    // save button 
	    saveButton = new JButton(bundle.getString("btn_save"));
	    saveButton.addActionListener(this);
	    panel.add(saveButton); 
	      
	    // cancel button 
	    cancelButton = new JButton(bundle.getString("btn_cancel"));
	    cancelButton.addActionListener(this);
	    panel.add(cancelButton);
        
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(panel);
    }

    public void actionPerformed(ActionEvent e) {
    	
    	if (transactions.getFund() == null) {
    		JOptionPane.showMessageDialog(null, bundle.getString("alert_select_fund"));
    	}      
    	else if(saveButton == e.getSource()) {
        	
    	    Date selectedDate = (Date) datePicker.getModel().getValue();
    	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	    String formattedDate = df.format(selectedDate);
    		
        	// save transaction
        	Transaction transaction = transactions.createObject(
    			desc.getText(), 
    			formattedDate,
//    			datePicker.getModel().getValue().toString(), 
    			Integer.parseInt(amount.getText()),
    			(category.getSelectedItem() != null) ? category.getSelectedItem().toString() : ""
        	);
        	
            if (transaction.isValid()) {
            	transactions.insert(transaction); // TODO handle false
            	setVisible(false);
            } 
            else {
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
    	else {
    		
    		// show the new transaction dialog 
            setVisible(true);
    	}   
    }
}
