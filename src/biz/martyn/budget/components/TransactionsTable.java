package biz.martyn.budget.components;

import java.util.ResourceBundle;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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

	public TransactionsTable(final Transactions transactions, final ResourceBundle bundle) {
		
		this.transactions = transactions;
		
		// changes to transactions data will automatically update table 
		transactions.addObserver(this);
		
		// set the table model 
		Object [] columnNames = { 
			bundle.getString("th_id"), 
			bundle.getString("th_description"), 
			bundle.getString("th_date"), 
			bundle.getString("th_category"), 
			bundle.getString("th_amount") 
		};
		setModel( new DefaultTableModel(columnNames, 0) );
		
		this.setAutoCreateRowSorter(true);

		// listener for when table data is changed 
		getModel().addTableModelListener(new TableModelListener() {

			@Override		
		    public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					
					String id = (String) getModel().getValueAt(e.getFirstRow(), 0);
					Transaction transaction = transactions.getById(id);
					String newValue = (String) getModel().getValueAt(e.getFirstRow(), e.getColumn());

					// map column to property, might need a better way to do this
					switch (e.getColumn()) {
					case 1: // desc
						transaction.desc = newValue;
						break;
					case 2: // date 
						transaction.date = newValue;
						break;
					case 3: // amount 
						transaction.category = newValue;
						break;
					case 4: // amount 
						transaction.amount = Integer.parseInt(newValue);
						break;
					}
					
					// it's difficult to do this internally so have to call these methods here 
					transactions.writeToFile();
					transactions.notifyObservers();
			    }
		    }
		});
		
		update();
	}

	@Override
	public void update() {
		
		DefaultTableModel tableModel = (DefaultTableModel) this.getModel();
		
		// rebuild the table rows 
		tableModel.setRowCount(0);
		for (String [] row : transactions.toStringArray()) {
			tableModel.addRow( row );
		}
	}
}
