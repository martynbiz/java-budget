package biz.martyn.budget.ui.components;

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

import biz.martyn.budget.models.Fund;
import biz.martyn.budget.models.Funds;

public class NewFundDialog extends JDialog implements ActionListener {
	
    private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel(new GridLayout(0, 1));
	
    private JTextField name = new JTextField();
    private JButton saveButton = null;
    private JButton cancelButton = null;
	
	/**
	 * @var Funds
	 */
	Funds funds;

    public NewFundDialog(Funds funds) {
		
		this.funds = funds;
        
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // description 
        panel.add(new JLabel("Name:"));
        panel.add(name);
        
        panel.add(new JSeparator());
	    
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
    	
    	// show the new fund dialog 
        setVisible(true);
        
        if(saveButton == e.getSource()) {
        	
        	// save fund
        	Fund fund = funds.createObject(
    			name.getText()
        	);
        	
            if (fund.isValid(funds)) {
            	funds.insert(fund); // TODO handle false
            	setVisible(false);
            } else {
            	String message = "Please fix the following:\n";
            	for( Object err : fund.getErrors() ) {
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
