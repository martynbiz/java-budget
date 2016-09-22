package biz.martyn.budget;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class TransactionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	JPanel panel = new JPanel(new GridLayout(0, 1));
	
	JTextField desc = new JTextField();
	JTextField date = new JTextField();
	JTextField amount = new JTextField("0");
	JComboBox category = new JComboBox();

	public TransactionDialog(JFrame parent, String title) {
		super(parent, title);
		
//		// set the position of the window
//		Point p = new Point(400, 400);
//		setLocation(p.x, p.y);
		
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
		
//		// Create a message
//		JPanel messagePane = new JPanel();
//		messagePane.add(new JLabel(message));
//		// get content pane, which is usually the
//		// Container of all the dialog's components.
//		getContentPane().add(messagePane);
//
//		// Create a button
//		JPanel buttonPane = new JPanel();
//		JButton button = new JButton("Close me");
//		buttonPane.add(button);
//		// set action listener on the button
//		button.addActionListener(new MyActionListener());
//		getContentPane().add(buttonPane, BorderLayout.PAGE_END);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		pack();
//		setVisible(true);
	}

//	// override the createRootPane inherited by the JDialog, to create the rootPane.
//	// create functionality to close the window when "Escape" button is pressed
//	public JRootPane createRootPane() {
//		JRootPane rootPane = new JRootPane();
//		KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
//		Action action = new AbstractAction() {
//			
//			private static final long serialVersionUID = 1L;
//
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("escaping..");
//				setVisible(false);
//				dispose();
//			}
//		};
//		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
//		inputMap.put(stroke, "ESCAPE");
//		rootPane.getActionMap().put("ESCAPE", action);
//		return rootPane;
//	}

	// an action listener to be used when an action is performed
	// (e.g. button is pressed)
	class NewTransactionListener implements ActionListener {

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
}
