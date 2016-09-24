package biz.martyn.budget.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import biz.martyn.budget.Observer;
import biz.martyn.budget.models.Fund;
import biz.martyn.budget.models.Funds;
import biz.martyn.budget.models.Transactions;

public class FundsComboBox extends JComboBox<String> implements Observer, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Funds funds;
	Transactions transactions;

	public FundsComboBox(Funds funds, Transactions transactions) {
		this.funds = funds;
		funds.addObserver(this);
        update();
        
        // allow transactions to be updated when we change funds combo
        this.transactions = transactions;
        this.addActionListener (this);
	}

	@Override
	public void update() {
		this.removeAllItems();
		for (Fund f : funds) {
	    	this.addItem( f.name );
		}
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (getSelectedItem() != null) {
			String fundName = getSelectedItem().toString();
			Fund fund = funds.getFundByName(fundName);
			transactions.setFund(fund);
		}
    }
}
