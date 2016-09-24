package biz.martyn.budget.components;

import javax.swing.JComboBox;

import biz.martyn.budget.Observer;
import biz.martyn.budget.models.Fund;
import biz.martyn.budget.models.Funds;

public class FundsComboBox extends JComboBox<String> implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Funds funds;

	public FundsComboBox(Funds funds) {
		this.funds = funds;
		funds.addObserver(this);
        update();
	}

	@Override
	public void update() {
		this.removeAllItems();
		for (Fund f : funds) {
	    	this.addItem( f.name );
		}
	}
}
