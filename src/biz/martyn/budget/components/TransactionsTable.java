package biz.martyn.budget.components;

import java.util.ResourceBundle;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import biz.martyn.budget.Observer;
import biz.martyn.budget.models.Transaction;
import biz.martyn.budget.models.Transactions;


/**
 * Table of transactions 
 */
public class TransactionsTable extends JTable implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @var Transactions
	 */
	Transactions transactions;

	public TransactionsTable(Transactions transactions, ResourceBundle bundle) {
		
		this.transactions = transactions;
		
		// changes to transactions data will automatically update table 
		transactions.addObserver(this);
		
		// set the table model 
		Object [] columnNames = { 
			bundle.getString("th_description"), 
			bundle.getString("th_date"), 
			bundle.getString("th_amount") 
		};
		setModel( new DefaultTableModel(columnNames, 0) );
		
		this.setAutoCreateRowSorter(true);
		
		update();

//		this.getModel().addTableModelListener(new TableModelListener() {
//
//			@Override		
//		    public void tableChanged(TableModelEvent e) {
//		       System.out.println(e);
//		    }
//		});
	}

	@Override
	public void update() {
		
		DefaultTableModel tableModel = (DefaultTableModel) this.getModel();
		
		// rebuild the table rows 
		tableModel.setRowCount(0);
		for (Transaction t : transactions) {
			tableModel.addRow( t.toStringArray() );
		}
	}
}
