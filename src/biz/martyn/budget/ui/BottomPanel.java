package biz.martyn.budget.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import biz.martyn.budget.models.Transactions;
import biz.martyn.budget.ui.components.CategoryComboBox;

public class BottomPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private JTextField dateGte;
	private JTextField dateLte;
	private JTextField amountGte;
	private JTextField amountLte;
	private CategoryComboBox category;
    
    /**
     * Used by iterator hasNext and next
     */
	private HashMap<String, Object> filter = new HashMap<>();
	
	public BottomPanel(final Transactions transactions, ResourceBundle bundle) {
		
		filter = transactions.getFilter();
		
		// build the panel up 
		
		// date gte
		dateGte = new JTextField((filter.containsKey("date_gte")) ? 
			(String) filter.get("date_gte") : "");
        add(new JLabel(bundle.getString("date_gte_label")));
        add(dateGte);
        
        // amount lte
        dateLte = new JTextField((filter.containsKey("date_lte")) ? 
			(String) filter.get("date_lte") : "");
		add(new JLabel(bundle.getString("date_lte_label")));
		add(dateLte);
		
		// amount gte
		amountGte = new JTextField((filter.containsKey("amount_gte")) ? 
			(String) filter.get("amount_gte") : "");
        add(new JLabel(bundle.getString("amount_gte_label")));
        add(amountGte);
		
		// amount lte 
        amountLte = new JTextField((filter.containsKey("amount_lte")) ? 
			(String) filter.get("amount_lte") : "");
        add(new JLabel(bundle.getString("amount_lte_label")));
        add(amountLte);
		
		// category  
		category = new CategoryComboBox(transactions, true); 
        add(new JLabel(bundle.getString("category_label")));    
	    add(category);
	    
	    JButton applyBtn = new JButton(bundle.getString("btn_apply"));
	    applyBtn.addActionListener(new ActionListener() {
	    	
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
	    });  
	    add(applyBtn);
	}
}
