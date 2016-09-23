package biz.martyn.budget;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

public class TransactionsToolbar extends JToolBar implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Funds funds;
	
	JButton newFundButton;
	JButton newTransactionButton;
	JComboBox<String> fundsComboBox;
	
	public TransactionsToolbar(Transactions transactions, Funds funds) {
		
		this.funds = funds;
	    
	    // fund button and dialog
	    newFundButton = new JButton("New fund");
		NewFundDialog fundsDialog = new NewFundDialog(funds);
		fundsDialog.setLocationRelativeTo(this);
	    newFundButton.addActionListener(fundsDialog);
	    this.add(newFundButton);
	    this.addSeparator();

	    // transaction button and dialog
	    newTransactionButton = new JButton("New transaction");
		NewTransactionDialog transactionsDialog = new NewTransactionDialog(transactions);
		transactionsDialog.setLocationRelativeTo(this);
	    newTransactionButton.addActionListener(transactionsDialog);
	    this.add(newTransactionButton);
	    this.addSeparator();
		
		// funds drop down
		funds.addObserver(this);
	    fundsComboBox = new JComboBox<>();
	    this.add(fundsComboBox);
	    
	    update();
	}

	@Override
	public void update() {
		fundsComboBox.removeAllItems();
		for (Fund f : funds.get()) {
	    	fundsComboBox.addItem( f.name );
		}
	}
}
