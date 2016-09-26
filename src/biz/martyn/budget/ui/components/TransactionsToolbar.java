package biz.martyn.budget.ui.components;

import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JToolBar;

import biz.martyn.budget.models.Funds;
import biz.martyn.budget.models.Transactions;

public class TransactionsToolbar extends JToolBar {// implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton newFundButton;
	JButton newTransactionButton;
	FundsComboBox fundsComboBox;
	
	public TransactionsToolbar(Transactions transactions, Funds funds, ResourceBundle bundle) {
		
		// fund button and dialog
	    newFundButton = new JButton(bundle.getString("btn_new_fund"));
		NewFundDialog fundsDialog = new NewFundDialog(funds);
		fundsDialog.setLocationRelativeTo(this);
	    newFundButton.addActionListener(fundsDialog);
	    this.add(newFundButton);
	    this.addSeparator();

	    // transaction button and dialog
	    newTransactionButton = new JButton(bundle.getString("btn_new_transaction"));
		NewTransactionDialog transactionsDialog = new NewTransactionDialog(transactions, bundle);
	    newTransactionButton.addActionListener(transactionsDialog);
	    this.add(newTransactionButton);
	    this.addSeparator();
		
		// funds drop down
	    fundsComboBox = new FundsComboBox(funds, transactions);
	    this.add(fundsComboBox);
	}
}
