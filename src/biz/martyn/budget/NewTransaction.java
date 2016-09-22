package biz.martyn.budget;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewTransaction implements ActionListener {

	JPanel panel = new JPanel(new GridLayout(0, 1));
	
	JTextField desc = new JTextField();
	JTextField date = new JTextField();
	JTextField amount = new JTextField("0");
	JComboBox category = new JComboBox();
	
	public NewTransaction() {
		
		panel.add(new JLabel("Description:"));
        panel.add(desc);
        
        // TODO date selector
        panel.add(new JLabel("Date:"));
        panel.add(date);
        
        panel.add(new JLabel("Amount:"));
        panel.add(amount);
        
        // TODO Can this be HashTable? e.g. {"internet": {name: "Internet"}}
        // TODO how to sort?
     	// TODO get from file 
        // TODO autocomplete box?
        panel.add(new JLabel("Category: (optional)"));
        String [] categoryData = {"Internet", "Clothes", "Rent", "Salary", "Groceries"};
        for( String cat : categoryData ) {
        	category.addItem(cat);
        }
        panel.add(category);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// reset options
		desc.setText("");
		date.setText("");
		amount.setText("0");
		category.setSelectedIndex(0);
		
		// TODO add another button - "Save and add another"
		
		int result = JOptionPane.showConfirmDialog(null, panel, "New transaction",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            
        	// save transaction
        	Transaction transaction = new Transaction(); //(desc.getText(), date.getText(), Integer.parseInt(amount.getText()), category.getSelectedItem().toString());
        	
        	transaction.desc = desc.getText();
        	transaction.date = date.getText();
        	transaction.amount = Integer.parseInt(amount.getText());
        	transaction.category = category.getSelectedItem().toString();
        	
            if (transaction.save()) {
            	System.out.println("Saved transaction");
            } else {
            	String message = "Please fix the following:\n";
            	for( String err : transaction.getErrors() ) {
                	message += "- " + err;
                }
            	JOptionPane.showMessageDialog(panel, message, "Error saving", JOptionPane.WARNING_MESSAGE);
            }
            
        } else {
            System.out.println("Cancelled");
        }
	}

}
