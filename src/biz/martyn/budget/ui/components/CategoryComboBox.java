package biz.martyn.budget.ui.components;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;

import biz.martyn.budget.Observer;
import biz.martyn.budget.models.Transactions;

public class CategoryComboBox extends JComboBox<String> implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Transactions transactions;
	
	boolean insertEmptyFirst;

	/**
	 * 
	 * @param transactions
	 */
	public CategoryComboBox(Transactions transactions) {
		this(transactions, false);
	}
	
	/**
	 * 
	 * @param transactions
	 * @param insertEmptyFirst Insert an empty category item for the first element
	 */
	public CategoryComboBox(Transactions transactions, boolean insertEmptyFirst) {
		this.transactions = transactions;
		this.insertEmptyFirst = insertEmptyFirst;
		transactions.addObserver(this);
        setEditable(true);
        update();
	}

	@Override
	public void update() {
		
		String[] categories = transactions.getCategoriesArray();
		
		// remove all so we can re-insert them 
		removeAllItems();
		
		// check if we have to set an empty first element for this combo box 
		if (insertEmptyFirst) {
			ArrayList<String> categoriesList = new ArrayList<>(Arrays.asList(categories));
			categoriesList.add(0, "");
			categories = categoriesList.toArray(new String[0]);
		}
		
		for( String c : categories ) {
        	addItem(c);
        }
	}
}
