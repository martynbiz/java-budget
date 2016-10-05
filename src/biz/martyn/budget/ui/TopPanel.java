package biz.martyn.budget.ui;

import java.util.ResourceBundle;

import javax.swing.JPanel;

import biz.martyn.budget.models.Funds;
import biz.martyn.budget.models.Transactions;
import biz.martyn.budget.ui.components.FundsComboBox;
import biz.martyn.budget.ui.components.NewTransactionDialog;

public class TopPanel extends JPanel {
	
	public TopPanel(Funds funds, Transactions transactions, ResourceBundle bundle) {
		FundsComboBox fundsComboBox = new FundsComboBox(funds, transactions);
	    add(fundsComboBox);
	}
}
