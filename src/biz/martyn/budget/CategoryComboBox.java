package biz.martyn.budget;

import javax.swing.JComboBox;

public class CategoryComboBox extends JComboBox<String> implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Transactions transactions;

	public CategoryComboBox(Transactions transactions) {
		this.transactions = transactions;
		transactions.addObserver(this);
        setEditable(true);
        update();
	}

	@Override
	public void update() {
		removeAllItems();
		String[] categories = transactions.getCategoriesArray();
		for( String c : categories ) {
        	addItem(c);
        }
	}
}
