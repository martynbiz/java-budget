package biz.martyn.budget.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import biz.martyn.budget.models.Funds;
import biz.martyn.budget.models.Transactions;

public class TransactionsToolbar extends JToolBar {// implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton newFundButton;
	private JButton newTransactionButton;
	private JButton deleteTransactionBtn;
	private FundsComboBox fundsComboBox;
	private NewFundDialog fundsDialog;
	
	private NewTransactionDialog newTransactionDialog;
	
	public TransactionsToolbar(final Transactions transactions, final Funds funds, final ResourceBundle bundle) {
	    
		fundsDialog = new NewFundDialog(funds);
		fundsDialog.setLocationRelativeTo(this);
		
	    newFundButton = new JButton(bundle.getString("btn_new_fund"));
	    newFundButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fundsDialog.setVisible(true);
			}
	    });

	    fundsComboBox = new FundsComboBox(funds, transactions);
	    
	    add(newFundButton);
	    addSeparator();
//	    add(newTransactionButton);
//	    addSeparator();
//		add(deleteTransactionBtn);
//		addSeparator();
	    add(fundsComboBox);
	}
}
