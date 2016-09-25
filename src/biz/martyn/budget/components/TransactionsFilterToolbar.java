package biz.martyn.budget.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import biz.martyn.budget.models.Transactions;

public class TransactionsFilterToolbar extends JToolBar implements ActionListener {
	
	public TransactionsFilterToolbar(Transactions transactions, ResourceBundle bundle) {
		
		this.transactions = transactions;
		
		// build the panel up 
		
		// date gte
		dateGte = new JTextField();
        this.add(new JLabel(bundle.getString("date_gte_label")));
        this.add(dateGte);
        
        // amount lte
		dateLte = new JTextField();
		this.add(new JLabel(bundle.getString("date_lte_label")));
		this.add(dateLte);
		
		// amount gte
		amountGte = new JTextField();
        this.add(new JLabel(bundle.getString("amount_gte_label")));
        this.add(amountGte);
		
		// amount lte 
        amountLte = new JTextField();
        this.add(new JLabel(bundle.getString("amount_lte_label")));
        this.add(amountLte);
		
		// category  
		category = new CategoryComboBox(transactions, true); 
        this.add(new JLabel(bundle.getString("category_label")));    
	    this.add(category);
	    
	    JButton applyBtn = new JButton(bundle.getString("btn_apply"));
	    applyBtn.addActionListener(this);  
	    this.add(applyBtn);
	}
	
	private static final long serialVersionUID = 1L;
	
	private Transactions transactions;

	private JTextField dateGte;
	private JTextField dateLte;
	private JTextField amountGte;
	private JTextField amountLte;
	private CategoryComboBox category;
    
    /**
     * Used by iterator hasNext and next
     */
	private HashMap<String, Object> filter = new HashMap<>();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// build filter from toolbar fields
		setFilterKey("date_gte", dateGte.getText());
		setFilterKey("date_lte", dateLte.getText());
		setFilterKey("amount_gte", amountGte.getText());
		setFilterKey("amount_lte", amountLte.getText());
		setFilterKey("category", category.getSelectedItem().toString());
		
		// set transactions filter
		transactions.setFilter(filter);
    }
	
	/**
	 * Will set the filter key of the transactions filter
	 * @param key
	 * @param value
	 */
	private void setFilterKey(String key, String value) {
		setFilterKey(key, value, false);
	}
	
	/**
	 * Will set the filter key of the transactions filter
	 * @param key
	 * @param value
	 * @param allowEmpty Allow empty values to be set
	 */
	private void setFilterKey(String key, String value, boolean allowEmpty) {
		if (value.isEmpty() && !allowEmpty) {
			if (filter.containsKey(key)) filter.remove(key);
		} else {
			filter.put(key, value);
		}
	}
}
