package biz.martyn.budget;

import javax.swing.JDialog; 
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;

public class TransactionDialog extends JDialog implements ActionListener {
	
    private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel(new GridLayout(0, 1));
	
    private JTextField desc = new JTextField();
    private JTextField date = new JTextField();
    private JTextField amount = new JTextField("0");
    private JComboBox<String> category = new JComboBox<>();
    private JButton saveButton = null;
    private JButton cancelButton = null;

    public TransactionDialog(JFrame frame) {
        super(frame, true);
        
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
        setLocationRelativeTo(frame);
    }

    public void actionPerformed(ActionEvent e) {
        
//    	// reset fields 
//    	desc.setText("");
//    	date.setText("");
//    	amount.setText("0");
//    	category.setSelectedIndex(0);
    	
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
        	
//        	transaction.desc = desc.getText();
//        	transaction.date = date.getText();
//        	transaction.amount = Integer.parseInt(amount.getText());
//        	transaction.category = category.getSelectedItem().toString();
        	
            if (transaction.save()) {
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
