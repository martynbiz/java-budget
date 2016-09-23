package biz.martyn.budget;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

public class TransactionsToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TransactionsToolbar(NewTransactionDialog dialog) {
		
//		JToolBar toolbar = new JToolBar();
	    this.setRollover(true);
	    
	    // fund button 
	    JButton newFundButton = new JButton("New fund");
	    this.add(newFundButton);
	    this.addSeparator();

	    // transaction button 
	    JButton newTransactionButton = new JButton("New transaction");
	    newTransactionButton.addActionListener(dialog);
	    this.add(newTransactionButton);
	    this.addSeparator();
	    
	    // fund drop down
	    String[] funds = new String[]{"Nationwide","Hokuriku"};
	    JComboBox<String> fundsComboBox = new JComboBox<>(funds);
	    this.add(fundsComboBox);
	}
}
